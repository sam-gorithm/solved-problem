import java.util.Arrays;
import java.util.Scanner;

public class N15654_somin {

    public static int n, m;
    public static int[] num;      // 입력받은 N개의 숫자 
    public static int[] arr;      // 선택된 숫자들의 인덱스 저장 
    public static boolean[] isused;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        num = new int[n];
        isused = new boolean[n];
        arr = new int[m];

        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }

        Arrays.sort(num);
        // 백트래킹 시작 
        func(0);
        
        System.out.print(sb.toString());
    }

    public static void func(int k) {
    	// m개를 모두 택했으면 
        if (k == m) {
            for (int i = 0; i < m; i++) {
                sb.append(num[arr[i]]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            // 아직 사용하지 않았다면
            if (!isused[i]) {
                arr[k] = i;         // k번째 인덱스를 i로 선택 
                isused[i] = true;   // 사용했다고 표시
                func(k + 1);     // 다음 자리 이동
                isused[i] = false;  // 모든 경우를 탐색 후 i를 사용 가능 상태로 변경
            }
        }
    }
}