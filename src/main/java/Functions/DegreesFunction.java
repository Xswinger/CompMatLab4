package Functions;

public class DegreesFunction extends AbstractFunction {

    private double[][] localData;

    public DegreesFunction(double[][] data) {
        super(data);
        name = "Степенная функция";
        n = data[0].length;
    }

    @Override
    public void solutionCycle() {

        findCoefficients();

        findApproximatesSolutions();

        standardDeviation();

        findEpsilons();
    }

    @Override
    public void findCoefficients() {
        double scale = Math.pow(10, 3);

        localData = new double[2][n];

        convertValue(0);
        convertValue(1);

        double sumX = calculateSumOfValue(1, localData[0]);
        double sumY = calculateSumOfValue(1, localData[1]);
        double sumSquareOfX = calculateSumOfValue(2, localData[0]);
        double sumOfXY = calculateSumOfXYMultiplication(1, 1, localData[0], localData[1]);

        double delta = (sumSquareOfX * n - sumX * sumX);
        double deltaFirst = (sumOfXY * n - sumX * sumY);
        double deltaSecond = (sumSquareOfX * sumY - sumX * sumOfXY);

        double coefficientB = Math.ceil((deltaFirst/delta)*scale)/scale;
        double coefficientA = Math.ceil(Math.exp(deltaSecond/delta)*scale)/scale;


        coefficients = new double[]{coefficientA, coefficientB};

    }

    private void convertValue(int index) {
        for (int i = 0; i < n; i++) {
            localData[index][i] = Math.log(data[index][i]);
        }
    }

    @Override
    public double approximateSolution(int i) {
        return (coefficients[0] * Math.pow(data[0][i], coefficients[1]));
    }
}
