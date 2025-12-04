import java.util.Arrays;
import java.util.Scanner;

public class N5648_jeongun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long[] arr = new long[N];
		
		for(int i = 0; i < N; i++) {
			String str = sc.next();
            String s = new StringBuilder(str).reverse().toString();
            arr[i] = Long.parseLong(s);
		}
		Arrays.sort(arr);
		
		for(int i = 0; i < N; i++) {
			System.out.println(arr[i]);
		}
	}
}