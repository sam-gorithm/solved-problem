import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class N11559_somin {

    static final int R = 12; // 행의 수
    static final int C = 6;  // 열의 수
    static char[][] field = new char[R][C]; // 뿌요 필드
    static boolean[][] visited; // 방문 여부 체크 배열
    static int[] dr = {-1, 1, 0, 0}; 
    static int[] dc = {0, 0, -1, 1}; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < R; i++) {
            field[i] = sc.next().toCharArray();
        }

        int count = 0; // 연쇄 횟수 저장

        while (true) {
            boolean popped = popPuyos();
            
            //터진 뿌요들이 있다면 
            if (popped) {
                count++; 
                applyGravity();   // 중력의 영향을 받아 아래로 떨어짐 
            } else {
                // 터진 뿌요가 없다면 종료
                break;
            }
        }

        System.out.println(count);
    }

    public static boolean popPuyos() {
        visited = new boolean[R][C];
        boolean hasPopped = false; // 터진 그룹이 있는지 체크

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 뿌요가 있고 아직 방문하지 않았을 경우 
                if (field[i][j] != '.' && !visited[i][j]) {
                    
                    // BFS
                    List<int[]> group = new ArrayList<>(); 
                    Queue<int[]> queue = new LinkedList<>(); // BFS를 위한 큐

                    // BFS 시작점
                    visited[i][j] = true;
                    queue.add(new int[]{i, j});
                    group.add(new int[]{i, j});

                    // 큐가 빌 때까지 
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int r = current[0]; 
                        int c = current[1]; 
                        char color = field[r][c]; // 현재 뿌요의 색깔

                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k]; 
                            int nc = c + dc[k]; 

                            // 필드 범위 내에 있고 방문하지 않았으며 현재 뿌요와 색이 같은지 확인
                            if (nr >= 0 && nr < R && nc >= 0 && nc < C &&
                                !visited[nr][nc] && field[nr][nc] == color) {
                                visited[nr][nc] = true;
                                queue.add(new int[]{nr, nc}); 
                                group.add(new int[]{nr, nc}); 
                            }
                        }
                    }

                    // BFS 탐색 후 그룹의 크기가 4 이상이면
                    if (group.size() >= 4) {
                        hasPopped = true; // 터졌다고 표시
                        // 포함된 모든 뿌요를 빈칸으로 변경
                        for (int[] puyo : group) {
                            field[puyo[0]][puyo[1]] = '.';
                        }
                    }
                }
            }
        }
        return hasPopped;
    }

    public static void applyGravity() {
        for (int j = 0; j < C; j++) {

            int emptyRow = R - 1; 
            
            for (int i = R - 1; i >= 0; i--) {
                // 만약 현재 칸에 뿌요가 있다면
                if (field[i][j] != '.') {
                    char temp = field[i][j]; // 현재 뿌요를 임시 저장
                    field[i][j] = '.';       // 원래 위치는 빈칸으로 
                    field[emptyRow][j] = temp; // 채워 넣을 가장 아래 빈칸으로 뿌요를 이동
                    emptyRow--;           
                }
            }
        }
    }
}