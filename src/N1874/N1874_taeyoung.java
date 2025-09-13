package N1874;

import java.util.*;
import java.io.*;

public class N1874_taeyoung {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		Stack<Integer> S = new Stack<>();
		
		int num = 1;

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			// 스택에 x 넣기
			while (num <= x) { // x보다 작은 수를 아직 안넣었다면
				S.push(num++); // x까지 계속 넣기
				sb.append('+').append("\n"); // push연산 -> +
			}
			if (S.peek() == x) { // 스택에 맨 위에 x가 있다면
				S.pop(); // x 꺼내기
				sb.append('-').append("\n"); // pop연산 -> -
			}
			if (!S.isEmpty() && S.peek() > x) { // 스택 맨 위에 수가 x보다 크다면 -> 원하는 수열 만들 수 없음
				System.out.println("NO"); // NO 출력 후
				return; // 끝내기
			}
		}
		System.out.println(sb); // 수열 완성되면 출력
	}
}
