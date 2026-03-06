package N23326;

import java.util.*;
import java.io.*;

public class N23326_taeyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		int[] A = new int[N + 1]; // i번 구역 명소 여부
		TreeSet<Integer> set = new TreeSet<>(); // 명소인 구역 정렬상태로 저장 (배열로 하면 다음 명소 찾는거 오래 걸림)

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			// 명소 위치 등록
			if (A[i] == 1)
				set.add(i);
		}

		// 현재 위치
		int now = 1;

		StringBuilder sb = new StringBuilder();

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());

			// 1 i
			if (type == 1) {
				int i = Integer.parseInt(st.nextToken());
				// 명소 여부 전환
				if (A[i] == 1) {
					A[i] = 0;
					set.remove(i);
				} else {
					A[i] = 1;
					set.add(i);
				}
			}
			// 2 x
			else if (type == 2) {
				int x = Integer.parseInt(st.nextToken());
				// x칸 이동하기
				now = ((now - 1 + x) % N) + 1;
			}
			// 3
			else {
				// 명소 없으면 -1
				if (set.isEmpty()) {
					sb.append(-1).append('\n');
				} else {
					// now 이상 중 가장 가까운 명소
					Integer next = set.ceiling(now);
					// now 이상인 명소가 없으면 -> 명소 중 가장 작은 구역 번호
					if (next == null)
						next = set.first();
					// 거리 계산
					int dist = (next - now + N) % N;
					sb.append(dist).append('\n');
				}

			}
		}
		System.out.println(sb);
	}
}

