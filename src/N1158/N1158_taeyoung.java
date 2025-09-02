package N1158;

import java.util.*;
import java.io.*;

public class N1158_taeyoung {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 사람 수
		int K = sc.nextInt(); // 몇 칸 이동

		List<Integer> A = new LinkedList<>(); 

		// 리스트에 1부터 N까지 입력
		for (int i = 1; i <= N; i++) {
			A.add(i);
		}

		int idx = -1; // 제거할 위치 변수

		List<Integer> answer = new ArrayList<>(); // 정답 입력할 리스트

		while (!A.isEmpty()) {
			int l = A.size(); // 남아있는 인원
			idx = (idx + K) % l; // k번째 사람. 원형이니 인덱스 범위 넘어가지 않게 l로 나눈 나머지
			answer.add(A.remove(idx--)); // 해당하는 사람을 제거하고 그 자리는 사라졌으니 인덱스 1 줄여준다.
		}
		// 출력 형식에 맞추기
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		for (int i = 0; i < N - 1; i++) {
			sb.append(answer.get(i));
			sb.append(", ");
		}
		sb.append(answer.get(N - 1));
		sb.append('>');

		System.out.println(sb);
	}
}

