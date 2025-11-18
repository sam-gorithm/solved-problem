package N14500;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class N14500_seoyeon {
    // 지도 크기
    static int N, M;
    // 게임 배열
    static int[][] game = new int[510][510];
    // 테트로미노 합 가질 변수, 가장 큰 합을 저장할 변수
    static int sum, maxSum;
    // 방문 배열
    static boolean[][] visited = new boolean[510][510];
    // 방향 배열
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                game[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 방문 처리
                visited[i][j] = true;
                dfs(i, j, 1, game[i][j]);
                visited[i][j] = false;
                checkT(i, j);
            }
        }

        bw.write(Integer.toString(maxSum));
        bw.flush();
        bw.close();
        br.close();

    }

    // T 모양을 검사하는 함수
    private static void checkT(int r, int c) {
        //ㅜ
        if (r >= 0 && r + 1 < N && c - 1 >= 0 && c + 1 < M) {
            int sum = game[r][c] + game[r + 1][c - 1] + game[r + 1][c] + game[r + 1][c + 1];
            if (maxSum < sum) {
                maxSum = sum;
            }
        }
        //ㅗ
        if (r + 1 < N && c - 1 >= 0 && c + 1 < M) {
            sum = game[r][c] + game[r + 1][c] + game[r][c - 1] + game[r][c + 1];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        // ㅓ
        if (r - 1 >= 0 && r + 1 < N && c - 1 >= 0) {
            sum = game[r][c] + game[r - 1][c] + game[r + 1][c] + game[r][c - 1];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        // ㅏ
        if (r - 1 >= 0 && r + 1 < N && c + 1 < M) {
            sum = game[r][c] + game[r - 1][c] + game[r + 1][c] + game[r][c + 1];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
    }

    private static void dfs(int r, int c, int depth, int currSum) {
        // base condition
        if (depth == 4) {
            if (currSum > maxSum) {
                maxSum = currSum;
            }
            return;
        }

        // 탐색
        for (int i = 0; i < dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 배열 범위 초과 확인, 방문 체크
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
                continue;
            }

            visited[nr][nc] = true;
            dfs(nr, nc, depth + 1, currSum + game[nr][nc]);
            // 백트래킹
            visited[nr][nc] = false;

        }
    }

}