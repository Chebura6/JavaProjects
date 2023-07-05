package is.programmingTechnologies.Interfaces;
import is.programmingTechnologies.Commands.CommandState;

/**
 * Command interface.
 */

public interface ICommand {
    CommandState State = null;
    void Execute() throws Exception;
    void Revert() throws Exception;
}
