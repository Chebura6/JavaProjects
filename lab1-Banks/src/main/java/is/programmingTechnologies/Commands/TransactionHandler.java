package is.programmingTechnologies.Commands;

import is.programmingTechnologies.Interfaces.ICommand;

/**
 * This class encapsulate execution of transaction chain logic.
 *
 * @version 1.0.0 23.02.2023
 */

public class TransactionHandler {
    private TransactionHandler _prev;
    private TransactionHandler _next;
    private ICommand _command;

    /**
     * Transaction handler constructor
     *
     * @param command
     * @throws Exception
     */

    public TransactionHandler(ICommand command) throws Exception {
        _prev = null;
        _next = null;

        if (command == null) throw new Exception("Nope");

        _command = command;
    }

    /**
     * Invoke command revert
     *
     * @throws Exception
     */

    public void Revert() throws Exception {
        if (_next != null) _next.Revert();
        _command.Revert();
    }

    /**
     * Encapsulate command execution logic
     *
     * @return boolean is execution was successful
     * @throws Exception
     */

    public boolean Execute() throws Exception {
        try {
            _command.Execute();
        }
        catch (Exception ex) {
            return false;
        }

        if (_next == null) return true;

        if (_next.Execute()) {
            return true;
        }

        _command.Revert();

        return false;
    }

    /**
     * Set next command in the chain
     *
     * @param handler what to set
     * @throws Exception
     */

    public void setNext(TransactionHandler handler) throws Exception{
        if (handler == null) throw new Exception();
        if (_next != null || handler._prev != null)
        {
            throw new NullPointerException("Handler already is set");
        }

        _next = handler;
        handler._prev = this;
    }
}
