import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] P;
    private static List<Integer>[] list;
    private static int res, sumA, sumB;
    private static boolean[] visited;
    private static boolean[] selected;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = new int[N];
        st = new StringTokenizer(br.readLine());

        list = new List[N];

        for (int i = 0; i < P.length; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < P.length; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int to = Integer.parseInt(st.nextToken())-1;
                list[i].add(to);
            }
        }
        res = Integer.MAX_VALUE;

        for (int i = 1; i < N; i++) { //한 구역에 1~N-1까지 고를 수 있다            
            selected = new boolean[N];
            comb(0, 0, i);
        }

        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static void comb(int start, int count, int goal) {//goal: a선거구의 구역수

        if (count == goal) {//원하는 수만큼 구역을 선택했다면
            int cnt = 0;
            sumA = 0;
            sumB = 0;
            visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(i, selected[i]);
                    cnt++;
                }
            }

            if (cnt == 2) {//무리의 개수가 2개이면
                res = Math.min(res, Math.abs(sumA - sumB)); //차이 구하기
                return;
            }

            return;

        }

        for (int i = start; i < N; i++) {
            selected[i] = true; //a선거구로 선택
            comb(i + 1, count + 1, goal); //start+1 이 아니라 i+1 (시간 초과)
            selected[i] = false;
        }

    }

    private static void dfs(int a, boolean isA) {

        if (isA) {
            sumA += P[a];
        } else {
            sumB += P[a];
        }

        for (Integer i : list[a]) { //연결되어 있는지
            if (!visited[i] && selected[i] == isA) { //연결된 것중 다른 선거구가 있는지 확인
                visited[i] = true;
                dfs(i, isA);
            }
        }

    }
}