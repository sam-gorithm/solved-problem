package N2577;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N2577_geonnam {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//숫자 3개 입력받기
		int n1 = Integer.parseInt(br.readLine());
		int n2 = Integer.parseInt(br.readLine());
		int n3 = Integer.parseInt(br.readLine());
		
		int mul = n1*n2*n3; // 숫자 3개 곱하기
		int[] arr = new int[10]; // 몇 번 쓰였는지 저장할 배열
		
		// 숫자를 하나씩만 받기 위해 mul을 문자열로 변환
		String multiple = Integer.toString(mul); 
		
		for(int i=0;i<multiple.length();i++) {
			for(int j=0;j<10;j++) { // 0~9까지 확인
				//j가 아스키코드 0~9이므로 숫자로 인식시키기 위해 '0'을 더해줌
				if(multiple.charAt(i) == (char)j + '0') { 
					arr[j]++;
				}
			}
		}
		for (int i : arr) {
			System.out.println(i);
		}
	}
}
