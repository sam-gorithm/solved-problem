package N7795;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class N7795_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		
		for(int tc=0;tc<T;tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 첫째 줄 수
			int M = Integer.parseInt(st.nextToken()); //둘째 줄 수
			
			List<Integer> listA = new ArrayList<>(); //첫째 줄 
			List<Integer> listB = new ArrayList<>(); //둘째 줄
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				listA.add(Integer.parseInt(st.nextToken()));
			}//입력값 추가
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				listB.add(Integer.parseInt(st.nextToken()));
			}//입력값 추가
			
			
			Collections.sort(listA, Comparator.reverseOrder()); //내림차순 정렬
			Collections.sort(listB, Comparator.reverseOrder()); //내림차순 정렬
			
			int result = check(listA, listB);
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	static int check(List<Integer> list1, List<Integer> list2) {
		int count = 0; // 쌍의 수
		
		for(int i=0;i<list1.size();i++) {
			int num1 = list1.get(i); //첫째 줄에서 하나 뽑고
			
			for(int j=0;j<list2.size();j++) {
				int num2 = list2.get(j); //둘째 줄에서 하나 뽑고
				
				if(num1 > num2) { // 첫째 줄에서 뽑은 수가 더 크다면
					count += list2.size()-j; //남은 둘째 줄 수의 갯수를 count에 추가
					break;
				}
			}
		}
		return count;
	}
}
