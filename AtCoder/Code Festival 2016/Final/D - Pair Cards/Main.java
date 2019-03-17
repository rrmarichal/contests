import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] raw = reader.readLine().split("\\s");
		int N = Integer.parseInt(raw[0]);
		int M = Integer.parseInt(raw[1]);
		raw = reader.readLine().split("\\s");
		RemainderItem[] C = new RemainderItem[M/2 + 1];
		for (int j = 0; j < C.length; j++)
			C[j] = new RemainderItem();
		for (int j = 0; j < N; j++) {
			int item = Integer.parseInt(raw[j]);
			int r = item % M;
			int c = r;
			if (r < C.length) {
				C[r].P++;
			}
			else {
				C[M-r].N++;
				c = M-r;
			}
			if (!C[c].ClassItems.containsKey(item)) 
				C[c].ClassItems.put(item, 1);
			else
				C[c].ClassItems.put(item, C[c].ClassItems.get(item) + 1);
		}
		long result = 0;
		for (int j = 0; j < C.length; j++) {
			// zero class adds all items.
			if (j == 0 || (M%2 == 0 && j == M / 2)) {
				result += (C[j].P + C[j].N) / 2;
			}
			else {
				int min = Math.min(C[j].P, C[j].N);
				int max = Math.max(C[j].P, C[j].N);
				long ep = 0;
				if (max - min > 1) {
					for (int key: C[j].ClassItems.keySet())
						if ((max == C[j].P && key % M< C.length) || (max == C[j].N && key % M >= C.length))
							ep += C[j].ClassItems.get(key) / 2;
					ep = Math.min(ep, (long)(max - min) / 2);
				}
				result += min + ep;
			}
		}
		System.out.println(result);
	}

	static class RemainderItem {
		int P, N;
		Map<Integer, Integer> ClassItems;

		public RemainderItem() {
			ClassItems = new HashMap<Integer, Integer>();
		}
	}
	
}
