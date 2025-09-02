package N10773;

import java.io.*;
import java.util.*;

public class N10773_seoyeon {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        int K = Integer.parseInt(br.readLine());

        // 수를 담을 스택 선언
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(br.readLine());

            // 0이면 스택이 비어있지 않을 경우 pop
            if (num == 0) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
            // 나머지 숫자가 나올 경우 푸시
            else {
                stack.push(num);
            }
        }

        int sum = 0;
        // 남아있는 값들은 모두 출력해서 sum에 누적함.
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        sb.append(sum).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}
