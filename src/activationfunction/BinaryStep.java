package activationfunction;

/**
 * @author Afan Chen
 */
public class BinaryStep implements ActivationFunction{
    @Override
    public double function(double x) {
        if(x>=0) return 1;
        else return 0;
    }

    @Override
    public double derivate(double x) {
        if(x!=0) return 0;
        else return Double.MAX_VALUE;
    }
}
