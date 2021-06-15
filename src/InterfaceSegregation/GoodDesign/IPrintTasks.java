package InterfaceSegregation.GoodDesign;

public interface IPrintTasks {
    boolean printContent(String content);
    boolean scanContent(String content);
    boolean photoCopyContent(String content);
}
