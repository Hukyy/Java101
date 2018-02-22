import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final char LEFT = '<';
    private static final char RIGHT = '>';
    private static final char EMPTY = '_';
    private static Set<String> visited;
    private static Deque<String> stack;

    private static List<String> legalMoves(String state){
        List<String> neighbours = new ArrayList<>();
        char[] current = state.toCharArray();
        int length = current.length;
        int index = state.indexOf(EMPTY);
        for (int i = index - 1;i >=index-2;i--){
            if (i<0){
                continue;
            }
            if (current[i] == RIGHT){
                current[i] = EMPTY;
                current[index] = RIGHT;
                String neighbour = new String(current);
                neighbours.add(neighbour);
                current[i] = RIGHT;
                current[index] = EMPTY;
            }
        }

        for (int i = index + 1;i <=index+2;i++){
            if (i>=length){
                continue;
            }
            if (current[i] == LEFT){
                current[i] = EMPTY;
                current[index] = LEFT;
                String neighbour = new String(current);
                neighbours.add(neighbour);
                current[i] = LEFT;
                current[index] = EMPTY;
            }
        }
        return neighbours;
    }

    private static void IDDFS(String start, String target) {
        int depth = 0;
        boolean flag = false;
        while (!flag) {
            visited = new HashSet<>();
            stack = new ArrayDeque<>();
            flag = dls(start, target, depth);
            depth++;
        }
        for (String frogs : stack) {
            System.out.println(frogs);
        }
    }

    private static boolean dls(String current, String target, int depth){
        visited.add(current);
        if (depth<0) {
            return false;
        }
        if (current.equals(target)){
            stack.push(current);
            return true;
        }
        for (String neighbour : legalMoves(current)){
            if (!visited.contains(neighbour) && dls(neighbour,target,depth-1)){
                stack.push(neighbour);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.close();
        StringBuilder startBuilder = new StringBuilder();
        for (int i=0;i<N;i++){
            startBuilder.append(RIGHT);
        }
        startBuilder.append(EMPTY);
        for (int i=0;i<N;i++){
            startBuilder.append(LEFT);
        }
        String start = startBuilder.toString();
        String target = startBuilder.reverse().toString();
        //long startTime = System.currentTimeMillis();
        IDDFS(start,target);
        //long endTime = System.currentTimeMillis();
        //System.out.println((endTime-startTime));
    }
}
