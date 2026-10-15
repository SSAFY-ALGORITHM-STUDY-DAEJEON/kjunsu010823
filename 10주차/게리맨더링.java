import java.io.*;
import java.util.*;

public class Main {
    static int N, min;
    static int[] pop;
    static boolean[][] link;
    static boolean[] sel;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        pop = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pop[i] = Integer.parseInt(st.nextToken());
        }

        link = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int v = Integer.parseInt(st.nextToken()) - 1; // 0번부터 쓰도록 -1
                link[i][v] = true;
                link[v][i] = true;
            }
        }

        sel = new boolean[N];
        min = Integer.MAX_VALUE;
        subset(0);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    static void subset(int idx) {
        // N개 구역을 다 정했으면 검사
        if (idx == N) {
            check();
            return;
        }

        // idx번 구역을 A구역에 넣는 경우
        sel[idx] = true;
        subset(idx + 1);

        // idx번 구역을 B구역에 넣는 경우
        sel[idx] = false;
        subset(idx + 1);
    }

    static void check() {
        // 각 구역의 출발점 찾기
        int a = -1;
        int b = -1;
        for (int i = 0; i < N; i++) {
            if (sel[i] && a == -1) {
                a = i;
            }
            if (!sel[i] && b == -1) {
                b = i;
            }
        }

        // 부분조합이라 한쪽이 비여있을 경우 탈락
        if (a == -1 || b == -1) {
            return;
        }

        // A구역, B구역에서 각각 dfs 한 번씩
        visited = new boolean[N];
        dfs(a);
        dfs(b);

        // 방문 못 한 구역이 하나라도 있으면 어느 쪽이 끊긴 것
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                return;
            }
        }

        // 인구 차이 계산
        int sumA = 0;
        int sumB = 0;
        for (int i = 0; i < N; i++) {
            if (sel[i]) {
                sumA += pop[i];
            } else {
                sumB += pop[i];
            }
        }
        min = Math.min(min, Math.abs(sumA - sumB));
    }

    static void dfs(int idx) {
        visited[idx] = true;

        for (int next = 0; next < N; next++) {
            if (!link[idx][next]) {          // 인접 안 하면 패스
                continue;
            }
            if (visited[next]) {             // 이미 갔으면 패스
                continue;
            }
            if (sel[next] != sel[idx]) {     // 다른 구역이면 패스
                continue;
            }
            dfs(next);
        }
    }
}
