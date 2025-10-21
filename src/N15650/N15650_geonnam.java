package N15650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N15650_geonnam {
	static int N,M;
	static int[] arr, sel;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N]; // 1~N까지를 담을 배열
		sel = new int[M]; // 조합 배열
		
		for(int i=0;i<N;i++) {
			arr[i] = i+1;
		} // 1~N 채우기
		
		comb(0,0);
		
		System.out.println(sb);
	}
	
	static void comb(int idx, int sidx) {
		if(sidx == M) { // M개를 채우면 sb에 넣고 return
			for(int i=0;i<M;i++) {
				sb.append(sel[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=idx;i<N;i++) {
			sel[sidx] = arr[i]; // 선택 배열에 추가 
			comb(i+1, sidx+1); //선택했으면 다음 칸을 채우러 재귀
		}
	}
}
