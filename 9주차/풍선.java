import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 양끝 1로 고정
        int[] arr = new int[N + 2];
        arr[0] = 1;
        arr[N + 1] = 1;
        for(int i = 1; i <= N; i++){
            arr[i] = sc.nextInt();
        }

        // TODO: 알고리즘 구현
        int answer = 0;
        // 풍선 l번과 r번 사이 남기고 터트리기 양 옆 끝자락 2개는 1로 고정하기 위해 N + 2로 씀
        int[][] DP = new int[N + 2][N + 2];


        // 탑다운 : 작은거에서 큰거 계산
        // 총 길이 len
        // 시작점 l과 r
        // 마지막 터트릴 풍선 k
        // 점화식 k풍선을 마지막으로 생각하고 터트리기
        // 그중 (dp[l][k] + dp[k][r] + k터트린거 계산)값이 최대가 되는 값이 정답
        //        for(int len = 2; len <= N + 1; len++){          // 구간 길이
        //            for(int l = 0; l + len <= N + 1; l++){      // 왼쪽 경계 l부터 k까지
        //                int r = l + len;                        // 오른쪽 경계 (남겨둠)
        //                for(int k = l + 1; k < r; k++){         // 마지막에 터트릴 풍선
        //                    // 왼쪽 구간 + 오른쪽 구간 + k 터트릴 때 점수(양옆은 l, r)
        //                    int val = dp[l][k] + dp[k][r] + arr[l] * arr[k] * arr[r];
        //                    if(val > dp[l][r]){                 // 최댓값 갱신
        //                        dp[l][r] = val;
        //                    }
        //                }
        //            }
        //        }


        
        System.out.println(answer);
    }

    
}
