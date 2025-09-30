package N1780;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class N1780_seoyeon {

    // 변수 선언
    static int[][] paper;

    // -1 종이 갯수
    static int countMinusOne = 0;
    // 0 종이 갯수
    static int countZero = 0;
    // 1 종이 갯수
    static int countOne = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 초기화
        paper = new int[N][N];

        // 색종이 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가장 큰 전체 범위부터 확인 -> 점점 범위 증가
        solve(0, 0, N);

        // 정답 출력
        System.out.println(countMinusOne);
        System.out.println(countZero);
        System.out.println(countOne);
    }

    // size: 탐색할 종이의 한 변의 길이
    public static void solve(int row, int col, int size) {


        // row와 col과 다른지 확인
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {

                // 다를 경우 9분할 시작
                if (paper[i][j] != paper[row][col]) {

                    // 9분할하기 위해 새로운 한 변의 길이 계산
                    int len = size / 3;

                    // 3*3 돌기
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {

                            // 재귀호출
                            solve(row + k * len, col + l * len, len);
                        }
                    }
                    // 9개 구역 확인이 끝나면 리턴
                    return;

                }

            }
        }

        // 현재 종이가 모두 같은 숫자임
        // 어떤 영역인지 확인
        if (paper[row][col] == -1) {
            countMinusOne++;
        } else if (paper[row][col] == 0) {
            countZero++;
        } else {
            countOne++;
        }

        return;
    }
}