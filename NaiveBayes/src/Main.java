import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        NaiveBayes bayes = new NaiveBayes("data.txt");
        bayes.algorithm();
    }
}
