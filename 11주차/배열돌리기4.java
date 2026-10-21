import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, min;
	static int[][] map;
	static int[][] rot;   // rot[i] = {r, c, s}
	static boolean[] visited;
	static int[] sel;
	static int[][] copy;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		visited = new boolean[K];
		sel = new int[K];

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		rot = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			rot[i][0] = Integer.parseInt(st.nextToken()) - 1;   // r (0-index)
			rot[i][1] = Integer.parseInt(st.nextToken()) - 1;   // c (0-index)
			rot[i][2] = Integer.parseInt(st.nextToken());       // s
		}
		
		// 순열 함수  -> 로테이트 함수 -> 계산 함수
		perm(0);
		
		sb.append(min);
		System.out.print(sb);
	}
	
	static void perm(int idx) {
		if(idx == K) {
			rotate();
			return;
		}
		
		for(int i = 0; i < K; i++) {
			if(visited[i]) {
				continue;
			}
			visited[i] = true;
			sel[idx] = i;
			perm(idx + 1);
			visited[i] = false;
		}
	}
	
	static void rotate() {
		copy = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		// 순서별로 rot 실행 -> 각 범위별로 시계방향 턴
		for(int t = 0; t < K; t++) {
			int r = rot[sel[t]][0];
			int c = rot[sel[t]][1];
			int s = rot[sel[t]][2];
			
			// 바깥 껍질부터 한 겹씩 안으로
			for(int i = 0; i < s; i++) {
				int sx = r - s + i;
				int sy = c - s + i;
				int ex = r + s - i;
				int ey = c + s - i;
				
				// 왼쪽 위 꼭짓점 저장
				int temp = copy[sx][sy];
				// 왼쪽변 위로 땡기기
				for(int j = sx; j < ex; j++) {
					copy[j][sy] = copy[j + 1][sy];
				}
				// 아래변 왼쪽으로 땡기기
				for(int j = sy; j < ey; j++) {
					copy[ex][j] = copy[ex][j + 1];
				}
				// 오른쪽변 아래로 땡기기
				for(int j = ex; j > sx; j--) {
					copy[j][ey] = copy[j - 1][ey];
				}
				// 위쪽변 오른쪽으로 땡기기
				for(int j = ey; j > sy + 1; j--) {
					copy[sx][j] = copy[sx][j - 1];
				}
				// 원래 왼쪽 위 값은 오른쪽 한 칸으로
				copy[sx][sy + 1] = temp;
			}
		}
		
		calc();
	}
	
	static void calc() {
		for(int i = 0; i < N; i++) {
			int total = 0;
			for(int j = 0; j < M; j++) {
				total += copy[i][j];
			}
			if(total < min) {
				min = total;
			}
		}
	}
}
