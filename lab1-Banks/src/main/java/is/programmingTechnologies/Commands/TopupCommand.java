package is.programmingTechnologies.Commands;

import is.programmingTechnologies.Interfaces.ICommand;

/**
 * This class encapsulate topup account command logic.
 *
 * @version 1.0.0 23.02.2023
 */

public class TopupCommand implements ICommand {
    private CommandState _commandState;
    private TransactionContext _context;

    /**
     * Topup command constructor
     *
     * @param context
     * @throws Exception
     */

    public TopupCommand(TransactionContext context) throws Exception{
        _commandState = new CommandState();
        _commandState.setCreated(true);

        if (context == null) throw new Exception();
        _context = context;
    }

    /**
     * Encapsulate an execution command logic
     *
     * @throws Exception
     */

    public void Execute() throws Exception {
        if (_commandState.Created == false) {
            throw new Exception("Invalid state");
        }

        _context.To.topupMoney(_context.Amount);
        _commandState.Executed = true;
    }

    /**
     * Encapsulate a revert command logic
     *
     * @throws Exception
     */

    public void Revert() throws Exception {
        if (_commandState.Executed == false) {
            throw new Exception("Invalid state");
        }

        _context.To.withdrawMoney(_context.Amount);
        _commandState.Reverted = true;
    }
}
