package N2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class N2493_HJ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<int[]> tops = new Stack<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int currentTop = Integer.parseInt(st.nextToken());

            // 탑이 비어있지 않고
            // 현재 탑보다 높다면
            while (!tops.isEmpty() && tops.peek()[1] < currentTop) {
                tops.pop();
            }

            if (tops.isEmpty()){   // 스택이 비었다면 수실할 탑 없음
                sb.append("0 ");
            }else{
                sb.append(tops.peek()[0]).append(" ");
            }
            tops.push(new int[]{i, currentTop});

        }
        System.out.println(sb.toString().trim());
    }
}
