import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
 
public class N10814_somin {
	public static void main(String[] args) {		
 
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		String[][] arr = new String[N][2];
		
 
		for(int i = 0; i < N; i++) {
			arr[i][0] = in.next();	// 나이
			arr[i][1] = in.next();	// 이름
		}
 
		
		Arrays.sort(arr, new Comparator<String[]>() {
			public int compare(String[] s1, String[] s2) {
				return Integer.parseInt(s1[0]) - Integer.parseInt(s2[0]);
			}
			
		});
		
		for(int i = 0; i < N; i++) {
			System.out.println(arr[i][0] + " " + arr[i][1]);
		}
        
		
	}
 
}