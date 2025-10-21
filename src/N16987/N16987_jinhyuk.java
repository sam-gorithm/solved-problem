package N16987;

import java.util.Scanner;

//	계란으로 계란치기 / 골드 5 / 204ms
public class N16987_jinhyuk {
	//static
    static int N; 	//계란 개수
    static int[] D; 	//내구도
    static int[] W; 	//무게
    static int max = 0;	//깨진 계란 최대 개수

    
    //main
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();	

        //내구도, 무게 입력
        D = new int[N];		
        W = new int[N];
        for (int i = 0; i < N; i++) {
            D[i] = sc.nextInt();
            W[i] = sc.nextInt();
        }

        //재귀
        hitEgg(0);

        //결과출력
        System.out.println(max);

    }	//main


    //hitEgg
    public static void hitEgg (int depth) {
    	
    	////기저조건
        //가장 오른쪽 계란 도달 시 개수 확인 및 갱신 후 종류
        if (depth == N) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (D[i] <= 0) {
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
            return;
        }

        ////재귀파트
        //해당 순서 계란이 깨져있다면 오른쪽 계란으로
        if (D[depth] <= 0) {
            hitEgg(depth + 1);
            return;
        }

        //계란집어서 치기 (0 -> N, 오른쪽으로)
        boolean flag = false;	//칠 수 있는 계란있는지 체크
        for (int i = 0; i < N; i++) {
            //자기 자신 / 내구도 0 이하 -> 칠 수 없음
            if (depth == i || D[i] <= 0) continue;

            //그 외 계란 칠 수 있음
            flag = true;

            //내구도 감소
            D[depth] -= W[i];
            D[i] -= W[depth];

            //이 상태로 옆에 계란깨러 가기
            hitEgg(depth + 1);

            //다음 경우의 수 위해 원상복구
            D[depth] += W[i];
            D[i] += W[depth];
        }

        //계란이 다 깨져있는 경우
        if (!flag) {
            hitEgg(N);
        }
        
    }	//hitEgg
    
    
}	//class