import java.io.*;
import java.lang.*;
import java.util.*;

class Spell implements Comparable<Spell> {
    long mana, seconds;

    public Spell(long seconds, long mana) {
        this.seconds = seconds;
        this.mana = mana;
    }

    public int compareTo(Spell o1) {
        if (mana < o1.mana) return -1;
        if (mana == o1.mana) return 0;
        return 1;
    }
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String[] nmk = r.readLine().split("\\s");
		long n = Integer.parseInt(nmk[0]);
		int m = Integer.parseInt(nmk[1]);
		int k = Integer.parseInt(nmk[2]);
		String[] xs = r.readLine().split("\\s");
		long x = Integer.parseInt(xs[0]);
		long s = Integer.parseInt(xs[1]);
		String[] as = r.readLine().split("\\s");
		String[] bs = r.readLine().split("\\s");
		String[] cs = r.readLine().split("\\s");
		String[] ds = r.readLine().split("\\s");

		List<Spell> spells = new ArrayList<Spell>();
		HashMap<Long, Integer> mp = new HashMap();
		for (int j = 0; j < m; j++) {
			long a = Long.parseLong(as[j]);
			long b = Long.parseLong(bs[j]);
			if (!mp.containsKey(b)) {
				mp.put(b, spells.size());
				spells.add(new Spell(a, b));
			}
			else
			if (a < spells.get(mp.get(b)).seconds) {
				spells.get(mp.get(b)).seconds = a;
			}
		}

		long[] c = new long[k + 1];
		long[] d = new long[k + 1];
		for (int j = 0; j < k; j++) {
			c[j] = Long.parseLong(cs[j]);
			d[j] = Long.parseLong(ds[j]);
		}
		// also try without any spell of type 2.
		c[k] = d[k] = 0;

		Collections.sort(spells);

		long[] mins = new long[m];
		mins[0] = spells.get(0).seconds;
		for (int j = 1; j < spells.size(); j++)
			mins[j] = Math.min(mins[j - 1], spells.get(j).seconds);

		long best = n * x;

		long time;
		for (int j = 0; j <= k; j++) {
			if (d[j] <= s) {
				int index = search(spells, s - d[j]);
				time = (n - c[j]) * (index >= 0 ? mins[index] : x);
				if (time < best)
					best = time;
			}
		}
		System.out.println(best);
	}

	private static int search(List<Spell> spells, long mana) {
		int l = 0, h = spells.size() - 1;
		while (l < h) {
			int p = l + (h-l+1)/2;
			if (mana >= spells.get(p).mana)
				l = p;
			else
				h = p - 1;
		}
		return mana >= spells.get(l).mana ? l : -1;
	}

}
