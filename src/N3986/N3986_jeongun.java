package N3986;

import java.util.Scanner;
import java.util.Stack;

public class N3986_jeongun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int count = 0;

        for(int tc = 1; tc <= T; tc++) {
            String str = sc.next();
            Stack<Character> stack = new Stack<>();

            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i); //문자 뽑기

                if(!stack.isEmpty() && stack.peek() == c) { //맨 위랑 비교해서 같으면 pop
                    stack.pop();
                } else { //아니면 push
                    stack.push(c);
                }
            }
            if(stack.isEmpty()) { //비어 있으면 좋은 단어 ..
                count++;
            }
        }
        System.out.println(count);
    }
}