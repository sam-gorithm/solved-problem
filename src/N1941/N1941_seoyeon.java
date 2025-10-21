package N1941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class N1941_seoyeon {
    static char[][] board = new char[5][5];
    static int ans = 0;
    static int[] combination = new int[7]; // 7명의 위치를 1차원 인덱스로 저장할 배열
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 25명 중에서 7명을 뽑는 모든 조합
        generateCombinations(0, 0);

        System.out.println(ans);
    }

    // 백트래킹
    public static void generateCombinations(int count, int start) {
        // base condition
        if (count == 7) {
            checkConditions();
            return;
        }

        for (int i = start; i < 25; i++) {
            combination[count] = i;
            generateCombinations(count + 1, i + 1);
        }
    }

    // 생성된 조합이 칠공주의 조건을 만족하는지 확인하는 함수
    public static void checkConditions() {
        boolean[][] isP7 = new boolean[5][5]; // 7명에 해당하는지 표시하는 2차원 배열
        int dasomCount = 0;
        int startX = -1, startY = -1;

        for (int i = 0; i < 7; i++) {
            int pos = combination[i];
            int x = pos / 5;
            int y = pos % 5;
            isP7[x][y] = true;

            if (board[x][y] == 'S') {
                dasomCount++;
            }

            // BFS 시작
            if (startX == -1) {
                startX = x;
                startY = y;
            }
        }

        // 이다솜파가 4명 이상인지 확인
        if (dasomCount < 4) {
            return;
        }

        // 7명이 모두 인접해 있는지 BFS로 확인
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];

        q.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        int adjCount = 1; // 인접한 사람의 수

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int curX = current[0];
            int curY = current[1];

            for (int k = 0; k < 4; k++) {
                int nx = curX + dx[k];
                int ny = curY + dy[k];

                // 범위를 벗어나거나, 이미 방문했거나, 7명에 속하지 않으면 건너뜀
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny] || !isP7[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                adjCount++;
            }
        }

        // BFS 결과, 연결된 사람이 7명이면 조건을 만족
        if (adjCount == 7) {
            ans++;
        }
    }
}