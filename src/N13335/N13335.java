package N13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class N13335 {

    public static void main(String[] args) throws IOException {

        // --- 1. 입력 처리 ---
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 트럭 수
        int w = Integer.parseInt(st.nextToken()); // 다리 길이
        int L = Integer.parseInt(st.nextToken()); // 다리 최대 하중

        // --- 2. 자료구조 초기화 ---

        // 2-1. 대기 트럭 큐 (waitingTrucks)
        Deque<Integer> queue = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }

        // 2-2. 다리 큐 (bridge)
        Deque<Integer> queue2 = new ArrayDeque<>();

        for (int i = 0; i < w; i++) {
            queue2.add(0);
        }

        // --- 3. 시뮬레이션 변수 초기화 ---

        int time = 0; // 시간을 표현할 변수
        int allWeight = 0; // 현재 다리 위 트럭 총 무게

        // --- 4. 시뮬레이션 루프 ---

        // '대기 트럭 큐'(queue)가 비어있지 않은 동안 계속 반복합니다.
        while (!queue.isEmpty()) {

            // (1) 시간이 1 흐릅니다.
            time++;

            // (2) 다리에서 트럭/공간이 나갑니다 (다리의 출구).
            // '다리 큐'(queue2)에서 맨 앞의 요소를 하나 꺼냅니다.
            int curr= queue2.remove();

            // 그리고 '현재 다리 위 총 무게'(allWeight)에서 방금 꺼낸 요소의 무게만큼 뺍니다.
            allWeight-=curr;

            // (3) 다리에 새 트럭/공간이 들어옵니다 (다리의 입구).

            // '대기 트럭 큐'(queue)에 남아있는 첫 번째 트럭의 무게를 확인합니다.
            int currW= queue.peek();

            // [Case 1: 새 트럭이 올라갈 수 있는 경우]
            // 만약 (현재 다리 위 총 무게 + 확인한 트럭의 무게)가
            // 다리의 최대 하중(L)보다 작거나 같다면,
            if((allWeight+currW)<=L){

            // '대기 트럭 큐'(queue)에서 그 트럭을 실제로 꺼냅니다.
                int currTruck= queue.remove();
            // '현재 다리 위 총 무게'(allWeight)에 그 트럭의 무게를 더합니다.
                allWeight+=currTruck;
            // '다리 큐'(queue2)의 맨 뒤에 그 트럭의 무게를 추가합니다.
                queue2.add(currTruck);

            // [Case 2: 하중 초과로 새 트럭이 못 올라오는 경우]
            } else {

            // '다리 큐'(queue2)의 맨 뒤에 0 (빈 공간)을 추가합니다.
                queue2.add(0);

            }

        } // while 루프의 끝


        // --- 5. 결과 계산 및 출력 ---

        // 위 while 루프는 '마지막 트럭이 다리 입구에 막 진입한' 시점에 종료됩니다.
        // 이 마지막 트럭이 다리 출구까지 완전히 건너려면 다리 길이(w)만큼의 시간이 더 필요합니다.

        // 따라서, (지금까지 계산된 'time')에 (다리 길이 'w')를 더한 값을
        // 최종 시간으로 화면에 출력합니다.
        System.out.println(time+w);

    }
}