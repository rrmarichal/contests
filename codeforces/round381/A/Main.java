import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String[] raw = r.readLine().split("\\s");
		long n = Long.parseLong(raw[0]);
		long a = Long.parseLong(raw[1]);
		long b = Long.parseLong(raw[2]);
		long c = Long.parseLong(raw[3]);

		if (n%4 == 0)
			System.out.println(0);
		else
		if (n%4 == 1)
			System.out.println(Math.min(3*a, Math.min(c, a + b)));
		else
		if (n%4 == 2)
			System.out.println(Math.min(b, Math.min(2*c, 2*a)));
		else
			System.out.println(Math.min(a, Math.min(3*c, b + c)));
	}
	
}
