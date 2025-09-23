package N2573;

import java.io.*;
import java.util.*;

public class N2573 {

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static int[][] iceMap;
    static boolean[][] visited;
    static Deque<Pos> queue;
    static int ansYear = 0;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 초기화
        iceMap = new int[N][M];

        // iceMap 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                iceMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ansYear = 0;

        while (true) {
            int chunkCnt = findArea();

            if (chunkCnt >= 2) {
                break;
            }
            if (chunkCnt == 0) {
                ansYear = 0;
                break;
            }

            descendingHeight();
            ansYear++;
        }

        // 정답 출력
        bw.write(ansYear+"");
        bw.flush();

    } // main

    // 빙산 녹이기
    private static void descendingHeight() {
        // TODO Auto-generated method stub
        int[][] meltMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (iceMap[i][j] > 0) {

                    for(int k=0; k<dr.length; k++) {
                        int nr= i+dr[k];
                        int nc= j+dc[k];

                        if(nr<0 || nr>=N || nc<0|| nc>=M) {
                            continue;
                        }

                        if(iceMap[nr][nc]==0) {
                            meltMap[i][j]+=1;
                        }
                    } // 인접 바다 탐색
                } // 지금이 빙산일 때
            }
        }

        // 빙하 높이 갱신
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                int height= iceMap[i][j]-=meltMap[i][j];
                if(height<0) {
                    iceMap[i][j]=0;
                }
                else {
                    iceMap[i][j]= height;
                }
            }
        }

    } // descendingHeight()

    // 현재 빙산의 영역 갯수를 반환하는 함수
    private static int findArea() {
        // TODO Auto-generated method stub
        queue= new ArrayDeque<>();

        visited= new boolean[N][M];

        // 빙산 갯수를 셀 변수
        int cnt=0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(iceMap[i][j]>0&&!visited[i][j]) {
                    cnt++;
                    queue.add(new Pos(i, j));
                    visited[i][j]= true;
                    bfs();
                }
            }
        }

        return cnt;
    } // find Area

    // 인접 영역 탐색
    private static void bfs() {
        // TODO Auto-generated method stub
        while(!queue.isEmpty()) {
            Pos curr= queue.remove();
            int r= curr.r;
            int c= curr.c;

            // 4
            for(int i=0; i<dr.length; i++) {
                int nr= r+dr[i];
                int nc= c+dc[i];

                if(nr<0 || nr>=N || nc<0|| nc>=M || visited[nr][nc]|| iceMap[nr][nc]==0) {
                    continue;
                }

                queue.add(new Pos(nr, nc));
                visited[nr][nc]= true;
            }
        }

    }

}
