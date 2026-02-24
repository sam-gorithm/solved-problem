package N16974;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N16974_geonnam {
	static long[] layer, patty;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long X = Long.parseLong(st.nextToken());

        layer = new long[N + 1];
        patty = new long[N + 1];

        layer[0] = 1;
        patty[0] = 1;

        // 길이와 패티 수 미리 계산
        for (int i = 1; i <= N; i++) {
            layer[i] = layer[i - 1] * 2 + 3;
            patty[i] = patty[i - 1] * 2 + 1;
        }

        System.out.println(re(N, X));
    }

    static long re(int n, long x) {

        if (x == 0) return 0;     // 아무것도 안 먹음
        if (n == 0) return 1;     // level 0은 패티 하나

        // x가 어딨는지 파악이 중요
        // 구조: B + (n-1) + P + (n-1) + B

        if (x == 1) {
            return 0;  // 첫 B만 먹음
        }
        else if (x <= 1 + layer[n - 1]) {
            // 왼쪽 (n-1) 버거로 이동
            return re(n - 1, x - 1);
        }
        else if (x == 1 + layer[n - 1] + 1) {
            // 가운데 패티까지
            return patty[n - 1] + 1;
        }
        else if (x <= 1 + layer[n - 1] + 1 + layer[n - 1]) {
            // 오른쪽 (n-1) 버거 내부
            return patty[n - 1] + 1
                   + re(n - 1, x - (layer[n - 1] + 2));
        }
        else {
            // 전체 다 먹음
            return patty[n];
        }
    }
}
