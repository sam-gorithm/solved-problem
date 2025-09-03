package N2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class N2493_jinhyuk {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
//		Scanner sc = new Scanner(System.in);
		//Scanner 사용 시 메모리 초과로 BufferedReader 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] tops = new int[N+1];
		
		//BufferedReader 사용으로 StringTokenizer 추가
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//해당 탑의 높이와 인덱스를 담을 스택
		Stack<Integer> top = new Stack<>();
		Stack<Integer> index = new Stack<>();
		
		//N개 탑에 대해 연산 수행
		for(int i=1; i<=N; i++) {			
//			tops[i] = sc.nextInt();
			tops[i] = Integer.parseInt(st.nextToken());
			
			//바로 왼쪽 탑뿐만 아니라 더 왼쪽의 탑이 레이저 수신할 수 있는지 확인
			while(!top.isEmpty()) {
				//왼쪽에 수신가능한 탑 존재
				if(top.peek() >= tops[i]) {
					sb.append(index.peek()).append(" ");
					break;
				}else {
					//수신가능한 탑있는지 스택 빌 때까지 빼기
					top.pop();
					index.pop();
				}
				
			}	//while
			
			//스택비어있다면 허공
			if(top.isEmpty()) {
				sb.append("0 ");
			}
			//해당 탑의 높이와 인덱스 스택에 담아줌
			top.push(tops[i]);
			index.push(i);
			
		}	//N개 탑에 대해 연산
		
		
		//결과출력
		System.out.println(sb);
		
		
		
	}	//main

	
}
