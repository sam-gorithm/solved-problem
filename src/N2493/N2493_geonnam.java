package N2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class N2493_geonnam {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        
        Stack<int[]> stack = new Stack<>();

        for (int i = 1; i <= N; i++) {
            int curH = Integer.parseInt(st.nextToken());

            // 스택을 확인하며 현재 탑보다 낮은 탑 제거
            while (!stack.isEmpty()) {
                int[] top = stack.peek();
                int topI = top[0];
                int topH = top[1];

                // 수신 탑 확인
                if (topH >= curH) {
                    sb.append(topI).append(" ");
                    break; 
                } else {
                    //맨 위 탑 확인 후 적으면 제거
                    stack.pop();
                }
            }

            //수신 탑이 있는지 확인
            if (stack.isEmpty()) {
                sb.append(0).append(" ");
            }

            // 현재 탑 추가
            stack.push(new int[]{i, curH});
        }

        System.out.println(sb);
    }
}
