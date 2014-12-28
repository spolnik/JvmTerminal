package specs.directories;

import com.wordpress.nprogramming.terminal.TerminalService;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RunWith(ConcordionRunner.class)
public class WorkingWithDirectoriesFixture {

    private final TerminalService terminal = new TerminalService();

    public boolean isDirectoryCreated(String dirContent, String dirName)
            throws IOException {

        return asList(dirContent).contains(dirName);
    }

    public boolean isRemoved(String dirContent, String dirName)
            throws IOException {

        return !asList(dirContent).contains(dirName);
    }

    public String directoryName(String fullPath)
            throws IOException {

        return Paths.get(fullPath).getFileName().toString();
    }

    public void runCommand(String rawCommand)
            throws IOException {

        terminal.processLinuxCommand(rawCommand);
    }

    public String runCommandWithResult(String rawCommand)
            throws IOException {

        return terminal.processLinuxCommand(rawCommand);
    }

    private List<String> asList(String text)
            throws IOException {

        return Arrays.asList(text.split(" "));
    }
}
