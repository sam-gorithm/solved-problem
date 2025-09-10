package N18258;

import java.util.*;

import java.util.*;

public class N18258_jeongun {
    static int max = 2000000;
    static int[] data = new int[max];
    static int head = 0, tail = 0;  // 큐 범위

    static void push(int x) {
        data[tail++] = x;
    }

    static int pop() {
        if (empty() == 1) {
            return -1;
        }
        return data[head++];
    }

    static int size() {
        return tail - head;
    }

    static int empty() {
        if(head == tail) {
            return 1;
        } else {
            return  0;
        }
    }

    static int front() {
        if (empty() == 1) {
            return -1;
        }
        return data[head];
    }

    static int back() {
        if (empty() == 1) {
            return -1;
        }
        return data[tail - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            String cmd = sc.next();

            if (cmd.equals("push")) { //명령
                int x = sc.nextInt();
                push(x);
            } else if (cmd.equals("pop")) {
                sb.append(pop()).append('\n');
            } else if (cmd.equals("size")) {
                sb.append(size()).append('\n');
            } else if (cmd.equals("empty")) {
                sb.append(empty()).append('\n');
            } else if (cmd.equals("front")) {
                sb.append(front()).append('\n');
            } else if (cmd.equals("back")) {
                sb.append(back()).append('\n');
            }
        }

        System.out.print(sb);
    }
}