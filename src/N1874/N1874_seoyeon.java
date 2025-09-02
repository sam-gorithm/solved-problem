package N1874;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

// 1~8까지의 수열이 원래 주어지고 예제대로 출력하기 위해서 해야되는 연산구하기
public class N1874_seoyeon {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb= new StringBuilder();

        int n= Integer.parseInt(br.readLine());
        // stack 선언
        Deque<Integer> stack = new ArrayDeque<>();

        // 푸시할 숫자 -> 1씩 증가 시킬 거임.
        int currNum=1;

        for(int i=0; i<n ; i++) {
            int targetNum= Integer.parseInt(br.readLine());

            // 현재 푸시할 숫자가 t.N보다 작거나 같으면 같을 때까지 삽입
            while(currNum <= targetNum){
                stack.push(currNum);
                sb.append("+\n");
                // 삽입하고 나면 1증가
                currNum+=1;
            }

            // 현재 값과 top이 같으면 pop
            if(stack.peek()==targetNum){
                stack.pop();
                sb.append("-\n");
            }
            // 나머지는 수열을 만들 수 없는 경우
            else{
                bw.write("NO");
                bw.flush();
                return;
            }


        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }
}
