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
            // 보물상자의 한 변의 길이
            int length = N / 4;
            int K = sc.nextInt();
            // 짜르기 쉽게 String으로 받았다가 StringBuilder에 넣기
            String pwd = sc.next();
            // StringBuilder 쓴 이유가 딱히 없습니다. String으로 충분히 구현이 가능하고 단지 제가 구현할 때 StringBuilder가 먼저 떠올라서 사용함
            StringBuilder sb = new StringBuilder();
            sb.append(pwd);
            // 비밀번호를 중복을 제거하여 저장하기 편하게 하기 위해 List 자료구조 사용
            List<String> list = new ArrayList<>();
            
            // for문으로 sb길이만큼 다돌려서 완전탐색
            for(int i = 0; i < sb.length(); i++){
                //length 길이 만큼 짤라서 저장 근데 중복일 경우 저장하지 않음
                for(int j = 0; j < 4; j++){
                    if(!list.contains(sb.substring(j * length, (j + 1) * length))){
                    	list.add(sb.substring(j * length, (j + 1) * length));
                	}
                }
                sb.insert(0, sb.substring(sb.length() - 1));
                sb.deleteCharAt(sb.length() - 1);
            }
            
            // 저장된 list에 있는 16진수를 10진수로 저장
            int[] temp = new int[list.size()];
            for(int i = 0; i < list.size(); i++){
                temp[i] = Integer.parseInt(list.get(i), 16);
            }
            
            //순서 정렬
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < list.size(); j++){
                    if(temp[i] > temp[j]){
                        int swp = temp[i];
                        temp[i] = temp[j];
                        temp[j] = swp;
                    }
                }
            }
            
            System.out.println("#" + test_case + " " + temp[K - 1]);            
		}
	}
}
