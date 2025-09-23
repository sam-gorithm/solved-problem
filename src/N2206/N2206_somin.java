import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N, M; // 세로(N), 가로(M)
    static int[][] map; // 0 -> 길, 1 -> 벽)
    static boolean[][][] visited; 

    static int[] dr = {-1, 1, 0, 0}; 
    static int[] dc = {0, 0, -1, 1}; 

    static class Node {
        int r, c, dist; // 현재 위치 (r, c), 시작점부터의 거리 (dist)
        int broken;   

        public Node(int r, int c, int dist, int broken) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.broken = broken;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        map = new int[N][M];
        visited = new boolean[N][M][2]; 

        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0'; 
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Node> queue = new LinkedList<>();

        // 시작점 (0, 0)을 큐에 추가. 거리 1, 벽은 아직 부수지 않음 
        queue.offer(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int r = current.r;
            int c = current.c;
            int dist = current.dist;
            int broken = current.broken;
            
            // 목적지(N-1, M-1)에 도달하면 현재까지의 거리를 반환하고 종료
            if (r == N - 1 && c == M - 1) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 지도 범위를 벗어나면 다음 탐색
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }

                // 다음 위치가 길 일 경우 
                if (map[nr][nc] == 0) {
                    // 해당 위치를 방문한 적이 없다면
                    if (!visited[nr][nc][broken]) {
                        visited[nr][nc][broken] = true; // 방문 처리
                        queue.offer(new Node(nr, nc, dist + 1, broken));
                    }
                } 
                // 다음 위치가 벽 일 경우 
                else { 
                    // 아직 벽을 부순 적이 없고, 해당 위치를 방문한 적이 없다면
                    if (broken == 0 && !visited[nr][nc][1]) {
                        visited[nr][nc][1] = true;
                        // 거리를 1 늘리고 벽을 부순 상태로 큐에 추가
                        queue.offer(new Node(nr, nc, dist + 1, 1));
                    }
                }
            }
        }

        // 불가능할 경우 -1 
        return -1;
    }
}