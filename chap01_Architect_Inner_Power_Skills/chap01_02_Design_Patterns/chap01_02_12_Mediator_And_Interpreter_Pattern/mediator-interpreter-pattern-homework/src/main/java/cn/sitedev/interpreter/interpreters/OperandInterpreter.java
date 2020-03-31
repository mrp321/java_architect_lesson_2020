package cn.sitedev.interpreter.interpreters;

public class OperandInterpreter implements IInterpreter {

    private int operand;

    public OperandInterpreter(int operand) {
        this.operand = operand;
    }

    public OperandInterpreter(String operand) {
        this.operand = Integer.valueOf(operand);
    }

    @Override
    public int interpret() {
        return this.operand;
    }
}
