package N9466;

import java.util.Scanner;

//	텀 프로젝트 / 골드 3 / 3716ms
public class N9466_jinhyuk {
	//static
	static int N;				//학생수
	static int[] graph;			//선택결과 (index : n번 학생 / value : 선택한 학생)
	static boolean[] visited;	//사이클 구성여부
	static boolean[] teamed;	//팀 구성여부
	static int team;			//팀을 이룬 학생 수
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		//tc
		for(int tc=1; tc<=T; tc++) {
			
			N = sc.nextInt();
			
			//초기화
			graph = new int[N+1];		//1 ~ N
			visited = new boolean[N+1];
			teamed = new boolean[N+1];
			team = 0;
			
			for(int i=1; i<=N; i++) {
				graph[i] = sc.nextInt();	//유향
			}//팀원선택
			
			for(int i=1; i<=N; i++) {
				//1번 학생부터 dfs
				if (!visited[i]) {
					dfs(i);
				}
			}
			
			//결과출력 (팀에 속하지 못한 학생 수)
			System.out.println(N - team);
		}	//tc
		
	}	//main


	//dfs (재귀)
	private static void dfs(int now) {
		visited[now] = true;
		int next = graph[now];
		
		//사이클X(!visited) -> 사이클 시작 (재귀)
		if(!visited[next]) {
			dfs(next);
		}
		//사이클O(visited)/팀X -> 사이클 완성 (기저조건)
		else if (!teamed[next]) {
			//사이클 이룬 학생수 세기
			int curr = next;
			while(true) {
				team++;
				curr = graph[curr];
				if(curr == next) {
					break;
				}
			}
		}
		
		//해당 학생에 대해 팀 구성여부 체크 완료
		teamed[now] = true;
	}	//dfs

	
}	//class
