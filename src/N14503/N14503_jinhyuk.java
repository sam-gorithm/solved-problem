package N14503;

import java.util.Scanner;

//	로봇 청소기 / 골드 5 / 136ms
public class N14503_jinhyuk {
	//static
	static int N, M;
	static int[][] map; //(0:청소X, 1:벽)
	static int cnt = 0; // 청소한 칸의 수
	//4방향탐색
    static int[] dr = {-1, 0, 1, 0};	// 0:북, 1:동, 2:남, 3:서
    static int[] dc = {0, 1, 0, -1};


    //main
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();	//N x M
        M = sc.nextInt();

        int r = sc.nextInt();	//(r,c)
        int c = sc.nextInt();
        int d = sc.nextInt();	//방향 (0:북, 1:동, 2:남, 3:서)

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        //청소 시작
        clean(r, c, d);

        //결과출력
        System.out.println(cnt);
        
    }	//main

    
    //clean
    static void clean(int r, int c, int d) {
        
        while (true) {
        	//0이면 청소 2로 초기화
            if (map[r][c] == 0) {
                map[r][c] = 2;
                cnt++;
            }

            boolean isCleaned = false;	//청소되지 않은 칸 체크

            //4방향탐색
            for (int i = 0; i < 4; i++) {
            	//반시계 방향으로 90도 회전
                int next_d = (d + 3) % 4;
                
                //회전 후 앞 칸 좌표
                int nr = r + dr[next_d];
                int nc = c + dc[next_d];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
                    //회전 후 전진
                    d = next_d;
                    r = nr;
                    c = nc;
                    isCleaned = true;
                    break;	//다시 (청소유무 / 4방향탐색)
                } else {
                	//청소할 칸X / 벽이면 회전만
                    d = next_d;
                }
            }//4방향탐색

            //4방향탐색 후 2번 조건
            if (isCleaned) {
                continue;	//1번 조건부터 다시 확인
            }

            //2-1. 후진
            int br = r - dr[d];
            int bc = c - dc[d];

            //2-2. 후진불가 종료
            if (br < 0 || br >= N || bc < 0 || bc >= M || map[br][bc] == 1) {
                break;
            } 
            //2-1. 후진 후 1번으로
            else {
                r = br;
                c = bc;
            }
        }//while
        
    }	//clean
    
    
}	//class