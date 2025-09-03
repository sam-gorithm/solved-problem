package N1158;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N1158_jinhyuk {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//(N,K) : N명, K번째 사람 삭제
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		List<Integer> people = new ArrayList<>();
		//1 ~ N명의 사람 리스트에 추가
		for(int i=1; i<=N; i++) {
			people.add(i);
		}
		
		//N명의 사람들이 순차적으로 삭제됨
		int[] ans = new int[N];
		//리스트의 index 0부터 k번째 사람 삭제
		int currentIndex = 0;
		//N번의 삭제 과정
		for(int i=0; i<N; i++) {
			//리스트의 index 0부터 시작 k번째 사람의 인덱스는 -1 해줘야함
			//people 리스트에 남은 사람들 사이에서 반복되야하므로 people.size()를 이용해 모듈러 연산
			currentIndex = (currentIndex + K - 1) % people.size();
			//k번째 사람을 순차적으로 정답 배열에 넣는다.
			ans[i] = people.remove(currentIndex);
		}
		
		//결과출력
		System.out.print("<");
		for(int i=0; i<N; i++) {
			System.out.print(ans[i]);
			if(i < N-1) {
				System.out.print("," + " ");
			}
		}
		System.out.println(">");
		
		
		
	}	//main

}
