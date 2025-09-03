package N10773;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class N10773_geonnam {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int sum = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0;i<n;i++) {
        	int num = Integer.parseInt(br.readLine());
        	if(num == 0 && !stack.isEmpty()) { //스택이 비어있을 때 pop하면 안되니까 조건 추가
        		stack.pop();
        	}else { // 0이 아닌 숫자일 때 스택에 넣기
        		stack.push(num);
        	}
        }
        
        while(!stack.isEmpty()) {
        	sum += stack.pop();
        }
        System.out.println(sum);
    }
}
