import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class N1158_somin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        // LinkedList에 1부터 N 까지의 값 저장 
        LinkedList<Integer> list = new LinkedList<>();
        IntStream.range(1, N + 1).forEach(x -> list.addLast(x));

        StringBuilder sb = new StringBuilder();
        int index = 0;
        sb.append("<");

        while (list.size() > 1) {
            // 현재 위치에서 k-1 만큼 다음 제거할 요소의 인덱스를 계산 
            // list의 크기로 나눠서 원형으로 순회 
            index = (index + K - 1) % list.size();
            sb.append(list.remove(index)).append(", ");
        }

        // 마지막 남은 요소를 추가 후 괄호 닫음 
        sb.append(list.remove()).append(">");
        System.out.println(sb);
    }
}