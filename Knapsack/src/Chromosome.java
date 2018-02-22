import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Chromosome {
    private static Item[] items = new Item[24];
    private boolean chromosome[];
    private boolean usable;

    static {
        items[0] = new Item("map", 90, 150);
        items[1] = new Item("compass", 130, 35);
        items[2] = new Item("water", 1530, 200);
        items[3] = new Item("sandwich", 500, 160);
        items[4] = new Item("glucose", 150, 60);
        items[5] = new Item("tin", 680, 45);
        items[6] = new Item("banana", 270, 60);
        items[7] = new Item("apple", 390, 40);
        items[8] = new Item("cheese", 230, 30);
        items[9] = new Item("beer", 520, 10);
        items[10] = new Item("suntan cream", 110, 70);
        items[11] = new Item("camera", 320, 30);
        items[12] = new Item("T-shirt", 240, 15);
        items[13] = new Item("trousers", 480, 10);
        items[14] = new Item("umbrella", 730, 40);
        items[15] = new Item("waterproof trousers", 420, 70);
        items[16] = new Item("waterproof overclothes", 430, 75);
        items[17] = new Item("note-case", 220, 80);
        items[18] = new Item("sunglasses", 70, 20);
        items[19] = new Item("towel", 180, 12);
        items[20] = new Item("socks", 40, 50);
        items[21] = new Item("book", 300, 10);
        items[22] = new Item("notebook", 900, 1);
        items[23] = new Item("tent", 2000, 150);
    }


    public Chromosome() {
        int len = items.length;
        chromosome = new boolean[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            chromosome[i] = random.nextBoolean();
        }
    }

    public Chromosome(boolean[] chromosome) {
        this.chromosome = chromosome;
    }

    public Chromosome(boolean[] gene1, boolean[] gene2) {
        int len1 = gene1.length;
        int len2 = gene2.length;
        int totalLen = len1 + len2;
        chromosome = new boolean[totalLen];
        for (int i = 0; i < len1; i++) {
            chromosome[i] = gene1[i];
        }
        for (int i = 0, j = len1; j < totalLen; i++, j++) {
            chromosome[j] = gene2[i];
        }
    }

    public int fitness() {
        int value = 0;
        for (int i = 0; i < chromosome.length; i++) {
            value += chromosome[i] ? items[i].getValue() : 0;
        }
        return value;
    }

    public int weight() {
        int weight = 0;
        for (int i = 0; i < items.length; i++) {
            weight += chromosome[i] ? items[i].getWeight() : 0;
        }
        usable = weight <= 5000;
        return weight;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public List<Chromosome> crossover(Chromosome other) {
        List<Chromosome> children = new ArrayList<>(2);
        int splitPointIndex = (int) (chromosome.length * 0.3);
        boolean[] thisFirstPart = new boolean[splitPointIndex];
        boolean[] thisSecondPart = new boolean[chromosome.length - splitPointIndex];
        boolean[] otherFirstPart = new boolean[splitPointIndex];
        boolean[] otherSecondPart = new boolean[chromosome.length - splitPointIndex];

        for (int i = 0; i < splitPointIndex; i++) {
            thisFirstPart[i] = chromosome[i];
            otherFirstPart[i] = other.chromosome[i];
        }
        for (int i = 0, j = splitPointIndex; j < chromosome.length; i++, j++) {
            thisSecondPart[i] = chromosome[j];
            otherSecondPart[i] = other.chromosome[j];
        }
        children.add(new Chromosome(thisFirstPart, otherSecondPart));
        children.add(new Chromosome(otherFirstPart, thisSecondPart));

        return children;
    }

    public void mutate() {
        Random random = new Random();
        int indexToMutate = random.nextInt(chromosome.length);
        chromosome[indexToMutate] = !chromosome[indexToMutate];
    }

    public boolean[] getChromosome() {
        boolean[] result;
        result = Arrays.copyOf(chromosome, chromosome.length);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chromosome.length; i++) {
            if (chromosome[i]) {
                sb.append(items[i].getName() + " weight: " + items[i].getWeight() + " value: " + items[i].getValue() + "\n");
            }
        }
        sb.append("Total weight: " + weight() + " total value: " + fitness());
        return sb.toString();
        //return Arrays.toString(chromosome);
    }
}
