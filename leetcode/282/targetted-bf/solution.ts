import assert from "node:assert"

type Expression = {
  E: string
  T: number
}

function _suffixFactor(num: string, low: number, L: number): Expression | null {
  if (num[low] == "0" && L > 1) return null
  const E = num.substring(low, low + L)
  return { E, T: parseInt(E) }
}

function _suffixTerms(num: string, low: number, L: number, suffixTerms: Expression[][][]): Expression[] {
  if (suffixTerms[low][L].length > 0) return suffixTerms[low][L]

  const result: Expression[] = [_suffixFactor(num, low, L)].filter(x => x != null)
  for (let l = 1; l < L; l++) {
    const lterms = _suffixTerms(num, low, L-l, suffixTerms)
    const lfactor = _suffixFactor(num, low+L-l, l)
    if (lfactor) {
      result.push(...lterms.map(lterm => ({ E: `${lterm.E}*${lfactor.E}`, T: lterm.T * lfactor.T })))
    }
  }

  return suffixTerms[low][L] = result
}

function _factor(num: string, T: number, L: number): Expression[] {
  if (num[0] == "0" && L > 1) return []

  const factorE = num.substring(0, L)
  const factorT = parseInt(factorE)
  if (factorT == T) return [{ E: factorE, T }]
  return []
}

function _term(num: string, T: number, L: number, suffixTerms: Expression[][][]): Expression[] {
  const terms: Expression[] = _factor(num, T, L)
  for (let l = 1; l < L; l++) {
    const lfactor = _suffixFactor(num, L-l, l)
    if (lfactor == null) continue;

    if (lfactor.T == 0 && T == 0) {
      terms.push(..._suffixTerms(num, 0, L-l, suffixTerms).map(t => ({ E: `${t.E}*${lfactor.E}`, T: T })))
    } else if (lfactor.T != 0 && T % lfactor.T == 0) {
      terms.push(..._term(num, T / lfactor.T, L-l, suffixTerms).map(t => ({ E: `${t.E}*${lfactor.E}`, T })))
    }
  }
  return terms
}

function _expression(num: string, T: number, L: number, suffixTerms: Expression[][][]): Expression[] {
  const expressions: Expression[] = _term(num, T, L, suffixTerms)
  for (let l = 1; l < L; l++) {
    const lterms = _suffixTerms(num, L-l, l, suffixTerms)
    for (const lterm of lterms) {
      expressions.push(..._expression(num, T - lterm.T, L-l, suffixTerms).map(e => ({ E: `${e.E}+${lterm.E}`, T })))
      expressions.push(..._expression(num, T + lterm.T, L-l, suffixTerms).map(e => ({ E: `${e.E}-${lterm.E}`, T })))
    }
  }
  return expressions
}

function addOperators(num: string, target: number): string[] {
  const suffixTerms: Expression[][][] = Array.from(
    { length: num.length },
    () => Array.from({length: num.length + 1}, () => []))

  return _expression(num, target, num.length, suffixTerms).map(e => e.E).sort()
}

