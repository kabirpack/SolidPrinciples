package DependencyInversion.GoodDesign;

public class BackendDeveloper implements IDeveloper{

    @Override
    public void develop() {
        writeJava();
    }

    public void writeJava(){
        System.out.println("Developing backend");
    }
}
