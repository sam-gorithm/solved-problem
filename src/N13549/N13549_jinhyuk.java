package N13549;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//	숨바꼭질 3 / 골드 5 / 132ms
public class N13549_jinhyuk {
	//static
	static int N, K;
	static int[] visited = new int[100001];
	
	//수빈이 이동 (X+1, X-1, 2*X)
	static int[] move = {-1,1,2};
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	//수빈이 위치
		K = sc.nextInt();	//동생 위치
		
		Deque<Integer> dq = new LinkedList<>();
		dq.add(N);
		visited[N] = 1;
		
		//bfs(while)
		while(!dq.isEmpty()) {
			int now = dq.pollFirst();
			//동생 찾았으면 종료
			if(now == K) {
				//visited[K] 반환하며 종료되는 것과 같음. visited[N] = 1 이었으므로 visited[K] - 1 이 정답.
				break;
			}
			
			//[우선순위] 순간이동 (시간포함X)
			int teleport = now * move[2];
			if(teleport >= 0 && teleport <= 100000) {
				if(visited[teleport] == 0) {
					visited[teleport] = visited[now];	//순간이동은 초 증가X
					dq.addFirst(teleport);
				}
			}//순간이동
			
			//앞,뒤 이동 (시간포함O)
			for(int i=0; i<2; i++) {
				int next = now + move[i];
				if(next >= 0 && next <= 100000) {
					if(visited[next] == 0) {
						visited[next] = visited[now] + 1;
						dq.addLast(next);
					}
				}
			}//앞,뒤 이동

		}	//bfs(while)
		
		//결과출력
		System.out.println(visited[K] - 1);
	}	//main

	
}	//class
