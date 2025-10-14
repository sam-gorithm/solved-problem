package N15652;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N15652_geonnam {
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
		
		dcomb(0, 0);
		
		System.out.println(sb);
	}
	
	static void dcomb(int idx, int length) {
		if(length == M) { //M까지 채워지면 sb에 넣고 return
			for(int i=0;i<M;i++) {
				sb.append(sel[i]).append(" "); //출력 형식 맞추기
			}
			sb.append("\n");
			return;
		}
		
		for(int i=idx;i<N;i++) {
			sel[length] = arr[i];
			dcomb(i, length+1); //중복 조합이니까 i를 데려간다.
		}
	}
}
