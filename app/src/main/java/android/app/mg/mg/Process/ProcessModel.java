package android.app.mg.mg.Process;

/**
 * Created by miladyalda on 2017-10-07.
 */

public class ProcessModel {

    String _id;
    String name;
    Steps [] steps;
    String version;


    public String getID() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public Steps[] getSteps() {
        return steps;
    }

    public void setSteps(Steps[] steps) {
        this.steps = steps;
    }

    public String getVersion() {
        return version;
    }
}
