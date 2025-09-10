package N1021;

import java.util.*;

public class N1021_jeongun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); //큐 크기
        int M = sc.nextInt(); //뽑을 원소

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            deque.addLast(i);
        }

        int answer = 0; //총 회전 수

        for (int i = 0; i < M; i++) {
            int target = sc.nextInt();
            int count = 0;

            //왼쪽 회전 수 == 덱 크기 - 오른쪽 회전 수니까
            //그냥 왼쪽으로만 돌린 횟수와
            //덱 - 왼쪽 회전 수(오른쪽 회전 수) 비교해서 최소 계산

            //target 맨 앞으로 올 때까지 왼쪽으로만 회전
            while (deque.peekFirst() != target) {
                deque.addLast(deque.pollFirst());
                count++;
            }

            //최소 비교
            answer += Math.min(count, deque.size() - count);

            //target 없애기
            deque.removeFirst();
        }

        System.out.println(answer);
    }
}