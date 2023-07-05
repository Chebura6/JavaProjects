package is.programmingTechnologies.Commands;

import is.programmingTechnologies.Interfaces.ICommand;

/**
 * This class encapsulate withdraw command logic.
 *
 * @version 1.0.0 23.02.2023
 */

public class WithdrawCommand implements ICommand {
    private CommandState _commandState;
    private TransactionContext _context;

    /**
     * Withdraw command constructor
     *
     * @param context
     * @throws Exception
     */
    public WithdrawCommand(TransactionContext context) throws Exception {
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

        _context.From.withdrawMoney(_context.Amount);
        _commandState.setExecuted(true);
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

        _context.From.topupMoney(_context.Amount);
        _commandState.setReverted(true);
    }
}
