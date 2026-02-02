package N10429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N10429_geonnam {
    static char[][] map = new char[3][3];
    static int N, M;
    static boolean check; // 잘 찾아졌는지
    static boolean[][] visited = new boolean[3][3]; // 방문 배열
    static int[] dr = {-1, 1, 0, 0}; // 방향
    static int[] dc = {0, 0, -1, 1};
    static int[][] answer; // 정답 경로

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            map[i] = br.readLine().toCharArray();
        }

        check = false;
        answer = new int[2*M-1][2];

        // 시작 가능한 숫자 위치
        int[][] start = {{0, 0}, {0, 2},{1, 1},{2, 0}, {2, 2}};

        for (int i = 0; i < 5; i++) {
            int r = start[i][0];
            int c = start[i][1];

            int value = map[r][c] - '0';
            int[][] path = new int[2*M-1][2];

            path[0][0] = r;
            path[0][1] = c;

            visited[r][c] = true;
            dfs(r, c, 1, value, '+', path);
            visited[r][c] = false;

            if (check) break;
        }

        if (!check) {
            System.out.println(0);
        } else {
            System.out.println(1);
            for (int i = 0; i < 2*M-1; i++) {
                System.out.println(answer[i][0] + " " + answer[i][1]);
            }
        }
    }

    // dfs로 누적하면서 판단
    static void dfs(int r, int c, int depth, int value, char op, int[][] path) {
        if (check) return;

        if (depth == 2*M-1) {
            if (value == N) {
                check = true;
                for (int i = 0; i < depth; i++) {
                    answer[i][0] = path[i][0];
                    answer[i][1] = path[i][1];
                }
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= 3 || nc >= 3) continue;
            if (visited[nr][nc]) continue;

            visited[nr][nc] = true;

            path[depth][0] = nr;
            path[depth][1] = nc;

            char ch = map[nr][nc];

            if (depth % 2 == 1) {
                // 연산자
                dfs(nr, nc, depth + 1, value, ch, path);
            } else {
                // 숫자
                int num = ch - '0';
                int nextValue = (op == '+') ? value + num : value - num;
                dfs(nr, nc, depth + 1, nextValue, op, path);
            }

            visited[nr][nc] = false;
        }
    }
}
