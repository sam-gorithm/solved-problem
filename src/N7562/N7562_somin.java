import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N7562_somin {

    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            int l = sc.nextInt(); // 체스판 한 변의 길이
            int X1 = sc.nextInt(); // 시작점
            int Y1 = sc.nextInt();
            int X2 = sc.nextInt(); // 목표점
            int Y2 = sc.nextInt();

            // 시작점과 목표점이 같으면 0 출력
            if (X1 == X2 && Y1 == Y2) {
                System.out.println(0);
                continue;
            }

            // 최소 이동 횟수를 저장하는 배열
            int[][] dist = new int[l][l];
            // -1로 초기화 -> 방문하지 않음
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < l; j++) {
                    dist[i][j] = -1;
                }
            }

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{X1, Y1});
            dist[X1][Y1] = 0;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                for (int k = 0; k < 8; k++) {
                    int nx = cur[0] + dx[k];
                    int ny = cur[1] + dy[k];

                    // 범위 확인
                    if (nx >= 0 && nx < l && ny >= 0 && ny < l) {
                        // 방문하지 않은 칸인지 확인
                        if (dist[nx][ny] == -1) {
                            // 거리를 1 증가시켜 저장
                            dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                            queue.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
            System.out.println(dist[X2][Y2]);
        }
    }
}