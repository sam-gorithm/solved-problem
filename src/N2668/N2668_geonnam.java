package N2668;

import java.io.*;
import java.util.*;

public class N2668_geonnam {
	static int N; 
	static int[] arr;
	static boolean[] visited, finished;
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine()); //배열 크기
		arr = new int[N+1]; //인덱스도 파악하기 위해서 1부터 저장
		list = new ArrayList<>(); //정답 저장 리스트
		
		for(int i=1;i<N+1;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}//배열 채우기
		
		visited = new boolean[N+1]; //방문 체크
		finished = new boolean[N+1]; //사이클 돌았는지 체크
		
		for(int i=1; i<N+1;i++) {
			if(!visited[i]) cycle(i);
		}
		
		//출력 형식 맞추기
		Collections.sort(list);
		sb.append(list.size());
		for (int v : list) {
			sb.append("\n").append(v);
		}
		System.out.println(sb);
	}
	
	//사이클이 일어나는 곳을 정답 리스트에 추가한다.
	private static void cycle(int cur) {
		//방문 처리
		visited[cur] = true;
		int next = arr[cur];
		
		//다음에 갈 곳을 방문하지 않았다면 거기로 들어간다.
		if(!visited[next]) {
			cycle(next);
		}
		//방문은 했는데 사이클 추가 안 한 경우
		else if(!finished[next]) {
			//새로운 사이클 발견
			int current = next;
			list.add(current);
			
			// 사이클 이어진 곳 정답 배열에 넣기
			while(arr[current] != next) {
				current = arr[current];
				list.add(current);
			}
		}
		finished[cur] = true;
	}
}
