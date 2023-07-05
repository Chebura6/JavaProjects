package is.programmingTechnologies.Commands;

/**
 * This class will store current command state
 *
 * @version 1.0.0 23.02.2023
 */
public class CommandState {
    public CommandState() {
        Created = false;
        Executed = false;
        Reverted = false;
    }
    public boolean Created;
    public boolean Executed;
    public boolean Reverted;

    public boolean getCreated() {
        return Created;
    }

    public void setCreated(boolean created) {
        Created = created;
    }

    public boolean getExecuted() {
        return Executed;
    }

    public void setExecuted(boolean executed) {
        Executed = executed;
    }

    public boolean getReverted() {
        return Reverted;
    }

    public void setReverted(boolean reverted) {
        Reverted = reverted;
    }
}
