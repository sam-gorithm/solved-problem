package N1021;

import java.util.*;
import java.io.*;

public class N1021_taeyoung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Deque<Integer> D = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            D.offer(i); // 덱에 1부터 N까지 입력
        }

        int ans = 0; // 총 이동 횟수

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int a = Integer.parseInt(st.nextToken()); // 뽑아야 하는 수
            if (a == D.peekFirst()) { // 뽑아야 하는 수가 맨 앞에 있다면
                D.pollFirst(); // 맨 앞 수를 뽑는다
                continue;
            }
            int cnt = 0; // 이동 횟수
            while (D.peekFirst() != a) { // 뽑아야 하는 수가 맨 앞에 올때까지
                D.offerLast(D.pollFirst()); // 맨 앞 원소를 맨 뒤로 이동
                cnt++; // 이동 횟수 1 증가
            }
            // 반대로 이동하는게 더 적을수도 있다
            if (cnt > D.size() / 2) // 덱 크기의 절반보다 많이 이동했다면 -> 반대 이동이 더 적음
                cnt = D.size() - cnt; // 반대 이동했을 경우의 횟수로 바꿔준다.
            ans += cnt; // 총 이동 횟수에 추가
            D.pollFirst(); // 맨 앞 수 뽑기
        }
        System.out.println(ans);
    }
}
