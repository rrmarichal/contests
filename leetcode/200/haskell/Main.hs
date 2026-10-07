-- Same idea as ../clojure/solution.clj: the grid becomes a set of land
-- coordinates, and each step removes one whole island from the set.
--
-- The type comments from the Clojure version are real, compiler-checked
-- signatures here, and everything is immutable by default.

module Main where

import Data.Set (Set)
import qualified Data.Set as Set

type Grid  = [String]          -- ["11000", "00100", ...]
type Cell  = (Int, Int)        -- (row, col)
type Cells = Set Cell

-- | Every land cell in the grid.
landCells :: Grid -> Cells
landCells grid = Set.fromList
  [ (r, c) | (r, row) <- zip [0 ..] grid
           , (c, ch)  <- zip [0 ..] row
           , ch == '1' ]

-- | Always exactly 4; some may be off the grid, which is fine because
-- those are never in a 'Cells' set.
neighbors :: Cell -> [Cell]
neighbors (r, c) = [(r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1)]

-- | Every cell next to the frontier that is still land.
landNeighbors :: Cells -> Cells -> Cells
landNeighbors frontier land = Set.fromList
  [ n | cell <- Set.toList frontier
      , n    <- neighbors cell
      , n `Set.member` land ]

-- | Removes the island containing @start@ from @land@.
-- Spreads outwards one ring at a time, deleting each ring as it is reached.
sink :: Cells -> Cell -> Cells
sink land start = go (Set.singleton start) (Set.delete start land)
  where
    -- go plays the role of Clojure's loop/recur: a recursive call in tail
    -- position, which GHC turns into a plain loop.
    go :: Cells -> Cells -> Cells
    go frontier remaining
      | Set.null frontier = remaining                  -- island gone
      | otherwise         = go next (remaining `Set.difference` next)
      where next = landNeighbors frontier remaining

-- | Sink an arbitrary island until no land is left; the number of steps is the answer.
-- Read right to left: build the set, keep sinking, stop at empty, count.
numIslands :: Grid -> Int
numIslands =
    length
  . takeWhile (not . Set.null)
  . iterate (\land -> sink land (Set.findMin land))
  . landCells

-- --- examples ------------------------------------------------------------------

stripes :: Grid
stripes = replicate 300 (take 300 (cycle "10"))

main :: IO ()
main = do
  print (numIslands ["11110", "11010", "11000", "00000"])  -- 1
  print (numIslands ["11000", "11000", "00100", "00011"])  -- 3
  print (numIslands ["0"])                                 -- 0
  print (numIslands stripes)                               -- 150
