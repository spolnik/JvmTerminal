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

    public void goToParentDir()
            throws IOException {

        terminal.processLinuxCommand("cd ..");
    }

    public void createDirectory(String dirName)
            throws IOException {

        terminal.processLinuxCommand("mkdir " + dirName);
    }

    public String isDirectoryCreated(String dirName)
            throws IOException {

        return listDirContent().contains(dirName)
                ? "created"
                : "not created";
    }

    public void changeWorkingDirectory(String dirName)
            throws IOException {

        terminal.processLinuxCommand("cd " + dirName);
    }

    public String workingDirectoryName()
            throws IOException {

        String workingDirectory = printWorkingDirectory();
        return Paths.get(workingDirectory).getFileName().toString();
    }

    public void removeDirectory(String dirName)
            throws IOException {

        terminal.processLinuxCommand("rmdir " + dirName);
    }

    public String isRemoved(String dirName)
            throws IOException {

        return listDirContent().contains(dirName)
                ? "not removed"
                : "removed";
    }

    private List<String> listDirContent()
            throws IOException {

        return Arrays.asList(terminal.processLinuxCommand("ls").split(" "));
    }

    private String printWorkingDirectory()
            throws IOException {

        return terminal.processLinuxCommand("pwd");
    }
}
