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

// https://www.hackerrank.com/challenges/short-palindrome/problem?isFullScreen=true

class Result
{

    /*
     * Complete the 'roadsAndLibraries' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER c_lib
     *  3. INTEGER c_road
     *  4. 2D_INTEGER_ARRAY cities
     */

    public static long roadsAndLibraries(int n, int clib, int croad, List<List<int>> cities)
    {
        if (clib <= croad) {
            return (long)n*(long)clib;
        }

        var connected = new bool[n];
        var graph = buildGraph(cities);
        var result = 0L;
        for (var i = 0; i < n; i++) {
            if (!connected[i]) {
                var size = dfs(i, graph, connected);
                result += (long)clib + (size-1)*(long)croad;
            }
        }

        return result;
    }

    private static long dfs(int node, Dictionary<int, List<int>> graph, bool[] connected) {
        connected[node] = true;
        var size = 1L;

        if (graph.ContainsKey(node)) {
            foreach (var child in graph[node]) {
                if (!connected[child]) {
                    size += dfs(child, graph, connected);
                }
            }
        }

        return size;
    }

    private static Dictionary<int, List<int>> buildGraph(List<List<int>> cities) {
        var graph = new Dictionary<int, List<int>>();
        foreach (var pair in cities) {
            if (!graph.ContainsKey(pair[0] - 1)) {
                graph.Add(pair[0] - 1, new List<int>());
            }
            if (!graph.ContainsKey(pair[1] - 1)) {
                graph.Add(pair[1] - 1, new List<int>());
            }

            graph[pair[0] - 1].Add(pair[1] - 1);
            graph[pair[1] - 1].Add(pair[0] - 1);
        }

        return graph;
    }

}

class Solution
{
    public static void Main(string[] args)
    {
        TextWriter textWriter = new StreamWriter(Console.OpenStandardOutput());

        int q = Convert.ToInt32(Console.ReadLine().Trim());

        for (int qItr = 0; qItr < q; qItr++)
        {
            string[] firstMultipleInput = Console.ReadLine().TrimEnd().Split(' ');

            int n = Convert.ToInt32(firstMultipleInput[0]);

            int m = Convert.ToInt32(firstMultipleInput[1]);

            int c_lib = Convert.ToInt32(firstMultipleInput[2]);

            int c_road = Convert.ToInt32(firstMultipleInput[3]);

            List<List<int>> cities = new List<List<int>>();

            for (int i = 0; i < m; i++)
            {
                cities.Add(Console.ReadLine().TrimEnd().Split(' ').ToList().Select(citiesTemp => Convert.ToInt32(citiesTemp)).ToList());
            }

            long result = Result.roadsAndLibraries(n, c_lib, c_road, cities);

            textWriter.WriteLine(result);
        }

        textWriter.Flush();
        textWriter.Close();
    }
}
