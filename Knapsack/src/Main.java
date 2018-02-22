public class Main {
    public static void main(String[] args) {
        GeneticAlgorithm algorithm = new GeneticAlgorithm(500, 0.9, 0.5);
        Chromosome chrom = algorithm.algorithm();
        System.out.println(chrom);
    }
}
