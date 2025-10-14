import java.util.Scanner;

public class N15652_somin {

    public static int n, m;
    public static int[] arr;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[m];

        func(0);
        
        System.out.print(sb.toString());
    }

    public static void func(int k) {
    	// m개를 모두 택했으면
        if (k == m) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 시작 지점
        int start = 1;
        if (k != 0) {
            start = arr[k-1];
        }

        for (int i = start; i <= n; i++) {
            arr[k] = i;  // k번째 수를 i로 선택 
            func(k + 1); 
        }
    }
}