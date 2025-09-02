package N10773;

import java.util.*;
import java.io.*;

public class N10773_taeyoung {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int K = sc.nextInt();

		Stack<Integer> S = new Stack<>();

		for (int i = 0; i < K; i++) {
			int n = sc.nextInt();
			if (n == 0) { // 정수가 0일 경우
				S.pop(); // 가장 최근에 쓴 수 지우기 -> pop()
			} else { // 아닐 경우
				S.push(n); // 해당 수 쓰기 -> push()
			}
		}

		int sum = 0; // 합 구하기

		while (!S.isEmpty()) {
			sum += S.pop(); // 하나씩 꺼내면서 더하기
		}

		System.out.println(sum);
	}
}
