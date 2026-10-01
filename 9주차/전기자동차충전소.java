import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[] hx = new int[N];
            int[] hy = new int[N];
            int[] hd = new int[N];

            for (int i = 0; i < N; i++) {
                hx[i] = sc.nextInt();
                hy[i] = sc.nextInt();
                hd[i] = sc.nextInt();
            }
            // 완탐

            // 먼저 충전소 세울 때 정하기
            // hx[i]와 hy[i]에서 d떨어진데 교집합으로 cnt
            int[][] cnt = new int[31][31]; // cnt 안에 몇개 들어가는지
            boolean[][] isHouse = new boolean[31][31]; // 집 위치 true
            for(int i = 0; i < N; i++){
                isHouse[hx[i] + 15][hy[i] + 15] = true;
                for(int x = hx[i] - hd[i]; x <= hx[i] + hd[i]; x++){
                    for(int y = hy[i] - hd[i]; y <= hy[i] + hd[i]; y++){
                        if(Math.abs(x - hx[i]) + Math.abs(y - hy[i]) <= hd[i] && x >= -15 && x <= 15 && y >= -15 && y <= 15){
                            cnt[x + 15][y + 15]++;
                        }
                    }
                }
            }


            // 충전소 1개 세울 때
            //만약 교집합 중 N개가 있을 때 1개만  충전소 세우기
            // 그중에서 모든 집 사이 거리의 최솟값 구하기
            int ans = Integer.MAX_VALUE;
            for(int x = -15; x <= 15; x++){
                for(int y = -15; y <= 15; y++){
                    // 만약 교집합에 N 있을 때 그리고 위치가 집이 아닐 때
                    if(cnt[x + 15][y + 15] == N && !isHouse[x + 15][y + 15]){
                        // 모든 거리들의 합
                        int sum = 0;
                        for(int i = 0; i < N; i++){
                            sum += Math.abs(x - hx[i]) + Math.abs(y - hy[i]);
                        }
                        if(sum < ans){
                            ans = sum;
                        }
                    }
                }
            }
            if(ans != Integer.MAX_VALUE){
                System.out.println("#" + tc + "ans");
                continue;
            }


            //충전소 2개일 때 미구현...




          
        }
    }
}
