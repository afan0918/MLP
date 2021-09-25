package test;

import activationfunction.Sigmoid;
import mlp.MultiLayerPerceptron;

public class ORTest {
    public static void main(String[] args) {
        int[] layers = {2, 3, 1};
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(layers, 0.5, new Sigmoid());

        double[][] input = {{1, 1}, {1, 0}, {0, 1}, {0, 0}};
        double[][] label = {{1}, {1}, {1}, {0}};

//        mlp.fit(input,label,1000);
//        上面這一段等價於下面，都可以
        double error;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < input.length; j++) {
                error = mlp.update(input[j], label[j]);
                System.out.println("Epoch = " + (i + 1) + ", Item = " + j + ", Error = " + error);
            }
        }

        for (int i = 0; i < input.length; i++) {
            double pred = mlp.predict(input[i])[0];
            System.out.println("Predict value : " + (pred > 0.5 ? 1 : 0) + "(" + pred + ")");
            System.out.println("Real value : " + label[i][0]);
        }
    }
}
