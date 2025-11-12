package N14890;

import java.util.*;
import java.io.*;

public class N14890_taeyoung {
	static int N, L;
	static int[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
            	A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
		
		int ans = 0;
		
		// 가로줄 확인
		for (int r = 0; r < N; r++) {
			int[] arr = new int[N];
			for (int c = 0; c < N; c++) {
				arr[c] = A[r][c];
			}
			if (check(arr))
				ans++;
		}
		// 세로줄 확인
		for (int c = 0; c < N; c++) {
			int[] arr = new int[N];
			for (int r = 0; r < N; r++) {
				arr[r] = A[r][c];
			}
			if (check(arr))
				ans++;
		}
		
		System.out.println(ans);
	}

	// 지나갈 수 있는 길인지
	static boolean check(int[] arr) {
		boolean[] used = new boolean[N];
		
		for (int i = 0; i < N - 1; i++) {
			int cur = arr[i];
			int next = arr[i + 1];
			
			if (cur == next)
				continue;
			
			if (Math.abs(cur - next) > 1)
				return false;
			
			if (cur + 1 == next) { // 다음 위치가 현재 위치보다 1 높으면
				// 이전 L칸 체크
				for (int j = i; j > i - L; j--) {
					// 범위 벗어나거나, 이미 사용했거나, 높이가 다르면 false
					if (j < 0 || used[j] || arr[j] != cur)
						return false;
					// 사용 체크
					used[j] = true;
				}
			}
			
			else if (cur - 1 == next) { // 다음 위치가 현재 위치보다 1 낮으면
				// 다음 L칸 체크
				for (int j = i + 1; j <= i + L; j++) {
					// 범위 벗어나거나, 이미 사용했거나, 높이가 다르면 false
					if (j >= N || used[j] || arr[j] != next)
						return false;
					// 사용 체크
					used[j] = true;
				}
			}
		}
		return true;
	}
}

