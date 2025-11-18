package N11931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class N11931_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		
		list.sort(Comparator.reverseOrder()); // 내림차순 정렬
		
		for (int a : list) {
			sb.append(a).append("\n"); //출력 형식 맞추기
		}
		
		System.out.println(sb);
	}
}
