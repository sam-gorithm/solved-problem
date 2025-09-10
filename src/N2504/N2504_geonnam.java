package N2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class N2504_geonnam {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String text = br.readLine();
		
		//Character로 선언하니까 곱할 때 숫자가 두 자리 이상이 되면 문제 발생
		Deque<Object> stack = new ArrayDeque<>();
		
		for(int i=0;i<text.length();i++) {
			char c = text.charAt(i);
			
			if(c == '(' || c == '[') {
				stack.push(c); // 열린 괄호면 push
			}else {
				if(stack.isEmpty()) { //뺄 것이 없으면 오류
					System.out.println(0);
					return;
				}
				
				if (c == ')') {
                    int value = 0;
                    boolean flag = false; // while문 빠져 나오고 짝이 안 맞을때
                    while (!stack.isEmpty()) {
                        Object top = stack.pop();
                        
                        if (top instanceof Character) { // top이 괄호일 때
                            char ch = (char) top;
                            if (ch == '(') {
                                if (value == 0) value = 1; // 괄호 안 값 없으면 1
                                stack.push(value * 2); // 있으면 2 곱하기
                                flag = true;
                                break; //while문 빠져 나오기
                            } else {
                                // 괄호 짝 안 맞음
                                System.out.println(0);
                                return;
                            }
                        } else {
                            // 숫자는 누적
                            value += (int) top;
                        }
                    }
                    if(!flag) {
                    	System.out.println(0);
                    	return;
                    }
                } else if (c == ']') { // )와 숫자말고 차이 없음
                    int value = 0;
                    boolean flag = false;
                    while (!stack.isEmpty()) {
                        Object top = stack.pop();
                        
                        if (top instanceof Character) {
                            char ch = (char) top;
                            if (ch == '[') {
                                if (value == 0) value = 1;
                                stack.push(value * 3); 
                                flag = true;
                                break;
                            } else {
                                System.out.println(0);
                                return;
                            }
                        } else {
                            value += (int) top;
                        }
                    }
                    if(!flag) {
                    	System.out.println(0);
                    	return;
                    }
                } else {
                    // 다른 문자일 경우도 0 출력 후 종료
                    System.out.println(0);
                    return;
                }
			}
		}
		
		int result = 0;
		while (!stack.isEmpty()) {
		    Object top = stack.pop();
		    if (top instanceof Character) { // 괄호 남아있으면 종료
		        System.out.println(0);
		        return;
		    }
		    result += (int) top; //남아있는 수 더하기
		}
		System.out.println(result);
	}
}
