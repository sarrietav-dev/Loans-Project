package logic.file_management;

import java.io.IOException;

/**
 * Interface that allows {@link logic.file_management.makers.LoanMaker} to retrieve multiple Loan objects.
 */
public interface MultipleMaker {
    Object[] makeMultiple() throws IOException;
}
