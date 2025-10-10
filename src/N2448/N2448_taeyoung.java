package N2448;

import java.io.*;
import java.util.*;

public class N2448_taeyoung {
    // 초기 별 배열
    static char[][] star = { { ' ', ' ', '*', ' ', ' ' }, { ' ', '*', ' ', '*', ' ' }, { '*', '*', '*', '*', '*' } };
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        sol(star);
    }

    // 정답을 구하기 위한 재귀 함수
    static void sol(char[][] C) {
        int h = C.length; // 배열 행 크기
        int w = 2 * h - 1; // 배열 열 크기
        if (h == N) { // 재귀 종료 조건 -> 배열 행 크기가 N
            // 배열 출력하기
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    sb.append(C[i][j]);
                }
                sb.append('\n');
            }
            System.out.println(sb);
            return;
        }
        int nh = 2 * h; // 다음 배열 행 크기
        int nw = 2 * nh - 1; // 다음 배열 열 크기

        // 다음 재귀로 넘어갈 배열
        char[][] tmp = new char[nh][nw];

        // char 디폴트 -> \u0000 (유니코드 널 문자) -> ' ' (빈 문자)로 채워준다.
        for (int i = 0; i < nh; i++) {
            Arrays.fill(tmp[i], ' ');
        }

        // 다음 배열 채우기
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // 삼각형 모양에서 가운데 제외한 상, 좌, 우 채우기
                tmp[i][j + h] = C[i][j]; // 상
                tmp[i + h][j] = C[i][j]; // 좌
                tmp[i + h][j + 2 * h] = C[i][j]; // 우
            }
        }
        // 새로운 배열로 재귀
        sol(tmp);
    }
}
