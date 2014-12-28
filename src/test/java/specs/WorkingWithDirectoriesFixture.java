package specs;

import com.wordpress.nprogramming.terminal.TerminalServiceDriver;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(ConcordionRunner.class)
public class WorkingWithDirectoriesFixture {

    private final TerminalServiceDriver driver =
            new TerminalServiceDriver();

    public boolean isDirectoryCreated(
            String dirContent, String dirName)
            throws IOException {

        return driver.asList(dirContent).contains(dirName);
    }

    public boolean isRemoved(String dirContent, String dirName)
            throws IOException {

        return !driver.asList(dirContent).contains(dirName);
    }

    public String directoryName(String fullPath)
            throws IOException {

        return driver.directoryName(fullPath);
    }

    public void runCommand(String rawCommand)
            throws IOException {

        driver.processLinuxCommand(rawCommand);
    }

    public String runCommandWithResult(String rawCommand)
            throws IOException {

        return driver.processLinuxCommand(rawCommand);
    }
}
