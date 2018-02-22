import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GeneticAlgorithm {
    private int p;
    private double r;
    private double m;
    private int maxWeight = 5000;
    private List<Chromosome> population;

    public GeneticAlgorithm(int p, double r, double m) {
        this.p = p;
        this.r = r;
        this.m = m;

        population = new ArrayList<>(p);
        for (int i = 0; i < p; i++) {
            population.add(new Chromosome());
        }
    }

    public Chromosome algorithm() {
        int bestValue = 0;
        int lastupdated = 0;
        sortPopulation();
        Chromosome bestChrom = getBestUsable();
        while (lastupdated < 50) {

            List<Chromosome> newPopulation = createNewPopulation();
            population = newPopulation;

            doTheCrossover();
            doTheMutation();

            lastupdated++;
            sortPopulation();
            Chromosome bestCandidate = getBestUsable();
            int candidateFitness = 0;
            if (bestCandidate != null) {
                candidateFitness = getChromosomeActuallFitness(bestCandidate);
            }
            if (bestValue < candidateFitness && bestCandidate.isUsable()) {
                bestChrom = new Chromosome(bestCandidate.getChromosome().clone());
                bestValue = candidateFitness;
                lastupdated = 0;
            }
        }
        System.out.println("\n" + bestChrom.fitness() + " weight: " + bestChrom.weight());
        return bestChrom;
    }

    private void doTheMutation() {
        Set<Integer> used = new HashSet<>();
        Random random = new Random();
        int populationSize = population.size();
        for (int i = 0; i < m * populationSize; i++) {
            int index;
            do {
                index = random.nextInt(populationSize);
            } while (used.contains(index));
            population.get(index).mutate();
            used.add(index);
        }
    }

    private void doTheCrossover() {
        int populationSize = population.size();
        Random random = new Random();
        for (int i = 0; i < r * p / 2; i++) {
            int firstChromosomeIndex = random.nextInt(populationSize);
            int secondChromosomeIndex;
            do {
                secondChromosomeIndex = random.nextInt(populationSize);
            }
            while (firstChromosomeIndex == secondChromosomeIndex);
            Chromosome first = population.get(firstChromosomeIndex);
            Chromosome second = population.get(secondChromosomeIndex);
            List<Chromosome> children = first.crossover(second);
            population.addAll(children);
        }
    }

    private List<Chromosome> createNewPopulation() {
        List<Chromosome> newPopulation = new ArrayList<>();
        newPopulation.add(population.get(0));
        newPopulation.add(population.get(1));
        newPopulation.add(population.get(2));
        Random random = new Random();
        Set<Integer> usedIndices = new HashSet<>();
        usedIndices.add(2);
        usedIndices.add(1);
        usedIndices.add(0);
        for (int i = 0; i < (1 - r) * p - 3; i++) {
            int index = 0;
            while (usedIndices.contains(index)) {
                index = random.nextInt(population.size() - 3) + 3;
            }
            newPopulation.add(population.get(index));
        }
        return newPopulation;
    }

    private void sortPopulation() {
        Collections.sort(population, new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome o1, Chromosome o2) {
                int o1Fitness = getChromosomeActuallFitness(o1);
                int o2Fitness = getChromosomeActuallFitness(o2);
                return Integer.compare(o2Fitness, o1Fitness);
            }
        });
    }

    private Chromosome getBestUsable() {
        for (Chromosome chrom : population) {
            if (chrom.isUsable()) {
                return chrom;
            }
        }
        return null;
    }

    private int getChromosomeActuallFitness(Chromosome chromosome) {
        int fitness = chromosome.fitness();
        int overweight = chromosome.weight() - maxWeight;
        fitness -= overweight > 0 ? overweight * 50 : 0;
        chromosome.setUsable(overweight <= 0);
        return fitness;
    }
}
