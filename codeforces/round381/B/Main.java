import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] raw = reader.readLine().split("\\s");
		int n = Integer.parseInt(raw[0]);
		int m = Integer.parseInt(raw[1]);
		raw = reader.readLine().split("\\s");
		int[] f = new int[n];
		for (int j = 0; j < n; j++)
			f[j] = Integer.parseInt(raw[j]) + (j > 0 ? f[j-1] : 0);
		int result = 0;
		for (int j = 0; j < m; j++) {
			raw = reader.readLine().split("\\s");
			int l = Integer.parseInt(raw[0]) - 1;
			int r = Integer.parseInt(raw[1]) - 1;
			int s = f[r] - (l > 0 ? f[l-1] : 0);
			if (s > 0)
				result += s;
		}
		System.out.println(result);
	}
	
}
