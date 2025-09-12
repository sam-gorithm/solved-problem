import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class N1021_somin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		Deque<Integer> deque = new ArrayDeque<>();

		
		// 1부터 N까지 순서대로 덱에 추가 
		for (int i = 1; i <= N; i++) {
			deque.addLast(i); 
		}

		int count = 0; // 총 횟수 
		
		
		// 뽑아내려는 원소의 개수만큼 반복 
		for (int i = 0; i < M; i++) {
			
			//뽑아야 하는 수 
			int value = sc.nextInt();

			int move = 0; //이동 횟수 
			
			// 첫 번째 원소가 value가 될 때까지 반복 
			while (deque.getFirst() != value) {
				// 첫 번째 원소를 맨 뒤에 추가 
				deque.addLast(deque.pollFirst());
				move++;

			}
			
			// value가 맨 앞에 위치한 상태
			
			// (덱의 크기 / 2)보다 큼 -> 오른쪽으로 이동하는 것이 더 빨랐음을 의미 
			// 오른쪽 이동 횟수로 변경 

			if (move > deque.size() / 2) {
				move = deque.size() - move;
			}

			count = count + move;
			deque.pollFirst();
		}

		System.out.println(count);

	}
}