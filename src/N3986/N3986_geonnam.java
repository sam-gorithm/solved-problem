package N3986;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class N3986_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int count = 0; // 좋은 단어의 수
		
		for(int t=0;t<n;t++) {
			Deque<Character> stack = new ArrayDeque<>();

			String a = br.readLine(); //단어
			char[] text = a.toCharArray();
			
			for(int i=0;i<text.length;i++) {
				//스택 맨위와 동일하다면 빼고 동일하지 않다면 넣기
				if(!stack.isEmpty() && stack.peek() == text[i]) {
					stack.pop();
				}else {
					stack.push(text[i]);
				}
			}
			if(stack.isEmpty()) count++; // 비어있다면 좋은 단어
		}
		System.out.println(count);
	}
}
