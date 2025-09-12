package N2164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class N2164_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		for(int i=1;i<=n;i++) {
			q.offer(i); //카드 채우기
		}
		
		while(q.size() != 1) {
			q.poll();//한장 버리고
			q.offerLast(q.poll());//맨 밑에 추가
		}
		System.out.println(q.poll());
	}
}
