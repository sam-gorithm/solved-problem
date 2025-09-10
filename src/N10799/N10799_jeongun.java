package N10799;

import java.util.Scanner;
import java.util.Stack;

public class N10799_jeongun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

            String str = sc.next();
            Stack <Character> stack = new Stack<>(); //한 글자씩이니 char

            int sum = 0; //막대기 합

            for(int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    stack.push('('); // ( push

                } else { // ')' -> 레이저
                    stack.pop();

                    if (str.charAt(i-1) == '(') { //이전 문자랑 비교 -> '()'면 레이저니까
                        sum += stack.size(); //스택에 저장된 막대기 수 sum에 ++

                    } else { // '))' -> 막대기 끝났으니까.. 조각 +1
                        sum += 1;
                    }
                }
            }

            System.out.println(sum);
        }
    }