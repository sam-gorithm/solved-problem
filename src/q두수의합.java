import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class q두수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //수열의 크기 n
        int n = Integer.parseInt(br.readLine());

        //수열 받아오기
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];
        for (int i = 1; i < n+1; i++)  arr[i] = Integer.parseInt(st.nextToken());

        //x 받아오기
        int x = Integer.parseInt(br.readLine());

        //순서쌍 갯수 찾기
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i+1; j < n+1; j++) {
                if(arr[i]+arr[j] == x) cnt++;
            }
        }

        System.out.println(cnt);
    }
}
