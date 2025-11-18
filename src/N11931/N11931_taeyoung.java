package N11931;

import java.util.*;
import java.io.*;

public class N11931_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(A); // 오름차순 정렬
		
		StringBuilder sb = new StringBuilder();
		
		// 내림차순 -> 뒤에서부터
		for (int i = N -1; i >= 0; i--) {
			sb.append(A[i]).append("\n"); 
		}
		
		System.out.println(sb);
	}

}
