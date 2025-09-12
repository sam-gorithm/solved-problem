import java.util.Scanner;
import java.util.Stack;

public class N3986_somin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int ans = 0;
		
		for (int i = 0; i <N; i++) {
			String word = sc.next();
			Stack<Character> s = new Stack<>();
			
			for (char c : word.toCharArray()) {
				// 스택이 비어있지 않고 스택의 맨 위 문자가 현재 문자와 같을 경우, 맨 위 문자 제거 
				if (!s.isEmpty() && s.peek() == c) {
					s.pop();
				} else {
					// 스택이 비었거나 맨 위 문자가 다를 경우, 현재 문자를 스택에 추가 
					s.push(c);
				}
			}
			
			// 스택이 비어있다면 모든 문자가 쌍을 이룬 것 -> 좋은 단어 
			if (s.isEmpty()) {
				ans++;
			}
		}
		
		System.out.println(ans);
		
	}
}