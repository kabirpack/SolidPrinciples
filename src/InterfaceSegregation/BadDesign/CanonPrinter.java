package InterfaceSegregation.BadDesign;
//cannot perform fax but enforced to implement
public class CanonPrinter implements IPrintTasks{

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
    public boolean faxContent(String content) {  // doesnt have this feature
        return false;
    }

    @Override
    public boolean photoCopyContent(String content) {
        System.out.println("PhotoCopy done");
        return true;
    }

    @Override
    public boolean printDuplexContent(String content) {  //doesnt have this feature
        return false;
    }
}
