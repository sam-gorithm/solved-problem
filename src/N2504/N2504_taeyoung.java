package N2504;

import java.util.*;
import java.io.*;

public class N2504_taeyoung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        int L = S.length();

        Deque<Character> stack = new ArrayDeque<>();

        int ans = 0;
        int tmp = 1; // 누적 곱
        char b = ' '; // 직전 원소
        for (int i = 0; i < L; i++) {
            char c = S.charAt(i);
            if (c == '(') {
                stack.push(c);
                tmp *= 2; // () 괄호에 둘러 쌓여 있다면 그 안의 수는 2 곱해야 함
            }
            else if (c == ')'){
                if (stack.isEmpty() || stack.peek() != '('){ // 올바른 괄호열 불가능한 경우
                    System.out.println(0);
                    return;
                }
                if (b == '('){ // 직전 원소와 쌍을 이룬다면 수 완성
                    ans += tmp; // 그 값을 더해 줌
                }
                stack.pop(); // 괄호 쌍 이뤘으니 스택에서 제거
                tmp /= 2; // 곱셈 범위 끝남 -> 다시 2로 나눠줌
            }
            else if (c == '[') {
                stack.push(c);
                tmp *= 3; // [] 괄호에 둘러 쌓여 있다면 그 안의 수는 3 곱해야 함
            }
            else if (c == ']'){
                if (stack.isEmpty() || stack.peek() != '['){ // 올바른 괄호열 불가능한 경우
                    System.out.println(0);
                    return;
                }
                if (b == '['){ // 직전 원소와 쌍을 이룬다면 수 완성
                    ans += tmp; // 그 값을 더해 줌
                }
                stack.pop(); // 괄호 쌍 이뤘으니 스택에서 제거
                tmp /= 3; // 곱셈 범위 끝남 -> 다시 3으로 나눠줌
            }
            b = c; // 직전 원소 갱신
        }
        if (stack.isEmpty()){ // 최종 스택이 비어있어야 올바른 괄호열
            System.out.println(ans); // 정답 출력
        } else { // 올바른 괄호열 아님
            System.out.println(0);
        }
    }
}
