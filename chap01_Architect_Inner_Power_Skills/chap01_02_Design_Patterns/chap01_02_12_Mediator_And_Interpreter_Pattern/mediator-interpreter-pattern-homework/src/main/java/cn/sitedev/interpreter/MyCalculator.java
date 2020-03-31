package cn.sitedev.interpreter;

import cn.sitedev.interpreter.interpreters.IInterpreter;
import cn.sitedev.interpreter.interpreters.OperandInterpreter;

import java.util.Stack;

public class MyCalculator {
    private Stack<IInterpreter> stack = new Stack<>();

    public MyCalculator(String expression) {
        this.parse(expression);
    }

    private void parse(String expression) {
        String[] elements = expression.split(" ");
        IInterpreter left, right;
        for (int i = 0; i < elements.length; i++) {
            String operator = elements[i];
            if (OperatorUtil.isOperator(operator)) {
                left = this.stack.pop();
                right = new OperandInterpreter(Integer.valueOf(elements[++i]));
                System.out.println("出栈:" + left.interpret() + "和" + right.interpret());
                this.stack.push(OperatorUtil.getInterpreter(left, right, operator));
                System.out.println("应用运算符:" + operator);
            } else {
                OperandInterpreter numInterpreter =
                        new OperandInterpreter(Integer.valueOf(elements[i]));
                this.stack.push(numInterpreter);
                System.out.println("入栈:" + numInterpreter.interpret());
            }
        }
    }

    public int calculate() {
        return this.stack.pop().interpret();
    }


}
