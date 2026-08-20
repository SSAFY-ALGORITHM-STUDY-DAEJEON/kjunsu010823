/*

1. ArrayList 안써도 되는데 사용해서 더 어려워짐
2. dx, dy 이동 쓰면 되는데 안써서 코드 길어짐
3. dx, dy에 이동하지 않음을 사용하면 되는데 사용안함
4. 전반적으로 dx, dy 사용 안하여 문제가 복잡해지고 코드길이에 따라 힘들어짐

*/
import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            // 이동시간 M
            int M = sc.nextInt();
            // BC의 개수 A
            int A = sc.nextInt();
            
            // 사용자 A의 이동 정보 (N -> M으로 수정)
            int[] userA = new int[M];
            for (int i = 0; i < M; i++) {
                userA[i] = sc.nextInt();
            }
            
            // 사용자 B의 이동 정보 (N -> M으로 수정)
            int[] userB = new int[M];
            for (int i = 0; i < M; i++) {
                userB[i] = sc.nextInt();
            }
            
            // BC 정보 (작성하신 배열 접근 방식 BC_X[j]를 유지하기 위해 기본 배열로 변경)
            int[] BC_X = new int[A];
            int[] BC_Y = new int[A];
            int[] BC_C = new int[A];
            int[] BC_P = new int[A];
            
            for (int i = 0; i < A; i++) {
                BC_X[i] = sc.nextInt();
                BC_Y[i] = sc.nextInt();
                BC_C[i] = sc.nextInt();
                BC_P[i] = sc.nextInt();
            }
            
            // A, B 유저의 위치 (문제 좌표계에 맞게 1과 10으로 세팅)
            int userA_X = 1;
            int userA_Y = 1;
            int userB_X = 10;
            int userB_Y = 10;
            
            // 총합
            int total = 0;

            // --- 초기 위치에서 충전 가능한지 확인 ---
            boolean[] initA_BC = new boolean[A];
            boolean[] initB_BC = new boolean[A];
            for (int j = 0; j < A; j++) {
                if (Math.abs(BC_X[j] - userA_X) + Math.abs(BC_Y[j] - userA_Y) <= BC_C[j]) {
                    initA_BC[j] = true;
                }
                if (Math.abs(BC_X[j] - userB_X) + Math.abs(BC_Y[j] - userB_Y) <= BC_C[j]) {
                    initB_BC[j] = true;
                }
            }

            int initialTemp = 0;
            for (int a = 0; a < A; a++) {
                for (int b = 0; b < A; b++) {
                    int sum = 0;
                    if (initA_BC[a] && initB_BC[b]) {
                        if (a == b) sum = BC_P[a]; // 반반 나누면 합은 본래 값과 동일
                        else sum = BC_P[a] + BC_P[b];
                    } else if (initA_BC[a]) {
                        sum = BC_P[a];
                    } else if (initB_BC[b]) {
                        sum = BC_P[b];
                    }
                    if (initialTemp < sum) initialTemp = sum;
                }
            }
            total += initialTemp;


            // M번의 이동시간(1~M) 진행
            for (int i = 0; i < M; i++) {
                // 1. A, B 이동
                if (userA[i] == 0) {
                } else if (userA[i] == 1) {
                    userA_Y--;
                } else if (userA[i] == 2) {
                    userA_X++;
                } else if (userA[i] == 3) {
                    userA_Y++;
                } else if (userA[i] == 4) {
                    userA_X--;
                }

                if (userB[i] == 0) {
                } else if (userB[i] == 1) {
                    userB_Y--;
                } else if (userB[i] == 2) {
                    userB_X++;
                } else if (userB[i] == 3) {
                    userB_Y++;
                } else if (userB[i] == 4) {
                    userB_X--;
                }

                // 2. A, B에 사용 가능한 BC 찾기
                boolean[] userA_BC = new boolean[A];
                boolean[] userB_BC = new boolean[A];
                for (int j = 0; j < A; j++) {
                    // math.abs -> Math.abs 로 대소문자 수정
                    if (Math.abs(BC_X[j] - userA_X) + Math.abs(BC_Y[j] - userA_Y) <= BC_C[j]) {
                        userA_BC[j] = true;
                    }
                    if (Math.abs(BC_X[j] - userB_X) + Math.abs(BC_Y[j] - userB_Y) <= BC_C[j]) {
                        userB_BC[j] = true;
                    }
                }

                // 3. 그리디로 사용 가능한 BC 중 제일 충전량 많은 것을 선택
                int temp = 0;
                for (int a = 0; a < A; a++) {
                    for (int b = 0; b < A; b++) {
                        int sum = 0;
                        // A, B 둘 다 BC 구역에 있을 때
                        if (userA_BC[a] && userB_BC[b]) {
                            // 같은 BC 선택 시 (어차피 반으로 나누고 더하므로 원래 충전량과 같음)
                            if (a == b) {
                                sum = BC_P[a];
                            } else {
                                sum = BC_P[a] + BC_P[b];
                            }
                        } 
                        // A만 BC 구역에 있을 때
                        else if (userA_BC[a]) {
                            sum = BC_P[a];
                        } 
                        // B만 BC 구역에 있을 때
                        else if (userB_BC[b]) {
                            sum = BC_P[b];
                        }

                        // 최댓값 갱신
                        if (temp < sum) {
                            temp = sum;
                        }
                    }
                }
                total += temp;
            }

            // 선언되지 않은 total_A + total_B 대신 total 변수 출력
            System.out.println("#" + test_case + " " + total);
        }
    }
}
