package N2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2630_geonnam {
	static int[][] map;
	static int count0, count1;
	
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 int n = Integer.parseInt(br.readLine());
		 map = new int[n][n];
		 
		 for(int i=0;i<n;i++) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 for(int j=0;j<n;j++) {
				 map[i][j] = Integer.parseInt(st.nextToken());
			 }
		 } //map 채우기
		 
		 count0 = 0; //0으로 이루어진 색종이
		 count1 = 0; //1로 이루어진 색종이
		 
		 re(0,0,n);
		 
		 System.out.println(count0);
		 System.out.println(count1);
	}
	
	static void re(int x, int y, int n) {
		//색종이를 잘라야할 때
		if(!check(x,y,n)) {
			int length = n/2;
			
			for(int i=0;i<2;i++) {
				for(int j=0;j<2;j++) {
					re(x+i*length, y+j*length,length);
				}
			}
		}
	}
	
	//한 숫자로만 이루어져있는지 판단하는 메서드
	static boolean check(int x, int y, int n) {
		int c = map[x][y];
		
		for(int i=x;i<x+n;i++) {
			for(int j=y;j<y+n;j++) {
				if(c != map[i][j]) return false;
			}
		}
		
		if(c == 0) count0++;
		else count1++;
		
		return true;
	}
}
