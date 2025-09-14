package N5430;

import java.util.*;
import java.io.*;

public class N5430_taeyoung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        next: for (int t = 0; t < T; t++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String S = br.readLine();
            S = S.substring(1, S.length() - 1); // 앞 뒤 괄호 제외
            String[] X = S.split(","); // "," 기준으로 나누기

            Deque<String> D = new ArrayDeque<>();
            for (String x : X) {
                if (!x.isEmpty()) // 빈 문자열은 제외
                    D.offer(x); // 덱에 추가
            }

            boolean R = false; // 뒤집어지면 true -> 뒤에서부터 제거
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == 'R') { // 뒤집기
                    R = !R; // true <-> false
                } else { // 버리기
                    if (D.isEmpty()) { // 덱이 비어있으면
                        System.out.println("error"); // 에러
                        continue next; // 다음 테스트케이스로 넘아간다.
                    }
                    if (R) { // 뒤집어져 있으면
                        D.pollLast(); // 뒤에서 제거
                    } else { // 아니면
                        D.pollFirst(); // 앞에서 제거
                    }
                }
            }
            StringBuilder sb = new StringBuilder(); // 정답 출력을 위한 StringBuilder
            sb.append("[");
            if (R) { // 뒤집어져 있으면
                while (!D.isEmpty()) {
                    sb.append(D.pollLast()).append(","); // 뒤에서부터 출력
                }
            } else { // 아니면
                while (!D.isEmpty()) {
                    sb.append(D.pollFirst()).append(","); // 앞에서부터 출력
                }
            }
            if (sb.length() > 1) // 수가 채워져 있는 경우에만
                sb.deleteCharAt(sb.length() - 1); // 마지막 , 지우기
            sb.append("]");
            System.out.println(sb);
        }
    }
}
