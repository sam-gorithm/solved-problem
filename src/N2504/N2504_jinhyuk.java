package N2504;

import java.util.Scanner;
import java.util.Stack;

//괄호의 값
public class N2504_jinhyuk {
    public static void main(String[] args) {

    	Scanner sc = new Scanner(System.in);
    	
        String str = sc.next();
        Stack<Character> stack = new Stack<>();
        
        int result = 0;
        int n = 1;	//배율
        for (int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);

            if(ch == '(') {
                //배율 2배, ')'만나 쌍 완성되면 누적배율 -> 점수
            	stack.push(ch);
                n *= 2;
            }else if(ch == '[') {
                //배율 3배, ']'만나 쌍 완성되면 누적배율 -> 점수
            	stack.push(ch);
                n *= 3;
            }else if(ch == ')') {
                //스택이 비었거나, 짝이 맞지않는 경우
                if (stack.isEmpty() || stack.peek() != '(') {
                    result = 0;
                    break;
                }

                //'(' 만나 쌍 완성되면 누적배율 -> 점수
                if(str.charAt(i - 1) == '(') {
                    result += n;
                }
                
                //괄호 쌍이루면 '(' 배율 2배 만큼 초기화
                stack.pop();
                n /= 2;

            }else if(ch == ']') {
            	//스택이 비었거나, 짝이 맞지않는 경우
                if(stack.isEmpty() || stack.peek() != '[') {
                    result = 0;
                    break;
                }
                
                //'[' 만나 쌍 완성되면 누적배율 -> 점수
                if(str.charAt(i - 1) == '[') {
                    result += n;
                }
                
                //괄호 쌍이루면 '[' 배율 3배 만큼 초기화
                stack.pop();
                n /= 3;
            }
        }//for

        //결과출력
        //스택에 괄호 남아있으면 올바르지 않은 괄호 쌍
        if(!stack.isEmpty()) {
            System.out.println(0);
        }else {
            System.out.println(result);
        }
    }	//main
    
    
}	//class

