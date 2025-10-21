package N15654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N15654_geonnam {
	static int N, M;
	static int[] arr, sel;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N]; // 수가 들어있는 배열
		sel = new int[M]; // 선택 배열
		visited = new boolean[N]; //방문 체크 배열
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//배열 채우기
		Arrays.sort(arr); //사전순 출력을 위해 정렬
		
		perm(0);
		
		System.out.println(sb);
	}
	
	static void perm(int length) {
		if(length == M) { //M까지 채워지면 sb에 넣고 return
			for(int i=0;i<M;i++) {
				sb.append(sel[i]).append(" "); //출력 형식 맞추기
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(!visited[i]) { //방문 안 했다면 방문
				visited[i] = true; 
				sel[length] = arr[i]; 
				perm(length+1);
				visited[i] = false; //방문 처리 되돌리기
			}
		}
	}
}
