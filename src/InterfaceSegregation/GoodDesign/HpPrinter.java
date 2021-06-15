package InterfaceSegregation.GoodDesign;

public class HpPrinter implements IPrintTasks,IFaxContent{
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

    @Override
    public boolean faxContent(String content) {
        return false;
    }
}
