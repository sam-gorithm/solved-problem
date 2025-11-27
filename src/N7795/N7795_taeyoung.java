package N7795;

import java.util.*;
import java.io.*;

public class N7795_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// 생명체 A
			int[] A = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			// 생명체 B
			int[] B = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}

			// 정렬
			Arrays.sort(A);
			Arrays.sort(B);

			// 현재 비교할 인덱스
			int idxA = 0;
			int idxB = 0;

			int ans = 0;

			// 인덱스 범위 내
			while (idxA < N && idxB < M) {
				// 현재 비교해야 할 인덱스에서
				if (A[idxA] > B[idxB]) { // A[idxA]가 B[idxB]를 먹을 수 있음
					idxB++; // 더 작은 B가 있는지 보기 위해 idxB++
				} else { // A[idxA]가 B[idxB]를 먹을 수 없음
					// A[idxA]는 idxB개의 B만 먹을 수 있음
					ans += idxB;
					idxA++; // 다음 A 확인하기
				}
			}

			// 아직 남은 A -> 모든 B 먹을 수 있다.
			if (idxA < N) {
				ans += (N - idxA) * M;
			}

			System.out.println(ans);
		}
	}
}
