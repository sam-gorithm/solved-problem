package N1167;

import java.util.*;
import java.io.*;

public class N1167_taeyoung {
	static int V, max, maxNode;
	static ArrayList<int[]>[] graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		V = Integer.parseInt(br.readLine());

		graph = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			while (v != -1) {
				int w = Integer.parseInt(st.nextToken());
				graph[u].add(new int[] { v, w });
				v = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[V + 1];
		visited[1] = true;
		dfs(1, 0);
		visited[1] = false;
		
		visited[maxNode] = true;
		dfs(maxNode, 0);

		System.out.println(max);
	}

	static void dfs(int u, int l) {
		if (l > max) {
			max = l;
			maxNode = u;
		}
		for (int[] next : graph[u]) {
			int v = next[0];
			int w = next[1];
			if (visited[v])
				continue;
			visited[v] = true;
			dfs(v, l + w);
			visited[v] = false;
		}
	}
}
