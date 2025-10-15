package N15654;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N15654_jeongun {
    static int N, M;
    static int[] arr, pick;
    static boolean[] check;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        int i = 0;
        st = null;
        while (i < N) {
            if (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            } else {
                arr[i++] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.sort(arr);

        pick = new int[M];
        check = new boolean[N];

        dfs(0);
        bw.flush();
    }

    static void dfs(int num) throws IOException {
        if (num == M) {
            for (int i = 0; i < M; i++) {
                bw.write(Integer.toString(pick[i]));
                if (i < M - 1) {
                    bw.write(' ');
                }
            }
            bw.write('\n');
            return;
        }

        for (int i = 0; i < N; i++) {
            if (check[i]) continue;
            check[i] = true;
            pick[num] = arr[i];
            dfs(num + 1);
            check[i] = false;
        }
    }
}
