declare var require: any
declare var process: any

const readline = require('readline')
let reader = readline.createInterface(process.stdin, process.stdout)

const solve = (lines: string[]) => {
  const T = parseInt(lines[0])
  let line = 0
  for (let test = 0; test < T; test++) {
    const mnp = lines[line + 1].split(' ')
    const M = parseInt(mnp[0])
    const N = parseInt(mnp[1])
    const P = parseInt(mnp[2])
    const maxInDay = new Array<number>(N)
    let johns: Array<number> | null = null

    for (let m = 0; m < M; m++) {
      const stepsLine = lines[line + m + 2].split(' ')
      if (m === P - 1) {
        johns = stepsLine.map(x => parseInt(x))
      } else {
        stepsLine.map((stepsStr, day) => {
          const steps = parseInt(stepsStr)
          if (maxInDay[day] === undefined || steps > maxInDay[day]) {
            maxInDay[day] = steps
          }
        })
      }
    }

    if (!johns) {
      throw new Error('assertion failed')
    }

    console.log(`Case #${test + 1}: ${johns.reduce((acc, steps, day) => acc + Math.max(0, maxInDay[day] - steps), 0)}`)
    line += M + 1
  }
}

// Read the input data and run all test cases.
const lines: string[] = []
reader.on('line', (line: string) => lines.push(line)).on('close', () => solve(lines))
