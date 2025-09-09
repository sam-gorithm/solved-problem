package N3986;

import java.util.*;
import java.io.*;

public class N3986_taeyoung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            String S = br.readLine();

            Deque<Character> Stack = new ArrayDeque<>();

            int L = S.length();
            if (L % 2 == 1) // 단어 길이 홀수면
                continue; // 불가능하므로 넘긴다

            for (int j = 0; j < L; j++) {
                char c = S.charAt(j);
                if (Stack.isEmpty() || Stack.peek() != c) // 스택이 비어있거나, 스택 맨 위에 다른 글자면
                    Stack.push(c); // 스택에 넣는다
                else { // 같은 글자라면
                    Stack.pop(); // 스택에서 꺼낸다.
                }
            }
            if (Stack.isEmpty()) // 최종적으로 스택이 비어있으면 -> 좋은 단어
                cnt++; // 카운트 1 증가
        }
        System.out.println(cnt);
    }
}
