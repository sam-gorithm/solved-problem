package N16985;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays; 

//	Maaaaaaaaaze / 골드 2 / 1128ms
public class N16985_jinhyuk {
    static int N = 5;
    static int[][][] originPlates = new int[N][N][N];
    static int[][][] maze = new int[N][N][N];	//미로
    static int[] arr = new int[N];				//층 순서
    static boolean[] check = new boolean[N];	//순열 체크 배열
    static int[] dir = new int[N];				//각 층 회전 정도
    static int min = Integer.MAX_VALUE;
    //6방향탐색
    static int[] dr = {0, 0, 1, -1, 0, 0};
    static int[] dc = {0, 0, 0, 0, 1, -1};
    static int[] dz = {1, -1, 0, 0, 0, 0};
    //Point 클래스
    static class Point {
        int r, c, z, dist;

        Point(int r, int c, int z, int dist) {
            this.r = r;
            this.c = c;
            this.z = z;
            this.dist = dist;
        }
    }

    
    //main
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    originPlates[i][j][k] = sc.nextInt();
                }
            }
        }//5x5x5입력

        //판쌓는순서 (순열)
        perm(0);

        //결과출력
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);	//탈출 불가능
        } else {
            System.out.println(min);	//탈출
        }
        
    }	//main


    //perm
    public static void perm(int depth) {
    	
    	//기저조건
        if (depth == N) {
            //회전하는 경우
            rotatePlate(0);
            return;
        }

        //재귀파트
        for (int i = 0; i < N; i++) {
            if (!check[i]) {
                check[i] = true;        
                arr[depth] = i;        
                perm(depth + 1); 
                check[i] = false;       
            }
        }
    }	//perm

    
    //rotatePlate
    public static void rotatePlate(int level) {
    	
    	//기저조건
        if (level == N) {
            maze();	//미로 생성
            bfs();	//BFS 실행
            return;
        }

        //재귀파트
        for (int i = 0; i < 4; i++) {
        	//0->1->2->3 : 시계방향으로 90도씩 회전
            dir[level] = i; 
            rotatePlate(level + 1);  
        }
        
    }	//rotatePlate

    
    //maze : 판 쌓는 순서 / 회전방향에 따라 미로 만들기
    public static void maze() {
        for (int i = 0; i < N; i++) {
        	
            int plateIndex = arr[i];     
            int[][] originPlate = originPlates[plateIndex];	//i번째 판

            int rotation = dir[i];
            maze[i] = rotate(originPlate, rotation);	//i번째 판 회전
        }
        
    }	//maze

    //rotate
    public static int[][] rotate(int[][] plate, int rotState) {
    	
        int[][] newPlate = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
            	//0->1->2->3 : 시계방향으로 90도씩 회전
                switch (rotState) {
                    case 0: //0도
                        newPlate[r][c] = plate[r][c];
                        break;
                    case 1: //90도
                        newPlate[c][N - 1 - r] = plate[r][c];
                        break;
                    case 2: //180도
                        newPlate[N - 1 - r][N - 1 - c] = plate[r][c];
                        break;
                    case 3: //270도
                        newPlate[N - 1 - c][r] = plate[r][c];
                        break;
                }
            }
        }
        return newPlate;
        
    }	//rotate

    
    //bfs
    public static void bfs() {

    	//시작점이나 도착점이 벽이면 불가능
        if (maze[0][0][0] == 0 || maze[N - 1][N - 1][N - 1] == 0) {
            return;
        }

        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][N][N];

        q.add(new Point(0, 0, 0, 0)); 
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Point current = q.poll();
            
            int r = current.r;
            int c = current.c;
            int z = current.z;
            int dist = current.dist;

            //도착
            if (r == N - 1 && c == N - 1 && z == N - 1) {
            	//이동횟수 갱신 후 종료
                min = Math.min(min, dist);
                return; 
            }

            //기존 최단경로보다 길어지면 패스
            if (dist >= min) {
                continue;
            }

            //6방향 탐색
            for (int i = 0; i < 6; i++) {
                int nr = r + dr[i]; 
                int nc = c + dc[i]; 
                int nz = z + dz[i];

                //유효범위체크
                if (nz < 0 || nr < 0 || nc < 0 || nz >= N || nr >= N || nc >= N) continue;
                if (visited[nz][nr][nc]) continue;
                if (maze[nz][nr][nc] == 0) continue;

                visited[nz][nr][nc] = true;
                q.add(new Point(nr, nc, nz, dist + 1)); 
            }//for
        }//while
        
    }	//bfs

    
}	//class