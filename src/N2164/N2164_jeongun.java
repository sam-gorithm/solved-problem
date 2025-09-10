package N2164;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N2164_jeongun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= N; i++) { //1부터
            q.offer(i); //입력
        }
        while (q.size() > 1) { //한 장 남을 때까지
            q.poll(); //맨 위(입력 순서 상 첫 번째) 버리고
            q.offer(q.poll()); //그 다음 카드 뽑아서 맨 밑으로
        }
        System.out.println(q.poll());
    }
}