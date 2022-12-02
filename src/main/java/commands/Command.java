package commands;

import javax.servlet.ServletException;
import javax.sound.midi.Receiver;
import java.io.IOException;

public interface Command {
    void execute() throws IOException, ServletException;
}
