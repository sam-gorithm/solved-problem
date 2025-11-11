package N14502;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

//	연구소 / 골드 4 / 312ms
public class N14502_jinhyuk {
	//static
    static int N, M;
    static int[][] map;
    static int max = 0;
    //4방향탐색 (상하좌우)
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    //Point
    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }	//Point

    
    //main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();	//N x M
        M = sc.nextInt();
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }//입력

        //벽세우기
        wall(0, 0);

        //결과출력
        System.out.println(max);
        
    }	//main

    
    //wall
    public static void wall(int wallCount, int start) {

    	//기저조건
        if (wallCount == 3) {
        	//bfs로 안전영역 구하고 종료
            bfs();
            return; 
        }

        //재귀파트
        for (int k = start; k < N * M; k++) {
        	//start부터 세워서 중복 방지
            int i = k / M;
            int j = k % M;

            if (map[i][j] == 0) {
                map[i][j] = 1;	//벽안세웠으면 세우기
                wall(wallCount + 1, k + 1);	//다음 벽 세우기 재귀
                map[i][j] = 0;	//다음 조합위해 초기화
            }
        }	//재귀파트
        
    }	//wall

    
    //bfs : 바이러스 퍼지지 않은 안전영역 탐색
    public static void bfs() {
    	
        int[][] tempMap = new int[N][M];	//벽 조합에 영향안가게 임시맵 생성해서 bfs
        Queue<Point> queue = new LinkedList<>();	//바이러스용 큐

        //원본맵 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempMap[i][j] = map[i][j];
                if (tempMap[i][j] == 2) {
                    //바이러스 큐에 추가
                    queue.add(new Point(i, j));
                }
            }
        }//원본맵 복사

        //바이러스 퍼뜨리기
        while (!queue.isEmpty()) {
        	
            Point now = queue.poll();
            //4방향탐색
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                //유효범위체크
                if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                	//빈 칸이면 바이러스로
                    if (tempMap[nr][nc] == 0) {
                        tempMap[nr][nc] = 2;
                        queue.add(new Point(nr, nc));
                    }
                }
            }//for
        }//바이러스 퍼뜨리기

        //안전영역 세기
        int safearea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempMap[i][j] == 0) {
                    safearea++;
                }
            }
        }

        //최대안전영역 갱신
        max = Math.max(max, safearea);
    }	//bfs
       
}	//class