package cn.sitedev.interpreter.interpreters;

public class DivInterpreter extends AbstractInterpreter {
    public DivInterpreter(IInterpreter left, IInterpreter right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return this.left.interpret() / this.right.interpret();
    }
}
