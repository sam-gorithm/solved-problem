package N5648;

import java.util.*;

public class N5648_somin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        
        long[] list = new long[N];

        // 뒤집어서 저장
        for (int i = 0; i < N; i++) {
            String temp = sc.next();
            
            // 뒤집기
            StringBuilder sb = new StringBuilder(temp);
            long reversed = Long.parseLong(sb.reverse().toString());
            
            list[i] = reversed;
        }

        Arrays.sort(list);

        for (long num : list) {
            System.out.println(num);
        }
        
    }
}