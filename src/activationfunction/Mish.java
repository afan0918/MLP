package activationfunction;

/**
 * 在維基百科上他還真的這麼長
 * @author Afan Chen
 */
public class Mish implements ActivationFunction {
    @Override
    public double function(double x) {
        double tmp = 1 + Math.exp(x);
        return x * Math.tanh(-Math.log1p(-tmp) / tmp);
    }

    @Override
    public double derivate(double x) {
        return (Math.exp(x) * (4 * Math.exp(2 * x) + Math.exp(3 * x) + 4 + 4 * x + Math.exp(x) * (6 + 4 * x)))
                / Math.pow(2 + 2 * Math.exp(x) + Math.exp(2 * x), 2);
    }
}
