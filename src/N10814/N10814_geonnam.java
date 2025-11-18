package N10814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class N10814_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); //회원 수
		
		List<int[]> age = new ArrayList<>(); //나이 저장 리스트
		List<String> name = new ArrayList<>(); // 이름 저장 리스트
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			age.add(new int[] {Integer.parseInt(st.nextToken()), i});
			name.add(st.nextToken());
		} // 나이를 입력받을 때는 입력 순서도 같이 받게 함
		
		//나이 리스트를 정렬하는데 나이가 같으면 입력 순서로 정렬
		age.sort(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[0] == b[0]) return a[1]-b[1];
				return a[0]-b[0];
			}
		});
		
		for (int[] a : age) {
            sb.append(a[0]).append(" ").append(name.get(a[1])).append("\n");
        }//출력 형식 맞추기

        System.out.print(sb);
	}
}
