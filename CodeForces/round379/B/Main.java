	
import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String[] d = r.readLine().split("\\s");
		int d2 = Integer.parseInt(d[0]);
		int d3 = Integer.parseInt(d[1]);
		int d5 = Integer.parseInt(d[2]);
		int d6 = Integer.parseInt(d[3]);
		int min256 = Math.min(d2, Math.min(d5, d6));
		int result = 256 * min256;
		d2 -= min256;
		result += 32 * Math.min(d2, d3);
		System.out.println(result);
	}
	
}
