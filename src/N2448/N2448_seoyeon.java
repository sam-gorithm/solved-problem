package N2448;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class N2448_seoyeon {

    // 별 패턴을 저장할 2차원 배열
    static char[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 삼각형의 너비는 높이(N) * 2 - 1
        stars = new char[N][2 * N - 1];

        // 배열을 공백으로 채움
        for (int i = 0; i < N; i++) {
            Arrays.fill(stars[i], ' ');
        }

        // 가장 큰 삼각형부터
        drawTriangle(0, N - 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(stars[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void drawTriangle(int row, int col, int size) {

        // Base Case: 가장 작은 단위(높이 3)에 도달하면 직접 그립니다.
        if (size == 3) {
            // 1번째 줄
            stars[row][col] = '*';
            // 2번째 줄
            stars[row + 1][col - 1] = '*';
            stars[row + 1][col + 1] = '*';
            // 3번째 줄 -> 밑변
            for (int i = 0; i < 5; i++) {
                stars[row + 2][col - 2 + i] = '*';
            }
            return;
        }

        // 현재 크기를 반으로 줄임
        int newSize = size / 2;

        // 3개의 작은 삼각형으로 나눔
        // 위쪽 삼각형
        drawTriangle(row, col, newSize);
        // 왼쪽 아래 삼각형
        drawTriangle(row + newSize, col - newSize, newSize);
        // 오른쪽 아래 삼각형
        drawTriangle(row + newSize, col + newSize, newSize);
    }
}
