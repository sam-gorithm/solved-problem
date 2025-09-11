package N2164;

import java.io.*;
import java.util.*;

public class N2146_seoyeon {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Deque<Integer> cards = new ArrayDeque<>();
        // 카드 입력받기
        for (int i = 1; i <= N; i++) {
            cards.add(i);
        }

        // 카드가 한 장 남을 때까지 반복함
        while (cards.size() > 1) {
            // 카드가 비어있지 않다면
            if (!cards.isEmpty()) {
                // 제일 앞의 카드를 버리고
                cards.remove();
                // 그 다음 위에 있는 카드를 바닥으로 버림
                cards.addLast(cards.remove());
            }
        }

        // 한 장 남기면 반복문을 탈출하므로 제일 위 값을 출력
        bw.write(cards.peek() + "");
        bw.flush();
        bw.close();
        br.close();
    }

}
