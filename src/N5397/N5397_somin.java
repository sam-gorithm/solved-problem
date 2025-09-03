import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class N5397_somin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			String input = sc.next();

			// 최종 비밀번호를 저장할 LinkedList
			LinkedList<Character> list = new LinkedList<>();
			ListIterator<Character> iterator = list.listIterator();

			// 입력받은 키로그 문자열을 순회
			for (char c : input.toCharArray()) {
				switch (c) {

				case '-':
					// iterator의 왼쪽에 문자가 있을 경우에만 삭제
					if (iterator.hasPrevious()) {
						iterator.previous(); // 삭제할 문자의 앞으로 커서를 이동
						iterator.remove(); // 커서가 방금 지나온 문자를 삭제
					}
					break;
				case '<':
					// iterator가 맨 앞이 아닐 경우에만 왼쪽으로 한 칸 이동
					if (iterator.hasPrevious()) {
						iterator.previous(); //이전 요소의 앞으로 이동시킴
					}
					break;
				case '>':
					//iterator가 맨 뒤가 아닐 경우에만 오른쪽으로 한 칸 이동
					if (iterator.hasNext()) {
						iterator.next(); // 다음 요소의 뒤로 이동시킴
					}
					break;
				default:
					// 일반 문자인 경우
					iterator.add(c);
					break;
				}
			}

			StringBuilder sb = new StringBuilder();
			for (char c : list) {
				sb.append(c);
			}

			System.out.println(sb.toString());

		}

	}
}