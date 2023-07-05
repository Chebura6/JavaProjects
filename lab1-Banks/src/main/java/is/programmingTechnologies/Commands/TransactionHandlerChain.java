package is.programmingTechnologies.Commands;

/**
 * This class stores first element of transaction chain.
 *
 *@version 1.0.0 23.02.2023
 */
public class TransactionHandlerChain {
    private TransactionHandler _root;

    /**
     * Transaction handler chain constructor
     *
     * @param handlers
     * @throws Exception
     */

    public TransactionHandlerChain(TransactionHandler... handlers) throws Exception{
        _root = handlers[0];
        for (int i = 0; i < handlers.length - 1; i++)
        {
            handlers[i].setNext(handlers[i + 1]);
        }
    }

    /**
     * Start command chain execution
     *
     * @return boolean is command execution was successful
     * @throws Exception
     */

    public boolean Execute() throws Exception {
        return _root.Execute();
    }

    /**
     * Revert command chain execution
     *
     * @throws Exception
     */

    public void Revert() throws Exception{
        _root.Revert();
    }

}
