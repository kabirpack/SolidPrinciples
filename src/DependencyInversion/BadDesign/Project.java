package DependencyInversion.BadDesign;

public class Project {

         BackEndDeveloper backEndDeveloper = new BackEndDeveloper();
         FrontEndDeveloper frontEndDeveloper = new FrontEndDeveloper();

         public void implement(){

         backEndDeveloper.writeJava();
         frontEndDeveloper.writeJavaScript();   // high level module depend on low level module

     }
}
