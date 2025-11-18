package N14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N14891_geonnam {
static List<ArrayList<Integer>> list;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		list = new ArrayList<>(); //톱니바퀴 배열들을 담을 곳
		
		for(int i=0;i<4;i++) {
			list.add(new ArrayList<Integer>()); //톱니바퀴 번호들을 저장할 배열
		}
		
		for(int i=0;i<4;i++) {
			String line = br.readLine();
			for(int j=0;j<8;j++) {
				list.get(i).add(line.charAt(j)- '0');
			}
		}// 입력값 채우기
		
		int K = Integer.parseInt(br.readLine()); //K번 회전
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) -1;
			int dir = Integer.parseInt(st.nextToken());
			
			turn(num, dir);
		}
		
		int result = 0;
		for(int i=0;i<4;i++) {
			if(list.get(i).get(0) != 0) {
				int score = 1 << i; //1,2,4,8
				result += score;
			}
		}
		
		System.out.println(result);
	}
	
	static void turn(int num, int dir) {
		//돌리기 전에 돌려야할 톱니 회전 방향 확인
		int[] dollilgga = new int[4];
		
		dollilgga[num] = dir;
		
		for(int i = num;i>0;i--) {
			if(change(i-1, i)) dollilgga[i-1] = -dollilgga[i];
			else break;
		}//num 기준으로 왼쪽 탐색
		
		for(int i = num;i<3;i++) {
			if(change(i, i+1)) dollilgga[i+1] = -dollilgga[i];
			else break;
		}//num 기준으로 오른쪽 탐색
		
		for(int i=0;i<4;i++) {
			int state = dollilgga[i];
			if(state == 1) {
				clockwise(i);
			}else if(state == -1) {
				cClockwise(i);
			}
		}
	}
	
	//시계 방향 돌리기
	static void clockwise(int num) {
		list.get(num).add(0, list.get(num).get(7));
		list.get(num).remove(8);
	}
	
	//반시계 방향 돌리기
	static void cClockwise(int num) {
		list.get(num).add(list.get(num).get(0));
		list.get(num).remove(0);
	}
	
	//돌려야하는지 확인 ( 2번이 오른쪽, 6번이 왼쪽 )
	static boolean change(int num, int num2) {
		if(list.get(num).get(2) != list.get(num2).get(6)) return true;
		return false;
	}
}
