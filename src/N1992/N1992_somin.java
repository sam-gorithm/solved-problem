import java.util.Scanner;

public class N1992_somin {

    public static int[][] image;
    public static StringBuilder sb = new StringBuilder();

    public static void solve(int r, int c, int n) {
        // 모두 같은 숫자인지 확인
        if (check(r, c, n)) {
            // 영역이 모두 같으면 해당 숫자 하나만 추가
            sb.append(image[r][c]);
        } else {
            // 영역이 섞여있을 경우 
            sb.append("(");

            int newN = n / 2;

            solve(r, c, newN);                // 왼쪽 위
            solve(r, c + newN, newN);         // 오른쪽 위
            solve(r + newN, c, newN);         // 왼쪽 아래
            solve(r + newN, c + newN, newN);  // 오른쪽 아래

            sb.append(")"); 
        }
    }

    public static boolean check(int r, int c, int n) {
        int value = image[r][c]; // 영역의 첫 번째 값

        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                // 다른 값이 하나라도 있으면 false 반환
                if (image[i][j] != value) {
                    return false;
                }
            }
        }
        // 모든 값이 동일하면 true 반환
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        image = new int[N][N];

        // N x N 입력받기 
        for (int i = 0; i < N; i++) {
            String line = sc.next(); 
            for (int j = 0; j < N; j++) {
                // char를 int로 
                image[i][j] = line.charAt(j) - '0';
            }
        }

        // 전체 영상에 대해 압축 시작
        solve(0, 0, N);

        System.out.println(sb.toString());

    }
}