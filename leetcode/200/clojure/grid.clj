(ns number-of-islands.grid)

;; Alternative to solution.clj: keep the grid as a matrix and flood it.
;; Nothing is mutated: sinking a cell returns a new grid with that cell set
;; to water, and the grid is threaded through a reduce over every cell.
;;
;; Types used in the signatures below (Clojure doesn't check them; they're notes):
;;
;;   Rows = a sequence of rows; a row is a string "11000" or a vector ["1" "1" "0" ...]
;;   Grid = [[\1 \0 ...] ...]          a vector of row vectors, so get-in / assoc-in work
;;   Cell = [row col]                  a pair of ints, e.g. [2 3]

;; Grid, Cell -> bool
(defn land? [grid cell]
  (boolean (#{\1 "1"} (get-in grid cell))))       ; off the grid -> nil -> false

;; Cell -> [Cell]   (always exactly 4; off-grid ones are simply never land)
(defn neighbors [[r c]]
  [[(inc r) c] [(dec r) c] [r (inc c)] [r (dec c)]])

;; Grid, Cell -> Grid
(defn sink
  "Returns a new grid with the island at `start` turned to water."
  [grid start]
  (loop [grid  grid
         stack [start]]                           ; [Cell]: cells still to visit
    (if (empty? stack)
      grid                                        ; nothing left to visit: island gone
      (let [cell  (peek stack)                    ; Cell: the last one pushed
            stack (pop stack)]                    ; [Cell]: the rest
        (if (land? grid cell)
          (recur (assoc-in grid cell \0)          ; new grid with this cell as water
                 (into stack (neighbors cell)))   ; visit its neighbours later
          (recur grid stack))))))                 ; water or already sunk: skip it

;; Rows -> int
(defn num-islands
  "Scan every cell; whenever one is still land, sink its island and count it."
  [rows]
  (let [grid  (mapv vec rows)                     ; Grid
        cells (for [r (range (count grid))        ; [Cell]: every cell, row by row
                    c (range (count (grid r)))]
                [r c])]
    (->> cells
         (reduce (fn [[grid islands] cell]        ; carry [Grid int] along
                   (if (land? grid cell)
                     [(sink grid cell) (inc islands)]
                     [grid islands]))
                 [grid 0])                        ; [Grid int]
         second)))                                ; int

;; --- examples ---------------------------------------------------------------

(def example-1 [["1" "1" "1" "1" "0"]
                ["1" "1" "0" "1" "0"]
                ["1" "1" "0" "0" "0"]
                ["0" "0" "0" "0" "0"]])

(def example-2 ["11000"
                "11000"
                "00100"
                "00011"])

(println (num-islands example-1))  ; 1
(println (num-islands example-2))  ; 3
(println (num-islands ["0"]))      ; 0
(println (num-islands (repeat 300 (apply str (take 300 (cycle "10"))))))  ; 150
(println (num-islands (repeat 300 (apply str (repeat 300 \1)))))          ; 1 (one 90k-cell island)
