import java.util.Scanner;

public class N9466_somin {
	private static int T, N, res;
	private static int arr[];
	private static boolean visited[], done[];

	public static void dfs(int idx) {
		if (done[idx])
			return;
		if (visited[idx]) {
			done[idx] = true;
			res++;
            
      // 나머지 팀원들 찾아서 카운트 
			int nextStudent = arr[idx];
			while (nextStudent != idx) {
				res++;
				done[nextStudent] = true;
				nextStudent = arr[nextStudent];
			}

			return;
		}

		visited[idx] = true;
		dfs(arr[idx]); // 다음 학생을 탐색
		done[idx] = true;
		visited[idx] = false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			N = sc.nextInt(); // 학생 수
			res = 0;
			arr = new int[N + 1];
			visited = new boolean[N + 1];
			done = new boolean[N + 1];

			for (int n = 1; n <= N; n++) {
				arr[n] = sc.nextInt(); // 학생 번호를 입력받음
			}

			for (int i = 1; i <= N; i++) {
				if (!done[i]) { // 아직 탐색하지 않은 학생인 경우 DFS 시작
					dfs(i);
				}
			}
			// 전체 학생 수에서 팀에 속한 학생 수를 뺌
			System.out.println(N - res);
		}
	}
}