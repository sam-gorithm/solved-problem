package N11650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class N11650_geonnam {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); //좌표 수
		
		List<int[]> list = new ArrayList<>(); 
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		} //x, y 좌표 추가
		
		//x좌표가 같으면 y좌표가 증가하는 순서
		list.sort(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[0] == b[0]) return a[1]-b[1];
				return a[0]-b[0];
			}
		});
		
		for (int[] a : list) {
            sb.append(a[0]).append(" ").append(a[1]).append("\n");
        }//출력 형식 맞추기

        System.out.print(sb);
	}
}
