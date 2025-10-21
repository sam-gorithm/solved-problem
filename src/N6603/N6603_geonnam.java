package N6603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N6603_geonnam {
	static int N;
	static int[] arr, sel;
	
	static StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = 1; // N이 처음에 0으로 설정되어 있으므로
		while(N != 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new int[N]; //집합
			sel = new int[6]; //뽑은 번호
			
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			comb(0,0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void comb(int idx, int length) {
		if(length == 6) { //6개의 번호가 다 뽑힐때
			for(int i=0;i<6;i++) {
				sb.append(sel[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=idx;i<N;i++) { //조합이므로 idx부터 시작
			sel[length] = arr[i];
			comb(i+1, length+1);
		}
	}
}
