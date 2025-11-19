import java.util.Scanner; 
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class N13460_somin {

    static int n, m;
    static Pair red, blue;
    static char[][] board;
    static int[][][][] dist;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class State {
        int rx, ry, bx, by;
        State(int rx, int ry, int bx, int by) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
        }
    }

    static int bfs() {
        Queue<State> q = new LinkedList<>();
        q.offer(new State(red.x, red.y, blue.x, blue.y));
        dist[red.x][red.y][blue.x][blue.y] = 0;

        while (!q.isEmpty()) {
            State cur = q.poll();
            int cnt = dist[cur.rx][cur.ry][cur.bx][cur.by];

            // 10번 넘게 탈출 못했을 경우 실패
            if (cnt >= 10) return -1;

            // 4방향으로 기울이기
            for (int i = 0; i < 4; i++) {
                int n_rx = cur.rx, n_ry = cur.ry, n_bx = cur.bx, n_by = cur.by;
                
                // 파랑 먼저 확인 
                while (board[n_bx + dx[i]][n_by + dy[i]] != '#') {
                    n_bx += dx[i];
                    n_by += dy[i];
                    if (board[n_bx][n_by] == 'O') break; // 구멍에 빠지면 멈춤
                }
                // 파랑이 탈출하면 실패 
                if (board[n_bx][n_by] == 'O') continue; 

                // 빨강 이동 
                while (board[n_rx + dx[i]][n_ry + dy[i]] != '#') {
                    n_rx += dx[i];
                    n_ry += dy[i];
                    if (board[n_rx][n_ry] == 'O') break; // 구멍에 빠지면 멈춤
                }
                
                // 빨강이 탈출하면 성공 
                if (board[n_rx][n_ry] == 'O') return cnt + 1;

                // 겹친 경우 
                if (n_rx == n_bx && n_ry == n_by) {
                    if (i == 0) { 
                        if (cur.ry < cur.by) n_ry--; else n_by--;
                    } else if (i == 1) { 
                        if (cur.rx < cur.bx) n_rx--; else n_bx--;
                    } else if (i == 2) { 
                        if (cur.ry > cur.by) n_ry++; else n_by++;
                    } else { 
                        if (cur.rx > cur.bx) n_rx++; else n_bx++;
                    }
                }

                // 방문한 적이 없거나 더 짧은 경로이면 큐에 추가
                if (dist[n_rx][n_ry][n_bx][n_by] != -1) continue;

                dist[n_rx][n_ry][n_bx][n_by] = cnt + 1;
                q.offer(new State(n_rx, n_ry, n_bx, n_by));
            }
        }
        return -1; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        m = sc.nextInt();

        board = new char[n][m];
        dist = new int[n][m][n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    Arrays.fill(dist[i][j][k], -1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
                
                // 빈 칸 으로 변경
                if (board[i][j] == 'R') {
                    red = new Pair(i, j);
                    board[i][j] = '.'; 
                } else if (board[i][j] == 'B') {
                    blue = new Pair(i, j);
                    board[i][j] = '.'; 
                }
            }
        }
        System.out.println(bfs());
    }
}