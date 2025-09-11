package N2504;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class N2504_seoyeon {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력받기
        String line = br.readLine();

        // 괄호의 수식을 계산한 결과를 담을 변수
        int answer = 0;
        // 괄호 중첩 *을 계산할 변수
        int multi=1;

        Deque<Character> stack = new ArrayDeque<>();
        // 괄호 스택에 담음
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') {
                stack.push(line.charAt(i));
                multi*=2;
            }
            else if (line.charAt(i) == '[') {
                stack.push(line.charAt(i));
                multi*=3;
            }
            // 닫힌 괄호
            else {
                if(line.charAt(i) == ')') {
                    // 올바른 괄호열이 아니므로 0으로 만들고 반복문 중단
                    if (stack.isEmpty() || stack.peek() != '('){
                        answer=0;
                        break;
                    }
                    // 올바른 괄호열
                    else{
                        if(line.charAt(i-1) == '(') {
                            answer += multi;
                        }
                    }
                    // 중첩 제거
                    multi /=2;
                    stack.pop();

                } // 닫는 소괄호
                else{
                    // 올바른 괄호열이 아니므로 0으로 만들고 반복문 중단
                    if (stack.isEmpty() || stack.peek() != '['){
                        answer=0;
                        break;
                    }
                    // 올바른 괄호열
                    else{
                        if(line.charAt(i-1) == '[') {
                            answer += multi;
                        }
                    }

                    // 중첩 제거
                    multi /=3;
                    stack.pop();
                } // 닫는 대괄호
            }
        }

        if(!stack.isEmpty()){
            answer=0;
        }
        System.out.println(answer);
    }
}
