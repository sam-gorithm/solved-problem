package N2577;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N2577_hj {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 세 개의 자연수 A,B,C
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		int multiple = A*B*C;
		List<Integer> list = new ArrayList<>();
		
		while(multiple>0) {
			list.add(multiple%10);
			multiple = multiple/10;	// 소수점 이하 버림
		}
		
		int[] result = new int[10];
		
		for (int i = 0; i < list.size(); i++) {
			result[list.get(i)]++;
		}
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

}
