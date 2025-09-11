package N18258;

import java.io.*;
import java.util.*;

public class N18258_seoyeon {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Integer> queue = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String inst = st.nextToken();
            if (inst.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                queue.add(num);
            } else if (inst.equals("pop")) {
                if (queue.isEmpty()) {
                    bw.write("-1");
                    bw.newLine();
                } else {
                    bw.write(queue.remove() + "");
                    bw.newLine();
                }
            } // pop
            else if (inst.equals("size")) {
                bw.write(queue.size() + "");
                bw.newLine();
            } else if (inst.equals("empty")) {
                if (queue.isEmpty()) {
                    bw.write("1");
                    bw.newLine();
                } else {
                    bw.write("0");
                    bw.newLine();
                }
            } // empty

            else if (inst.equals("front")) {
                if (queue.isEmpty()) {
                    bw.write("-1");
                    bw.newLine();
                } else {
                    bw.write(queue.peekFirst() + "");
                    bw.newLine();
                }
            } // front
            else if (inst.equals("back")) {
                if (queue.isEmpty()) {
                    bw.write("-1");
                    bw.newLine();
                } else {
                    bw.write(queue.peekLast() + "");
                    bw.newLine();
                }
            } // back
        } // 반복문
        bw.flush();
        bw.close();
        br.close();

    }

}
