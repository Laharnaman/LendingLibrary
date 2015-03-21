package models;

public class LoanAlreadyExistsException extends Exception {
	private Loan loanToAdd;

	public LoanAlreadyExistsException(Loan loan) {
		this.loanToAdd=loan;
	}
	public Loan getLoanToAdd() {
		return loanToAdd;
	}
	
}
