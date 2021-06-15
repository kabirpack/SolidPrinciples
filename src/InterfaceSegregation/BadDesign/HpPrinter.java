package InterfaceSegregation.BadDesign;

public class HpPrinter implements IPrintTasks{

    @Override
    public boolean printContent(String content) {
        System.out.println("print done");
        return true;
    }

    @Override
    public boolean scanContent(String content) {
        System.out.println("Scan Done");
        return true;
    }

    @Override
    public boolean faxContent(String content) {
        System.out.println("Fax Done");
        return true;
    }

    @Override
    public boolean photoCopyContent(String content) {
        System.out.println("PhotoCopy done");
        return true;
    }

    @Override
    public boolean printDuplexContent(String content) {
        return false;
    }
}
