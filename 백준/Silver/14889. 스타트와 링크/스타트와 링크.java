import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int res;
    private static int[][] map;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        res = Integer.MAX_VALUE;
        visited = new boolean[N];
        comb(0, 0);

        System.out.println(res);
    }

    private static void comb(int start, int count) { //start team

        if (count == N / 2) {
            res = Math.min(res, calc());
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                comb(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }

    private static int calc() {

        int a = 0, b = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i] && visited[j]) { //start
                    a += map[i][j];
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i] && !visited[j]) {//link
                    b += map[i][j];
                }
            }
        }

        return Math.abs(a-b);

    }
}