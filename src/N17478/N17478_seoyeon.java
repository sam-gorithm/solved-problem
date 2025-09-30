package N17478;

import java.io.*;
import java.util.*;
public class N17478_seoyeon {

    // 재귀 반복 횟수
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");

        recur(0);
    }

    // 함수 정의: 현재 재귀의 깊이 받기
    private static void recur(int depth) {

        // 깊이에 맞는 들여쓰기 설정
        String prefix = "____".repeat(depth);

        // 5. 재귀의 탈출 조건
        if (depth == N) {
            // 마지막 깊이에서는 질문과 최종 답변을 출력
            System.out.println(prefix + "\"재귀함수가 뭔가요?\"");
            System.out.println(prefix + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");

            System.out.println(prefix + "라고 답변하였지.");

            // 리턴
            return;
        }


        // 재귀 반복

        // 이야기 출력
        System.out.println(prefix + "\"재귀함수가 뭔가요?\"");
        System.out.println(prefix + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
        System.out.println(prefix + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
        System.out.println(prefix + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");

        // 1증가 하여 재귀 호출
        recur(depth + 1);

        // 리턴되면 돌아옴.
        System.out.println(prefix + "라고 답변하였지.");
    }
}