package N10799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class N10799_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Deque<Character> stack = new ArrayDeque<>();
		int result = 0; // 막대기 수
		char b ='-'; // 레이저인지 확인하기 위해서 선언
		
		String a = br.readLine();
		for (char c : a.toCharArray()) {
			if(c == '(') {
				stack.push(c);
			}else { //닫는 괄호
				if(!stack.isEmpty()) {
					stack.pop(); // 하나 빼고 
					if(b != '(') { // 레이저가 아니면 마지막 막대 개수 한 개 증가
						result++;
					}else { // 레이저면 막대수만큼 증가
						result += stack.size();
					}
				}
			}
			b = c;
		}
		System.out.println(result);
	}
}
