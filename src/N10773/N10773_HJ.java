package N10773;

import java.util.Scanner;
import java.util.Stack;

public class N10773_HJ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int input = sc.nextInt();
            if (input==0){
                stack.pop();
                continue;
            }
            stack.push(input);
        }
        int sum = 0;
        for(int num : stack){
            sum += num;
        }
        System.out.println(sum);
    }
}
