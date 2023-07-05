package is.programmingTechnologies.Commands;

import is.programmingTechnologies.Interfaces.ICommand;

/**
 * This class encapsulate initial amount command logic.
 *
 * @version 1.0.0 23.02.2023
 */

public class InitialAmountCommand implements ICommand {
    private CommandState _commandState;
    private TransactionContext _context;

    /**
     * Initial amount command constructor
     *
     * @param context
     * @throws Exception
     */

    public InitialAmountCommand(TransactionContext context) throws Exception{
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

        _context.To.topupDepositInitialAmount(_context.Amount);
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

        _context.To.revertTopupDepositInitialAmount(_context.Amount);
        _commandState.setReverted(true);
    }
}
