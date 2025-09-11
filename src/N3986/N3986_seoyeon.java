package N3986;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

// 선이 교차되지 않아야하므로 다른 문자가 위로 올때는 바로 pop되어야 하고, 그 다음 문자는 즉시 pop (얘는 스택 연산 중에 검사)
// 마지막에 stack에 남은 값이 없어야함. (얘는 스택 돌고 나와서 그 결과 검사)
public class N3986_seoyeon {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력받기
        int N= Integer.parseInt(br.readLine());
        // 정답 단어 갯수
        int ansCount=0;
        // 정답 단어인지 확인하는 변수
        boolean isAns=true;

        // 단어 입력
        for(int i=0; i<N; i++){
            Deque<Character> stack= new ArrayDeque<>();
            isAns=true;
            String line= br.readLine();

            // 단어 수 만큼 돌면서 연산
            for(int j=0; j<line.length(); j++){

                // 자신과 같은 문자가 바로 위 탑 값이면 pop
                if(!stack.isEmpty()&& stack.peek()==line.charAt(j)) {
                    stack.pop();
                }
                // 그렇지 않으면 push
                else{
                    stack.push(line.charAt(j));
                }

            }
            // 다 돌고 난 이후 스택이 비어있지 않으면 좋은 단어 아님
            if(!stack.isEmpty()){
                isAns=false;
            }
            // true면 좋은 단어
            if(isAns){
                ansCount++;
            }
        }
        bw.write(ansCount+"");
        bw.flush();
    }

}
