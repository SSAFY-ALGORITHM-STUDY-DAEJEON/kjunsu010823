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
            String two = sc.next();
            String three = sc.next();
            long ans = 0;
            // 각각의 모든 경우의 수 수집
            List<Long> two_10 = new ArrayList<>();
            List<Long> three_10 = new ArrayList<>();
            
            // 모든 경우의 수 String -> Integer
            for(int i = 0; i < two.length(); i++){
                StringBuilder sb = new StringBuilder(two);
                if(sb.charAt(i) == '0'){
                    sb.setCharAt(i, '1');
                }
                else if(sb.charAt(i) == '1'){
                    sb.setCharAt(i, '0');
                }
                two_10.add(Long.parseLong(sb.toString(), 2));
            }
            
            for(int i = 0; i < three.length(); i++){
                StringBuilder sb = new StringBuilder(three);
                if(sb.charAt(i) == '0'){
                    sb.setCharAt(i, '1');
                    three_10.add(Long.parseLong(sb.toString(), 3));
                    sb.setCharAt(i, '2');
                    three_10.add(Long.parseLong(sb.toString(), 3));
                }
                else if(sb.charAt(i) == '1'){
                    sb.setCharAt(i, '0');
                    three_10.add(Long.parseLong(sb.toString(), 3));
                    sb.setCharAt(i, '2');
                    three_10.add(Long.parseLong(sb.toString(), 3));
                }
                else if(sb.charAt(i) == '2'){
                    sb.setCharAt(i, '0');
                    three_10.add(Long.parseLong(sb.toString(), 3));
                    sb.setCharAt(i, '1');
                    three_10.add(Long.parseLong(sb.toString(), 3));
                }
            }
            
            // 매치되는거 찾기
            for(int i = 0; i < two_10.size(); i++){
                for(int j = 0; j < three_10.size(); j++){
                    if(two_10.get(i).equals(three_10.get(j))){
                        ans = two_10.get(i);
                        break;
                    }
                }
            }
            System.out.println("#" + test_case + " " + ans);
            
		}
	}
}
