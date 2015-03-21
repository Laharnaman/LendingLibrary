package models;

import java.util.ArrayList;

import utilities.LoanStatus;

public class LoansRegistry {

	private ArrayList<Loan> registry;

	public LoansRegistry() {
		registry = new ArrayList<Loan>();
	}

	public void addLoan(Loan loan) throws LoanAlreadyExistsException {
		//Check to make sure loan does not already exist
		if(this.registry.contains(loan)) {
			throw new LoanAlreadyExistsException(loan);
		}

		//Add to registry if you get this far
		registry.add(loan);
		System.out.println(loan.toString());
	}

	public Loan findLoan(String bookID) throws LoanNotFoundException {

		for (Loan l : registry) {
			if (l.getBook().getID().equalsIgnoreCase(bookID)
					&& l.getStatus().equals(LoanStatus.CURRENT)) {
				return l;
			}
		}

		throw new LoanNotFoundException();
	}

	public boolean isBookOnLoan(String bookID) {

		try {
			Loan foundLoan = findLoan(bookID);
			return true;
		} catch (LoanNotFoundException e) {
			return false;
		}

	}

	public ArrayList<Loan> getRegistry() {
		return registry;
	}

}
