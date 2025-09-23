package N13549;

import java.util.*;
import java.io.*;

public class N13549_taeyoung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈
        int K = Integer.parseInt(st.nextToken()); // 동생

        // 수빈이가 동생보다 앞에 있다면 -> 뒤로 이동만 가능
        if (N >= K) {
            System.out.println(N - K);
            return;
        }

        Deque<Integer> Q = new ArrayDeque<>(); // BFS를 위한 큐

        boolean[] checked = new boolean[100001]; // 방문 체크

        // 문제 풀이 방법 -> 역으로 동생이 움직인다고 생각
        Q.offer(K);
        checked[K] = true;
        // 순간이동은 시간 소모 X
        // 지금 동생 위치가 짝수라면? 절반 위치로 이동 가능
        // 반복 조건 -> 짝수, 방문 안한 위치
        // N보다 작아지면 거기까지 (더 작아지는건 의미 없다)
        while(K % 2 == 0 && K > N && !checked[K / 2]) {
            K /= 2;
            Q.offer(K);
            checked[K] = true;
        }

        int ans = 0; // 정답 : 몇 초 후인지


        while(!Q.isEmpty()) {
            int l = Q.size(); // 시간대별로 확인
            for (int i = 0; i < l; i++) {
                int x = Q.poll(); // 동생 위치
                if (x == N) { // 수빈이 위치 같으면
                    System.out.println(ans); // 정답 출력
                    return;
                }
                // 1초 후 가능한 위치 큐에 넣기
                if (!checked[x - 1]) { // x-1 방문 안했으면
                    Q.offer(x - 1);
                    checked[x - 1] = true;
                }
                K = x - 1;
                // x-1 짝수라면...
                while(K % 2 == 0 && K > N && !checked[K / 2]) {
                    K /= 2;
                    Q.offer(K);
                    checked[K] = true;
                }
                if (!checked[x + 1]) { // x+1 방문 안했으면
                    Q.offer(x + 1);
                    checked[x + 1] = true;
                }
                K = x + 1;
                // x+1 짝수라면...
                while(K % 2 == 0 && K > N && !checked[K / 2]) {
                    K /= 2;
                    Q.offer(K);
                    checked[K] = true;
                }
            }
            ans++; // 해당 시간대 확인 끝 -> 정답 1 증가
        }
    }
}
