package cn.sitedev.interpreter;

import cn.sitedev.interpreter.interpreters.*;

public class OperatorUtil {
    public static boolean isOperator(String symbol) {
        return "+,-,*,/".contains(symbol);
    }

    public static IInterpreter getInterpreter(IInterpreter left, IInterpreter right,
                                              String symbol) {
        IInterpreter interpreter = null;
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
                break;
        }
        return interpreter;
    }
}
