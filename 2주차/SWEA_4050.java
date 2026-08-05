import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            int[] C = new int[N];
            int total = 0;
            for(int i = 0; i < N; i++){
                C[i] = sc.nextInt();
                total += C[i];
            }
            // 오름차순 정렬
            Arrays.sort(C);
            int pack = N / 3;
            int discount = 0;
            // 뒤에서 3번째 마다 오는 옷들중 묶음 만큼 할인
            for(int i = 0; i < pack; i++){
                discount += C[N - 3 - 3 * i];
            }
            System.out.println("#" + test_case + " " + (total - discount));
		}
	}
}
