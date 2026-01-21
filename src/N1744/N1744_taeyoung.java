package N1744;

import java.io.*;
import java.util.*;

public class N1744_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		List<Integer> p = new ArrayList<>();
		List<Integer> m = new ArrayList<>();

		int ans = 0;
		
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if (x > 1)
				p.add(x);
			else if (x <= 0) 
				m.add(x);
			else
				ans += x;
		}

		Collections.sort(p);
		Collections.sort(m);

		for (int i = p.size() - 1; i > 0; i -= 2) {
			ans += p.get(i) * p.get(i - 1);
		}
		if (p.size() % 2 != 0)
			ans += p.get(0);

		for (int i = 0; i < m.size() -1 ; i += 2) {
			ans += m.get(i) * m.get(i + 1);
		}
		if (m.size() % 2 != 0)
			ans += m.get(m.size() - 1);
		
		System.out.println(ans);
	}
}
