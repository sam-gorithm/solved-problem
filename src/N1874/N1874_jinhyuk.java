package N1874;

import java.util.Scanner;
import java.util.Stack;

//N1874_스택수열
public class N1874_jinhyuk {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//1 ~ N 숫자를 담을 스택
		//'+', '-' 출력을 Stringbuilder
		Stack<Integer> st = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		//1 ~ N 까지의 수
		int N = sc.nextInt();
		
		//수열 입력을 저장하기위한 배열
		//출력되야하는 수의 인덱스
		int[] num = new int[N+1];	//N개의 수, 인덱스 편의를 위해 크기 N+1
		int target = 1;
		
		//N개 수열 입력받기
		for(int i=1; i<=N; i++) {
			num[i] = sc.nextInt();
		}
		
		//1부터 오름차순으로 스택에 넣고 빼며 연산 수행
		for(int i=1; i<=N; i++) {
			//1부터 오름차순으로 넣기
			st.push(i);
			sb.append("+\n");
			
			//스택이 비어있지 않고, 스택 제일 위의 수가 꺼내야하는 수열의 수에 해당하면 pop
			while(!st.empty() && st.peek() == num[target]) {
				st.pop();
				sb.append("-\n");
				//다음 수
				target++;
			}
		}
		
		//연산 후 스택이 비어있지 않다면
		//ex) 스택 제일 위의 수보다 출력 되야 하는 수열의 수가 작을 시 -> NO
		if(st.empty()) {
			//결과출력 (가능)
			System.out.print(sb);
		}else {
			//결과출력 (불가능)
			System.out.println("NO");
		}
		
		
	}	//main
}
