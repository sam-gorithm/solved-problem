package N1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N1992_seoyeon {

    // 영상 데이터를 저장할 배열
    public static int[][] image;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        image = new int[N][N];

        // 배열에 저장
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                image[i][j] = line.charAt(j) - '0';
            }
        }

        // 가장 큰 전체 영상부터 압축
        compress(0, 0, N);

        System.out.println(sb);
    }

    public static void compress(int row, int col, int size) {

        // 현재 영역이 모두 같은 숫자로 이루어져 있는지 확인
        if (isSameColor(row, col, size)) {
            sb.append(image[row][col]);
        } else {
            // 0과 1이 섞여 있다면, 분할을 시작
            int newSize = size / 2;

            // 압축 시작을 의미하는 ( 추가
            sb.append('(');

            // 4개의 사분면을 재귀 호출
            compress(row, col, newSize);
            compress(row, col + newSize, newSize);
            compress(row + newSize, col, newSize);
            compress(row + newSize, col + newSize, newSize);

            // 압축 끝
            sb.append(')');
        }
    }

    public static boolean isSameColor(int row, int col, int size) {
        int baseColor = image[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (image[i][j] != baseColor) {
                    return false; // 다른 색을 발견하면 false
                }
            }
        }
        return true; // 모든 색이 같다면 true
    }
}
