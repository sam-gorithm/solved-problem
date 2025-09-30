package N2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2630_seoyeon {

    // 색종이 데이터를 저장할 배열
    static int[][] paper;
    // 하얀색 색종이의 개수
    static int whiteCount = 0;
    // 파란색 색종이의 개수
    static int blueCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N);

        System.out.println(whiteCount);
        System.out.println(blueCount);
    }

    public static void solve(int row, int col, int size) {

        // 현재 구역의 첫 번째 칸 색깔을 기준 색깔로 정함
        int baseColor = paper[row][col];

        // 현재 구역이 모두 같은 색인지 확인
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {

                // 만약 기준 색깔과 다른 색깔의 칸을 발견하면,
                if (paper[i][j] != baseColor) {

                    // 4개의 구역으로 나누어 각각 재귀 호출
                    int newSize = size / 2;
                    solve(row, col, newSize); // 1사분면
                    solve(row, col + newSize, newSize); // 2사분면
                    solve(row + newSize, col, newSize); // 3사분면
                    solve(row + newSize, col + newSize, newSize); // 4사분면

                    return;
                }
            }
        }

        // for문을 무사히 통과했다면 모든 칸의 색이 같다
        // 기준 색깔에 따라 카운트를 1 증가
        if (baseColor == 0) { // 하얀색
            whiteCount++;
        } else { // 파란색
            blueCount++;
        }
    }
}