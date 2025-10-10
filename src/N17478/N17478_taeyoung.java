package N17478;

import java.io.*;
import java.util.*;

public class N17478_taeyoung {
	static int N;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 재귀 횟수
		sb = new StringBuilder();
		
		rec(0);
		
		System.out.println(sb);
	}

	// 재귀 함수
	static void rec(int n) {
		if (n > N) // 재귀 종료 조건
			return;
		
		if (n == 0) { // 재귀 시작에서만 출력
			sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		}
		
		// 매 재귀마다 반복 출력
		append____(n);
		sb.append("\"재귀함수가 뭔가요?\"\n");
		
		// 재귀 깊이 N 도달
		if (n == N) {
			append____(n);
			sb.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
		}
		// 재귀 깊이 N보다 작음
		else {
			append____(n);
			sb.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
			append____(n);
			sb.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
			append____(n);
			sb.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
		}
		
		// 다음 재귀
		rec(n + 1);
		
		// 재귀 호출 끝나고 출력
		append____(n);
		sb.append("라고 답변하였지.\n");
	}
	
	// 재귀 깊이만큼 ____ 출력
	static void append____(int n) {
		for (int j = 0; j < n; j++) {
			sb.append("____");
		}
	}
}
