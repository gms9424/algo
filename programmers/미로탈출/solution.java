import java.util.Queue;
import java.util.LinkedList;

public class solution {

    char[][] map;
    int answer = Integer.MAX_VALUE;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    int R, C;

    public int solution(String[] maps) {

        R = maps.length;
        C = maps[0].length();

        map = new char[R][C];

        for (int i = 0; i < maps.length; i++) {
            map[i] = maps[i].toCharArray();
        }

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (map[i][j] == 'S') {
                    bfs1(i, j);
                    break;
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public void bfs1(int sr, int sc) {

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sr, sc, 0));
        boolean[][] visited = new boolean[R][C];
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int r = node.r;
            int c = node.c;
            int ncount = node.count;

            if (map[r][c] == 'L') {
                answer = ncount;
                bfs2(r, c, ncount);
            }

            for (int d = 0; d < dc.length; d++) {
                int nr = dr[d] + r;
                int nc = dc[d] + c;

                if (nr >= R || nr < 0 || nc >= C || nc < 0 || map[nr][nc] == 'X' || visited[nr][nc] == true) {
                    continue;
                }

                visited[nr][nc] = true;
                q.add(new Node(nr, nc, ncount + 1));
            }

        }

    }

    public void bfs2(int sr, int sc, int res) {

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sr, sc, 0));
        boolean[][] visited = new boolean[R][C];
        visited[sr][sc] = true;


        while (!q.isEmpty()) {
            Node node = q.poll();
            int r = node.r;
            int c = node.c;
            int ncount = node.count;

            if (map[r][c] == 'E') {
                answer += ncount;
                return;
            }

            for (int d = 0; d < dc.length; d++) {
                int nr = dr[d] + r;
                int nc = dc[d] + c;

                if (nr >= R || nr < 0 || nc >= C || nc < 0 || map[nr][nc] == 'X' || visited[nr][nc] == true) {
                    continue;
                }

                visited[nr][nc] = true;
                q.add(new Node(nr, nc, ncount + 1));
            }
        }

        if (answer == res) {
            answer = Integer.MAX_VALUE;
        }
    }
}

class Node {
    int r, c, count;

    public Node(int r, int c, int count) {
        this.r = r;
        this.c = c;
        this.count = count;
    }
}

