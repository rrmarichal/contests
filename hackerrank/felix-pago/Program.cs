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

class MaxHeapComparer : IComparer<int>
{
    public int Compare(int x, int y)
    {
        // For a max heap, we want larger values to have higher priority,
        // so we reverse the default comparison order.
        return y.CompareTo(x);
    }
}

class PairComparer : IComparer<Pair>
{
    public int Compare(Pair x, Pair y)
    {
        return y.Value.CompareTo(x.Value);
    }
}

class Pair {
    public int Value;
    public int Index;
}

class Result
{

    /*
     * Complete the 'frequencyOfMaxNewUsers' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY numbers
     *  2. INTEGER_ARRAY q
     */

    public static List<int> frequencyOfMaxNewUsers(List<int> numbers, List<int> q) {
        // return Optimal(numbers, q);
        // return BruteForce(numbers, q);
        return BruteForce2(numbers, q);
    }

    private static List<int> Optimal2(List<int> numbers, List<int> q) {
        var pairs = new List<Pair>();
        for (var i = 0; i < numbers.Count; i++) {
            pairs.Add(new Pair { Value = numbers[i], Index = i + 1 });
        }

        pairs.Sort(new PairComparer());
        // [(5,1), (5,3), (4,2), (3,4), (2,5)]


        return numbers;
    }

    private static List<int> BruteForce2(List<int> numbers, List<int> q) {
        // var result = new List<int>();
        var max = numbers.Last();
        var count = 1;
        var queryMap = new Dictionary<int, int>();
        queryMap.Add(numbers.Count, count);

        for (var index = numbers.Count - 1; index > 0; index--) {
            if (numbers[index] > max) {
                max = numbers[index];
                count = 1;
            }
            else if (numbers[index] == max) {
                count++;
            }

            queryMap.Add(index, count);
         }

         var result = new List<int>();
         for (var i = 0; i < q.Count; i++) {
            result.Add(queryMap[q[i]]);
         }

         return result;
    }

    private static List<int> BruteForce(List<int> numbers, List<int> q) {
        var result = new List<int>();

        for (var i = 0; i < q.Count; i++) {
            result.Add(FindMaxCount(numbers, q[i]-1));
        }

        return result;
    }

    private static int FindMaxCount(List<int> numbers, int index) {
        var max = numbers[index];
        var count = 1;

        for (var i = index+1; i < numbers.Count; i++) {
            if (numbers[i] == max) {
                count++;
            }
            else if (numbers[i] > max) {
                max = numbers[i];
                count = 1;
            }
        }

        return count;
    }

    private static List<int> Optimal(List<int> numbers, List<int> q) {
        var map = new Dictionary<int, int>();
        for (var i = 0; i < numbers.Count; i++) {
            if (!map.ContainsKey(numbers[i])) {
                map.Add(numbers[i], 0);
            }

            map[numbers[i]]++;
        }

        var maxHeap = new PriorityQueue<int, int>(numbers.Count, new MaxHeapComparer());
        foreach (var value in numbers) {
            maxHeap.Enqueue(value, value);
        }

        // q.Sort();

        for (var i = 0; i < q.Count; i++) {
            var max = maxHeap.Peek();
            // maxHeap.Dequeue();
        }

        return new List<int>();
    }

}

class Solution
{
    public static void Main(string[] args)
    {
        TextWriter textWriter = new StreamWriter(new Console.OpenStandardOutput());

        int numbersCount = Convert.ToInt32(Console.ReadLine().Trim());

        List<int> numbers = new List<int>();

        for (int i = 0; i < numbersCount; i++)
        {
            int numbersItem = Convert.ToInt32(Console.ReadLine().Trim());
            numbers.Add(numbersItem);
        }

        int qCount = Convert.ToInt32(Console.ReadLine().Trim());

        List<int> q = new List<int>();

        for (int i = 0; i < qCount; i++)
        {
            int qItem = Convert.ToInt32(Console.ReadLine().Trim());
            q.Add(qItem);
        }

        List<int> result = Result.frequencyOfMaxNewUsers(numbers, q);

        textWriter.WriteLine(String.Join("\n", result));

        textWriter.Flush();
        textWriter.Close();
    }
}
