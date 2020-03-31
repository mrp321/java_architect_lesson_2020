package cn.sitedev.calculate;

public class OperatorUtil {
    public static boolean isOperator(String symbol) {
        return "+".equals(symbol) || "-".equals(symbol) || "*".equals(symbol) || "/".equals(symbol);
    }

    public static Interpreter getInterpreter(IArithmeticInterpreter left,
                                             IArithmeticInterpreter right, String symbol) {
        Interpreter interpreter = null;
        switch (symbol) {
            case "+":
                interpreter = new AddInterpreter(left, right);
                break;
            case "-":
                interpreter = new SubInterpreter(left, right);
                break;
            case "*":
                interpreter = new MultiInterpreter(left, right);
                break;
            case "/":
                interpreter = new DivInterpreter(left, right);
                break;
            default:
                interpreter = null;
                break;
        }
        return interpreter;
    }
}
