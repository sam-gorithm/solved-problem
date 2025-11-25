package N3190;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;

//	뱀 / 골드 4 / 120ms
public class N3190_jinhyuk {
	//static
	//4방향탐색
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0}; 

    
    //main
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();	//N x N
        int K = sc.nextInt();	//사과개수

        int[][] board = new int[N][N];

        for (int i = 0; i < K; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            board[r - 1][c - 1] = 1;	//(1,1) -> (0,0) 인덱스 사용
        }

        int L = sc.nextInt();	//뱀 방향 전환 횟수
        Map<Integer, Character> turnInfo = new HashMap<>();
        for (int i = 0; i < L; i++) {
            int time = sc.nextInt();
            char dir = sc.next().charAt(0);
            turnInfo.put(time, dir);
        }//입력 끝

        int time = 0;
        int currDir = 0;	//뱀 처음 방향 (오른쪽)

        // 뱀의 몸통 위치 저장 (맨 앞 : 머리 / 맨 뒤 : 꼬리)
        Deque<int[]> snake = new ArrayDeque<>();
        
        //시작 위치
        snake.addFirst(new int[]{0, 0});
        board[0][0] = 2; //뱀이 있으면 2로 초기화

        while (true) {
            
            time++;

            //1. 머리를 다음칸에 위치
            int[] head = snake.peekFirst();
            int headR = head[0];
            int headC = head[1];

            //2. 다음 위치 / 부딪히는지 확인 (벽/몸)
            int nextR = headR + dr[currDir];
            int nextC = headC + dc[currDir];

            //벽에 부딪힌다면
            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) {
                break; //게임종료
            }
            //자기몸(뱀)이라면
            if (board[nextR][nextC] == 2) {
                break; //게임종료
            }

            //3. 이동
            //사과있는지 확인
            if (board[nextR][nextC] == 1) {
            	//사과있다면 머리만 이동 꼬리는 그대로
            	//머리좌표로 초기화 (밑에서 2로 처리)
            } else {
                //사과없으면 꼬리는 줄어들기
                int[] tail = snake.pollLast();	//꼬리좌표
                board[tail[0]][tail[1]] = 0;	//꼬리좌표 0으로 초기화
            }

            //사과유무에 관계없이 머리 좌표 이동
            snake.addFirst(new int[]{nextR, nextC});	//이동 후 새 머리 좌표 추가
            board[nextR][nextC] = 2;
            
            //방향 전환 시간 경과 확인
            if (turnInfo.containsKey(time)) {
                char turn = turnInfo.get(time);
                if (turn == 'D') {	//오른쪽으로 90도
                    currDir = (currDir + 1) % 4; 
                } else {			//왼쪽으로 90도
                    currDir = (currDir + 3) % 4;
                }
            }
        }//while

        //결과출력
        System.out.println(time);
    }	//main
    
}	//class