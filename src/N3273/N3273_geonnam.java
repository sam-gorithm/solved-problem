package N3273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N3273_geonnam {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); //수열의 크기 n
        StringTokenizer st = new StringTokenizer(br.readLine()); // 수열
        int x = Integer.parseInt(br.readLine()); // x
        int[] arr = new int[n]; // 배열 생성
        
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        } // 배열 초기화
        
        Arrays.sort(arr); // 배열 정렬
        
        //정렬하고 양쪽 끝 부터 시작해서 판단하면 판단 횟수가 훨씬 줄음
        int p1 = 0;
        int p2 = n-1;
        int count = 0;
        
        while(p1 < p2) {
        	int sum = arr[p1] + arr[p2];
            if(sum == x) { //x와 같으면 count 증가 시키고 다음 쌍으로 넘어감
                count++;
                p1++;
                p2--;
            }else if(sum > x) { 
            	// sum이 x보다 크면 p2를 감소시켜야 함 p1을 감소시키면 배열 크기 신경써야 하고 배열 전체를 볼 수 없음
            	p2--;
            }else {
            	//sum이 x보다 작을때는 위와 비슷한 이유로 p1을 증가
            	p1++;
            }
        }
        System.out.println(count);
    }
}
