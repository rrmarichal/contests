namespace stock_exchange;

struct HeapItem {
    public int Value;
    public int Index;
}

class IndexedMaxHeap {
    private int size;
    private HeapItem[] heap;
    private int[] indices;

    public IndexedMaxHeap(int[] values) {
        Init(values);
    }

    private void Init(int[] values) {
        size = values.Length;
        heap = new HeapItem[size];
        indices = new int[size];

        for (var i = 0; i < size; i++) {
            heap[i] = new HeapItem { Value = values[i], Index = i };
            indices[i] = i;
        }

        for (var i = LastRootIndex(); i >= 0; i--) {
            MaxHeapify(i);
        }
    }

    private void MaxHeapify(int index) {
        while (index <= LastRootIndex()) {
            var maxChildIndex = LeftIndex(index);
            if (maxChildIndex == null) {
                return;
            }

            var rightIndex = RightIndex(index);
            if (rightIndex != null) {
                if (heap[rightIndex.Value].Value > heap[maxChildIndex.Value].Value) {
                    maxChildIndex = rightIndex;
                }
            }

            if (heap[index].Value < heap[maxChildIndex.Value].Value) {
                Swap(index, maxChildIndex.Value);
            }

            index = maxChildIndex.Value;

            // Print();
        }
    }

    public void Print() {
        Console.WriteLine(string.Join(", ", heap.Take(size).Select(item => string.Format("({0}, {1})", item.Value, item.Index))));
        Console.WriteLine(string.Join(", ", indices.Take(size)));
        Console.WriteLine();
    }

    private void Swap(int x, int y) {
        // Console.WriteLine("Swap({0}, {1})", x, y);

        var tmpIndex = indices[heap[x].Index];
        indices[heap[x].Index] = indices[heap[y].Index];
        indices[heap[y].Index] = tmpIndex;

        (heap[y], heap[x]) = (heap[x], heap[y]);
    }

    private int LastRootIndex() {
        return (size - 1) / 2;
    }

    private Nullable<int> LeftIndex(int index) {
        if (2*index + 1 < size) {
            return 2*index + 1;
        }

        return null;
    }

    private Nullable<int> RightIndex(int index) {
        if (2*index + 2 < size) {
            return 2*index + 2;
        }

        return null;
    }

    public int Peek() {
        return heap[0].Value;
    }

    public void Delete(int index) {
        var tmp = indices[index];
        Swap(indices[index], size - 1);
        size--;
        MaxHeapify(tmp);
    }
}

class Quadratic {
    public static int Solve(int[] stockPrice) {
        var maxProfit = int.MinValue;
        for (var i = 0; i < stockPrice.Length - 1; i++) {
            for (var j = i + 1; j < stockPrice.Length; j++) {
                if (stockPrice[j] - stockPrice[i] > maxProfit) {
                    maxProfit = stockPrice[j] - stockPrice[i];
                }
            }
        }
        return maxProfit;
    }
}

class MaxHeap {
    public static int Solve(int[] stockPrice) {
        var indexHeap = new IndexedMaxHeap(stockPrice);
        var min = int.MaxValue;
        var maxProfit = int.MinValue;
        // indexHeap.Print();

        for (var i = 0; i < stockPrice.Length; i++) {
            indexHeap.Delete(i);
            // indexHeap.Print();

            if (stockPrice[i] < min) {
                min = stockPrice[i];
                if (indexHeap.Peek() - min > maxProfit) {
                    maxProfit = indexHeap.Peek() - min;
                }
            }
        }
        return maxProfit;
    }
}

class DivideConquer {
    private static int MaxProfit(int[] stockPrice, int left, int right) {
        if (right - left <= 0) {
            return 0;
        }

        var partition = (left + right) / 2;
        var partitionMax = Math.Max(MaxProfit(stockPrice, left, partition), MaxProfit(stockPrice, partition + 1, right));

        var minLeft = stockPrice.Skip(left).Take(partition - left + 1).Min();
        var maxRight = stockPrice.Skip(partition + 1).Take(right - partition).Max();
        return Math.Max(partitionMax, maxRight - minLeft);
    }

    public static int Solve(int[] stockPrice) {
        return MaxProfit(stockPrice, 0, stockPrice.Length - 1);
    }
}

class Program {
    static void Main(string[] args) {
        // int N = int.Parse(Console.ReadLine());
        // var stockPrice = new int[N];
        // for (var i = 0; i < N; i++) {
        //     stockPrice[i] = int.Parse(Console.ReadLine());
        // }
        // Console.WriteLine("StockPrice: {0}", string.Join(", ", stockPrice));
        // Console.WriteLine("Quadratic: {0}", Quadratic.Solve(stockPrice));
        // Console.WriteLine("MaxHeap: {0}", MaxHeap.Solve(stockPrice));
        // Console.WriteLine("DivideConquer: {0}", DivideConquer.Solve(stockPrice));

        for (var t = 0; t < 100; t++) {
            var N = 1000;
            var stockPrice = GenerateRandomStockPrices(N);

            var quadratic = Quadratic.Solve(stockPrice);
            var maxHeap = MaxHeap.Solve(stockPrice);
            var divideConquer = DivideConquer.Solve(stockPrice);

            if (quadratic != maxHeap || quadratic != divideConquer) {
                Console.WriteLine("StockPrice: {0}", string.Join(", ", stockPrice));
                Console.WriteLine("Quadratic: {0}", quadratic);
                Console.WriteLine("MaxHeap: {0}", maxHeap);
                Console.WriteLine("DivideConquer: {0}", divideConquer);
                return;
            }
        }
        Console.WriteLine("All match.");
    }

    static int[] GenerateRandomStockPrices(int size) {
        var result = new int[size];
        var rnd = new Random(Environment.TickCount);
        for (var i = 0; i < size; i++) {
            result[i] = rnd.Next(0, 1200);
        }
        return result;
    }
}
