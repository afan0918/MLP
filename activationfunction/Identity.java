package activationfunction;

/**
 * @author Afan Chen
 */
public class Identity implements ActivationFunction{
    @Override
    public double function(double x) {
        return x;
    }

    @Override
    public double derivate(double x) {
        return 1;
    }
}
