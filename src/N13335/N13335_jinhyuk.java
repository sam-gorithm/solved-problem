package N13335;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

//	트럭 / 실버 1 / 160ms
public class N13335_jinhyuk {
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();	//트럭 수
        int w = sc.nextInt();	//다리 길이
        int L = sc.nextInt();	//다리 최대 하중

        //트럭입력
        Queue<Integer> truck = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            truck.add(sc.nextInt());
        }

        //다리입력 (길이 w 만큼 처음에 0 으로 채움)
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            bridge.add(0); 
        }

        int cnt = 0;	//소요시간
        int currW = 0;	//현재 다리위 트럭 무게
        int crossed = 0;	//다리를 건넌 트럭 수

        //n개 트럭이 모두 건널때까지
        while (crossed < n) {
        	
            cnt++;	//단위시간

            int exit = bridge.poll();
            //다리끝에서 트럭이 나왔다면
            if (exit > 0) {
                currW -= exit;	//다리위 무게에서 트럭무게 빼주고
                crossed++;		//건넌 트럭수 증가
            }

            //건너지않은 트럭있다면
            if (!truck.isEmpty()) {
            	
                int nextTruck = truck.peek();
                //현재 다리 하중 + 다음 트럭 무게가 L 이하라면
                if (currW + nextTruck <= L) {
                	//다음 트럭 진입
                    int crossingTruck = truck.poll();           
                    bridge.add(crossingTruck);			//다리에 트럭 추가
                    currW += crossingTruck;				//현재 다리 무게에 추가
                } else {
                	//하중 초과로 트럭이 못들어옴 (0 추가)
                    bridge.add(0);
                }
            }
            //모든 트럭이 건넜다면
            else {
                bridge.add(0);
            }
            
        }//while

        // 4. 결과 출력
        System.out.println(cnt);
        
    }	//main
    
    
}	//class