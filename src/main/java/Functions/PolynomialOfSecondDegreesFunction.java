package Functions;

public class PolynomialOfSecondDegreesFunction extends AbstractFunction {

    public PolynomialOfSecondDegreesFunction(double[][] data) {
        super(data);
        name = "Полиномиальная функция 2-й степени";
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

        double sumX = calculateSumOfValue(1, data[0]);
        double sumY = calculateSumOfValue(1, data[1]);
        double sumSquareOfX = calculateSumOfValue(2, data[0]);
        double sumCubeOfX = calculateSumOfValue(3, data[0]);
        double sumFourthDegreeOfX = calculateSumOfValue(4, data[0]);
        double sumOfXY = calculateSumOfXYMultiplication(1, 1, data[0], data[1]);
        double sumOfSquareXY = calculateSumOfXYMultiplication(2, 1, data[0], data[1]);

        double[][] a = new double[][]{{n, sumX, sumSquareOfX}, {sumX, sumSquareOfX, sumCubeOfX}, {sumSquareOfX, sumCubeOfX, sumFourthDegreeOfX}};
        double[] b = new double[]{sumY, sumOfXY, sumOfSquareXY};

        int count = 3;

        for (int k = 0; k < count - 1; k++) {
            // Поиск максимального элемента в столбце k
            int maxRow = k;
            double maxElement = Math.abs(a[k][k]);
            for (int i = k + 1; i < count; i++) {
                double absElement = Math.abs(a[i][k]);
                if (absElement > maxElement) {
                    maxElement = absElement;
                    maxRow = i;
                }
            }

            // Обмен местами строк k и maxRow
            if (maxRow != k) {
                double[] temp = a[k];
                a[k] = a[maxRow];
                a[maxRow] = temp;
                double tempB = b[k];
                b[k] = b[maxRow];
                b[maxRow] = tempB;
            }

            // Приведение матрицы к треугольному виду
            for (int i = k + 1; i < count; i++) {
                double factor = a[i][k] / a[k][k];
                b[i] -= factor * b[k];
                for (int j = k; j < count; j++) {
                    a[i][j] -= factor * a[k][j];
                }
            }
        }

        // Решение уравнений методом обратного хода
        double[] x = new double[count];
        for (int i = count - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < count; j++) {
                sum += a[i][j] * x[j];
            }
            x[count - 1 - i] = Math.ceil(((b[i] - sum) / a[i][i])*scale)/scale;
        }

        coefficients = x;
    }

    @Override
    public double approximateSolution(int i) {
        return (coefficients[0] * Math.pow(data[0][i], 2) + coefficients[1] * data[0][i] + coefficients[2]);
    }
}
