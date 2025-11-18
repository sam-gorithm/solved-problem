package N14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14888_geonnam {
	static int N;
	static int[] arr;
	static int[] op;
	static int max, min;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		op = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//정수 배열
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}//연산자 배열
		
		max = Integer.MIN_VALUE;// 최댓값
		min = Integer.MAX_VALUE; //최솟값
		
		dfs(1, arr[0]); // 처음 수는 있어야 계산 가능
		System.out.println(max);
		System.out.println(min);
	}
	
	static void dfs(int idx, int result) {//순서, 결과
		//다 돌았을 때 최댓값, 최솟값 계산
		if(idx == N) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		
		for(int i=0;i<4;i++) {
			if(op[i] > 0) {
				op[i] -= 1;
				
				int value = result;
				//조건에 맞게 계산
				if(i == 0) {
					value += arr[idx];
				}else if(i == 1) {
					value -= arr[idx];
				}else if(i == 2) {
					value *= arr[idx];
				}else if(i == 3) {
					value /= arr[idx];
				}
				
				dfs(idx+1, value); //재귀
				op[i] += 1; //복구
			}
		}
	}
}
