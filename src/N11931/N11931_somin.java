import java.util.Scanner;

public class N11931_somin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        boolean[] isNum = new boolean[2000001];
        int half = 1000000; 
        
        int n = sc.nextInt();
        
        for(int i = 0; i < n; i++) {
            int t = sc.nextInt();
            isNum[t + half] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        
        // 내림차순 출력을 위해 배열의 끝부터 0까지 순회
        for(int i = 2000000; i >= 0; i--) {
            if(isNum[i]) {
                sb.append(i - half).append('\n');
            }
        }
        System.out.print(sb);
    }
}