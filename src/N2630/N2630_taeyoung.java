package N2630;

import java.io.*;
import java.util.*;

public class N2630_taeyoung {
	static int x, y; // x: 1(파란색), y: 0(하얀색)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] A = new int[N][N]; // 초기 배열
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		sol(A);
		
		System.out.println(y); // 하얀색
		System.out.println(x); // 파란색
		
	}
	
	// 정답을 구하기 위한 재귀 함수
	static void sol(int[][] arr) {
		if (check(arr)) { // 재귀 종료 조건 -> 하나의 수로 이루어졌다면
			return;
		}
		int l = arr.length; // 배열 크기
		
		// 같은 수 종이 아님 -> 4등분
		for (int i = 0; i < l; i += l/2) {
			for (int j = 0; j < l; j += l/2) {
				// 4등분 위한 임시 배열
				int[][] tmp = new int[l/2][l/2]; // 행, 열 크기 1/2
				for (int r = 0; r < l/2; r++) {
					for (int c = 0; c < l/2; c++) {
						tmp[r][c] = arr[i+r][j+c]; // i,j -> 시작 위치(좌상단) / r,c -> 시작점에서 상대적인 위치
					}
				}
				sol(tmp); // 분할한 배열로 재귀 호출
			}
		}
	}
	
	// 주어진 배열이 하나의 수로 이루어졌는지 확인하는 함수
	static boolean check(int[][] arr) {
		int l = arr.length; // 배열의 크기
		int n = arr[0][0]; // 비교를 위한 배열 첫 원소
		
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l; j++) {
				if (arr[i][j] != n) // 배열을 탐색하며 다른 수가 나오면 false
					return false;
			}
		}
		// 2중 for 문 통과 -> 하나의 수로 이루어짐
		switch (n) { // 어떤 수인지에 따라 각각 x, y 증가시킨다
		case 1: 
			x++;
			break;
		case 0:
			y++;
			break;
		}
		return true; // true 반환
	}
}
