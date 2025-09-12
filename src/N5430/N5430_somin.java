import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class N5430_somin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		while (T-- > 0) {
			String p = sc.next(); // 수행할 함수 
			int n = sc.nextInt(); // 배열에 들어있는 수의 개수 
			String input = sc.next(); // 배열에 들어있는 정수 
			
			Deque<Integer> deque = new LinkedList<>();
			
			if (n > 0) {
				// 대괄호 제거 
				input = input.substring(1, input.length() -1);
				String[] nums = input.split(",");
				
				// 각 숫자를 정수로 변환하여 덱의 맨 뒤에 추가 
				for (String num : nums) {
					deque.add(Integer.parseInt(num));
				}
			}
			
			boolean error = false;
			boolean reverse = false;
			
			for (char c : p.toCharArray()) {
				if (c == 'R') {
					reverse = !reverse;
				} else {
					if (deque.isEmpty()) {
						error = true;
						break;
					}
					
					if (!reverse) deque.pollFirst();
					else deque.pollLast();
				}
			}
			
			if (error) {
				System.out.println("error");
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append("[");
				if (!deque.isEmpty()) {
					if (!reverse) {
						//뒤집히지 않았을 경우 앞에서부터 순차적으로 출력 
						while (!deque.isEmpty()) {
							sb.append(deque.pollFirst());
							if (!deque.isEmpty()) sb.append(",");
						}
						
					} else {
						// 뒤집힌 상태일 경우 뒤에서부터 출력 
						while (!deque.isEmpty()) {
							sb.append(deque.pollLast());
							if (!deque.isEmpty()) sb.append(",");
						}
					}
				}
				
				sb.append("]");
				System.out.println(sb);
			}
			
			
		}
		
		
	}
}