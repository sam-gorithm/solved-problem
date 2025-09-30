package N2447;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N2447_geonnam {
    static char[][] map; // 별 찍을 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        map = new char[n][n];

        // 공백으로 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = ' ';
            }
        }

        re(0, 0, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(map[i]).append("\n");
        }
        System.out.print(sb);
    }

    // 별 그리기 
    static void re(int x, int y, int N) {
        if (N == 1) { // 가장 작은 단위
            map[x][y] = '*';
            return;
        }

        int size = N / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // 가운데를 공백으로 두기 위해
                if (i == 1 && j == 1) continue;

                // 재귀
                re(x + i * size, y + j * size, size);
            }
        }
    }
}