assert.deepEqual(addOperators("1234", 5), ["12-3-4"])
assert.deepEqual(addOperators("123", 6), ["1*2*3", "1+2+3"])
assert.deepEqual(addOperators("234", 24), ["2*3*4"])
assert.deepEqual(addOperators("232", 8), ["2*3+2", "2+3*2"])
assert.deepEqual(addOperators("345623749", 9191), [])
assert.deepEqual(addOperators("3456237490", 9191), [])
assert.deepEqual(addOperators("232", 25), ["23+2"])
assert.deepEqual(addOperators("105", 5), ["1*0+5", "10-5"])
assert.deepEqual(addOperators("00", 0), ["0*0", "0+0", "0-0"])
assert.deepEqual(addOperators("000", 0), [
  "0*0*0",
  "0*0+0",
  "0*0-0",
  "0+0*0",
  "0+0+0",
  "0+0-0",
  "0-0*0",
  "0-0+0",
  "0-0-0",
])
assert.deepEqual(addOperators("999999999", 81), [
  "9+9+9+9+9+9+9+9+9",
  "999-9*99-9-9-9",
  "999-9-9*99-9-9",
  "999-9-9-9*99-9",
  "999-9-9-9-9*99",
  "999-9-9-9-99*9",
  "999-9-9-99*9-9",
  "999-9-99*9-9-9",
  "999-99*9-9-9-9",
])
assert.deepEqual(addOperators("123456789", 45), [
  "1*2*3*4*5-6-78+9",
  "1*2*3*4+5+6-7+8+9",
  "1*2*3+4+5+6+7+8+9",
  "1*2*3+4+5-6*7+8*9",
  "1*2*3+4-5*6+7*8+9",
  "1*2*3+4-5*6-7+8*9",
  "1*2*3-4*5+6*7+8+9",
  "1*2*3-4*5-6+7*8+9",
  "1*2*3-4*5-6-7+8*9",
  "1*2*3-45+67+8+9",
  "1*2*34+56-7-8*9",
  "1*2*34-5+6-7-8-9",
  "1*2+3*4-56+78+9",
  "1*2+3+4+5*6+7+8-9",
  "1*2+3+4-5+6*7+8-9",
  "1*2+3+4-5-6+7*8-9",
  "1*2+3+45+67-8*9",
  "1*2+3-45+6+7+8*9",
  "1*2+34+5-6-7+8+9",
  "1*2+34+56-7*8+9",
  "1*2+34-5+6+7-8+9",
  "1*2+34-56+7*8+9",
  "1*2+34-56-7+8*9",
  "1*2-3*4+5+67-8-9",
  "1*2-3+4-5-6*7+89",
  "1*2-3-4*5+67+8-9",
  "1*2-3-4+56-7-8+9",
  "1*2-34+5*6+7*8-9",
  "1*23+4*5-6+7-8+9",
  "1*23-4-56-7+89",
  "1+2*3*4*5+6+7-89",
  "1+2*3*4+5*6+7-8-9",
  "1+2*3*4-5+6*7-8-9",
  "1+2*3+4*5*6+7-89",
  "1+2*3+4*5-6+7+8+9",
  "1+2*3-4-5-6*7+89",
  "1+2*34-5*6+7+8-9",
  "1+2+3*4*5+6-7-8-9",
  "1+2+3*4+5+6*7-8-9",
  "1+2+3*45-6-78-9",
  "1+2+3+4+5+6+7+8+9",
  "1+2+3+4+5-6*7+8*9",
  "1+2+3+4-5*6+7*8+9",
  "1+2+3+4-5*6-7+8*9",
  "1+2+3-4*5+6*7+8+9",
  "1+2+3-4*5-6+7*8+9",
  "1+2+3-4*5-6-7+8*9",
  "1+2+3-45+67+8+9",
  "1+2-3*4*5+6+7+89",
  "1+2-3*4+5*6+7+8+9",
  "1+2-3*4-5+6*7+8+9",
  "1+2-3*4-5-6+7*8+9",
  "1+2-3*4-5-6-7+8*9",
  "1+2-3+4*5+6*7-8-9",
  "1+2-3+45+6-7-8+9",
  "1+2-3+45-6+7+8-9",
  "1+2-3-4-5*6+7+8*9",
  "1+2-3-45-6+7+89",
  "1+2-34+5+6+7*8+9",
  "1+2-34+5+6-7+8*9",
  "1+2-34-5-6+78+9",
  "1+23*4+5-6-7*8+9",
  "1+23*4-5-6*7+8-9",
  "1+23*4-56+7-8+9",
  "1+23+4+5+6+7+8-9",
  "1+23+4-5*6+7*8-9",
  "1+23+4-5-67+89",
  "1+23-4*5+6*7+8-9",
  "1+23-4*5-6+7*8-9",
  "1+23-4-5+6+7+8+9",
  "1+23-4-5-6*7+8*9",
  "1+23-45+67+8-9",
  "1-2*3*4+5-6+78-9",
  "1-2*3*4-5-6+7+8*9",
  "1-2*3+4*5+6+7+8+9",
  "1-2*3+4*5-6*7+8*9",
  "1-2*3+4+5+6*7+8-9",
  "1-2*3+4+5-6+7*8-9",
  "1-2*3+4+56+7-8-9",
  "1-2*3+45-67+8*9",
  "1-2*3-4+5*6+7+8+9",
  "1-2*3-4-5+6*7+8+9",
  "1-2*3-4-5-6+7*8+9",
  "1-2*3-4-5-6-7+8*9",
  "1-2*34+5*6-7+89",
  "1-2+3*4*5-6-7+8-9",
  "1-2+3+4-5*6+78-9",
  "1-2+3+45+6-7+8-9",
  "1-2+3-4*5-6+78-9",
  "1-2+3-45+6-7+89",
  "1-2-3*4+5+6+7*8-9",
  "1-2-3*4-5-6+78-9",
  "1-2-3+4-5+67-8-9",
  "1-2-3+45-6-7+8+9",
  "1-2-34+5+6+78-9",
  "1-2-34+56+7+8+9",
  "1-2-34-5+6+7+8*9",
  "1-23*4+5+6*7+89",
  "1-23+4*5-6*7+89",
  "1-23+4-5+67-8+9",
  "1-23+45-67+89",
  "1-23-4+5+67+8-9",
  "1-23-4-5-6-7+89",
  "12*3*4-5*6-78+9",
  "12*3+4+5+6-7-8+9",
  "12*3+4+5-6+7+8-9",
  "12*3-4-5-6+7+8+9",
  "12*3-4-56+78-9",
  "12+3*4+5+6-7+8+9",
  "12+3*45-6-7-89",
  "12+3+4-56-7+89",
  "12+3-4*5+67-8-9",
  "12+3-45+6+78-9",
  "12+34-5-6-7+8+9",
  "12-3*4*5+6+78+9",
  "12-3*4-5+67-8-9",
  "12-3+4*5+6-7+8+9",
  "12-3+4+56-7-8-9",
  "12-3-4+5*6-7+8+9",
  "12-3-4-56+7+89",
  "12-3-45-6+78+9",
])
