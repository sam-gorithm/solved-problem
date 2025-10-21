package N15655;

import java.io.*;
import java.util.*;

public class N15655_seoyeon {

    static int N, M;
    static int arr[];
    static int input[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        arr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        backTrack(0, 0);
        System.out.println(sb.toString());

    }

    private static void backTrack(int depth, int next) {
        // base condition
        if (depth == M) {
            for (int a : arr) {
                sb.append(a + " ");
            }
            sb.append("\n");
            return;
        }

        // 재귀부
        for (int i = next; i < N; i++) {

            arr[depth] = input[i];
            backTrack(depth + 1, i + 1);

        }

    }

}

