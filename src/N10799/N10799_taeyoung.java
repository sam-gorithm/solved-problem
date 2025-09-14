package N10799;

import java.util.*;
import java.io.*;

public class N10799_taeyoung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        int L = S.length();

        Deque<Character> stack = new ArrayDeque<>();

        int cnt = 0;

        char b = ' '; // 직전 원소
        for (int i = 0; i < L; i++) {
            char c = S.charAt(i);
            if (c == '(') { // 여는 괄호는
                stack.push(c); // 스택에 넣기
            } else { // 닫는 괄호의 경우
                stack.pop(); // 일단 스택에서 하나 빼기
                if (b == '(') { // 직전에 여는 괄호였다면 -> 레이저 -> 현재 스택의 크기가 막대기의 수
                    cnt += stack.size(); // 스택의 크기만큼 조각 추가
                } else { // 직전에 여는 괄호 아님 -> 막대기 끝
                    cnt++; // 조각 수 1 증가
                }
            }
            b = c; // 직전 원소 갱신
        }
        System.out.println(cnt);
    }
}
