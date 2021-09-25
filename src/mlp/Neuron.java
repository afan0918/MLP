package mlp;

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
        Weights = new double[prevLayerSize];
        Bias = Math.random() / 10000000000000.0;
        Delta = Math.random() / 10000000000000.0;
        Value = Math.random() / 10000000000000.0;

        for (int i = 0; i < Weights.length; i++)
            Weights[i] = Math.random() / 10000000000000.0;
    }
}