import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class N1181_jeongun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<String> list = new ArrayList<String>();
		
		for(int i = 0; i < N; i++) {
			list.add(sc.next());
		}
		Collections.sort(list, new Comparator<String>() {
			public int compare(String a, String b) {
				if(a.length() != b.length()) {
					return a.length() - b.length();
				}
				return a.compareTo(b);
			}
		});
		
		if (list.size()>0) {
            System.out.println(list.get(0));
        }
		
        for (int i = 1; i < list.size(); i++) {
            if (!list.get(i).equals(list.get(i-1))) {
                System.out.println(list.get(i));
            }
        }
	}
}
