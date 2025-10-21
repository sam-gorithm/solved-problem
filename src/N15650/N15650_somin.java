import java.util.Scanner;

public class N15650_somin {

    public static int n, m;
    public static int[] arr; // 결과를 담는 배열 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[m]; 

        // 백트래킹 함수 호출 
        func(0);
    }

    public static void func(int k) {
        //m개를 모두 선택했을 경우 
        if (k == m) {
            // 배열에 저장된 수열을 출력
            for (int i = 0; i < m; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        // 시작 지점 
        int start = 1;
        if (k != 0) {
            // 이전에 선택한 수 보다 커야 하므로 +1
            start = arr[k-1] + 1;
        }

        for (int i = start; i <= n; i++) {
            arr[k] = i;       // k번째 수를 i로 선택 
            func(k + 1);
        }
    }
}