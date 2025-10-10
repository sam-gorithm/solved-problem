package N1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N1992_geonnam {
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 int n = Integer.parseInt(br.readLine()); //행렬 크기
		 map = new int[n][n];
		 
		 for(int i=0;i<n;i++) {
			 String text = br.readLine();
			 for(int j=0;j<n;j++) {
				 map[i][j] = text.charAt(j)-'0';
			 }
		 }//map 채우기
		 
		 re(0,0,n);
		 System.out.println(sb);
		 
	}
	//재귀 함수
	static void re(int x, int y, int n) {
		if(!check(x,y,n)) {
			sb.append("(");// 나누어져야 하므로 괄호 열기
			int length = n/2; //4조각으로 나누어야 하므로
			
			//4조각으로 나누어 재귀
			for(int i=0;i<2;i++) {
				for(int j=0;j<2;j++) {
					re(x+i*length,y+j*length,length);
				}
			}
			sb.append(")"); //재귀가 끝난 후 닫힌 괄호 추가
		}
	}
	
	//같은 숫자로만 이루어져 있는지 확인
	static boolean check(int x, int y, int n) {
		int c = map[x][y];
		
		for(int i=x;i<x+n;i++) {
			for(int j=y;j<y+n;j++) {
				if(c != map[i][j]) return false;
			}
		}
		//같은 숫자로만 이루어진 경우
		if(c == 0) sb.append(0);
		else sb.append(1);
		
		return true;
	}
}
