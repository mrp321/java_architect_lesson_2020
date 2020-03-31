package cn.sitedev.interpreter.interpreters;

public class SubInterpreter extends AbstractInterpreter {
    public SubInterpreter(IInterpreter left, IInterpreter right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return this.left.interpret() - this.right.interpret();
    }
}
