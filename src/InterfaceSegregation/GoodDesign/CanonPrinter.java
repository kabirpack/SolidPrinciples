package InterfaceSegregation.GoodDesign;

public class CanonPrinter implements  IPrintTasks{
    @Override
    public boolean printContent(String content) {
        return false;
    }

    @Override
    public boolean scanContent(String content) {
        return false;
    }

    @Override
    public boolean photoCopyContent(String content) {
        return false;
    }
}
