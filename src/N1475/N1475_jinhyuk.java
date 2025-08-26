package N1475;
import java.util.Arrays;
import java.util.Scanner;

public class N1475_jinhyuk {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//입력받은 숫자를 문자열로 변환
		String str = sc.next();
		
		//0 ~ 9 까지 카운팅할 배열
		int[] arr = new int[10];
		
		//0 ~ 9 까지 횟수 카운팅
		for(int i=0; i<str.length(); i++) {
			int digit = str.charAt(i) - '0';
			
			//arr[9]는 0인 상태가 유지되도록 하고, 6이 나온 횟수로 합치기
			if(digit == 9) {
				digit = 6;
			}
			
			//해당 숫자 카운팅 배열 증가
			arr[digit]++;
		}
		
		// ex) arr[6] = 5 -> 3세트가 필요함 ( 5/2 = 2, 5%2 = 1 -> 2 + 1 = 3)
		arr[6] = (arr[6] / 2) + (arr[6] % 2);
		// 0 ~ 9까지 오름차순 정렬
		Arrays.sort(arr);
		
		//제일 많이 있는 숫자만큼 세트가 필요하다 (1세트에 숫자 1개씩 존재)
		System.out.println(arr[9]);
		
		
	}	//main

}
