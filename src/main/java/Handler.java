import Functions.*;
import Response.FunctionResponse;
import Response.InitialData;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "HandlerServlet", value = "/handler")
public class Handler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PrintWriter writer = resp.getWriter();

        resp.setContentType("text/html");

        Response mainResponse = new Response();
        List<FunctionResponse> response = new ArrayList<>(6);
        InitialData initialData = new InitialData();

        double correlationCoefficient;
        double[] xData = Arrays.stream(req.getParameterValues("xData[]")).mapToDouble(Double::parseDouble).toArray();
        double[] yData = Arrays.stream(req.getParameterValues("yData[]")).mapToDouble(Double::parseDouble).toArray();

        initialData.setX(xData);
        initialData.setY(yData);

        double[][] data = new double[2][];
        data[0] = xData;
        data[1] = yData;

        AbstractFunction[] functions = initializeFunctions(new AbstractFunction[6], data);

        for (int i = 0; i <= 5; i++) {

            response.add(new FunctionResponse());

            functions[i].solutionCycle();

            response.get(i).setFunctionsView(functions[i].name);
            response.get(i).setCoefficients(functions[i].coefficients);
            response.get(i).setStandardDeviation(functions[i].standardDeviation);
            response.get(i).setApproximate(functions[i].approximateSolutions);
            response.get(i).setEpsilons(functions[i].epsilons);

        }

        correlationCoefficient = LinearFunction.correlationCoefficient;

        mainResponse.setResponse(response);
        mainResponse.setCorrelationCoefficient(correlationCoefficient);

        writer.println(gson.toJson(mainResponse));

        resp.setContentType("text/html");

    }

    private AbstractFunction[] initializeFunctions(AbstractFunction[] functions, double[][] data) {

        functions[0] = new LinearFunction(data);
        functions[1] = new DegreesFunction(data);
        functions[2] = new PolynomialOfSecondDegreesFunction(data);
        functions[3] = new PolynomialOfThreeDegreesFunction(data);
        functions[4] = new LogarithmicFunction(data);
        functions[5] = new ExponentialFunction(data);

        return functions;
    }

}
