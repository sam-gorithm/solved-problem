import java.util.Scanner;
import java.util.Stack;

public class N2504_somin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		Stack<Character> st = new Stack<>();
		int sum = 0;
		int num = 1;
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			if (ch == '(') {
				num *= 2; // 소괄호가 시작되면 2를 곱함 
				st.push(ch);
			} else if (ch == '[') {
				num *= 3; // 대괄호가 시작되면 3을 곱함 
				st.push(ch);
			} else if (ch == ')') {	
				// 스택이 비어있거나 짝이 맞지 않으면 올바르지 않은 괄호열 
				if (st.isEmpty() || st.peek() != '(') {
					sum = 0;
					break;
				}
				
				// 직전이 '()' 인 경우 sum에 값을 더함 
				if (str.charAt(i - 1) == '(') {
					sum += num;
				}
				
				st.pop();
				num /= 2; // 소괄호 쌍이 사라졌으므로 2로 나눔 
			} else if (ch == ']') {
				if (st.isEmpty() || st.peek() != '[') {
					sum = 0;
					break;
				}
				
				// 직전이 '[]' 인 경우 sum에 값을 더함 
				if(str.charAt(i-1) == '[') {
					sum += num;
				}
				
				st.pop();
				num /= 3; // 대괄호 쌍이 사라졌으므로 3으로 나눔 
			}
		}
		
		if (!st.isEmpty()) {
			System.out.println(0);
		} else {
			System.out.println(sum);
		}
	}
}