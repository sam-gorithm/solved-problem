import java.util.Scanner;
import java.util.ArrayDeque;

public class N14891_somin {

    static ArrayDeque<Integer>[] gear = new ArrayDeque[5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= 4; i++) {
            gear[i] = new ArrayDeque<>();
            String line = sc.next();
            for (int j = 0; j < 8; j++) {
                gear[i].add(line.charAt(j) - '0');
            }
        }

        int K = sc.nextInt(); // 회전 횟수

        for (int i = 0; i < K; i++) {
            int gearNum = sc.nextInt(); // 회전시킬 톱니바퀴 번호
            int direction = sc.nextInt(); // 회전 방향 

            // 톱니바퀴가 어느 방향으로 회전할지
            int[] directions = new int[5];
            directions[gearNum] = direction;

            // 왼쪽 톱니바퀴
            for (int j = gearNum - 1; j >= 1; j--) {
                // 현재 톱니와 오른쪽 톱니의 맞닿는 극이 다르면
                if (getEle(j, 2) != getEle(j + 1, 6)) {
                    // 오른쪽 톱니바퀴의 반대 방향으로 회전
                    directions[j] = -directions[j + 1];
                } else {
                    // 같으면 더 이상 전파되지 않음
                    break;
                }
            }

            // 오른쪽 톱니바퀴
            for (int j = gearNum + 1; j <= 4; j++) {
                // 현재 톱니와 왼쪽 톱니의 맞닿는 극이 다르면
                if (getEle(j, 6) != getEle(j - 1, 2)) {
                    // 왼쪽 톱니바퀴의 반대 방향으로 회전
                    directions[j] = -directions[j - 1];
                } else {
                    // 같으면 더 이상 전파되지 않음
                    break;
                }
            }
            
            // 모든 톱니바퀴를 한 번에 회전 
            for (int j = 1; j <= 4; j++) {
                rotate(j, directions[j]);
            }
        }

        int totalScore = 0;
        if (gear[1].peekFirst() == 1) totalScore += 1;
        if (gear[2].peekFirst() == 1) totalScore += 2;
        if (gear[3].peekFirst() == 1) totalScore += 4;
        if (gear[4].peekFirst() == 1) totalScore += 8;
        
        System.out.println(totalScore);

    }

    public static void rotate(int gearNum, int direction) {
        if (direction == 1) { // 시계 방향 회전
            // 마지막 원소를 뽑아서 맨 앞에 추가
            gear[gearNum].addFirst(gear[gearNum].pollLast());
        } else if (direction == -1) { // 반시계 방향 회전
            // 첫 번째 원소를 뽑아서 맨 뒤에 추가
            gear[gearNum].addLast(gear[gearNum].pollFirst());
        }
    }


    public static int getEle(int gearNum, int index) {
        // 배열로 변환하여 확인
        return gear[gearNum].toArray(new Integer[0])[index];
    }
}