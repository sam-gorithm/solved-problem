package N15651;

import java.io.*;
import java.util.*;

public class N15651_seoyeon {

    static int N, M;
    static int[] arr;
    static StringBuilder sb= new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 초기화
        arr = new int[M];

        backTrack(0);
        System.out.println(sb.toString());
    }

    private static void backTrack(int depth) {

        // base condition
        if (depth == M) {
            for (int a : arr) {
                sb.append(a+" ");
            }
            sb.append("\n");
            return;
        }

        // 재귀부
        for (int i = 1; i <= N; i++) {
            // 수 담기
            arr[depth] = i;
            // 다음 자리 채우기
            backTrack(depth + 1);

        }
    }

}

