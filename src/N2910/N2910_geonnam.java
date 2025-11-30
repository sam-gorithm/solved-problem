package N2910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Value {
	int number, count, order; //입력 값, 등장 횟수, 입력 순서
	
	public Value(int number, int count, int order) {
		this.number = number;
		this.count = count;
		this.order = order;
	}
}

public class N2910_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 숫자 수
		int C = Integer.parseInt(st.nextToken()); 
		
		st = new StringTokenizer(br.readLine());
		
		Map<Integer, Value> map = new LinkedHashMap<>(); //중복을 제거하기 위해
		
		for(int i=0;i<N;i++) {
			int number = Integer.parseInt(st.nextToken()); //숫자 값
			
			if(map.containsKey(number)) // 이미 숫자가 등장한 적이 있다면 등장횟수만 추가
				map.get(number).count++;
			else //등장한 적이 없다면 새로 만들기
				map.put(number, new Value(number, 1, i));
		}
		
		//map의 값들만 list로 바꿈 (정렬 위해)
		List<Value> list = new ArrayList<>(map.values());
		
		//등장 횟수 우선으로 정렬, 같다면 입력 순서 비교
		list.sort((a, b) -> {
			if(b.count == a.count) return a.order - b.order;
			return b.count - a.count;
		});
		
		//리스트 순회
		for (Value value : list) {
			//등장 횟수만큼 출력
			for(int i=0;i<value.count;i++) {
				sb.append(value.number).append(" ");
			}
		}
		
		System.out.println(sb);
	}
}
