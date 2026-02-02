package N11657;

import java.util.*;
import java.io.*;

public class N11657_taeyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 노드 리스트에 입력
		List<int[]> L = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			L.add(new int[] { s, e, t });
		}
		
		// 1에서 출발하는 거리 배열
		long[] dist = new long[N+1];
		long INF = Long.MAX_VALUE;
		Arrays.fill(dist, INF);
		dist[1] = 0;
		
		// 벨만-포드
		// N-1번 반복
		for (int i = 0; i < N - 1; i++) {
			for (int[] edge : L) {
				int s = edge[0];
				int e = edge[1];
				int t = edge[2];
				if (dist[s] != INF && dist[e] > dist[s] + t) {
                    dist[e] = dist[s] + t;
                }
			}
		}
		
		// 음수 사이클 확인
		boolean cycle = false;
		for (int[] edge : L) {
            int s = edge[0];
            int e = edge[1];
            int t = edge[2];
            if (dist[s] != INF && dist[e] > dist[s] + t) {
                cycle = true;
                break;
            }
        }
		
		// 정답 출력
		if (cycle) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] != INF)
                	System.out.println(dist[i]);
                else
                	System.out.println(-1);
            }
        }
	}
}

