package N2164;

import java.util.*;
import java.io.*;

public class N2164_taeyoung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> Q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            Q.offer(i); // 큐에 1부터 N까지 추가
        }

        for (int i = 0; i < N - 1; i++) { // 한 장 남을 때까지 -> N-1번
            Q.poll(); // 제일 위 카드 버리기
            Q.offer(Q.poll()); // 제일 위에 있는 카드 맨 아래로
        }
        System.out.println(Q.peek()); // 마지막 남은 카드 출력
    }
}
