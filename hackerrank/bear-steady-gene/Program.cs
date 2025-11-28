using System.CodeDom.Compiler;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Diagnostics.CodeAnalysis;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Runtime.Serialization;
using System.Text.RegularExpressions;
using System.Text;
using System;

class GeneCount {
    public GeneCount(int c, int g, int a, int t) {
        this.C = c;
        this.G = g;
        this.A = a;
        this.T = t;
    }

    public int C { get; }
    public int G { get; }
    public int A { get; }
    public int T { get; }

    public GeneCount withGene(char value) {
        if (value == 'C') {
            return new GeneCount(C+1, G, A, T);
        }

        if (value == 'G') {
            return new GeneCount(C, G+1, A, T);
        }

        if (value == 'A') {
            return new GeneCount(C, G, A+1, T);
        }

        // Default to T
        return new GeneCount(C, G, A, T+1);
    }

    public GeneCount add(GeneCount value) {
        return new GeneCount(C + value.C, G + value.G, A + value.A, T + value.T);
    }

    public GeneCount subtract(GeneCount value) {
        return new GeneCount(C - value.C, G - value.G, A - value.A, T - value.T);
    }

    public override string ToString() {
        return $"C:{C} G:{G} A:{A} T:{T}";
    }
}

class Result {
    /*
     * Complete the 'steadyGene' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING gene as parameter.
     */

    public static int steadyGene(string gene) {
        var l = 0;
        var r = gene.Length - 1;
        var memo = precomp(gene);

        while (l < r) {
            var target = (l + r)/2;
            if (steady(gene, memo, target)) {
                r = target;
            } else {
                l = target + 1;
            }
        }
        return l;
    }

    private static GeneCount[] precomp(string gene) {
        var result = new GeneCount[gene.Length];
        result[0] = new GeneCount(0, 0, 0, 0).withGene(gene[0]);
        for (var i = 1; i < gene.Length; i++) {
            result[i] = result[i - 1].withGene(gene[i]);
        }

        return result;
    }

    private static bool steady(string gene, GeneCount[] memo, int count) {
        var threshold = gene.Length / 4;
        for (var i = 0; i < gene.Length - count + 1; i++) {
            // test replacing sequence gene[i..i+target] balances gene count at or below threshold.
            var geneCount = countMemo(gene, memo, i, count);
            if (geneCount.C > threshold
                || geneCount.G > threshold
                || geneCount.A > threshold
                || geneCount.T > threshold) {
                continue;
            }

            return true;
        }

        return false;
    }

    private static GeneCount countMemo(string gene, GeneCount[] memo, int index, int count) {
        // Count occurrences of value in [0..index-1] + [index+count..gene.Length - 1]
        var result = index > 0 ? memo[index - 1] : new GeneCount(0, 0, 0, 0);
        if (index + count < gene.Length) {
            result = result.add(memo[gene.Length - 1]);
            if (index + count > 0) {
                result = result.subtract(memo[index + count - 1]);
            }
        }

        return result;
    }
}

class Program
{
    public static void Main(string[] args)
    {
        TextWriter textWriter = new StreamWriter(Console.OpenStandardOutput());

        int n = Convert.ToInt32(Console.ReadLine().Trim());

        string gene = Console.ReadLine();

        int result = Result.steadyGene(gene);

        textWriter.WriteLine(result);

        textWriter.Flush();
        textWriter.Close();
    }
}
