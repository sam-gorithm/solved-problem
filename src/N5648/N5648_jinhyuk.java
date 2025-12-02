package N5648;

import java.util.Arrays;
import java.util.Scanner;

//	역원소 정렬 / 실버 5 / 472ms
public class N5648_jinhyuk {
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();	//1 ~ 1,000,000

        //int 선언 시 런타임 에러
        long[] nums = new long[n];

        for (int i = 0; i < n; i++) {
        	//String 형태로 입력 받고 StringBuilder로 뒤집기
            String str = sc.next();
            StringBuilder sb = new StringBuilder(str);
            String revStr = sb.reverse().toString();
            
            nums[i] = Long.parseLong(revStr);
        }//입력

        //오름차순 정렬
        Arrays.sort(nums);

        //결과출력
        for (long num : nums) {
            System.out.println(num);
        }
        
    }	//main
    
    
}	//class