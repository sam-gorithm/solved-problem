import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N5427_somin {
    static int w, h, res; // w: 너비, h: 높이, res: 결과 시간

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static char[][] map;
    static Queue<int[]> queue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();

        while (T-- > 0) {
            w = sc.nextInt();
            h = sc.nextInt();
            map = new char[h][w];
            queue = new LinkedList<>();

            int x = 0, y = 0;

            for (int i = 0; i < h; i++) {
                String line = sc.next();
                for (int j = 0; j < w; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == '*') {
                        // 불의 위치 큐에 추가
                        queue.add(new int[]{i, j});
                    } else if (map[i][j] == '@') {
                        // 상근이의 위치 저장
                        x = i;
                        y = j;
                    }
                }
            }
            // 불을 모두 넣은 후 마지막으로 상근이 위치를 큐에 추가
            queue.add(new int[]{x, y});

            res = 0;

            if (bfs()) {
                sb.append(res).append("\n");
            } else {
                sb.append("IMPOSSIBLE").append("\n");
            }
        }

        System.out.print(sb);
    }

    public static boolean bfs() {
        // 더 이상 움직일 곳이 없을 때까지 반복
        while (!queue.isEmpty()) {
            res++;

            // 현재 큐의 크기만큼만 정확히 반복
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] now = queue.poll();
                char currType = map[now[0]][now[1]]; // 현재 칸의 타입 ('*' 또는 '@')

                // 4방향 탐색
                for (int j = 0; j < 4; j++) {
                    int nx = now[0] + dy[j];
                    int ny = now[1] + dx[j];

                    // 지도 경계를 벗어나는 경우
                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        // 현재 이동하는게 상근이라면 탈출
                        if (currType == '@') {
                            return true;
                        }
                        continue;
                    }

                    // 빈 공간이 아니면 이동 불가
                    if (map[nx][ny] != '.') {
                        continue;
                    }

                    // 빈 공간이면
                    map[nx][ny] = currType; // 다음 칸에 복사
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        // 큐가 비었는데 탈출하지 못하면 실패
        return false;
    }
}