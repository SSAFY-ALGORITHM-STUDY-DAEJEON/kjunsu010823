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
            int stick = 0;
            int cut = 0;
            String str = sc.next();
            
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '('){
                    if(str.charAt(i + 1) == ')'){
                        cut += stick;
                        i++;
                    }
                    else{
                        stick++;
                    }
                }
                else{
                    stick--;
                    cut++;
                }
            }
            System.out.println("#" + test_case + " " + cut);
		}
	}
}
