package specs.directories;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class WorkingWithDirectoriesFixture {
    
    public void goToParentDir() {

    }

    public void createDirectory(String dirName) {

    }

    public String isDirectoryCreated(String dirName) {
        return "not created";
    }

    public void changeWorkingDirectory(String dirName) {

    }

    public String pwdCommandEndsWith() {
        return "bad";
    }

    public void removeDirectory(String dirName) {

    }

    public String isRemoved(String dirName) {
        return "not removed";
    }
}
