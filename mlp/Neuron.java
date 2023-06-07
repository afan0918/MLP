package mlp;


import java.util.Random;

/**
 * 神經元部分
 * @author Afan Chen
 */
public class Neuron {
    public double Value;
    public double[] Weights;
    public double Bias;
    public double Delta;

    /**
     * @param prevLayerSize 上一層神經元數量(接輸入
     */
    public Neuron(int prevLayerSize) {
        Random random = new Random();
        
        Weights = new double[prevLayerSize];
        Bias = random.nextGaussian();
        Delta = random.nextGaussian();
        Value = random.nextGaussian();

        for (int i = 0; i < Weights.length; i++)
            Weights[i] = random.nextGaussian();
    }
}
