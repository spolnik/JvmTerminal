package specs;

import com.wordpress.nprogramming.terminal.TerminalServiceDriver;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(ConcordionRunner.class)
public class WorkingWithFilesFixture {

    private final TerminalServiceDriver driver =
            new TerminalServiceDriver();

    public boolean isFileListed(
            String dirContent, String fileName)
            throws IOException {

        return driver.asList(dirContent).contains(fileName);
    }

    public boolean areFilesListed(
            String dirContent, String first, String second)
            throws IOException {

        return driver.asList(dirContent).contains(first)
                && driver.asList(dirContent).contains(second);
    }

    public boolean areFilesRemoved(
            String dirContent, String first, String second)
            throws IOException {

        return !(driver.asList(dirContent).contains(first)
                || driver.asList(dirContent).contains(second));
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
