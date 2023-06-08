package Functions;

public abstract class AbstractFunction implements Function {

    public String name;
    //x, y
    public double[][] data;
    //count of pair x, y
    public int n;
    //б
    public double standardDeviation;
    //fi(x)
    public double[] approximateSolutions;
    //fi(x) - y
    public double[] epsilons;

    public double[] coefficients;

    AbstractFunction(double[][] data) {
        this.data = data;
        n = data[0].length;
        approximateSolutions = new double[n];
        epsilons = new double[n];
    }

    public void standardDeviation() {
        double scale = Math.pow(10, 3);

        double numerator = numeratorOfStandardDeviation();

        standardDeviation = Math.ceil(Math.pow((numerator/n), 0.5)*scale)/scale;
    }

    public double numeratorOfStandardDeviation() {

        double sum = 0;

        for (int i = 0; i < n; i++) {
            sum += (Math.pow(approximateSolutions[i] - data[1][i], 2));
        }

        return sum;
    }

    public void findEpsilons() {
        for (int i = 0; i < n; i++) {
            epsilons[i] = calculateEpsilon(i);
        }
    }

    public double calculateEpsilon(int index) {
        double scale = Math.pow(10, 3);
        return Math.ceil((approximateSolutions[index] - data[1][index]) * scale) / scale;
    }

    public void findApproximatesSolutions() {
        double scale = Math.pow(10, 3);
        for (int i = 0; i < n; i++) {
            approximateSolutions[i] = Math.ceil(approximateSolution(i) * scale) / scale;
        }
    }

    public double calculateSumOfValue(int degree, double[] values) {
        double sum = 0;

        for (double value : values) {
            sum += Math.pow(value, degree);
        }

        return sum;

    }

    public double calculateAverageValue(int index) {

        double sum = 0;

        for (int i = 0; i < n; i++) {
            sum += data[index][i];
        }

        return (sum/n);
    }

    public double calculateSumOfXYMultiplication(int xDegree, int yDegree, double[] xValues, double[] yValues) {
        double sum = 0;

        for (int i = 0; i < xValues.length; i++) {
            sum += (Math.pow(xValues[i], xDegree) * Math.pow(yValues[i], yDegree));
        }

        return sum;

    }

}
