import java.util.Scanner;

public class N14888_somin {

    static int n;
    static int[] num; // 숫자 저장 배열
    static int[] cal = new int[4]; // 연산자 개수
    static int mx = Integer.MIN_VALUE; // 최대값
    static int mn = Integer.MAX_VALUE; // 최소값

    static void func(int ans, int k) {
        // n개의 숫자를 모두 사용했을 경우 최대,최소값 업데이트
        if (k == n) {
            mx = Math.max(mx, ans);
            mn = Math.min(mn, ans);
            return;
        }

        // 연산자 순회 
        for (int i = 0; i < 4; i++) {
            // 사용할 연산자가 남아있지 않으면 넘어감 
            if (cal[i] == 0) continue;

            // 연산자 사용
            cal[i]--;

            if (i == 0)       func(ans + num[k], k + 1);
            else if (i == 1)  func(ans - num[k], k + 1);
            else if (i == 2)  func(ans * num[k], k + 1);
            else if (i == 3)  func(ans / num[k], k + 1);
            
            cal[i]++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        num = new int[n]; 

        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }
        for (int i = 0; i < 4; i++) {
            cal[i] = sc.nextInt();
        }

        func(num[0], 1);

        System.out.println(mx);
        System.out.println(mn);
    }
}