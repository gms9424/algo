import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//말이 되고픈 원숭이
public class Main {
    private static int K;
    private static int R;
    private static int C, res;
    private static int[][] map;
    static int hdr[] = {-2, -1, 1, 2, -2, -1, 1, 2};
    static int hdc[] = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {-0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = Integer.MAX_VALUE;

        bfs(0, 0);

        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static void bfs(int sr, int sc) {

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sr, sc, 0, 0));
        boolean[][][] visited = new boolean[R][C][K+1];
        visited[sr][sc][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int count = cur.count;
            int ncheck = cur.check;

            if (r == R - 1 && c == C - 1) {
                res = count;
                return;
            }

            for (int d = 0; d<dr.length; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];
                ncheck = cur.check;

                if(nr<0 || nc< 0 || nr>=R || nc>=C || map[nr][nc]==1 || visited[nr][nc][ncheck]){
                    continue;
                }

                visited[nr][nc][ncheck] = true;
                q.add(new Node(nr,nc,count+1, ncheck)); //원숭이 걸음

            }

            if(ncheck<K){//아직 기회가 더 남았다면
                for (int d=0;d<hdr.length;d++){
                    int nr = r+hdr[d];
                    int nc = c+hdc[d];
                    ncheck = cur.check;

                    if(nr<0 || nc< 0 || nr>=R || nc>=C || map[nr][nc]==1 || visited[nr][nc][ncheck+1]){
                        continue;
                    }

                    visited[nr][nc][ncheck+1] = true;
                    q.add(new Node(nr,nc,count+1, ncheck+1)); //말 걸음
                }
            }
        }

    }

    static class Node {
        int r, c, count, check;

        public Node(int r, int c, int count, int check) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.check = check;
        }
    }
}