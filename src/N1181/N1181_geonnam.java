package N1181;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class N1181_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 단어 수
		Set<String> set = new HashSet<>(); // 중복을 없애기 위해
		
		for(int i=0;i<N;i++) {
			set.add(br.readLine());
		}//set에 추가
		
		List<String> list = new ArrayList<>(set); //set을 list로 변환
		
		list.sort(new Comparator<String>() {
			public int compare(String s1, String s2) {
				if(s1.length() == s2.length())
					return s1.compareTo(s2);
				return s1.length() - s2.length(); 
			}
		});//길이가 짧은 것부터, 길이가 같다면 사전 순
		
		for(int i=0;i<list.size();i++) {
			sb.append(list.get(i)).append("\n");
		} // 출력 형식 맞추기
		System.out.println(sb);
	}
}
