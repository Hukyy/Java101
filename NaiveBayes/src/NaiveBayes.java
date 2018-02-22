import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NaiveBayes {
    List<Elector> electors;


    public NaiveBayes(String dataFile) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get(dataFile));

        electors = reader.lines()
                .map(Elector::new)
                .collect(Collectors.toList());
    }

    public void algorithm() {
        Collections.shuffle(electors);
        List<Elector> test;
        List<Elector> training;

        double[] accuracy = new double[10]; // we calculate accuracy for each iteration of cross validation here;
        for (int i = 0; i < 10; i++) { // 10 fold cross validation
            test = electors.subList(i * 43, (i + 1) * 43); // we have 435 instances
            training = new ArrayList<>();
            training.addAll(electors);
            training.removeAll(test);
            accuracy[i] = crossValidation(training, test);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Accuracy for iteration " + (i+1) + " is " + accuracy[i]);
        }

        System.out.println("Accuracy from all iterations is " + Arrays.stream(accuracy).average().getAsDouble());
    }

    private double crossValidation(List<Elector> training, List<Elector> test) {
        int[][] frequencyTable = new int[17][4];
        double[][] likelyhoodTable = new double[17][4];

        for (Elector elector : training) {
            if (elector.getClassName().equalsIgnoreCase("democrat")) {
                frequencyTable[0][0]++;
            } else {
                frequencyTable[0][1]++;
            }

            for (int i = 1; i < 17; i++) { // fill frequency table for democrats and republicans
                if (elector.getClassName().equalsIgnoreCase("democrat")) {
                    if (elector.getAttribute(i).equalsIgnoreCase("y")) {
                        frequencyTable[i][0]++;
                    } else if (elector.getAttribute(i).equalsIgnoreCase("n")) {
                        frequencyTable[i][1]++;
                    }
                } else {
                    if (elector.getAttribute(i).equalsIgnoreCase("y")) {
                        frequencyTable[i][2]++;
                    } else if (elector.getAttribute(i).equalsIgnoreCase("n")) {
                        frequencyTable[i][3]++;
                    }
                }
            }
        }

        likelyhoodTable[0][0] = 1.0 * frequencyTable[0][0] / (frequencyTable[0][0] + frequencyTable[0][1]); // P(democrat)
        likelyhoodTable[0][1] = 1 - likelyhoodTable[0][0]; // P(republican)

        for (int i = 1; i < 17; i++) {
            likelyhoodTable[i][0] = 1.0 * frequencyTable[i][0] / frequencyTable[0][0]; // P(y|democrat);
            likelyhoodTable[i][1] = 1.0 * frequencyTable[i][1] / frequencyTable[0][0]; // P(n|democrat)
            likelyhoodTable[i][2] = 1.0 * frequencyTable[i][2] / frequencyTable[0][1]; // P(y|republican);
            likelyhoodTable[i][3] = 1.0 * frequencyTable[i][3] / frequencyTable[0][1]; // P(n|republican)
        }


        //testing
        int count = 0;
        int success = 0;
        for (Elector elector : test) {
            double democrats = likelyhoodTable[0][0];
            double republicans = likelyhoodTable[0][1];
            for (int i = 1; i < 17; i++) {
                if (elector.getAttribute(i).equalsIgnoreCase("?")) { // we ignore unknown values
                    continue;
                }
                if (elector.getAttribute(i).equalsIgnoreCase("y")) {
                    democrats *= likelyhoodTable[i][0];  //[i][0] -> yes|democrat
                    republicans *= likelyhoodTable[i][2]; //[i][2]  -> yes|republican
                } else {
                    democrats *= likelyhoodTable[i][1];  //[i][1] -> no|democrat
                    republicans *= likelyhoodTable[i][3]; //[i][3] -> no|republican
                }
            }
            count++;
//            democrats = 1.0 * democrats / (democrats+republicans);     //normalization that we don't actually need
//            republicans = 1.0 - democrats;

            String guessClass = democrats > republicans ? "democrat" : "republican";
            if (guessClass.equalsIgnoreCase(elector.getClassName())) {
                success++;
            }
        }
        return 1.0 * success / count;
    }
}
