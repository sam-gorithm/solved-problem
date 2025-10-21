package N14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14891_seoyeon {

    static String[] board = new String[4];

    // x : 번호
    // dir : 방향, 1번의 회전을 처리하는 함수
    static void go(int x, int dir) {
        int[] dirs = new int[4];
        dirs[x] = dir;

        // 왼쪽으로 회전
        int idx = x;
        while (idx > 0 && board[idx].charAt(6) != board[idx - 1].charAt(2)) {
            dirs[idx - 1] = -dirs[idx];
            idx--;
        }

        // 오른쪽으로 회전
        idx = x;
        while (idx < 3 && board[idx].charAt(2) != board[idx + 1].charAt(6)) {
            dirs[idx + 1] = -dirs[idx];
            idx++;
        }

        for (int i = 0; i < 4; i++) {
            if (dirs[i] == -1) { // 반시계 방향
                board[i] = board[i].substring(1) + board[i].charAt(0);
            } else if (dirs[i] == 1) { // 시계 방향
                board[i] = board[i].charAt(7) + board[i].substring(0, 7);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            board[i] = br.readLine();
        }

        int k = Integer.parseInt(br.readLine());

        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            go(x - 1, dir);
        }

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            if (board[i].charAt(0) == '1') {
                ans += (1 << i);
            }
        }
        System.out.println(ans);
    }
}
