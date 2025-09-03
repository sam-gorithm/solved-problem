package N1475;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N1475_geonnam {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//9랑 6은 뒤집어서 사용 가능
		String num = br.readLine();
		int[] arr = new int[9]; // 0~8까지 담을 배열 , 6이랑 9는 같이 담을 것
		int idx = 0;
		int max = Integer.MIN_VALUE;
		
		for(int i=0;i<num.length();i++) {
			int c = num.charAt(i)-'0'; // 문자형이므로 문자0을 빼줘야 숫자가 나온다
			if(c==6 || c==9) {
				arr[6]++; //6이나 9가 나올때 한 곳에 담는다.
			}else {
				arr[c]++;
			}
		}
		
		for(int i=0;i<9;i++) { //arr[6]을 제외하고 최댓값
			if(i==6) {
				continue;
			}
			if(max < arr[i]) {
				max = arr[i];
				idx = i;
			}
		}
		
		//세트 수 비교해서 더 큰 값 출력
		if(arr[6] %2 == 0) {
			if(arr[6]/2 >= arr[idx]) { 
				System.out.println(arr[6]/2);
			}else {
				System.out.println(arr[idx]);
			}
		}else {//홀수면 int형이라 소숫점이 버려지므로 +1
			if(arr[6]/2+1 >= arr[idx]) {
				System.out.println(arr[6]/2+1);
			}else {
				System.out.println(arr[idx]);
			}
		}
	}
}
