package N2493;

import java.util.*;
import java.io.*;

public class N2493_taeyoung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Deque<int[]> stack = new ArrayDeque<>(); // 스택에 int 배열 -> 탑의 높이와 몇 번째 탑인지 {h, i}

        for (int i = 1; i <= N; i++) {
            int h = Integer.parseInt(st.nextToken()); // 확인할 탑 높이

            // 스택 맨 위 탑의 높이와 비교 -> 지금 확인할 탑보다 높지 않다면 제거
            while (!stack.isEmpty() && stack.peek()[0] <= h) { 	
                stack.pop();
            }
            if (stack.isEmpty()) { // 스택 비어있다 -> 앞쪽 탑중에 더 높은 탑이 없다는 뜻
                sb.append("0 ");
            } else { // 스택에 원소 남아있다면 -> 앞쪽 탑중에서 더 높은 탑 발견한 것
                sb.append(stack.peek()[1]).append(" ");
            }
            stack.push(new int[]{h, i}); // 확인 완료한 탑 정보 스택에 넣는다.
        }

        System.out.println(sb.toString());
    }
}
