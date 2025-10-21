package N14891;

import java.util.*;
import java.io.*;

public class N14891_taeyoung {
	static LinkedList<Character>[] gear; // 인덱스로 조회, 양 끝 제거 및 추가 -> LinkedList 사용

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		gear = new LinkedList[4]; // 톱니바퀴 4개

		// 초기 상태 입력
		for (int i = 0; i < 4; i++) {
		    gear[i] = new LinkedList<>();
		    String S = br.readLine();
		    for (int j = 0; j < 8; j++) {
		        gear[i].add(S.charAt(j));
		    }
		}
		
		int K = Integer.parseInt(br.readLine()); // 회전 횟수
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()) - 1; // 회전시킨 톱니바퀴 (인덱스 0부터라 -1)
			int R = Integer.parseInt(st.nextToken()); // 회전 방향
			
			rotate(N, R);
		}
		
		System.out.println(getScore());
	}
	
	// 회전 함수
	static void rotate(int N, int R) {
		int[] isRotate = new int[4]; // 각 바퀴 회전 여부 및 방향
		isRotate[N] = R;
		
		// 초기 바퀴 기준 좌측 바퀴 회전 여부 확인
		for (int i = N; i > 0; i--) {
			if (gear[i-1].get(2) == gear[i].get(6)) // 맞닿은 극 같으면 회전 안함
				break;
			isRotate[i-1] = -isRotate[i]; // 반대 방향 회전
		}
		
		// 초기 바퀴 기준 우측 바퀴 회전 여부 확인
		for (int i = N; i < 3; i++) {
			if (gear[i].get(2) == gear[i+1].get(6)) // 맞닿은 극 같으면 회전 안함
				break;
			isRotate[i+1] = -isRotate[i]; // 반대 방향 회전
		}
		
		// 회전시키기
		for (int i = 0; i < 4; i++) {
			if (isRotate[i] == 1) { // 시계방향 회전
				gear[i].addFirst(gear[i].removeLast()); // 마지막 원소가 처음으로
			}
			else if (isRotate[i] == -1) { // 반시계방향 회전
				gear[i].addLast(gear[i].removeFirst()); // 처음 원소가 마지막으로
			}
		}
	}
	
	// 점수 계산 함수
	static int getScore() {
		int score = 0;
		for (int i = 0; i < 4; i++) {
			int X = gear[i].getFirst() - '0'; // 12시 방향 정수형으로 (S-1, N-0)
			score += X * Math.pow(2, i); // 인덱스 i 바퀴 12시가 S극이면 2^i 점
		}
		return score;
	}
}
