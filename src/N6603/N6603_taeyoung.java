package N6603;

import java.io.*;
import java.util.*;

public class N6603_taeyoung {
	static int k;
	static int[] A, tmp; // A: 주어진 수 집합. tmp: A에서 6개 선택한 배열
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			k = Integer.parseInt(st.nextToken());
			if (k == 0) // 0이면 종료
				break;

			A = new int[k];
			tmp = new int[6];

			for (int i = 0; i < k; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			bt(0);
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	// 백트래킹 함수
	static void bt(int n) {
		if (n == 6) { // 6개 다 채웠으면
			// 출력 형식에 맞게
			for (int i = 0; i < 6; i++) {
				sb.append(tmp[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		// 조합 -> 오름차순으로
		for (int i = 0; i < k; i++) {
			if (n == 0 || tmp[n - 1] < A[i]) {
				tmp[n] = A[i];
				bt(n + 1);
			}
		}
	}
}
