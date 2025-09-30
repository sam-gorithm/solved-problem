package N2447;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class N2447_seoyeon {

    // 별 패턴을 저장할 배열
    static char[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        stars = new char[N][N];

        // 배열을 공백으로 채움
        for (int i = 0; i < N; i++) {
            Arrays.fill(stars[i], ' ');
        }

        drawStars(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(stars[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void drawStars(int row, int col, int size) {

        // (1 1)이면 별을 찍고 종료
        if (size == 1) {
            stars[row][col] = '*';
            return;
        }

        // 한 구역의 크기 계산
        int newSize = size / 3;

        // 3 3의 각 구역을 순회
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                // 가운데(1, 1) 구역은 공백이므로 건너뜀
                if (i == 1 && j == 1) {
                    continue;
                }

                // 나머지 8개 구역 재귀 호출
                drawStars(row + i * newSize, col + j * newSize, newSize);
            }
        }
    }
}
