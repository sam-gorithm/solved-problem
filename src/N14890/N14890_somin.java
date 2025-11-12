import java.util.Scanner;

public class N14890_somin {

    static int n, l;
    static int[][] board;

    static boolean check(int[] line) {
        int idx = 0;
        int cnt = 1; 

        while (idx < n - 1) {
            int diff = line[idx + 1] - line[idx];

            // 높이 차이가 1보다 크면 설치할 수 없음  
            if (Math.abs(diff) > 1) return false;

            // 높이가 같을 경우 
            if (diff == 0) {
                cnt++;
                idx++;
            }
            // 다음 칸이 더 높을 경우 
            else if (diff == 1) {
                // l보다 작아서 경사로를 놓을 수 없으면 종료 
                if (cnt < l) return false;
                cnt = 1;
                idx++;
            }
            
            // 다음 칸이 더 낮을 경우 
            else if (diff == -1) {
                // l 길이 만큼의 칸이 없으면 설치 불가 
                if (idx + l >= n) return false;
                
                // 앞으로 올 l개의 칸이 모두 같은 높이인지 확인
                for (int i = idx + 1; i < idx + l; i++) {
                    if (line[i] != line[i + 1]) return false;
                }
                
                // 다음칸과 비교 
                idx = idx + l;
                cnt = 0;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        int ans = 0;
        int[] line = new int[n];

        // 가로
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                line[j] = board[i][j];
            }
            if (check(line)) ans++;
        }

        // 세로
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                line[j] = board[j][i];
            }
            if (check(line)) ans++;
        }

        System.out.println(ans);
    }
}