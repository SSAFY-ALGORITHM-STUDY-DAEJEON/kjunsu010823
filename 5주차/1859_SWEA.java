/*

	1. 실수로 최댓값 찾는거에 break문 썼음
  2. ing형으로할 시 오버플로우 발생함 (int의 범위 2.1 * 10의 9승)
	3. 경우의 수 잘못계산(max가 끝에 있을 때 에러났음)

*/

import java.util.Scanner;
import java.io.FileInputStream;

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
            int[] arr = new int[N];
            for(int i = 0; i < N; i++){
                arr[i] = sc.nextInt();
            }
            int max = Integer.MIN_VALUE;
            int max_index = 0;
            int start_index = 0;
            long total = 0;

            while(start_index < N){
                // max, max_index 값
                for(int i = start_index; i < N; i++){
                	if(max < arr[i]){
                    	max = arr[i];
                    	max_index = i;
                	}
            	}
                
                //total에 계산
                for(int i = start_index; i < max_index; i++){
                    total += max - arr[i];
                }
                
                // 다음값 계산
                start_index = max_index + 1;
                max = 0;
            }
            
            System.out.println("#" + test_case + " " + total);
		}
	}
}
