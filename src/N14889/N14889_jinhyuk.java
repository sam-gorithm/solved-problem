package N14889;

import java.util.Scanner;
import java.util.ArrayList;

//	스타트와 링크 / 실버 1 / 472ms
public class N14889_jinhyuk {
	//static
    static int N;
    static int[][] S;
    static boolean[] visited;	//스타트팀 뽑힌 여부판단
    static int min = Integer.MAX_VALUE;

    
    //main
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        S = new int[N][N];
        visited = new boolean[N];	//0 ~ (N-1)

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                S[i][j] = sc.nextInt();
            }
        }//입력

        //최소 능력치 구하기 (조합)
        comb(0, 0);

        //결과출력
        System.out.println(min);
        
    }	//main

    
    //comb : 스타트팀 뽑기 (현재사람, 뽑힌사람수)
    public static void comb(int index, int count) {

        //기저조건
        if (count == N / 2) {
            //능력치 차이 구하고 종료
            diff();
            return;
        }

        //재귀파트 (현재 index ~ (N-1) 까지)
        for (int i = index; i < N; i++) {
        	//아직 스타트팀X
            if (!visited[i]) {
            	//스타트팀으로 뽑고 재귀
                visited[i] = true;
                comb(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }	//comb

    //diff
    public static void diff() {
        
        ArrayList<Integer> startTeam = new ArrayList<>();
        ArrayList<Integer> linkTeam = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                startTeam.add(i);	//true : 스타트팀
            } else {
                linkTeam.add(i);	//false : 링크팀
            }
        }
        
        int startScore = 0;
        int linkScore = 0;

        //팀별로 능력치 계산
        //S_ij => 팀 당 두명씩 짝지어서 계산
        //스타트팀
        for (int i = 0; i < N / 2; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                int p1 = startTeam.get(i);
                int p2 = startTeam.get(j);
                startScore += S[p1][p2] + S[p2][p1];
            }
        }
        //링크팀
        for (int i = 0; i < N / 2; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                int p1 = linkTeam.get(i);
                int p2 = linkTeam.get(j);
                linkScore += S[p1][p2] + S[p2][p1];
            }
        }
        
        //능력치 차이 최소값 갱신
        int currdiff = Math.abs(startScore - linkScore);
        min = Math.min(min, currdiff);
    }	//diff
    
}	//class