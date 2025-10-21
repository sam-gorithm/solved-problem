package N14499;

import java.util.Scanner;

//	주사위 굴리기 / 골드 4 / 180ms
public class N14499_jinhyuk {
	//static
    static int[] dice = new int[7];	//주사위 1 ~ 6
    static int N, M, x, y;
    static int[][] map;
    
    
    //main
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();	//N x M
        M = sc.nextInt();	//N x M
        x = sc.nextInt();	//(x,y)
        y = sc.nextInt();	//(x,y)
        int K = sc.nextInt();	//명령의 개수

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        
        //동:1/서:2/북:3/남:4
        int[] dx = {0, 0, 0, -1, 1}; 
        int[] dy = {0, 1, -1, 0, 0}; 

        for (int i = 0; i < K; i++) {
            int command = sc.nextInt();
            //다음좌표
            int nx = x + dx[command];
            int ny = y + dy[command];
            //유효범위체크
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            //유효범위 안이면 주사위 굴리기
            roll(command);

            //좌표갱신
            x = nx;
            y = ny;

            //이동한 칸 0 이라면
            if (map[x][y] == 0) {
                //주사위 바닥면 숫자 해당칸에 복사
                map[x][y] = dice[6];
            } else {
                //이동한 칸이 0 이 아니라면, 해당칸의 숫자를 주사위 바닥면에 복사
                dice[6] = map[x][y];
                //복사하고 해당칸 숫자 0
                map[x][y] = 0;
            }

            //결과출력
            System.out.println(dice[1]);
        }
        
    }	//main

    
    //roll
    static void roll(int dir) {
        int temp;
        //		[2]
        //	[4]	[1]	[3]
        //		[5]
        //		[6]
        
        //회전에 영향받지않는 2면을 제외한 나머지4면 초기화
        switch (dir) {
            case 1:	//동
                temp = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[6]; 
                dice[6] = dice[3]; 
                dice[3] = temp;    
                break;
                
            case 2:	//서
                temp = dice[1];
                dice[1] = dice[3]; 
                dice[3] = dice[6]; 
                dice[6] = dice[4]; 
                dice[4] = temp;    
                break;

            case 3:	//북
                temp = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[6]; 
                dice[6] = dice[2]; 
                dice[2] = temp;    
                break;

            case 4:	//남
                temp = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[6]; 
                dice[6] = dice[5]; 
                dice[5] = temp;    
                break;
        }//switch
        
    }	//roll
    
    
}	//class