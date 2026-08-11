/*

오답노트:
1. 일단 이 문제 풀 경험치가 없음 걍 개어려움
2. 백트래킹에서 논리가 아직 미숙함 기본 백트랙킹 문제 풀어야함
3. dfs 매개변수 설정이 좀 어려움

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] map;
    static ArrayList<int[]> cores;
    static int maxCore;
    static int minWire;
    
    // 상, 하, 좌, 우 델타 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    
                    // 코어(1)인 경우
                    if (map[i][j] == 1) {
                        // 가장자리 코어는 이미 연결된 것으로 간주하므로 리스트에 넣지 않음
                        if (i == 0 || i == N - 1 || j == 0 || j == N - 1) continue;
                        cores.add(new int[]{i, j});
                    }
                }
            }

            maxCore = 0;
            minWire = Integer.MAX_VALUE;

            // DFS 탐색 시작 (인덱스, 연결된 코어 수, 전선 길이의 합)
            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + minWire);
        }
    }

    static void dfs(int idx, int cCnt, int wLen) {
        // [핵심] 가지치기: 남은 코어를 다 연결해도 현재까지의 최대 코어 수보다 작다면 탐색 종료
        if (cCnt + (cores.size() - idx) < maxCore) return;

        // 모든 코어를 다 확인한 경우 (기저 조건)
        if (idx == cores.size()) {
            if (maxCore < cCnt) {
                maxCore = cCnt;
                minWire = wLen;
            } else if (maxCore == cCnt) {
                // 코어 수가 같다면 전선 길이가 더 짧은 것을 선택
                minWire = Math.min(minWire, wLen);
            }
            return;
        }

        int[] cur = cores.get(idx);
        int r = cur[0];
        int c = cur[1];

        // 4방향 탐색
        for (int d = 0; d < 4; d++) {
            int count = 0;
            int nr = r;
            int nc = c;
            
            // 해당 방향으로 전선을 끝까지 놓아봄
            while (true) {
                nr += dx[d];
                nc += dy[d];
                
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) break; // 가장자리에 도달함 (연결 성공)
                if (map[nr][nc] != 0) { // 빈칸(0)이 아니면(코어나 다른 전선을 만나면)
                    count = 0; // 연결 불가 처리
                    break;
                }
                count++;
            }

            // 연결이 가능하다면
            if (count > 0) {
                // 전선 깔기 (맵을 2로 표시)
                fill(r, c, d, count, 2);
                
                // 다음 코어 탐색
                dfs(idx + 1, cCnt + 1, wLen + count);
                
                // 백트래킹: 깔았던 전선 다시 거두기 (맵을 0으로 복구)
                fill(r, c, d, count, 0);
            }
        }
        
        // 현재 코어를 아예 연결하지 않고 다음 코어로 넘어가는 경우도 반드시 고려해야 함
        dfs(idx + 1, cCnt, wLen);
    }

    // 전선을 깔거나 지우는 함수
    static void fill(int r, int c, int d, int count, int val) {
        int nr = r;
        int nc = c;
        for (int i = 0; i < count; i++) {
            nr += dx[d];
            nc += dy[d];
            map[nr][nc] = val;
        }
    }
}
