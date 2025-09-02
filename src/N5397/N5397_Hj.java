package barking_dog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class N5397_Hj {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			String input = br.readLine();

			List<Character> result = new LinkedList<>();
			ListIterator<Character> iterator = result.listIterator();

			for (int i = 0; i < input.length(); i++) {
				char key = input.charAt(i);

				if (key == '<') {
					if (iterator.hasPrevious())
						iterator.previous();
				} else if (key == '>') {
					if (iterator.hasNext())
						iterator.next();
				} else if (key == '-') {
					if (iterator.hasPrevious()) {
						iterator.previous();
						iterator.remove();
					}
				} else {
					iterator.add(key);
				}

			}
			StringBuilder sb = new StringBuilder();
			for (Character character : result) {
				sb.append(character);
			}
			System.out.println(sb.toString());
		}

	}
}