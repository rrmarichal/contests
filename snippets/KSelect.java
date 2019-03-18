import java.util.Random;

public class KSelect {

	public static void main(String[] args) {
        int[] a = readInput(args);
        int k = new Random().nextInt(a.length);
        int kth = select(a, k, 0, a.length - 1);
        System.out.println(String.format("%dth element is '%d'", k, kth));
	}

	private static int[] readInput(String[] args) {
		int[] a = new int[args.length];
		for (int j = 0; j < args.length; j++) {
			a[j] = Integer.parseInt(args[j]);
		}
		return a;
	}

	public static int select(int[] a, int k, int l, int h) {
		assert h - l + 1 >= k;
		if (l == h) {
			return a[l];
		}
		int p = partition(a, l, h);
		if (p - l + 1 >= k) {
			return select(a, k, l, p);
		}
		return select(a, k - (p - l + 1), p + 1, h);
	}
	
	public static int partition(int[] a, int l, int h) {
		int pivot = a[l];
		int x = l, y = h;
		while (x <= y) {
			while (a[x] <= pivot) x++;
			while (a[y] >= pivot) y--;
			
			if (x <= y) {
				swap(a, x, y);
				x++; y--;
			}
        }
        // a[l .. y] <= a[y + 1 ..  h]
		return y;
	}

	private static void swap(int[] a, int x, int y) {
		int tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
    }
    
}
