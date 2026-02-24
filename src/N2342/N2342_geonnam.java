package N2342;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N2342_geonnam {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //배열 길이 파악하고 저장
        int length = st.countTokens();
        int[] arr = new int[length-1];
        
        for(int i=0;i<length-1;i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        /* 위치별로 배열 저장 [왼][오]
         *     1
         * 2   0   4
         *     3
         */
        int[][] dp = new int[5][5];
        
        // dp 배열 초기화
        for(int i=0;i<5;i++) {
        	Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        
        for(int i=0;i<arr.length;i++) {
        	//이동 위치
        	int x = arr[i];
        	
        	//value 배열 무한대로 초기화
        	int[][] value = new int[5][5];
        	for(int a=0;a<5;a++) {
            	Arrays.fill(value[a], Integer.MAX_VALUE);
            }
        	
        	for(int l=0;l<5;l++) {
        		for(int r=0;r<5;r++) {
        			//도달하지 못한 곳
        			if(dp[l][r] == Integer.MAX_VALUE) continue;
        			
        			//왼발 이동
        			value[x][r] = Math.min(value[x][r], dp[l][r] + cost(l,x));
        			
        			//오른발 이동
        			value[l][x] = Math.min(value[l][x], dp[l][r] + cost(r,x));
        		}
        	}
        	dp = value; //dp 업데이트
        }
        //제일 작은 값 찾기
        int result = Integer.MAX_VALUE;
        for (int l=0; l<5; l++) {
            for (int r=0; r<5; r++) {
                result = Math.min(result, dp[l][r]);
            }
        }
        System.out.println(result);
    }
    //비용 계산
    static int cost(int x, int y) {
    	//중앙에서 움직일 때
    	if(x == 0) {
    		return 2;
    	}
    	//같은 자리에서 다시 밟을 때
    	else if(x == y) {
    		return 1;
    	}
    	else {
    		//반대편
    		if(Math.abs(x-y) == 2) return 4;
    		//인접
    		else return 3;
    	}
    }
}
