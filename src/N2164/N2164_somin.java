import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N2164_somin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			q.add(i);
		}

		// 카드가 1장 남을 때까지 
		while (q.size() > 1) {
			// 제일 위에 있는 카드를 버림 
			q.poll();
			// 제일 위에 있는 카드를 제일 아래로 옮김 
			q.add(q.poll());

		}

		System.out.println(q.peek());
	}
}