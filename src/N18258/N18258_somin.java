import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N18258_somin {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Queue<Integer> Q = new LinkedList<>();

		int n = Integer.parseInt(br.readLine());
		int last = -1;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String q = st.nextToken();

			switch (q) {
			case "push":
				int val = Integer.parseInt(st.nextToken());
				Q.offer(val);
				// 마지막 원소 업데이트 
				last = val;
				break;
			case "pop":
				if (Q.isEmpty()) {
					sb.append(-1).append('\n');
				} else {
					sb.append(Q.poll()).append('\n');
				}
				break;
			case "size":
				sb.append(Q.size()).append('\n');
				break;
			case "empty":
				sb.append(Q.isEmpty() ? 1 : 0).append('\n');
				break;
			case "front":
				if (Q.isEmpty()) {
					sb.append(-1).append('\n');
				} else {
					sb.append(Q.peek()).append('\n');
				}
				break;
			case "back":
				if (Q.isEmpty()) {
					sb.append(-1).append('\n');
				} else {
					sb.append(last).append('\n');
				}
				break;

			}
		}
		System.out.println(sb);

	}
}