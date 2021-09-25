/**
 * 儲存一層的神經元
 * @author Afan Chen
 */
public class Layer {
    public Neuron[] Neurons;
    public int Length;

    /**
     * Layer of Neurons
     *
     * @param neuronsNumber 本層需要創建多少神經元
     * @param prevSize 上一層有多少神經元
     */
    public Layer(int neuronsNumber, int prevSize) {
        Length = neuronsNumber;
        Neurons = new Neuron[neuronsNumber];

        for (int i = 0; i < Length; i++)
            Neurons[i] = new Neuron(prevSize);
    }
}
