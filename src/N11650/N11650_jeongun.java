import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class N11650_jeongun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] p = new int[N][2];

        int i;
        for (i = 0; i < N; i++) {
            p[i][0] = sc.nextInt();
            p[i][1] = sc.nextInt();
        }

        Arrays.sort(p, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] > b[0]) return 1;
                else if (a[0] < b[0]) return -1;
                else {
                    if (a[1] > b[1]) return 1;
                    else if (a[1] < b[1]) return -1;
                    else return 0;
                }
            }
        });
        for (i = 0; i < N; i++) {
            System.out.println(p[i][0] + " " + p[i][1]);
        }
    }
}
