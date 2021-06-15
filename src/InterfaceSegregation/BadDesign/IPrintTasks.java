package InterfaceSegregation.BadDesign;

public interface IPrintTasks
{
    boolean printContent(String content);
    boolean scanContent(String content);
    boolean faxContent(String content);
    boolean photoCopyContent(String content);
    boolean printDuplexContent(String content);   // newly added method, which clients are enforced to use.
}
