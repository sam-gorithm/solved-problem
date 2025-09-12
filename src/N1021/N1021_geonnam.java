package N1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class N1021_geonnam {
static ArrayDeque<Integer> q; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		q = new ArrayDeque<>();
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 큐의 크기
		int m = Integer.parseInt(st.nextToken()); // 뽑는 수
		int[] arr = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} // 뽑아내려고 하는 원소
		
		for(int i=1;i<=n;i++) {
			q.offer(i); // 숫자 채우기
		}
		
		int count = 0; //연산 횟수 계산
		
		for(int i=0;i<arr.length;i++) {
			int value = arr[i];
			
			if(value == q.peek()) {
				q.poll(); // 바로 뽑을 수 있을 경우
			}
			else if(check(value) == 1) { //왼쪽과 가까울 때
				while(q.peek() != value) {
					q.offer(q.poll());
					count++;
				}
				q.poll();
			}else { //오른쪽과 가까울 때
				while(q.peek() != value) {
					q.offerFirst(q.pollLast());
					count++;
				}
				q.poll();
			}
		}
		System.out.println(count);
	}
	
	static int check(int x) { // 어디쪽과 가까운지 비교하기 위한 함수
		int c1 = 0;
		int c2 = 0;
		
		for (int num : q) { //x가 어디 위치에 있는지 파악
			if(num == x) break;
			c1++;
		}
		c2 = q.size()-c1;
		
		if(c1<c2) return 1;
		else return -1;
	}
}
