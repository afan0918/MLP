package logisticregression;

import java.util.Arrays;
import java.util.Random;

/**
 * author: Afan Chen
 * 機器學習算法:邏輯回歸
 * 損失函數:交叉熵
 * 激勵函數:sigmoid
 * 疊代算法:隨機梯度上升算法
 * 因為邏輯回歸好像也就只有一種做法
 * 所以就乾脆這樣提高可用性
 */

public class LogisticRegression {

    /**
     * sigmoid函數
     * @param num 權重與特徵的內積
     * @return 事件發生機率
     */
    public static double sigmoid(double num) {
        return 1.0 / (1 + Math.exp(-num));
    }

    /**
     * 將訓練結果回傳
     * @param datas 資料
     * @param inx 第幾筆資料
     * @param weights 權重
     * @return 權重與特徵的內積
     */
    public static double predict(double[][] datas, int inx, double[] weights) {

        double res = 0;

        for (int i = 0; i < datas.length; i++) {
            res += datas[inx][i] * weights[i];
        }

        return res;
    }

    /**
     * 將該筆數據跑過一次得到新的權重後回傳
     */
    public static double[] update(double[][] datas, int inx, double[] weights, double alpha , double error) {

        double[] res = new double[weights.length];

        for (int i = 0; i < weights.length; i++) {
            res[i] = alpha * error * datas[inx][i] + weights[i];
        }

        return res;
    }

    /**
     * 訓練模型
     * @param datas 資料
     * @param labels 標籤
     */
    public static double[] fit(double[][] datas, double[] labels) {

        Random r = new Random();
        double[] res = new double[datas[0].length];

        Arrays.fill(res, 1);

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < datas.length; j++) {
                double alpha = 4 / (1.0 + j + i) + 0.01;
                int randIndex = r.nextInt(datas.length);
                double h = sigmoid(predict(datas, randIndex, res));
                double error=labels[randIndex]-h;
                res = update(datas, randIndex, res, alpha,error);
            }
        }

        return res;
    }

    /**
     * 訓練模型
     * @param datas 資料
     * @param labels 標籤
     * @param maxIter 疊代次數，沒設終止條件等他跑完
     * @return 特徵權重
     */
    public static double[] fit(double[][] datas, double[] labels, int maxIter) {

        Random r = new Random();
        double[] res = new double[datas[0].length];

        Arrays.fill(res, 1);

        for (int i = 0; i < maxIter; i++) {
            for (int j = 0; j < datas.length; j++) {
                double alpha = 4 / (1.0 + j + i) + 0.01;
                int randIndex = r.nextInt(datas.length);
                double h = sigmoid(predict(datas, randIndex, res));
                double error=labels[randIndex]-h;
                res = update(datas, randIndex, res, alpha,error);
            }
        }

        return res;
    }

    /**
     * 預測發生機率
     * @param feature 特徵值
     * @param weights 權重
     * @return 回傳機率
     */
    public static double predict(double[] feature, double[] weights) {

        double prob = 0;

        for (int i = 0; i < feature.length; i++) {
            prob += feature[i] * weights[i];
        }

        prob = sigmoid(prob);

        return prob;
    }

    /**
     * 預測結果(哪種可能比較大
     * @param feature 特徵值
     * @param weights 權重
     * @return 回傳預測
     */
    public static double predict_classification(double[] feature, double[] weights) {

        double prob = 0;

        for (int i = 0; i < feature.length; i++) {
            prob += feature[i] * weights[i];
        }

        prob = sigmoid(prob);

        return prob>=0.5?1:0;
    }
}