package N2577;
import java.util.Scanner;

public class N2577_jinhyuk {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//a, b, c 입력
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		//0 ~ 9 까지 카운팅 횟수를 담을 배열
		int[] arr = new int[10];
		//a, b, c를 곱한 수, 
		int num = a * b * c;
		//num을 String 으로 변환
		String str = String.valueOf(num);
		
		//자릿수별 숫자의 카운팅 횟수 계산
		for(int i=0; i<str.length(); i++) {
			int digit = str.charAt(i) - '0';
			arr[digit]++;
		}
		
		//결과 출력
		for(int i=0; i<10; i++) {
			System.out.println(arr[i]);
		}
		
		
	}	//main
}
