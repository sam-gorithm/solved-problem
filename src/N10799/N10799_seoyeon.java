package N10799;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class N10799_seoyeon {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Character> stack= new ArrayDeque<>();
        int answer=0;

        // 글자 입력받기
        String str= br.readLine();

        // 입력 크기 만큼 돌음
        for(int i=0; i<str.length(); i++){
            // 현재 비교할 문자
            char c= str.charAt(i);
            // 여는 괄호이면 push
            if(c=='(') {
                stack.push(c);

            }
            // 아니면 pop
            else{
                stack.pop();
                // 쇠막대기 끝일 경우
                if(str.charAt(i-1)==')'){
                    answer++;
                }
                // 레이저 끝인 경우
                else if (str.charAt(i-1)=='(') {
                    answer+=stack.size();
                }
            }


        }
        System.out.println(answer);
    }
}
