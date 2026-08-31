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
            int up = 0;
            int down = 0;
            for(int i = 0; i < N - 1; i++){
                if(arr[i] - arr[i + 1] > 0){
                    if(arr[i] - arr[i + 1] > down){
                        down = arr[i] - arr[i + 1];
                    }
                }
                else{
                    if(arr[i + 1] - arr[i]> up){
                        up = arr[i + 1] - arr[i];
                    }
                }
            }
            
            System.out.println("#" + test_case + " " + up + " " + down);
		}
	}
}
