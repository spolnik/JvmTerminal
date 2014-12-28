package specs;

import com.wordpress.nprogramming.terminal.TerminalService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

final class TerminalServiceDriver {

    private final TerminalService terminal =
            new TerminalService();

    String processLinuxCommand(String rawCommand)
            throws IOException {

        return terminal.processLinuxCommand(rawCommand);
    }

    List<String> asList(String text)
            throws IOException {

        return Arrays.asList(text.split(" "));
    }

    String directoryName(String fullPath)
            throws IOException {

        return Paths.get(fullPath)
                .getFileName().toString();
    }
}
