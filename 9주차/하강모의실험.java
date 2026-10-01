import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[][] g = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    g[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 아래방향 내려갈 때
            // 첫행 시작점
            for(int start = 0; start < N; start++){
                // 만약 시작점에 블록이 없으면 패스
                if(g[0][start] == 0){
                    continue;
                }

                // 만약 시작점에 블록이 있으면
                boolean stop = true;
                double G = 1;
                int down = 1;   // 다음에 확인할 행
                int size = 1;   // 내려가는 덩어리의 블록 수
                g[0][start] = 0;  // 내려간거 시작

                while(stop){
                    // 일단 내려갈 깊이 탐색 + G 계산
                    boolean fin = true;
                    while(fin){
                        // 배열 넘지 않고 0일 때
                        if(down < N && g[down][start] == 0){
                            down++;
                            G *= 1.9;
                        }
                        else{
                            fin = false;
                        }
                    }

                    // 바닥이면
                    if(down == N){
                        stop = false;
                        continue;
                    }

                    // 그후 F 계산 아래에 블록 몇개인지 파악
                    int F = 0;
                    boolean cnt = true;
                    while(cnt){
                        // 범위 벗어나지 않고 1일 때
                        if(down + F < N && g[down + F][start] == 1){
                            F++;
                        }
                        else{
                            cnt = false;
                        }
                    }

                    // 만약 아래에 저항력이 쎄면 if문
                    if(G <= F){
                        stop = false;
                    }
                    else{
                        // G 중력에 힘 더함
                        G += F;
                        // 내려가는 덩어리 수에 저항하는 덩어리 합치기
                        size += F;
                        // 합쳐진 블록 칸 비우기
                        for(int i = 0; i < F; i++){
                            g[down + i][start] = 0;
                        }
                        // 내려가는 시작점 맞추기
                        down += F;
                    }
                }

                // 덩어리를 최종 위치에 다시 채우기
                for(int i = down - size; i < down; i++){
                    g[i][start] = 1;
                }
            }






            // 오른쪽 정렬 시작점
            for(int start = 0; start < N; start++){
                if(g[start][0] == 0){
                    continue;
                }

                boolean stop = true;
                double G = 1;
                int right = 1;
                int size = 1;
                g[start][0] = 0;

                while(stop){
                    boolean fin = true;
                    while(fin){
                        if(right < N && g[start][right] == 0){
                            right++;
                            G *= 1.9;
                        }
                        else{
                            fin = false;
                        }
                    }

                    if(right == N){
                        stop = false;
                        continue;
                    }

                    int F = 0;
                    boolean cnt = true;
                    while(cnt){
                        if(right + F < N && g[start][right + F] == 1){
                            F++;
                        }
                        else{
                            cnt = false;
                        }
                    }

                    if(G <= F){
                        stop = false;
                    }
                    else{
                        G += F;
                        size += F;
                        for(int i = 0; i < F; i++){
                            g[start][right + i] = 0;
                        }
                        right += F;
                    }
                }

                for(int i = right - size; i < right; i++){
                    g[start][i] = 1;
                }
            }

            // 끝행, 끝열 몇갠지 카운트
            int down_total = 0;
            int right_total = 0;
            for(int i = 0; i < N; i++){
                if(g[N - 1][i] == 1){
                    down_total++;
                }
                if(g[i][N - 1] == 1){
                    right_total++;
                }
            }
            System.out.println("#" + tc + " " + down_total + " " + right_total);
        }
    }
}
