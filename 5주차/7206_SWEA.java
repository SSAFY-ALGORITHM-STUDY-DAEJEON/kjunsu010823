/*

1. DP 또는 DFS, BFS 로 푸는건 알았으나 일주일 반동안 코테 손 놓았더니 감을 잃음
2. 종료 조건 하나를 발견 못함 : 내가 나눈 arr가 끝까지 가서 조회했을 때 끝에 걸 출력하고 종료
3. 이제 슬슬 함수들이 길어져서 한 코드에 다 못넣음 -> 이제 슬슬 함수 분리하기
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

class Solution
{
    // 전역변수로 둠 -> 전역함수가 읽게
    static int[] arr = new int[100000];

    public static void main(String args[]) throws Exception
    {
        //System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

      // -1로 초기화해서 -1을 읽었을 때 종료 유도
        Arrays.fill(arr, -1);

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();

            // DP함수를 사용
            System.out.println("#" + test_case + " " + dp(n));
        }
        sc.close();
    }

    static int dp(int n) {

        // 종료 기저 조건 : 10 이하 종료
        if (n < 10) return 0;

        // 만약 -1이 아니고 문자의 끝이면 그대로 출력
        if (arr[n] != -1) return arr[n];
        
        int maxTurns = 0;
        String s = Integer.toString(n);
        
        ArrayList<ArrayList<String>> allSplits = new ArrayList<>();
        generateSplits(s, 0, new ArrayList<>(), allSplits);

        // 이건 AI가 구현해줌 내가 한 것 아님
        // 곱셈 진행
        for (ArrayList<String> parts : allSplits) {
            int prod = 1;
            for (String part : parts) {
                prod *= Integer.parseInt(part);
            }

            // 제일 많은 진행을 한 것을 선
            maxTurns = Math.max(maxTurns, 1 + dp(prod));
        }
        
        return arr[n] = maxTurns;
    }

    // 이건 그냥 문자 나눠주는거
    static void generateSplits(String s, int start, ArrayList<String> current, ArrayList<ArrayList<String>> allSplits) {
        if (start == s.length()) {
            if (current.size() > 1) {
                allSplits.add(new ArrayList<>(current));
            }
            return;
        }
        
        for (int i = start + 1; i <= s.length(); i++) {
            current.add(s.substring(start, i));
            generateSplits(s, i, current, allSplits);
            current.remove(current.size() - 1);
        }
    }
}
