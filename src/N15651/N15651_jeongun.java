package N15651;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class N15651_jeongun {
    static int N, M;
    static int[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        dfs(0);
        bw.flush();
    }

    static void dfs(int num) throws IOException {
        if (num == M) {
            for (int i = 0; i < M; i++) {
                bw.write(Integer.toString(arr[i]));
                if (i < M - 1) {
                    bw.write(' ');
                }
            }
            bw.write('\n');
            return;
        }
        for (int j = 1; j <= N; j++) {
            arr[num] = j;
            dfs(num + 1);
        }
    }
}
