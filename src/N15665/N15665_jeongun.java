package N15665;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N15665_jeongun {
    static int N, M;
    static int[] arr, pick;
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
                arr[i] = Integer.parseInt(st.nextToken());
                i++;
            }
        }
        Arrays.sort(arr);

        pick = new int[M];
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

        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (arr[i] == prev) {
                continue; // 같은 깊이에서 같은 값 시작 중복 제거
            }
            pick[num] = arr[i];
            dfs(num + 1);
            prev = arr[i];
        }
    }
}
