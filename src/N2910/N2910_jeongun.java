import java.util.Scanner;

public class N2910_jeongun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int C = sc.nextInt();

		int[] num = new int[N]; //값 저장
		int[] count = new int[N]; //등장 횟수
		int[] order = new int[N]; //등장 순서
		int m = 0;

		for (int i = 0; i < N; i++) {
			int x = sc.nextInt();
			
			//이미 본 값인지 검사
			int idx = -1;
			for (int j = 0; j < m; j++) {
				if (num[j] == x) {
					idx = j;
					break;
				}
			}
			
			//처음 보면 저장
			if (idx == -1) {
				num[m] = x;
				count[m] = 1;
				order[m] = m;
				m++;
				//이미 있던 애면 빈도++
			} else {
				count[idx]++;
			}
		}
		//정렬 -> 빈도 내림차
		for (int i = 0; i < m - 1; i++) {
			int best = i;
			for (int j = i + 1; j < m; j++) {
				if (count[j]>count[best]) {
					best = j;
				//빈도 같으면 먼저 나온 애 우선
				} else if (count[j] == count[best] && order[j] < order[best]) {
					best = j;
				}
			}
			//best != i면 바꾸기
			if (best != i) {
				int tv = num[i];
				num[i] = num[best];
				num[best] = tv;
				
				int tc = count[i];
				count[i] = count[best];
				count[best] = tc;
				
				int to = order[i];
				order[i] = order[best];
				order[best] = to;
			}
		}
		
		for (int i = 0; i < m; i++) {
			for (int k = 0; k < count[i]; k++) {
				System.out.print(num[i] + " ");
			}
		}
	}

}
