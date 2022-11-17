import java.util.ArrayList;
import java.util.List;

class Object {
    public String objectName;

    public Object(String objectName) {
        this.objectName = objectName;
    }

    public void printObjectName() {
        System.out.println("This is method of " + this.objectName);
    }
}

class RunObjectInArrrayList {
    private List<Object> objectsToRun = new ArrayList<Object>();

    public RunObjectInArrrayList(List<Object> objectsToRun) {
        this.objectsToRun = objectsToRun;
    }

    public void runObject(int objectOption) {
        this.objectsToRun.get(objectOption - 1).printObjectName();
    }

    public void getPropertyOfObject(Object object) {
        System.out.println("I got this object name: " + object.objectName);
    }
}

class App {
    public static void main(String[] args) {
        List<Object> objectsToRun = new ArrayList<Object>();
        Object o1 = new Object("Object 1");
        Object o2 = new Object("Object 2");
        Object o3 = new Object("Object 3");

        objectsToRun.add(o1);
        objectsToRun.add(o2);
        objectsToRun.add(o3);

        RunObjectInArrrayList runner = new RunObjectInArrrayList(objectsToRun);
        runner.runObject(1);
        runner.getPropertyOfObject(o1);
    }
}
