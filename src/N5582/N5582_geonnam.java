package N5582;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N5582_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//문자열 입력 받기
		String line1 = br.readLine();
		String line2 = br.readLine();
		
		int[][] dp = new int[line1.length()][line2.length()];
		
		int result = 0; // 가장 긴 문자열 길이 저장
		
		for(int i=0;i<line1.length();i++) {
			for(int j=0;j<line2.length();j++) {
				if(line1.charAt(i) == line2.charAt(j)) {
					if(i>=1 && j>=1) {
						dp[i][j] = dp[i-1][j-1]+1;
					}else {
						dp[i][j] = 1;
					}
				}
				result = Math.max(result, dp[i][j]);
			}
		}
		System.out.println(result);
	}
}
