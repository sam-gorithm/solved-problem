package N1021;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

//회전하는 큐
public class N1021_jinhyuk {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();	//1 ~ N
		int M = sc.nextInt();	//뽑으려는 수의 개수
		
		//뽑으려는 수
		int[] arr = new int[M];
		for(int i=0; i<M; i++) {
			arr[i] = sc.nextInt();
		}
		
		//덱 생성 및 1 ~ N 할당
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			dq.addLast(i);
		}

		int ans = 0;
		//뽑기
		for(int i=0; i<M; i++) {
			int cnt = 0;
			//앞쪽으로만 원하는 수 나올 때까지 뽑기
			while(dq.peek() != arr[i]) {
				dq.addLast(dq.pollFirst());
				cnt++;
			}
			//앞, 뒤 어느쪽으로 뽑는게 더 적었을 지 계산해서 정답에 추가
			ans += Math.min(cnt, dq.size() - cnt);
			//원하는 수 뽑아내기
			dq.pollFirst();
			
		}	//뽑기
		
		//결과출력
		System.out.println(ans);
	}	//main

}
