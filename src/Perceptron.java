import java.util.ArrayList;
import java.util.Random;

/**
 * 單層感知機，其實跟MLP沒什麼關係，但我懶得單獨為他開一個repository
 * @author Afan Chen
 */
public class Perceptron {
    private double eta = 0.01;    //學習率
    private int nIter = 50;       //迭代次數
    private ArrayList<Integer> errors;   //預測錯誤數量

    private double[] w;

    /**
     * 使用預設學習率的建構子
     */
    public Perceptron() {
    }

    /**
     * 建構子
     * @param eta 學習率
     * @param nIter 迭代次數
     */
    public Perceptron(double eta, int nIter) {
        this.eta = eta;
        this.nIter = nIter;
    }

    /**
     * 根據設定的eta跟迭代次數進行訓練
     * @param x data
     * @param y label
     */
    public void fit(double[][] x, double[] y) {
        Random random = new Random();
        w = new double[x[0].length + 1];
        errors = new ArrayList<>();

        for (int i = 0; i < w.length; i++) {
            w[i] = random.nextGaussian();
        }

        for (int i = 0; i < nIter; i++) {
            int error = 0;
            for (int j = 0; j < y.length; j++) {
                double update = eta * (y[j] - predict(x[j]));//更新權重值
                w[w.length - 1] += update;
                for (int k = 0; k < x[j].length; k++) {
                    w[k] += update * x[j][k];
                }
                error += update != 0 ? 1 : 0;
            }
            errors.add(error);
        }
    }

    /**
     * 用來給我存取訓練過程中有多少的訓練錯誤
     */
    public int epochError = 0;

    /**
     * 其實是update，只是我拿來畫迭代瞬間的圖
     * @param x data
     * @param y label
     * @param w weight
     * @return new weight
     */
    public double[] epoch(double[][] x, double[] y, double[] w) {
        this.w = w;
        epochError = 0;

        for (int j = 0; j < y.length; j++) {
            double update = eta * (y[j] - predict(x[j]));//更新權重值
            w[w.length - 1] += update;
            for (int k = 0; k < x[j].length; k++) {
                w[k] += update * x[j][k];
            }
            epochError += update != 0 ? 1 : 0;
        }
        return w;
    }

    /**
     * 雖然念起來是內積，但要注意他有先加上weights的最後一項(常數項)
     * @param x 單筆data
     * @return 內積結果加上常數項
     */
    private double dot(double[] x) {
        double pred = w[x.length];
        for (int i = 0; i < x.length; i++) {
            pred += x[i] * w[i];
        }
        return pred;
    }

    /**
     * 因為我要畫迭代的圖就又創了這個，把權重部分獨立出來
     * @param x 單筆data
     * @param weight 要傳入的權重
     * @return 內積結果加上常數項
     */
    private double dot(double[] x, double[] weight) {
        double pred = weight[x.length];
        for (int i = 0; i < x.length; i++) {
            pred += x[i] * weight[i];
        }
        return pred;
    }

    /**
     * 預測出結果是1 or -1
     * @param x 單筆data
     * @return 1 or -1 的預測值
     */
    public double predict(double[] x) {
        return dot(x) >= 0 ? 1 : -1;
    }

    /**
     * 預測出結果是1 or -1
     * @param x 單筆data
     * @return 1 or -1 的預測值
     */
    public double predict(double[] x, double[] w) {
        return dot(x, w) >= 0 ? 1 : -1;
    }

    /**
     * 把用fit function訓練出來過程紀錄的error list取出來
     * @return error list
     */
    public ArrayList<Integer> getErrors() {
        return errors;
    }

    /**
     * 獲取 weight
     * @return weight
     */
    public double[] getW() {
        return w;
    }

    /**
     * 設定 weight
     * @param w weight
     */
    public void setW(double[] w) {
        this.w = w;
    }
}
