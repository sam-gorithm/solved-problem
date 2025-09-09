package N5430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class N5430_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for(int tc=0;tc<t;tc++) {
			StringBuilder sb = new StringBuilder();
			ArrayDeque<Integer> q = new ArrayDeque<>();
			String a = br.readLine();
			char[] text = a.toCharArray(); // 함수들
			
			int n = Integer.parseInt(br.readLine()); //배열에 들어있는 수
			
			String arr = br.readLine();
			arr = arr.substring(1,arr.length()-1); // 양쪽 대괄호 제거
			
			String[] number;
			if(arr.isEmpty()) {
				number = new String[0];
			}else {
				number = arr.split(","); //,기준으로 나누기 
			}
			
			for (String num : number) {
				q.offer(Integer.parseInt(num));
			}
			
			boolean reverse = false; // 뒤집혔는지 확인하기 위해
			
			for(int i=0;i<text.length;i++) {
				char c = text[i];
				if(c == 'R') {
					reverse = !reverse;
				}else {
					if(q.isEmpty()) {
						sb.append("error");
						break;
					}
					if(reverse) q.pollLast(); // 뒤집혔으면 뒤에서부터
					else q.pollFirst();
				}
			}
			if (!sb.toString().equals("error")) {
			    sb.append("[");
			    if (!q.isEmpty()) {
			        if (!reverse) {
			            sb.append(q.poll());
			            while (!q.isEmpty()) {
			                sb.append(",").append(q.poll());
			            }
			        } else { // 뒤집혔을 때
			            sb.append(q.pollLast());
			            while (!q.isEmpty()) {
			                sb.append(",").append(q.pollLast());
			            }
			        }
			    }
			    sb.append("]");
			}
			System.out.println(sb);
		}
	}
}
