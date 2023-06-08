package Response;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private List<FunctionResponse> response = new ArrayList<>(6);

    private double correlationCoefficient;

    public List<FunctionResponse> getResponse() {
        return response;
    }

    public void setResponse(List<FunctionResponse> response) {
        this.response = response;
    }

    public double getCorrelationCoefficient() {
        return correlationCoefficient;
    }

    public void setCorrelationCoefficient(double correlationCoefficient) {
        this.correlationCoefficient = correlationCoefficient;
    }
}
