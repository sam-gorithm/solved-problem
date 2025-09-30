package N1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1780_geonnam {
	static int[][] map;
	static int count0, count1, count_1;
	
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 int n = Integer.parseInt(br.readLine()); //행렬 크기
		 map = new int[n][n];
		 count0 = 0; //0으로 이루어진 종이 개수
		 count1 = 0; //1으로 이루어진 종이 개수
		 count_1 = 0; //-1으로 이루어진 종이 개수
		 
		 for(int i=0;i<n;i++) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 for(int j=0;j<n;j++) {
				 map[i][j] = Integer.parseInt(st.nextToken());
			 }
		 } //map 채우기
		 
		 re(0,0,n);
		 
		 System.out.println(count_1);
		 System.out.println(count0);
		 System.out.println(count1);
		 
	}
	//재귀함수
	static void re(int x, int y, int n) {
		//한 개의 숫자로 이루어지지 않았을 때
		if(!check(x,y,n)) {
			int length = n/3; //9조각으로 나눈 행렬의 크기
			
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					re(x+i*length, y+j*length, length);
				}
			}
			
		}
	}
	
	//한 개의 숫자로만 채워졌는지 확인하는 메서드
	static boolean check(int x, int y, int n) {
		int c = map[x][y]; //첫번째를 기준으로 판단하기 위해
		
		for(int i=x;i<x+n;i++) {
			for(int j=y;j<y+n;j++) {
				if(c != map[i][j]) return false;
			}
		}//통과하면 모두 같은 숫자로 이루어졌다는 뜻
		
		if(c == 0) count0++;
		else if(c == 1) count1++;
		else count_1++;
		
		return true;
	}
}
