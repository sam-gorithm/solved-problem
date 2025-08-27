import java.util.Scanner;

public class N1475_JYR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();

        //0~9배열 생성
        int[] arr = new int[10];

        //숫자 갯수 카운트 및 저장
        for (int i = 0; i < n.length(); i++) {
            int num = Integer.parseInt(n.charAt(i)+"");
            arr[num]++;
        }

        //6, 9의 최대 필요 세트 구하기
        int sum = arr[6] + arr[9];
        if(sum % 2 == 0) {arr[6] = 0; arr[9] = sum/2;}
        else {arr[6] = 0; arr[9] = sum/2+1;}

        //필요한 최대 세트 개수 구하기
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 10; i++) {
            if(max < arr[i]) max = arr[i];
        }

        System.out.println(max);
    }
}

