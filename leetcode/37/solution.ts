import assert from "node:assert";

type Board = {
  row: number;
  column: number;
};

function _validRow(board: string[][], row: number, k: number) {
  for (let c = 0; c < board[0].length; c++) {
    if (board[row][c] == k.toString()) return false;
  }
  return true;
}

function _validColum(board: string[][], column: number, k: number) {
  for (let r = 0; r < board.length; r++) {
    if (board[r][column] == k.toString()) return false;
  }
  return true;
}

function _validBox(board: string[][], row: number, column: number, k: number) {
  for (let r = 0; r < 3; r++)
    for (let c = 0; c < 3; c++) {
      if (board[3*Math.floor(row / 3) + r][3*Math.floor(column / 3) + c] == k.toString()) return false;
    }
  return true;
}

/**
 * Return valid values at r, c
 */
function _open(board: string[][], row: number, column: number) {
  const result: number[] = [];
  for (let k = 1; k <= 9; k++) {
    if (
      _validRow(board, row, k) &&
      _validColum(board, column, k) &&
      _validBox(board, row, column, k)
    ) {
      result.push(k);
    }
  }
  return result;
}

function _solve(
  board: string[][],
  empty: Board[],
  filled: boolean[],
  count: number,
): boolean {
  if (count == empty.length) {
    return true;
  }

  let next = 0;
  while (filled[next]) next++;
  let r = empty[next].row;
  let c = empty[next].column;
  const options = _open(board, r, c);
  if (options.length == 0) return false

  for (const option of options) {
    filled[next] = true;
    board[r][c] = option.toString();
    if (_solve(board, empty, filled, count + 1)) return true
    board[r][c] = "."
    filled[next] = false;
  }

  return false
}

function solveSudoku(board: string[][]): void {
  const empty: Board[] = [];
  for (let i = 0; i < board.length; i++)
    for (let j = 0; j < board[0].length; j++) {
      if (board[i][j] == ".") empty.push({ row: i, column: j });
    }

  if (empty.length == 0) return;
  const filled = new Array(empty.length).fill(false);

  _solve(board, empty, filled, 0);
}

const b0 = [
  ["5", "3", ".", ".", "7", ".", ".", ".", "."],
  ["6", ".", ".", "1", "9", "5", ".", ".", "."],
  [".", "9", "8", ".", ".", ".", ".", "6", "."],
  ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
  ["4", ".", ".", "8", ".", "3", ".", ".", "1"],
  ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
  [".", "6", ".", ".", ".", ".", "2", "8", "."],
  [".", ".", ".", "4", "1", "9", ".", ".", "5"],
  [".", ".", ".", ".", "8", ".", ".", "7", "9"],
];
solveSudoku(b0);
assert.deepEqual(b0, [
  ["5", "3", "4", "6", "7", "8", "9", "1", "2"],
  ["6", "7", "2", "1", "9", "5", "3", "4", "8"],
  ["1", "9", "8", "3", "4", "2", "5", "6", "7"],
  ["8", "5", "9", "7", "6", "1", "4", "2", "3"],
  ["4", "2", "6", "8", "5", "3", "7", "9", "1"],
  ["7", "1", "3", "9", "2", "4", "8", "5", "6"],
  ["9", "6", "1", "5", "3", "7", "2", "8", "4"],
  ["2", "8", "7", "4", "1", "9", "6", "3", "5"],
  ["3", "4", "5", "2", "8", "6", "1", "7", "9"],
]);

const b1 = [
  ["5", "3", "4", "6", "7", "8", "9", "1", "2"],
  ["6", "7", "2", "1", "9", "5", "3", "4", "8"],
  ["1", "9", "8", "3", "4", "2", "5", "6", "7"],
  ["8", "5", "9", "7", "6", "1", "4", "2", "3"],
  ["4", "2", "6", "8", "5", "3", "7", "9", "1"],
  ["7", "1", "3", "9", "2", "4", "8", "5", "6"],
  ["9", "6", "1", "5", "3", "7", "2", "8", "4"],
  ["2", "8", "7", "4", "1", "9", "6", "3", "5"],
  ["3", "4", "5", "2", "8", "6", "1", "7", "."],
];
solveSudoku(b1);
assert.deepEqual(b1, [
  ["5", "3", "4", "6", "7", "8", "9", "1", "2"],
  ["6", "7", "2", "1", "9", "5", "3", "4", "8"],
  ["1", "9", "8", "3", "4", "2", "5", "6", "7"],
  ["8", "5", "9", "7", "6", "1", "4", "2", "3"],
  ["4", "2", "6", "8", "5", "3", "7", "9", "1"],
  ["7", "1", "3", "9", "2", "4", "8", "5", "6"],
  ["9", "6", "1", "5", "3", "7", "2", "8", "4"],
  ["2", "8", "7", "4", "1", "9", "6", "3", "5"],
  ["3", "4", "5", "2", "8", "6", "1", "7", "9"],
]);

