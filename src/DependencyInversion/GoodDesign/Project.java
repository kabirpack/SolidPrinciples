package DependencyInversion.GoodDesign;

import java.util.ArrayList;
import java.util.List;

public class Project {
    List<IDeveloper> developers = new ArrayList<>();

    public Project(List<IDeveloper> developers) {
        this.developers = developers;
    }

    public void implement(){
        for (IDeveloper developer : developers){  // interface acts as middle layer between high level and low level module
           developer.develop();
        }
    }
}