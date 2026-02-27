package N10993;

import java.util.*;
import java.io.*;

public class N10993_taeyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		char[][][] star = new char[N + 1][][];
		star[1] = new char[][] { { '*' } };

		for (int i = 2; i <= N; i++) {
			int h = (1 << i) - 1;          // height = 2^i - 1
		    int w = (1 << (i + 1)) - 3;    // width  = 2^(i+1) - 3
		    int mid = 1 << (i - 1);        // 가운데 정렬용 오프셋 = 2^(i-1)
			
			// 다음 별 배열 만들기
			star[i] = new char[h][w];
			
			// 공백 채워두기
			for (int y = 0; y < h; y++) {
				Arrays.fill(star[i][y], ' ');
			}
			// 짝수인 경우
			if (i % 2 == 0) {
				// 이전 별 모양 이동
				for (int row = 0; row < star[i - 1].length; row++) {
					for (int col = 0; col < star[i - 1][0].length; col++) {
						star[i][row + 1][col + mid] = star[i - 1][row][col];
					}
				}
				// 겉부분 채우기
				// 맨 위는 다 채움
				Arrays.fill(star[i][0], '*');
				// 나머지는 양 끝만
				for (int row = 1; row < h; row++) {
					star[i][row][row] = '*';
					star[i][row][w - row - 1] = '*';
				}
			} else {
				// 이전 별 모양 이동
				for (int row = 0; row < star[i - 1].length; row++) {
					for (int col = 0; col < star[i - 1][0].length; col++) {
						star[i][row + mid - 1][col + mid] = star[i - 1][row][col];
					}
				}
				// 겉부분 채우기
				// 맨 아래는 다 채움
				Arrays.fill(star[i][h - 1], '*');
				// 나머지는 양 끝만
				for (int row = 1; row < h; row++) {
					star[i][h - row - 1][row] = '*';
					star[i][h - row - 1][w - row - 1] = '*';
				}
			}
		}

		print(star[N]);

	}

	// 별 배열 출력 함수
	static void print(char[][] a) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			int last = -1; // 행에서 마지막 '*' 위치
			for (int j = a[i].length - 1; j >= 0; j--) {
	            if (a[i][j] == '*') { 
	            	last = j; 
	            	break; 
	            }
	        }
			sb.append(a[i], 0, last + 1);
			sb.append('\n');
		}
		System.out.print(sb);
	}
}



