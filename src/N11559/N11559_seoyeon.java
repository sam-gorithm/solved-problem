package N11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class N11559_seoyeon {

    static int N = 12;
    static int M = 6;
    static char[][] game = new char[N][M];
    // 인접한 상하좌우 탐색
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static Deque<int[]> queue = new ArrayDeque<>();
    static boolean[][] visited;

    static List<int[]> remove = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            game[i] = line.toCharArray();
        }

        // 연쇄 수 기록
        int ans = 0;

        // 시작점 탐색
        while (true) {
            boolean isFlag = false;

            visited = new boolean[N][M];

            List<int[]> allPuyosToPop = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 방문하지 않았고 빈 공간이 아닌 경우 bfs 탐색
                    if (!visited[i][j] && game[i][j] != '.') {

                        remove.clear();

                        visited[i][j] = true;
                        remove.add(new int[] {i, j});
                        queue.add(new int[] {i, j});
                        bfs(i, j);

                        // [문제점 4] if (remove.size() >= 4) 검사가 여기서 (j 루프 안에서) 이루어져야 합니다.
                        if (remove.size() >= 4) {
                            isFlag = true;

                            allPuyosToPop.addAll(remove);
                        }
                    }
                }


            }

            if (!isFlag) {
                break;
            }

            ans++;

            for (int[] pos : allPuyosToPop) {
                game[pos[0]][pos[1]] = '.';
            }

            // 뿌요 내리기
            down();
        }
        System.out.println(ans);

    }

    private static void down() {

        for (int j = 0; j < M; j++) {
            // 뿌요 임시 저장 큐
            Deque<Character> col = new ArrayDeque<>();

            for (int i = N - 1; i >= 0; i--) {
                // 뿌요라면
                if (game[i][j] != '.') {
                    col.add(game[i][j]);

                }

            }

            for (int i = 0; i < N; i++) {
                game[i][j] = '.';
            }
            int currentRow = N - 1;

            while (!col.isEmpty()) {
                char puyo = col.remove();

                game[currentRow][j] = puyo;

                currentRow--;

            }
        }


    }

    // 같은 색+상하좌우 인접한 4개 이상
    private static void bfs(int startR, int startC) {

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int r = curr[0];
            int c = curr[1];

            // 4
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || game[nr][nc] == '.' ||
                        game[nr][nc] != game[startR][startC]) {
                    continue;
                }

                // 방문
                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc});
                remove.add(new int[] {nr, nc});


            }

        }
    }

}