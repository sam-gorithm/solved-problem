package N5430;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

//AC
public class N5430_jinhyuk {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		//tc
		for(int tc=1; tc<=T; tc++) {
			
			String p = sc.next();
			int n = sc.nextInt();
			
			//[1,2,3,4] 등 입력받기 위한 StringTokenizer
			StringTokenizer st = new StringTokenizer(sc.next(), "[,]");
			Deque<String> dq = new ArrayDeque<>();
			for(int i=0; i<n; i++) {
				dq.offer(st.nextToken());
			}
			
			//'R' 나올때마다 뒤집으면 시간 초과 가능성
			//isFlipped -> dq.pollLas, !isFlipped -> dq.pollFirst
			boolean isFlipped = false;
			boolean isValid = true;
			//함수처리
			for(int i=0; i<p.length(); i++) {
				char ch = p.charAt(i);
				//'D' 함수
				if(ch == 'D') {
					//'D' 함수인데 비어있으면 error
					if(dq.isEmpty()) {
						isValid = false;
						break;
					}
					//뒤집힌 상태라면 pollLast로 구현
					if(isFlipped) dq.pollLast();
					else dq.pollFirst();
				}	//'D'
				//'R' 함수
				else isFlipped = !isFlipped;
			}
			
			//결과출력
			if(isValid) {
				StringBuilder sb = new StringBuilder();
				while(!dq.isEmpty()) {
					sb.append(isFlipped ? dq.pollLast() : dq.pollFirst());
					if(!dq.isEmpty()) {
						sb.append(",");
					}
				}
				//유효할 때 결과출력
				System.out.println("[" + sb + "]");
			}else {
				//에러발생 시 결과출력
				System.out.println("error");
			}
		}	//tc
		
		
	}	//main

}
