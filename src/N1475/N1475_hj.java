package N1475;
import java.util.Arrays;
import java.util.Scanner;

public class N1475_hj {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 다솜이의 방 번호 N
		int N = sc.nextInt();
		int[] result = new int[9];
		
		while (N>0) {
			result[N%10==9?6:N%10]++;
			N /= 10;
		}
		result[6] = (int) Math.ceil(result[6]/2.0);
		System.out.println(Arrays.stream(result).max().orElse(0));
	}
}

