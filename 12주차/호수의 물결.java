/*
  7단계 흙탕물 일기 할 때 임시 저장할 (x, y) 값을 만들어야 했는데 못만듬 -> 임시 저장 변수가 2개 이상일 때 문제를 못풀어봐서 당황함
  나머지는 자잘한 실수
*/

import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k, t, total, count;
    static ArrayList<Integer>[] stone;
    static int[][] weapon, water, power;
    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> point;
    static boolean[][] tempgroup;
    static ArrayList<Integer> groupX, groupY; // 이번 덩어리에 속한 칸들


    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            weapon = new int[k][6];
            water = new int[n][m]; // 혼탁도
            power = new int[n][m]; // 물결 세기
            point = new ArrayList<>();
            stone = new ArrayList[k];
            for (int i = 0; i < k; i++) {
                stone[i] = new ArrayList<>();
            }

            
            // (c, s, d, a, h, p) 열, 발사 시각, 발사 간격, 돌 무게, 날아가는 거리, 돌의 수
            for(int i = 0; i < k; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 6; j++){
                    weapon[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            t = 1;
            simul();
            
            sb.append("#").append(tc).append(" ");
            // 1번째 턴부터 마지막 턴까지 점수 출력
            for(int i = 0; i < point.size(); i++){
                sb.append(point.get(i)).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void simul(){
        boolean check = true;
        while(check){
            // 이번 턴 물결 세기 초기화
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    power[i][j] = 0;
                }
            }

            // 1. 돌 이동: 날아가는 중인 돌 모두 1씩 옮기기
            for(int i = 0; i < k; i++){
                for(int j = 0; j < stone[i].size(); j++){
                    stone[i].set(j, stone[i].get(j) + 1);
                }
            }

            // 2. 돌 발사
            for(int i = 0; i < k; i++){
                // 만약 돌 발사 t시간이고, 돌 갯수가 0 이상이면
                if(weapon[i][1] == t && weapon[i][5] > 0){
                    stone[i].add(1);
                    weapon[i][1] += weapon[i][2];
                    weapon[i][5] -= 1;
                }
            }

            // 3. 돌 낙하: 먼저 날린 돌이 맨 앞(0번)에 있으므로 맨 앞만 확인
            for(int i = 0; i < k; i++){
                if(!stone[i].isEmpty() && stone[i].get(0) == weapon[i][4]){
                    stone[i].remove(0);
                    fall(i);
                }
            }

            // 4. 점수 계산
            total = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(Math.max(0, power[i][j] - water[i][j]) > 0){
                        total += power[i][j] - water[i][j];
                    }
                }
            }
            point.add(total);

            // 5. 흙탕물 가라앉기 tempwater 는 water를 카피한거, 혼탁도가 동시에 떨어져서
            int[][] tempwater = new int[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    tempwater[i][j] = water[i][j];
                }
            }
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    // 흙탕물이 있는 칸만
                    if(tempwater[i][j] == 0){
                        continue;
                    }
                    boolean found = true;
                    for(int d = 0; d < 4; d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(nx >= 0 && nx < n && ny >= 0 && ny < m){
                            if(tempwater[nx][ny] > 0){
                                found = true;
                            }
                            else{
                                found = false;
                                break;
                            }
                        }
                        else{
                            found = false;
                            break;
                        }
                    }
                    if(found){
                        water[i][j]--;
                    }
                    else{
                        water[i][j] -= 2;
                        if(water[i][j] < 0){
                            water[i][j] = 0;
                        }
                    }
                }
            }

            // 6. 물살
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m - 1; j++){
                    water[i][j] = water[i][j + 1];
                }
            }
            for(int i = 0; i < n; i++){
                water[i][m - 1] = 0;
            }

            // 7. 흙탕물 일기
            //tempgroup해서 덩어리 진걸 (물결 세기가 0보다 큰 칸)
            tempgroup = new boolean[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(power[i][j] > 0){
                        tempgroup[i][j] = true;
                    }
                    else{
                        tempgroup[i][j] = false;
                    }
                }
            }

            // DFS해서 true인것만 카운트
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(tempgroup[i][j]){
                        total = 0;
                        count = 0;
                        groupX = new ArrayList<>();
                        groupY = new ArrayList<>();
                        tempgroup[i][j] = false;
                        DFS(i, j);

                        // 덩어리 평균을 덩어리 칸 전부에 더하기
                        int avg = total / count;
                        for(int g = 0; g < groupX.size(); g++){
                            water[groupX.get(g)][groupY.get(g)] += avg;
                        }
                    }
                }
            }

            // 종료 조건: 50턴이거나, 날릴 돌도 날아가는 돌도 없으면
            boolean remain = false;
            for(int i = 0; i < k; i++){
                if(weapon[i][5] > 0 || !stone[i].isEmpty()){
                    remain = true;
                }
            }
            if(t == 50 || !remain){
                check = false;
            }

            t++;
        }
    }

    static void fall(int idx){
        int x = n - weapon[idx][4];
        int y = weapon[idx][0];

        // 모든 칸을 다 보면서 물결 세기 더하기
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int val = 3 * weapon[idx][3] - 5 * (Math.abs(x - i) + Math.abs(y - j));
                if(val > 0){
                    power[i][j] += val;
                }
            }
        }
    }

    static void DFS(int x, int y){
        total += power[x][y];
        count += 1;
        groupX.add(x);
        groupY.add(y);
        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx >= 0 && nx < n && ny >= 0 && ny < m){
                if(tempgroup[nx][ny]){
                    tempgroup[nx][ny] = false;
                    DFS(nx, ny);
                }
            }
        }
    }
}
