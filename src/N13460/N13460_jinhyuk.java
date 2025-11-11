package N13460;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

//	구슬 탈출 2 / 골드 1 / 100ms
public class N13460_jinhyuk {
	//static
    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;	//[빨간_r][빨간_c][파란_r][파란_c]
    static Queue<int[]> q = new LinkedList<>();	//int[] = {빨간r, 빨간c, 파란r, 파란c, 기울임횟수}
    //4방향탐색 (상하좌우)
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    //구슬 처음 위치
    static int RED_r, RED_c, BLUE_r, BLUE_c;
    
    
    //main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') {
                    RED_r = i;
                    RED_c = j;
                } else if (map[i][j] == 'B') {
                    BLUE_r = i;
                    BLUE_c = j;
                }
            }
        }//입력

        //BFS
        int result = bfs();
        
        //결과출력
        System.out.println(result);

    }	//main

    
    //bfs
    static int bfs() {
    	//처음 구슬 위치 삽입
        q.add(new int[]{RED_r, RED_c, BLUE_r, BLUE_c, 0});
        visited[RED_r][RED_c][BLUE_r][BLUE_c] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int rr = current[0];
            int rc = current[1];
            int br = current[2];
            int bc = current[3];
            int count = current[4];

            //10번 이하로 빨간 구슬 탈출불가 시 -1 출력
            if (count >= 10) {
                continue;
            }

            //4방향탐색
            for (int i = 0; i < 4; i++) {
            	//move 함수 -> 해당방향으로 굴린 결과
                int[] next_RED = move(rr, rc, dr[i], dc[i]);
                int[] next_BLUE = move(br, bc, dr[i], dc[i]);

                int nrr = next_RED[0];
                int nrc = next_RED[1];
                int r_dist = next_RED[2]; // 빨간 구슬 이동 거리

                int nbr = next_BLUE[0];
                int nbc = next_BLUE[1];
                int b_dist = next_BLUE[2]; // 파란 구슬 이동 거리
                
                //파란구슬이 구멍으로 빠졌으면 실패
                if (map[nbr][nbc] == 'O') {
                    continue;
                }

                //빨간구슬이 구멍으로 빠졌으면 성공
                if (map[nrr][nrc] == 'O') {
                    return count + 1;
                }

                //두 구슬이 같은 칸
                if (nrr == nbr && nrc == nbc) {
                    //더 많이 굴러온 구슬이 해당칸X
                    if (r_dist > b_dist) {
                    	//빨간 구슬이 더 멀리서 옴
                        nrr -= dr[i];
                        nrc -= dc[i];
                    } else {
                    	//파란 구슬이 더 멀리서 옴
                        nbr -= dr[i];
                        nbc -= dc[i];
                    }
                }

                //다음 탐색
                if (!visited[nrr][nrc][nbr][nbc]) {
                    visited[nrr][nrc][nbr][nbc] = true;
                    q.add(new int[]{nrr, nrc, nbr, nbc, count + 1});
                }
            }//4방향탐색
        }//while

        //10번 이하로 탈출 불가 -1 출력
        return -1;
    }

    
    //move
    static int[] move(int r, int c, int dr, int dc) {
    	
        int dist = 0;
        //다음 칸이 벽X / 구멍X => 굴리기
        while (map[r + dr][c + dc] != '#' && map[r][c] != 'O') {
            r += dr;
            c += dc;
            dist++;
        }
        
        return new int[]{r, c, dist};	//굴러간 좌표 반환
    }	//move
    
    
}	//class