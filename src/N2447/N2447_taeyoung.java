package N2447;

import java.io.*;
import java.util.*;

public class N2447_taeyoung {
    static char[][] star = { {'*'}};
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        sol(star);
    }

    // 정답을 구하기 위한 재귀 함수
    static void sol(char[][] C) {
        int l = C.length; // 배열 길이
        if (l == N) { // 재귀 종료 조건 -> 배열 크기가 N
            // 배열 출력하기
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < l; j++) {
                    sb.append(C[i][j]);
                }
                sb.append('\n');
            }
            System.out.println(sb);
            return;
        }
        // 다음 재귀로 넘어갈 배열
        char[][] tmp = new char[l * 3][l * 3];

        // char 디폴트 -> \u0000 (유니코드 널 문자) -> ' ' (빈 문자)로 채워준다.
        for (int i = 0; i < l * 3; i++) {
            Arrays.fill(tmp[i], ' ');
        }

        // 다음 배열 채우기
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                // 9등분 기준으로 가운데 제외한 나머지 부분을 채운다.
                // 1 2 3
                // 4   6
                // 7 8 9
                tmp[i][j] = C[i][j]; // 1
                tmp[i + l][j] = C[i][j]; // 4
                tmp[i + 2 * l][j] = C[i][j]; // 7
                tmp[i][j + l] = C[i][j]; // 2
                tmp[i + 2 * l][j + l] = C[i][j]; // 8
                tmp[i][j + 2 * l] = C[i][j]; // 3
                tmp[i + l][j + 2 * l] = C[i][j]; // 6
                tmp[i + 2 * l][j + 2 * l] = C[i][j]; // 9
            }
        }
        // 새로운 배열로 재귀
        sol(tmp);
    }
}
