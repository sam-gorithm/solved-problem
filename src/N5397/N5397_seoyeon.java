package N5397;

import java.util.*;
import java.io.*;

public class N5397_seoyeon {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스
        int T = Integer.parseInt(br.readLine());

        // T가 0이 되면 종료
        while(T-- > 0) {
            StringBuilder sb = new StringBuilder();
            // 왼쪽 오른쪽 각각 스택 선언
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();
            // 입력 받기
            String input = br.readLine();

            // 한 글자씩 읽어들여서 각각 명령을 수행함.
            for(char ch : input.toCharArray()) {
                switch(ch) {
                    // < => 커서 오른쪽으로 이동
                    case '<':
                        if(!left.empty()) {
                            right.push(left.pop());
                        }
                        break;
                    case '>': // > => 커서 왼쪽으로 이동
                        if(!right.empty()) {
                            left.push(right.pop());
                        }
                        break;
                    case '-': // - 커서 왼쪽 글자 삭제
                        if(!left.empty()) {
                            left.pop();
                        }
                        break;
                    default: // 나머지는 push
                        left.push(ch);
                        break;
                }
            }

            // 스택에 남은 문자열 출력
            // 왼쪽에 남은걸 오른쪽으로 모두 푸시함.
            while(!left.empty()) {
                right.push(left.pop());
            }
            while(!right.empty()) {
                sb.append(right.pop());
            }
            System.out.println(sb.toString());

        }
    }
}