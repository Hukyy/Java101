import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Node implements Comparable<Node>{
    private int[][] state;
    private int g;
    private int len;
    private Node parent;
    private String direction;
    private int f;
    private static final String[] dir = {"right","left","down","up"};

    public Node(int[][]state){
        this.state = state;
    }

    public Node(int[][] state, int g, Node parent){
        this.len = state.length;
        this.state = new int[len][len];
        for (int i=0;i<len;i++){
            for (int j=0;j<len;j++){
                this.state[i][j] = state[i][j];
            }
        }
        this.g = g;
        this.parent = parent;
        this.f = this.f();
    }


    private int heuristic(){

        int manhattan = 0;
        for (int i=0;i<len;i++){
            for (int j=0;j<len;j++){
                if (state[i][j] == 0)
                    continue;
                int number = state[i][j] - 1;
                int targetRow = number / len;
                int targetCol = number % len;

                manhattan+= Math.abs(i - targetRow);
                manhattan+= Math.abs(j-targetCol);

            }
        }
        return manhattan;
    }

    private int f(){
        return g + heuristic();
   }

    private boolean valid(int x, int y){
        return x>=0 && x<len && y>=0 && y<len;
    }

    public List<Node> children(){
        List<Node> result = new ArrayList<>();
        int row = -1, col = -1;
        for (int i=0;i<len;i++){
            for (int j=0;j<len;j++){
                if (state[i][j] == 0){
                    row = i;
                    col = j;
                    break;
                }
            }
            if (row != -1){
                break;
            }
        }
        int[] dx = {0,0,-1,1};
        int[] dy = {-1,1,0,0};

        for (int i=0;i<4;i++){
            int x = row+dx[i];
            int y = col+dy[i];
            if (valid(x,y)){
                int current = state[x][y];
                state[x][y] = 0;
                state[row][col] = current;
                Node child = new Node(state,g+1,this);
                child.direction = dir[i];
                result.add(child);
                state[x][y] = current;
                state[row][col] = 0;
            }
        }
        return result;
    }

    public String getDirection(){
        return direction;
    }
    public Node getParent(){
        return parent;
    }
    public int getG(){
        return g;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return Arrays.deepEquals(state, node.state);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(state);
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(f, o.f);
    }
}
