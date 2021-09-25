import activationfunction.ActivationFunction;

/**
 * MLP
 * @author Afan Chen
 */
public class MultiLayerPerceptron implements Cloneable {
    protected double LearningRate = 0.6;
    protected Layer[] Layers;
    protected ActivationFunction activationFunction;

    /**
     * MLP的建構子
     * @param layers layers.length為輸入層數，裡面是多少神經元
     * @param learningRate 學習率
     * @param fun 激勵函數，new一個activationfunction資料夾內的class進來就好
     */
    public MultiLayerPerceptron(int[] layers, double learningRate, ActivationFunction fun) {
        LearningRate = learningRate;
        activationFunction = fun;

        Layers = new Layer[layers.length];

        for (int i = 0; i < layers.length; i++) {
            if (i != 0) {
                Layers[i] = new Layer(layers[i], layers[i - 1]);
            } else {
                Layers[i] = new Layer(layers[i], 0);
            }
        }
    }

    /**
     * 一開始沒寫，後來發現對於其他人好像不太方便，就一次訓練的部分寫出來
     * @param x data
     * @param y label
     * @param epoch 迭代次數
     */
    public void fit(double[][] x,double[][] y,int epoch){
        if(x.length!= y.length){
            throw new ArithmeticException("哇哇輸光，資料筆數請相等");
        }

        for(int i=0;i<epoch;i++){
            for(int j=0;j<x.length;j++){
                update(x[j],y[j]);
            }
        }
    }

    /**
     * 預測
     * @param x 要預測的資料
     * @return y 預測結果
     */
    public double[] predict(double[] x) {
        double value;
        double[] y = new double[Layers[Layers.length - 1].Length];

        for (int i = 0; i < Layers[0].Length; i++) {
            Layers[0].Neurons[i].Value = x[i];
        }

        for (int i = 1; i < Layers.length; i++) {
            for (int j = 0; j < Layers[i].Length; j++) {
                value = 0.0;

                for (int k = 0; k < Layers[i - 1].Length; k++) {
                    value += Layers[i].Neurons[j].Weights[k] * Layers[i - 1].Neurons[k].Value;
                }

                value += Layers[i].Neurons[j].Bias;
                Layers[i].Neurons[j].Value = activationFunction.function(value);
            }
        }

        for (int i = 0; i < Layers[Layers.length - 1].Length; i++) {
            y[i] = Layers[Layers.length - 1].Neurons[i].Value;
        }

        return y;
    }

    /**
     * 更新算法
     * @param x 單筆資料
     * @param y 該筆資料的 label
     * @return 錯誤率
     */
    public double update(double[] x, double[] y) {
        double[] new_output = predict(x);
        double error;

        for (int i = 0; i < Layers[Layers.length - 1].Length; i++) {
            error = y[i] - new_output[i];
            Layers[Layers.length - 1].Neurons[i].Delta = error * activationFunction.derivate(new_output[i]);
        }

        for (int i = Layers.length - 2; i >= 0; i--) {
            for (int j = 0; j < Layers[i].Length; j++) {
                error = 0;

                for (int k = 0; k < Layers[i + 1].Length; k++) {
                    error += Layers[i + 1].Neurons[k].Delta * Layers[i + 1].Neurons[k].Weights[j];
                }

                Layers[i].Neurons[j].Delta = error * activationFunction.derivate(Layers[i].Neurons[j].Value);
            }

            for (int j = 0; j < Layers[i + 1].Length; j++) {
                for (int k = 0; k < Layers[i].Length; k++) {
                    Layers[i + 1].Neurons[j].Weights[k] +=
                            LearningRate * Layers[i + 1].Neurons[j].Delta * Layers[i].Neurons[k].Value;
                }
                Layers[i + 1].Neurons[j].Bias += LearningRate * Layers[i + 1].Neurons[j].Delta;
            }
        }

        error = 0;
        for (int i = 0; i < y.length; i++) {
            error += Math.abs(new_output[i] - y[i]);
        }

        error = error / y.length;
        return error;
    }

    /**
     * @return 學習率
     */
    public double getLearningRate() {
        return LearningRate;
    }


    /**
     * @param rate 學習率
     */
    public void setLearningRate(double rate) {
        LearningRate = rate;
    }


    /**
     * 設定激勵函數
     * @param fun 激勵函數
     */
    public void setActivationFunction(ActivationFunction fun) {
        activationFunction = fun;
    }
}

