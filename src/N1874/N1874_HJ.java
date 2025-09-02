package N1874;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class N1874_HJ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        List<Character> result = new ArrayList<>();
        int k = 1;
        for (int i = 0; i < N; i++) {
            int input = sc.nextInt();
            while(k<=input){
                stack.push(k);
                result.add('+');
                k++;
            }
            if (!stack.isEmpty() && stack.peek()==input){
                stack.pop();
                result.add('-');
            }else {
                System.out.println("NO");
                return;
            }
        }
        for(char sign:result){
            System.out.println(sign);
        }
    }
}
