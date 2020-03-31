package cn.sitedev.interpreter.interpreters;

public class AddInterpreter extends AbstractInterpreter {
    public AddInterpreter(IInterpreter left, IInterpreter right) {
        super(left, right);
    }

    @Override
    public int interpret() {
        return this.left.interpret() + this.right.interpret();
    }
}
