package N18258;

import java.util.*;
import java.io.*;

public class N18258_taeyoung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N]; // 큐를 구현하기 위한 배열
        int head = 0; // 가장 앞 원소 인덱스
        int tail = 0; // 가장 뒤 원소 인덱스

        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String S = st.nextToken();
            if (S.equals("push")) {
                A[tail++] = Integer.parseInt(st.nextToken()); // 가장 뒤에 원소 입력 후 tail 1 증가
            } else if (S.equals("pop")) {
                if (head == tail) { // head와 tail 같다 -> 큐가 비어있다.
                    sb.append(-1).append('\n'); // -1
                } else {
                    sb.append(A[head++]).append('\n'); // 가장 앞에 있는 정수 출력. head 1 증가
                }
            } else if (S.equals("size")) {
                sb.append(tail - head).append('\n'); // tail과 head의 차이가 큐에 들어있는 원소 수
            } else if (S.equals("empty")) {
                if (head == tail) { // 큐가 비어있으면
                    sb.append(1).append('\n'); // 1
                } else {
                    sb.append(0).append('\n'); // 아니면 0
                }
            } else if (S.equals("front")) {
                if (head == tail) { // 큐가 비어있으면
                    sb.append(-1).append('\n'); // -1
                } else {
                    sb.append(A[head]).append('\n'); // 가장 앞에 있는 정수 출력
                }
            } else if (S.equals("back")) {
                if (head == tail) { // 큐가 비어있으면
                    sb.append(-1).append('\n'); // -1
                } else {
                    sb.append(A[tail - 1]).append('\n'); // 가장 뒤에 있는 정수 출력
                }
            }
        }
        System.out.println(sb);
    }
}
