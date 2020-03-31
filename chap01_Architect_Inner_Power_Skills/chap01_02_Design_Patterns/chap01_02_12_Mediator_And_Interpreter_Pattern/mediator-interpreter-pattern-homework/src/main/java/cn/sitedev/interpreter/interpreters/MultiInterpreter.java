package cn.sitedev.interpreter.interpreters;

public class MultiInterpreter extends AbstractInterpreter {

    public MultiInterpreter(IInterpreter left, IInterpreter right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return this.left.interpret() * this.right.interpret();
    }
}
