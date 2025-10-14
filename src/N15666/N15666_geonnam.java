package N15666;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N15666_geonnam {
	static int N, M;
	static int[] arr, sel;
	
	static StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N]; // 수가 들어있는 배열
		sel = new int[M]; // 선택 배열
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//배열 채우기

		Arrays.sort(arr); //사전순 출력을 위해 정렬
		comb(0, 0);
		System.out.println(sb);
	}
	
	static void comb(int idx, int length) {
		if(length == M) { //M까지 채워지면 sb에 넣고 return
			for(int i=0;i<M;i++) {
				sb.append(sel[i]).append(" "); //출력 형식 맞추기
			}
			sb.append("\n");
			return;
		}
		
		int same = Integer.MAX_VALUE; //같은 칸에 같은 숫자가 들어가는 걸 확인하는 변수
		
		for(int i=idx;i<N;i++) {
			if(same != arr[i]) {
				same = arr[i];
				sel[length] = arr[i];
				comb(i, length+1);
			}
		}
	}
}
