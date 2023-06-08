package Functions;

public interface Function {

    public void solutionCycle();

    public double approximateSolution(int i);

    public void findCoefficients();

    public double calculateSumOfValue(int degree, double[] values);

    public double calculateSumOfXYMultiplication(int xDegree, int yDegree, double[] xValues, double[] yValues);
}
