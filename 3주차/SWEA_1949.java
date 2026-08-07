import java.util.Scanner;

class Solution {
    // 여러 메서드에서 공유할 변수들은 static으로 선언
    static int N, K, max;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    
    /*
    오답노트
    1. 일단 변수 셋팅에서 visited를 사용하지 않음 ---->>>>   조건 자체가 단방향을 강제할 때 visited 쓰는 습관 들이기
    2. 백트레킹에 미숙함  ---->>>> 백 트래킹으로 데이터 원상복구
    */
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            K = sc.nextInt();
            map = new int[N][N];
            visited = new boolean[N][N];
            max = 0;
            
            int highest = 0;

            // 맵 입력 및 최고 높이 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] > highest) {
                        highest = map[i][j];
                    }
                }
            }
            
            // 최고 높이의 모든 봉우리에서 탐색 시작
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == highest) {
                        visited[i][j] = true;
                        // 자기 자신도 길이에 포함되므로 count는 1부터 시작
                        dfs(i, j, 1, true);
                        visited[i][j] = false; // 탐색 종료 후 방문 해제
                    }
                }
            }
            System.out.println("#" + test_case + " " + max);
        }
    }
    
    public static void dfs(int x, int y, int count, boolean chance) {
        // 매 탐색마다 최대 길이 갱신
        if (count > max) {
            max = count;
        }

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 맵 범위를 벗어나지 않고, 아직 방문하지 않은 곳일 때
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                
                // 1. 공사 없이 그냥 갈 수 있는 경우
                if (map[x][y] > map[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, count + 1, chance);
                    visited[nx][ny] = false; // 백트래킹
                } 
                // 2. 그냥 갈 수는 없지만, 공사 찬스가 있고 깎아서 갈 수 있는 경우
                else if (chance && map[nx][ny] - K < map[x][y]) {
                    visited[nx][ny] = true;
                    int originalHeight = map[nx][ny]; // 원래 높이 백업
                    
                    // 핵심: 뒤의 경로를 위해 딱 필요한 만큼만(현재 높이보다 1 낮게) 깎음
                    map[nx][ny] = map[x][y] - 1; 
                    
                    dfs(nx, ny, count + 1, false); // 찬스를 사용했으므로 false 전달
                    
                    map[nx][ny] = originalHeight; // 지형 복구 (백트래킹)
                    visited[nx][ny] = false; // 방문 해제 (백트래킹)
                }
            }
        }
    }
}
