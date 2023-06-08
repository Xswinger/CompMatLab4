package Functions;

public class LinearFunction extends AbstractFunction {

    public static double correlationCoefficient;

    public LinearFunction(double[][] data) {
        super(data);
        name = "Линейная функция";
        n = data[0].length;
    }

    public void calculateCorrelationCoefficient() {
        double scale = Math.pow(10, 6);

        double numerator = 0;
        double sumOfDifferenceX = 0;
        double sumOfDifferenceY = 0;

        double averageX = calculateAverageValue(0);
        double averageY = calculateAverageValue(1);

        for (int i = 0; i < n; i++) {

            double differenceBetweenAccurateAndAverageX = data[0][i] - averageX;
            double differenceBetweenAccurateAndAverageY = data[0][i] - averageY;

            numerator += (differenceBetweenAccurateAndAverageX * differenceBetweenAccurateAndAverageY);
            sumOfDifferenceX += (Math.pow(differenceBetweenAccurateAndAverageX, 2));
            sumOfDifferenceY += (Math.pow(differenceBetweenAccurateAndAverageY, 2));

        }

        correlationCoefficient = Math.ceil((numerator/Math.pow((sumOfDifferenceX * sumOfDifferenceY), 0.5)) * scale) / scale;
    }

    @Override
    public void solutionCycle() {
        findCoefficients();

        findApproximatesSolutions();

        standardDeviation();

        findEpsilons();

        calculateCorrelationCoefficient();
    }

    @Override
    public void findCoefficients() {


        double scale = Math.pow(10, 3);

        double sumX = calculateSumOfValue(1, data[0]);
        double sumY = calculateSumOfValue(1, data[1]);
        double sumSquareOfX = calculateSumOfValue(2, data[0]);
        double sumOfXY = calculateSumOfXYMultiplication(1, 1, data[0], data[1]);

        double delta = (sumSquareOfX * n - sumX * sumX);
        double deltaFirst = (sumOfXY * n - sumX * sumY);
        double deltaSecond = (sumSquareOfX * sumY - sumX * sumOfXY);

        double coefficientA = Math.ceil((deltaFirst/delta)*scale)/scale;
        double coefficientB = Math.ceil((deltaSecond/delta)*scale)/scale;

        coefficients = new double[]{coefficientA, coefficientB};
    }

    @Override
    public double approximateSolution(int i) {
        return (coefficients[0] * data[0][i] + coefficients[1]);
    }

}
