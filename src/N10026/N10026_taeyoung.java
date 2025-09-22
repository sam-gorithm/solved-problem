package N10026;

import java.io.*;
import java.util.*;

public class N10026_taeyoung {
    static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        char[][] A1 = new char[N][N]; // R, G, B 배열
        char[][] A2 = new char[N][N]; // 적록색약 -> R + G, B 배열

        for (int i = 0; i < N; i++) {
            String S = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = S.charAt(j);
                A1[i][j] = c; // A1에는 컬러 그대로 입력
                if (c == 'G') { // G를 R로 통일
                    c = 'R';
                }
                A2[i][j] = c; // A2에는 바꾼 컬러 입력
            }
        }
        System.out.println(count(A1) + " " + count(A2));
    }

    static int count(char[][] A) { // 구역의 수를 구하기 위한 함수
        int cnt = 0; // 구역의 수
        boolean[][] checked = new boolean[N][N]; // 방문 체크 배열
        Queue<int[]> Q = new ArrayDeque<>(); // BFS를 위한 큐

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!checked[i][j]) { // 아직 확인하지 않은 위치일때
                    cnt++; // 새로운 구역 -> 1 증가
                    char now = A[i][j]; // 현재 색
                    Q.offer(new int[] { i, j }); // 현재 위치 큐에 넣기
                    checked[i][j] = true;
                    while (!Q.isEmpty()) { // 큐가 빌때까지는 계속 같은 색
                        int[] tmp = Q.poll();
                        int r = tmp[0];
                        int c = tmp[1];
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dir[k][0];
                            int nc = c + dir[k][1];
                            // 인덱스 벗어나면 넘어가기
                            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                                continue;
                            }
                            // 이미 확인한 위치 넘어가기
                            if (checked[nr][nc]) {
                                continue;
                            }
                            // 다음 위치가 같은 색이라면
                            if (A[nr][nc] == now) {
                                Q.offer(new int[] { nr, nc }); // 다음 위치 큐에 넣기
                                checked[nr][nc] = true; // 방문 체크
                            }
                        }
                    }
                }
            }
        }
        return cnt; // 구역의 수 리턴
    }
}
