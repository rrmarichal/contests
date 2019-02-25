package contests.ioi;

import java.io.PrintWriter;
import java.util.Scanner;

class SFenwick {

    private int size;
    private int[] tree;
  
    public SFenwick(int max) {
      tree = new int[max+1];
    }

    public void init(int size) {
      this.size = size;
      for (int j = 1; j <= size; j++) {
        tree[j] = j&-j;
      }
    }
  
    /**
     * Return the sum of items from 0 to index.
     */
    public int sum(int index) {
      index++;
      int sum = 0;
      while (index > 0) {
        sum += tree[index];
        index -= index&-index;
      }
      return sum;
    }
  
    /**
     * Decrease item at index by 1.
     */
    public void update(int index) {
      index++;
      while (index <= size) {
        tree[index]--;
        index += index&-index;
      }
    }
  
}

class HeapItem {

    public int item, index;

    public HeapItem(int item, int index) {
      this.item = item;
      this.index = index;
    }

    public boolean lessThan(HeapItem other) {
      if (item < other.item) {
        return true;
      }
      if (item > other.item) {
        return false;
      }
      return index < other.index;
    }

}

class SHeap {

  private int size;
  private int[] A;
  private HeapItem[] heap;

  /**
   * Construct a heap from elements in A[L..R]
   */
  public SHeap(int[] A) {
    this.A = A;
    heap = new HeapItem[A.length];
  }

  public void init(int L, int R) {
    initHeap(A, L, R);
    buildHeap();
  }

  private HeapItem[] initHeap(int[] A, int L, int R) {
    size = R-L+1;
    for (int j = L; j <= R; j++) {
      heap[j-L] = new HeapItem(A[j], j-L);
    }
    return heap;
  }

  private void buildHeap() {
    for (int j = size/2-1; j>=0; j--) {
      heapDown(j);
    }
  }

  private void swap(int j, int k) {
    HeapItem tmp = heap[j];
    heap[j] = heap[k];
    heap[k] = tmp;
  }

  private void heapDown(int k) {
    if (k < size/2) {
      int minChild = 2*k+1;
      if (2*(k+1) < size && heap[2*(k+1)].lessThan(heap[minChild])) {
        minChild = 2*(k+1);
      }
      if (heap[minChild].lessThan(heap[k])) {
        swap(k, minChild);
        heapDown(minChild);
      }
    }
  }

  public HeapItem extractMin() {
    HeapItem min = heap[0];
    heap[0] = heap[--size];
    heapDown(0);
    return min;
  }

  public int size() {
    return size;
  }

}

public class App {
    
    private SHeap sheap;
    private SFenwick sfenwick;

    public App(int[] A) {
      sheap = new SHeap(A);
      sfenwick = new SFenwick(A.length);
    }

    public long bruteForce(int[] A, int L, int R) {
        long count = 0;
        for (int j = L; j < R; j++)
            for (int k = j+1; k <= R; k++)
                if (A[j] > A[k]) {
                    count++;
                }
        return count;
    }

    public long successiveMinCounter(int L, int R) {
        sheap.init(L, R);
        sfenwick.init(R-L+1);
        long inversions = 0;
        for (int j=0; j < R-L; j++) {
            HeapItem min = sheap.extractMin();
            inversions += sfenwick.sum(min.index) - 1;
            sfenwick.update(min.index);
        }
        return inversions;
    }

    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int j=0; j < N; j++) {
            A[j] = sc.nextInt();
        }
        App app = new App(A);
        int Q = sc.nextInt();
        for (int j=0; j < Q; j++) {
            int L = sc.nextInt(), R = sc.nextInt();
            // System.out.println(app.bruteForce(A, L-1, R-1));
            pw.println(app.successiveMinCounter(L-1, R-1));
        }
        pw.close();
        sc.close();
    }

}
