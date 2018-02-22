import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


public class Main {

    public static void AStar(Node start, Node Target){
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(start);
        Set<Node> closed = new HashSet<>();
        while (!pq.isEmpty()){
            Node current = pq.poll();
            if (current.equals(Target)){
                System.out.println(current.getG());
                printPath(current);
                return;
            }
            if (!closed.contains(current)){
                closed.add(current);
                for (Node adj : current.children()){
                    if (!closed.contains(adj)){
                        pq.offer(adj);
                    }
                }
            }
        }
    }

    private static void printPath(Node current) {
        if (current != null){
            printPath(current.getParent());
            if (current.getParent() != null) {
                System.out.println(current.getDirection());
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int side = (int)Math.sqrt(n+1);
        int[][] init = new int[side][side];

        for (int i=0;i<side;i++){
            for (int j=0;j<side;j++){
                init[i][j] = in.nextInt();
            }
        }
        in.close();

        int[][] targetState = new int[side][side];
        int num = 1;
        for (int i=0;i<side;i++){
            for (int j=0;j<side;j++){
                targetState[i][j] = num;
                num++;
            }
        }
        targetState[side-1][side-1] = 0;

        Node start = new Node(init,0,null);
        Node target = new Node(targetState);
        long before = System.currentTimeMillis();
        AStar(start,target);
        long after = System.currentTimeMillis();
        System.out.println((after-before));
    }
}
