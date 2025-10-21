package N6603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N6603 {

    static int k;
    static int[] input;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static boolean[] exclude;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                break;
            }
            input = new int[k];
            arr = new int[6];
            exclude = new boolean[6];
            for (int i = 0; i < k; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }

            backTrack(0, 0);

            sb.append("\n");
        } // while
        System.out.println(sb.toString());
    }

    private static void backTrack(int depth, int next) {
        // baseCondition
        if (depth == 6) {
            for (int a : arr) {
                sb.append(a + " ");
            }
            sb.append("\n");
            return;
        }

        // 재귀부
        for (int i = next; i < k; i++) {
            arr[depth] = input[i];
            backTrack(depth + 1, i + 1);
        }

    }
}
