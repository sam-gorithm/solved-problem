import java.util.Arrays;
import java.util.Scanner;

public class N7795_jeongun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] arr1 = new int[N]; //A
            int[] arr2 = new int[M]; //B

            for (int i = 0; i < N; i++) {
            	arr1[i] = sc.nextInt();
            }
            
            for (int j = 0; j < M; j++) { arr2[j] = sc.nextInt();
            }
            Arrays.sort(arr1);
            Arrays.sort(arr2);

            int result = 0;
            int count = 0;

            for (int i = 0; i < N; i++) {
                while (count < M && arr2[count] < arr1[i]) {
                	 //B에서 더 작은 애들 count++
                    count++;
                }
                result += count;
            }

            System.out.println(result);
        }
    }
}
