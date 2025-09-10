package N1874;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class N1874_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>(); 
		StringBuilder sb = new StringBuilder(); 
		
		int max = 0; // push할 때 오름차순을 지키기 위해서 만든 변수
		
		for(int i=0;i<n;i++) {
			int num = Integer.parseInt(br.readLine());
			if(i==0) { //첫 순서엔 무조건 push
				for(int j=1;j<=num;j++) {
					stack.push(j);
					sb.append("+").append("\n");
					max = num;
				}
				stack.pop(); // num까지 넣어줬으니까 한 번 빼준다.
				sb.append("-").append("\n");
			}else {
				if(max < num) { // 오름차순을 지킬때만 push , i=0일 때랑 동일
					for(int j=max;j<num;j++) {
						stack.push(j+1);
						sb.append("+").append("\n");
					}
					max = num; // 최댓값 설정
					stack.pop();
					sb.append("-").append("\n");
				}
				//스택이 비어있지 않고 num과 같다면 빼준다.
				else if(!stack.isEmpty() && stack.peek() == num) {
					stack.pop();
					sb.append("-").append("\n");
				}
				//num이 스택 맨 위보다 작으면 pop
				else if(!stack.isEmpty() && stack.peek() > num) {
					boolean flag = false; // 만약 num과 같은 수를 못 찾으면 No하기 위해 설정
					while(!stack.isEmpty()) {
						int a = stack.pop();
						if(a == num) {
							flag = true;
							break;
						}
					}
					if(!flag) { // num과 같은 수를 스택에서 못찾았을 때
						sb = new StringBuilder();
						sb.append("NO");
						break;
					}
				}else { // 다른 경우들(예를 들어 오름차순 안 지키는 경우들이 여기로 오게끔 했다.)
					sb = new StringBuilder();
					sb.append("NO");
					break;
				}
			}
		}
		System.out.println(sb);
	}
}
