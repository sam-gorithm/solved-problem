package N5582;

import java.util.*;
import java.io.*;

public class N5582_taeyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 문자열
		String a = br.readLine();
		String b = br.readLine();

		// 문자열을 배열로 변환
		char[] A = a.toCharArray();
		char[] B = b.toCharArray();

		// dp[i][j] : 문자열 A의 i번째 문자까지, B의 j번째 문자까지 고려했을 때 공통 부분 문자열 길이
		int[][] dp = new int[A.length + 1][B.length + 1];

		int ans = 0;

		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= B.length; j++) {
				if (A[i - 1] == B[j - 1]) { // 새로 확인할 글자가 같으면
					dp[i][j] = dp[i - 1][j - 1] + 1; // 이전 공통 부분 문자열 길이 + 1
					ans = Math.max(ans, dp[i][j]); // 정답 갱신
				} 
				else
					dp[i][j] = 0;
			}
		}
		System.out.println(ans);
	}

}
