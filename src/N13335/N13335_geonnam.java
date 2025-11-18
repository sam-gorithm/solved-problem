package N13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class N13335_geonnam {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); //트럭 수
		int w = Integer.parseInt(st.nextToken()); //다리 길이
		int l = Integer.parseInt(st.nextToken()); //최대 하중
		
		int[] truck = new int[n]; //트럭 무게 배열
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}//입력값 받기
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=0;i<w;i++) {
			q.add(0);
		}// 다리 
		
		int time = 0; //시간 측정
		int count = 0; //트럭 측정
		int weight = 0; //무게 확인
		
		while(true) {
			weight -= q.poll(); // 맨 앞 트럭
			
			if(weight + truck[count] <= l) {
				q.add(truck[count]); //트럭이 들어갈 수 있으면 트럭 넣기
				weight += truck[count];
				count++;
			}else {
				q.add(0); //트럭이 못 들어갈 때
			}
			
			time++;
			if(count == n) break; //트럭이 다 들어갔으면 종료
		}
		time += w;//마지막 트럭 들어가는 시간
		System.out.println(time);
	}
}
