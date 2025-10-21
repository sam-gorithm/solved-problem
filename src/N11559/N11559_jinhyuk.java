package N11559;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//	Puyo Puyo / 골드 4 / 96ms
public class N11559_jinhyuk {
	//static
    static int R = 12;
    static int C = 6;
    static char[][] map = new char[R][C];
    //4방향탐색 (상하좌우)
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    //Point 클래스
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

        for (int i = 0; i < R; i++) {
            String line = sc.next(); 
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }//입력

        int cnts = 0;	//연쇄횟수
        //게임시작
        while (true) {
        	
            //4개 이상 연결된 색 찾고 터트리기
            boolean isPopped = puyo(); 
            //안터졌으면 종료
            if (!isPopped) {
                break;
            }

            //터졌으면 연쇄횟수 추가
            cnts++;

            //중력적용
            gravity();
        }

        //결과출력
        System.out.println(cnts);
        
    }	//main

    
    //puyo
    static boolean puyo() {
        boolean[][] visited = new boolean[R][C]; 
        boolean popped = false; 

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
            	//	빈공간X / 방문X
                if (map[i][j] != '.' && !visited[i][j]) {
                	//해당지점 색 좌표 추가하며 bfs
                    List<Point> group = bfs(i, j, visited);
                    //같은색 4개 이상 모이면 터트리기
                    if (group.size() >= 4) {
                        popped = true; 
                        for (Point p : group) {
                            map[p.r][p.c] = '.';
                        }
                    }
                }
            }//for-j
        }//for-i
        return popped;
        
    }	//puyo

    
    //bfs
    static List<Point> bfs(int r, int c, boolean[][] visited) {
        List<Point> group = new ArrayList<>(); 
        Queue<Point> q = new LinkedList<>();   

        char color = map[r][c]; 
        
        q.offer(new Point(r, c));	//bfs를 위한 Queue
        visited[r][c] = true; 		//방문처리
        group.add(new Point(r, c)); //같은색 좌표 저장

        while (!q.isEmpty()) {
            Point now = q.poll(); 
            //4방향탐색
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i]; 
                int nc = now.c + dc[i]; 

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                if (!visited[nr][nc] && map[nr][nc] == color) {
                    visited[nr][nc] = true;      
                    q.offer(new Point(nr, nc));  
                    group.add(new Point(nr, nc)); 
                }
            }//for
        }	//while
        return group;
        
    }


    //gravity : 열마다 중력 작용 / 아래->위 행 순서대로 중력 작용
    static void gravity() {
        for (int col = 0; col < C; col++) {
            
            int emptyRow = R - 1;	//제일 밑에 행 인덱스 11

            for (int row = R - 1; row >= 0; row--) {
                //빈 공간X
                if (map[row][col] != '.') {            
                    char temp = map[row][col];	//현재색 저장
                    map[row][col] = '.';      	//빈 공간으로 만들고
                    map[emptyRow][col] = temp; 	//빈 공간 중 제일 아래행으로 내려버리기
                    
                    emptyRow--;	//제일 밑에 칸 높아짐
                }
            }
        }
    }	//gravity
    
    
}	//class