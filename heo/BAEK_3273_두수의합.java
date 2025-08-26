import java.util.Arrays;
import java.util.Scanner;

public class BAEK_3273_두수의합 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//수열의 크기 n
		int n = sc.nextInt();
		//수열을 담기 위한 배열
		int[] arr = new int[n];
		//수열값 할당
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		//이중 포인터를 위해 오름차순 정렬
		Arrays.sort(arr);
		
		//두 수의 합 자연수 x
		int x = sc.nextInt();
		
		//이중 포인터 start, end
		//두 수의 합 sum
		//두 수의 합이 x인 쌍의 개수를 세기 위한 count
		int start = 0;
		int end = n - 1;
		int sum = 0;
		int count = 0;
		
		//start = end 일 때까지 연산
		while(start < end) {
			//두 수의 합 sum
			sum = arr[start] + arr[end];
			
			//두 수의 합이 x 보다 작으면 start 포인터 한 칸 앞으로 이동
			if(sum < x) {
				start++;
			}
			//두 수의 합이 x 보다 크다면 end 포인터 한 칸 뒤로 이동
			else if(sum > x) {
				end--;
			}
			//두 수의 합이 x 라면 count 증가 start 포인터만 한 칸 앞으로 이동
			//(맨 처음부터 만족하는 경우를 위해 end 카운터는 가만히)
			else {
				start++;
				count++;
			}
			
		}	//while
		
		
		//결과 출력
		System.out.println(count);
	}	//main

}
