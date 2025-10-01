import java.util.Scanner;

public class N1780_somin {

    static int[][] paper;
    static int[] counts = new int[3];

    public static boolean check(int x, int y, int n) {
        int first = paper[x][y]; // 영역의 첫 번째 값
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                // 첫 번째 값과 다른 값이 하나라도 있으면 false 
                if (paper[i][j] != first) {
                    return false;
                }
            }
        }
        // 모든 값이 동일하면 true 반환
        return true;
    }

    public static void solve(int x, int y, int n) {
        // 모두 같은 수인지 확인 
        if (check(x, y, n)) {
            counts[paper[x][y] + 1]++;
        } 
        // 같지 않을 경우 
        else {
            int newN = n / 3; // 한 변의 길이는 1/3
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // 함수 호출
                    solve(x + i * newN, y + j * newN, newN);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        paper = new int[N][N];

        // N x N 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                paper[i][j] = sc.nextInt();
            }
        }

        // 재귀 시작
        solve(0, 0, N);

        System.out.println(counts[0]); // -1
        System.out.println(counts[1]); // 0
        System.out.println(counts[2]); // 1
        
    }
}