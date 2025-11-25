package N14890;

import java.util.Scanner;

//	경사로 / 골드 3 / 204ms
public class N14890_jinhyuk {
	// static
	static int N, L;
	static int[][] map;

	// main
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // N x N
		L = sc.nextInt(); // 경사로 길이
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		} // 입력

		int cnt = 0; // 지나갈 수 있는 길의 개수

		// 행 체크
		for (int i = 0; i < N; i++) {
			if (linecheck(map[i])) {
				cnt++;
			}
		}

		// 열 체크
		for (int j = 0; j < N; j++) {
			int[] colLine = new int[N];
			for (int i = 0; i < N; i++) {
				colLine[i] = map[i][j];
			}

			if (linecheck(colLine)) {
				cnt++;
			}
		}

		// 결과출력
		System.out.println(cnt);

	} // main

	// linecheck : 해당 행/열이 가능한 길인지 체크
	public static boolean linecheck(int[] line) {

		boolean[] check = new boolean[N]; // 경사로 놓였는지 체크

		// i와 i+1 비교 경사로 놓을지 체크
		for (int i = 0; i < N - 1; i++) {
			int curr = line[i];
			int next = line[i + 1];
			int diff = curr - next; // 높이 차이

			// 같으면 통과
			if (diff == 0) {
				continue;
			}

			// 높이차이 2 이상이면 경사로 놔도 불가능
			if (Math.abs(diff) > 1) {
				return false;
			}

			// 좌 -> 우 낮아짐
			if (diff == 1) {
				// 경사로 가능여부 체크
				for (int j = 1; j <= L; j++) {
					int checkIdx = i + j;
					// 맵밖으로 벗어나면X
					if (checkIdx >= N) {
						return false;
					}
					// 경사로 놓을 곳과 높이 다르면X
					if (line[checkIdx] != next) {
						return false;
					}
					// 이미 경사로 설치되었으면X
					if (check[checkIdx]) {
						return false;
					}
				} // 경사로 가능여부 체크

				// 경사로 설치
				for (int j = 1; j <= L; j++) {
					check[i + j] = true;
				}
			}

			// 좌 -> 우 높아짐
			else if (diff == -1) {
				// 경사로 가능여부 체크
				for (int j = 0; j < L; j++) {
					int checkIdx = i - j;
					// 맵밖으로 벗어나면X
					if (checkIdx < 0) {
						return false;
					}
					// 경사로 놓을 곳과 높이 다르면X
					if (line[checkIdx] != curr) {
						return false;
					}
					// 이미 경사로 설치되었으면X
					if (check[checkIdx]) {
						return false;
					}
				} // 경사로 가능여부 체크

				// 경사로 설치
				for (int j = 0; j < L; j++) {
					check[i - j] = true;
				}
			}
		} // i와 i+1 비교 경사로 놓을지 체크

		// 가능한 길
		return true;
	} // linecheck

} // class