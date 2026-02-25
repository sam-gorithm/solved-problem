package N2668;

import java.util.*;
import java.io.*;

public class N2668_taeyoung {
	static int N;
	static int[] A; // A[i] : i에서 다음으로 가는 노드
	// visited[i] : 전체 DFS 과정에서 i를 한 번이라도 방문했는지
	// finished[i] : i에 대한 DFS 처리가 완전히 끝났는지
	static boolean[] visited, finished;
	static List<Integer> ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		A = new int[N + 1];
		visited = new boolean[N + 1];
		finished = new boolean[N + 1];
		ans = new ArrayList<>();
		
		for (int i = 1; i <= N ; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		// 모든 정점에서 시작
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				dfs(i);
			}
		}
		
		Collections.sort(ans);
		
		StringBuilder sb = new StringBuilder();
		sb.append(ans.size()).append("\n");
        for (int x : ans) sb.append(x).append("\n");
        System.out.print(sb);
	}
	
	static void dfs(int u) {
		visited[u] = true; // u를 현재 DFS에서 방문 처리
		int v = A[u]; // u -> v
		
		// 아직 안 간 노드면 계속 따라간다
		if (!visited[v]) {
			dfs(v);
		}
		// visited[v] == true 이면서 finished[v] == false 
		// => v가 현재 DFS 경로 안에 있다
		else if (!finished[v]) {
			int cur = v;
			// 이때 사이클에 포함되는 노드만 ans에 담아야 함
			while (cur != u) {
				ans.add(cur);
				cur = A[cur];
			}
			ans.add(u);
		}
		// u의 DFS가 종료됨
		finished[u] = true;
	}
}

