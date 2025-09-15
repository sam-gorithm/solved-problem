package N2164;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N2164_jinhyuk {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Queue<Integer> q = new LinkedList<>();
//		Deque<Integer> q = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			q.offer(i);
		}
		
		while(q.size() > 1) {
			q.poll();
			q.offer(q.poll());
		}
		System.out.println(q.poll());
		
		
	}	//main

}
