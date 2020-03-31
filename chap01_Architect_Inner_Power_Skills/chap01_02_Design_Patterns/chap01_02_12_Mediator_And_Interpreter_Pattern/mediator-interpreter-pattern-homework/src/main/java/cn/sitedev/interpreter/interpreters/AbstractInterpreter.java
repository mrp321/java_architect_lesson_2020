package cn.sitedev.interpreter.interpreters;

public abstract class AbstractInterpreter implements IInterpreter {
    protected IInterpreter left;
    protected IInterpreter right;

    public AbstractInterpreter(IInterpreter left, IInterpreter right) {
        this.left = left;
        this.right = right;
    }
}
