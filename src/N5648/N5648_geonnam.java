package N5648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class N5648_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 숫자의 수
		
		List<Long> list = new ArrayList<>(); // 슛자를 저장할 리스트
		
		//n개의 숫자를 채울때까지 반복
		while(list.size() < n) {
			//다음 토큰이 없으면 다음줄 읽어오기 / 읽을 줄이 없다면 끝내기 / 아예 빈줄이 나올 경우 방지
			while(!st.hasMoreTokens()) {
				String line = br.readLine();
				if(line == null) break;
				st = new StringTokenizer(line);
			}
			
			String number = st.nextToken(); //문자열로 받는다.
			long value = reverse(number);
			
			list.add(value); //변환된 값을 저장
		}
		
		Collections.sort(list); //정렬
		
		for(int i=0;i<list.size();i++) {
			sb.append(list.get(i)).append("\n");
		} // 출력 형식 맞추기
		
		System.out.println(sb); // 출력
	}
	
	//받은 문자열을 뒤집어서 숫자로 변환해주는 함수
	static long reverse(String number) {
		char[] num = number.toCharArray();
		
		//양쪽 끝에서부터 swap해서 뒤집기
		for(int i=0, j = num.length-1; i<j; i++,j--) {
			char tmp = num[i];
			num[i] = num[j];
			num[j] = tmp;
		}
		
		number = new String(num); //char 배열을 새로운 문자열로 합치기
		
		long value = Long.parseLong(number); //문자열을 Long형으로 바꾸기
		
		return value;
	}
}
