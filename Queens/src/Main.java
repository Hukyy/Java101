import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        in.close();
        Queens queens = new Queens(n);
        //System.out.println(queens);
        long before = System.currentTimeMillis();
        queens.minConflicts();
        long after = System.currentTimeMillis();
        System.out.println((after - before));
        //System.out.println(queens);
    }
}
