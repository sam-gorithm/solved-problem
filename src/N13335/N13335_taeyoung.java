package N13335;

import java.util.*;
import java.io.*;

public class N13335_taeyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 트럭 수
		int W = Integer.parseInt(st.nextToken()); // 다리 길이
		int L = Integer.parseInt(st.nextToken()); // 최대 하중

		Deque<Integer> truck = new ArrayDeque<>(); // 트럭 무게 저장

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			truck.offer(Integer.parseInt(st.nextToken()));
		}

		Deque<int[]> bridge = new ArrayDeque<>(); // 현재 다리 위 트럭 {남은 거리, 무게}

		int ans = 0; // 정답

		while (!truck.isEmpty() || !bridge.isEmpty()) { // 아직 안올라간 트럭 있거나, 다리 위에 트럭 남아있는 경우 반복
			ans++;
			int B = bridge.size(); // 현재 다리 위 트럭 수
			for (int i = 0; i < B; i++) {
				int[] T = bridge.poll();
				int TL = T[0]; // 남은 거리
				int TW = T[1]; // 무게
				if (TL - 1 == 0) { // 1초 후 다리 완전히 건넘
					L += TW; // 가능한 하중 다시 증가
				} else { // 여전히 다리 위
					bridge.offer(new int[] { TL - 1, TW }); // 남은 거리 1감소 후 큐에 넣기
				}
			}
			if (!truck.isEmpty() && truck.peek() <= L) { // 다리에 트럭이 더 올라갈 수 있다면
				int x = truck.poll(); // 다리 올라갈 트럭 무게
				bridge.offer(new int[] { W, x }); // 다리 큐에 트럭 상태 넣기
				L -= x; // 가능한 하중 감소
			}
		}
		System.out.println(ans);
	}
}
