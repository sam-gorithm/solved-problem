package N5397;

import java.util.*;
import java.io.*;

public class N5397_taeyoung {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			String S = sc.next();
			int L = S.length();
			// 커서 기준으로 왼쪽, 오른쪽 문자를 각각 deque에 넣어준다.
			Deque<Character> left = new ArrayDeque<>();
			Deque<Character> right = new ArrayDeque<>();

			for (int j = 0; j < L; j++) { // 문자열 길이만큼
				char c = S.charAt(j);
				if (c == '<') { // 커서 왼쪽 이동 -> left 마지막 원소 right 맨 앞으로
					if (!left.isEmpty()) {
						right.offerFirst(left.pollLast());
					}
				} else if (c == '>') { // 커서 오른쪽 이동 -> right 처움 원소 left 맨 뒤로
					if (!right.isEmpty()) {
						left.offerLast(right.pollFirst());
					}
				} else if (c == '-') { // 커서 앞 글자 지우기 -> left 마지막 원소 없애기
					if (!left.isEmpty()) {
						left.pollLast();
					}
				} else { // 입력 -> left 맨 뒤에 추가
					left.offerLast(c);
				}
			}
			// left right 합쳐서 출력하기
			StringBuilder sb = new StringBuilder();
			for (char c : left) sb.append(c);
			for (char c : right) sb.append(c);
			System.out.println(sb);
		}
	}
}

