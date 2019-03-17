import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int H = Integer.parseInt(reader.readLine().split("\\s")[0]);
		for (int j = 0; j < H; j++) {
			String[] line = reader.readLine().split("\\s");
			for (int k = 0; k < line.length; k++)
				if ("snuke".equals(line[k])) {
					System.out.println(String.format("%c%d", k+65, j+1));
					return;
				}
		}
	}
	
}
