import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class N13335_somin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 트럭의 수
        int w = sc.nextInt(); // 다리의 길이
        int L = sc.nextInt(); // 다리의 최대하중

        Queue<Integer> waitingTrucks = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            waitingTrucks.add(sc.nextInt());
        }

        int time = 0; // 총 걸린 시간
        int currentWeight = 0; // 현재 다리 위의 트럭 무게 합

        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        // 대기 트럭이 모두 다리 위로 올라갈 때까지 
        while (!waitingTrucks.isEmpty()) {
            time++; 

            currentWeight -= bridge.poll();

            int nextTruck = waitingTrucks.peek();

            if (currentWeight + nextTruck <= L) {
                // 올라갈 수 있다면 대기 큐에서 트럭을 빼서 다리에 올림 
                currentWeight += nextTruck;
                bridge.add(waitingTrucks.poll());
            } else {
                // 못 올라간다면 트럭 대신 빈 공간을 다리에 채워 넣음 
                bridge.add(0);
            }
        }

        time += w;

        System.out.println(time);

    }
}