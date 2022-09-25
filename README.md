用java寫的MLP，整體不到一千行，希望對大家會有幫助。

### 激勵函數

激勵函數大家可以fork過去自己加或者添加其他功能。

目前支援

BinaryStep, Gaussian, GrowingCosineUnit, Identity, LeakyReLU, Mish, ReLU, SiLU, Sigmoid, Softplus, Tanh

### 構建模型

```java
int[] layers = {2, 3, 1};
float eta = 0.05;
MultiLayerPerceptron mlp = new MultiLayerPerceptron(layers, eta, new Sigmoid());
```
這樣構建出來的模型大致如下

![image](https://user-images.githubusercontent.com/70462625/192167881-4840ef85-c833-4146-b23d-b0868df2ad4b.png)

MLP有三個參數
* layer : 決定層數和神經元數量
* eta : 學習率
* new Sigmoid() : 傳入激勵函數

### 使用範例

#### CircleTest

```java
import activationfunction.Sigmoid;  // 激勵函數
import mlp.MultiLayerPerceptron;    // MLP主體

public class CircleTest {
    public static void main(String[] args) {
        int[] layers = {2, 3, 1};
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(layers, 0.8, new Sigmoid());

        double[][] input = new double[360][2];
        double[][] label = new double[360][1];

        for (int i = 0; i < 360; i++) {
            input[i][0] = Math.cos(i);
            input[i][1] = Math.sin(i);

            label[i][0] = (input[i][0] > 0 && input[i][0] > 0) ? 1 : 0;
        }

//        mlp.fit(input, label, 10000);
//        上面這一段等價於下面，都可以
        double error;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < input.length; j++) {
                error = mlp.update(input[j], label[j]);
                System.out.println("Epoch = " + (i + 1) + ", Item = " + j + ", Error = " + error);
            }
        }

        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            double pred = mlp.predict(input[i])[0];
            System.out.println("Predict value : " + (pred > 0.5 ? 1 : 0) + "(" + pred + ")");
            System.out.println("Real value : " + label[i][0]);
            sum += ((pred > 0.5 && label[i][0] > 0.5) || (pred < 0.5 && label[i][0] < 0.5)) ? 1 : 0;
        }
        System.out.println("Predict success : " + sum + "/" + input.length + "(" + sum * 100 / input.length + "%)");
    }
}
```

#### ORTest

```java
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
```
