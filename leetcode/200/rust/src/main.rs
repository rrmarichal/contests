// Same idea as ../clojure/solution.clj: the grid becomes a set of land
// coordinates, and each step removes one whole island from the set.
//
// The type comments from the Clojure version are real types here.

use std::collections::HashSet;

type Cell = (i32, i32); // (row, col); i32 so a neighbour can be -1
type Cells = HashSet<Cell>;

/// Every land cell in the grid.
fn land_cells(grid: &[Vec<char>]) -> Cells {
    grid.iter()
        .enumerate()
        .flat_map(|(r, row)| {
            row.iter()
                .enumerate()
                .filter(|&(_, &ch)| ch == '1')
                .map(move |(c, _)| (r as i32, c as i32))
        })
        .collect()
}

/// Always exactly 4; some may be off the grid, which is fine because
/// those are never in a `Cells` set.
fn neighbors((r, c): Cell) -> [Cell; 4] {
    [(r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1)]
}

/// Every cell next to `frontier` that is still in `land`.
fn land_neighbors(frontier: &Cells, land: &Cells) -> Cells {
    frontier
        .iter()
        .flat_map(|&cell| neighbors(cell)) // Cell, may contain duplicates
        .filter(|cell| land.contains(cell)) // only the ones still land
        .collect() // Cells: duplicates removed
}

/// Removes the island containing `start` from `land`.
/// Spreads outwards one ring at a time, deleting each ring as it is reached.
///
/// Takes `land` by value and hands it back: the caller gives up the old set
/// and receives the smaller one, so nobody else can see it change.
fn sink(mut land: Cells, start: Cell) -> Cells {
    land.remove(&start);
    let mut frontier = Cells::from([start]); // the ring reached in the last step
    while !frontier.is_empty() {
        frontier = land_neighbors(&frontier, &land);
        for cell in &frontier {
            land.remove(cell);
        }
    }
    land
}

/// Sink an arbitrary island until no land is left; the number of steps is the answer.
pub fn num_islands(grid: Vec<Vec<char>>) -> i32 {
    let mut land = land_cells(&grid);
    let mut count = 0;
    while let Some(&start) = land.iter().next() {
        land = sink(land, start);
        count += 1;
    }
    count
}

// --- examples ----------------------------------------------------------------

/// `["11000", "00100"]` -> the `Vec<Vec<char>>` LeetCode passes in.
fn grid(rows: &[&str]) -> Vec<Vec<char>> {
    rows.iter().map(|row| row.chars().collect()).collect()
}

fn stripes() -> Vec<Vec<char>> {
    vec!["10".repeat(150).chars().collect(); 300]
}

fn main() {
    println!("{}", num_islands(grid(&["11110", "11010", "11000", "00000"]))); // 1
    println!("{}", num_islands(grid(&["11000", "11000", "00100", "00011"]))); // 3
    println!("{}", num_islands(grid(&["0"]))); // 0
    println!("{}", num_islands(stripes())); // 150
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example_1() {
        assert_eq!(num_islands(grid(&["11110", "11010", "11000", "00000"])), 1);
    }

    #[test]
    fn example_2() {
        assert_eq!(num_islands(grid(&["11000", "11000", "00100", "00011"])), 3);
    }

    #[test]
    fn all_water() {
        assert_eq!(num_islands(grid(&["0"])), 0);
    }

    #[test]
    fn stripes_300x300() {
        assert_eq!(num_islands(stripes()), 150);
    }
}
