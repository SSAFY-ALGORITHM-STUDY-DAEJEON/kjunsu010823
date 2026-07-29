import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            // 덤프 횟수
            int count = sc.nextInt();
            // 덤프
            int[] dump = new int[100];
            // 덤프 입력
            for(int i = 0; i < 100; i++){
                dump[i] = sc.nextInt();
            }
            
            // 덤프 횟수가 떨어질 때까지 무한 반복
            while(count > 0){
                // 덤프의 최대값, 최소값
  	      	    int max = Integer.MIN_VALUE;
    	        int min = Integer.MAX_VALUE;
                //횟수 차감
                count--;
                
                // 단순 탐색
                for(int i = 0; i < 100; i++){
                    if(max < dump[i]){
                        max = dump[i];
                    }
                    else if(min > dump[i]){
                        min = dump[i];
                    }
                }
                //최대값을 가진 덤프중 하나를 차감시키고 종료 (덤프값이 같은 것들이 존재하므로 하나 찾고 바로 종료)
                for(int i = 0; i < 100; i++){
                    if(max == dump[i]){
                        dump[i]--;
                        break;
                    }
                }
                //최소값도 마찬가지
                for(int i = 0; i < 100; i++){
                    if(min == dump[i]){
                        dump[i]++;
                        break;
                    }
                }
            }
            
            //최대값 최소값 차이 찾기
            int finalMax = Integer.MIN_VALUE;
            int finalMin = Integer.MAX_VALUE;
            for(int i = 0; i < 100; i++){
                if(finalMax < dump[i]){
                    finalMax = dump[i];
                }
                else if(finalMin > dump[i]){
                    finalMin = dump[i];
                }
            }
            
            System.out.println("#" + test_case + " " + (finalMax - finalMin));
		}
	}
}
