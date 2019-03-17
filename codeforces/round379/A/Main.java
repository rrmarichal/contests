import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		r.readLine();
		String games = r.readLine();
		int anton = 0;
		for (int j = 0; j < games.length(); j++)
			if (games.charAt(j) == 'A')
				anton++;
			else
				anton--;
		if (anton == 0)
			System.out.println("Friendship");
		else
		if (anton > 0)
			System.out.println("Anton");
		else
			System.out.println("Danik");
	}
	
}

