package N14891;

import java.util.Scanner;

//	톱니바퀴 / 골드 5 / 104ms
public class N14891_jinhyuk {
	//static
    static int[][] gears = new int[5][8];	//[톱니바퀴][톱니]

    
    //main
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= 4; i++) {
            String line = sc.next(); 
            for (int j = 0; j < 8; j++) {
                gears[i][j] = line.charAt(j) - '0';
            }
        }//톱니바퀴별 톱니 입력

        int K = sc.nextInt();	//회전수

        for (int i = 0; i < K; i++) {
            int gear = sc.nextInt();	//톱니바퀴
            int dir = sc.nextInt();     //회전방향 (1 : 시계 / -1 : 반시계)

            //톱니바퀴 회전 / 연쇄 회전
            rotate(gear, dir);
        }

        //회전 후 점수계산
        int score = 0;
        //12방향(0번인덱스) S극이면 점수추가
        if (gears[1][0] == 1) score += 1; // 1번 톱니
        if (gears[2][0] == 1) score += 2; // 2번 톱니
        if (gears[3][0] == 1) score += 4; // 3번 톱니
        if (gears[4][0] == 1) score += 8; // 4번 톱니

        //결과출력
        System.out.println(score);

    }	//main


    //rotate
    static void rotate(int startGear, int startDir) {
        
        int[] gearDir = new int[5];	//1~4번 기어 회전방향 (0: 회전X / 1: 시계방향 / 2: 반시계방향)
        gearDir[startGear] = startDir; // 시작 톱니는 무조건 회전

        //왼쪽 톱니바퀴에 영향
        for (int i = startGear; i >= 2; i--) {
            //왼쪽 톱니바퀴의 2번 톱니와 오른쪽 톱니바퀴의 6번 인덱스 다르면 회전
            if (gears[i][6] != gears[i - 1][2]) {
                //반대로 회전
                gearDir[i - 1] = -gearDir[i];
            } else {
                //톱니 극 같으면 회전X / 전파X
                break;
            }
        }

        //오른쪽 톱니바퀴에 영향
        for (int i = startGear; i <= 3; i++) {
            //왼쪽 톱니바퀴의 2번 톱니와 오른쪽 톱니바퀴의 6번 인덱스 다르면 회전
            if (gears[i][2] != gears[i + 1][6]) {
                //반대로 회전
                gearDir[i + 1] = -gearDir[i];
            } else {
            	//톱니 극 같으면 회전X / 전파X
                break;
            }
        }

        //모든 회전방향 결정 후 동시에 회전
        for (int i = 1; i <= 4; i++) {
            if (gearDir[i] == 1) {
            	//시계방향회전
                clockwise(i); 
            } else if (gearDir[i] == -1) {
            	//반시계방향회전
                counterClockwise(i);
            }
        }//회전
        
    }	//rotate


    //clockwise : [0, 1, 2, 3, 4, 5, 6, 7] -> [7, 0, 1, 2, 3, 4, 5, 6]
    static void clockwise(int gearNum) {
    	
        int temp = gears[gearNum][7];
        
        for (int i = 7; i > 0; i--) {
            gears[gearNum][i] = gears[gearNum][i - 1];
        }

        gears[gearNum][0] = temp;
        
    }	//clockwise

    //counterClockwise : [0, 1, 2, 3, 4, 5, 6, 7] -> [1, 2, 3, 4, 5, 6, 7, 0]
    static void counterClockwise(int gearNum) {
        
        int temp = gears[gearNum][0];
        
        for (int i = 0; i < 7; i++) {
            gears[gearNum][i] = gears[gearNum][i + 1];
        }
        
        gears[gearNum][7] = temp;
        
    }	//counterClockwise
 
    
}	//class