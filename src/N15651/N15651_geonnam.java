package N15651;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N15651_geonnam {
	static int N, M;
	static int[] arr, sel;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		sel = new int[M];
		
		for(int i=0;i<N;i++) {
			arr[i] = i+1;
		} // 1~N 채우기
		
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
			sel[length] = arr[i]; //중복이 가능하므로 선택할 칸에 arr[0]부터 넣게 한다.
			perm(length+1);
		}
	}
}
