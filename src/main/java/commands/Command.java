package commands;

import javax.sound.midi.Receiver;
import java.io.IOException;

public interface Command {
    void execute() throws IOException;
}
