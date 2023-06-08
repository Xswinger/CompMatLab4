package Response;

public class FunctionResponse {

    private String functionsView;

    private double[] approximate;

    private double[] epsilons;

    private double[] coefficients;

    private double standardDeviation;

    public double[] getApproximate() {
        return approximate;
    }

    public void setApproximate(double[] approximate) {
        this.approximate = approximate;
    }

    public double[] getEpsilons() {
        return epsilons;
    }

    public void setEpsilons(double[] epsilons) {
        this.epsilons = epsilons;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public String getFunctionsView() {
        return functionsView;
    }

    public void setFunctionsView(String functionsView) {
        this.functionsView = functionsView;
    }
}
