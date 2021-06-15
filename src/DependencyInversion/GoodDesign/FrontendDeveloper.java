package DependencyInversion.GoodDesign;

public class FrontendDeveloper implements IDeveloper{

    @Override
    public void develop() {
        writeJavaScript();
    }

    public void writeJavaScript(){
        System.out.println("Developing backend");
    }
}

