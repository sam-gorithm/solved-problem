import java.util.Scanner;

public class N10814_jeongun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int max = 200;

        //사람 저장
        String[][] arr = new String[max][N];
        int[] count = new int[max];

        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            String str = sc.next();

            arr[num][count[num]] = str;
            count[num]++; 
        }

        for (int i = 1; i < max; i++) {
            for (int j = 0; j < count[i]; j++) {
                System.out.println(i + " " + arr[i][j]);
            }
        }
    }
}