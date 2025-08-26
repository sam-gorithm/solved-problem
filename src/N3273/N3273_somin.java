import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] arr = new int[N];

        // 입력값을 빠르게 찾기 위한 HashSet 
        HashSet<Integer> set = new HashSet<>();

        // Set에 저장
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            set.add(arr[i]);
        }

        int X = sc.nextInt();

        int count = 0; // 쌍의 개수

        // arr[i]에 대해 (x - arr[i])가 존재하는지 확인 -> 쌍 가능 
        for (int i = 0; i < N; i++) {
            if (set.contains(X - arr[i])) {
                count++;
            }
        }

        // 각 쌍은 두 번씩 세어지므로 2로 나눠줌 
        System.out.println(count / 2);

    }
}