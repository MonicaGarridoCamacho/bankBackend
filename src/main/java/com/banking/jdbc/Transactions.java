package com.banking.jdbc;

public class Transactions {

	private String accountId;
	private String transactionId;
	private String transactionInformation;
	private String addressLine;
	private String amount;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionInformation() {
		return transactionInformation;
	}

	public void setTransactionInformation(String transactionInformation) {
		this.transactionInformation = transactionInformation;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Transactions(String accountId, String transactionId, String transactionInformation, String addressLine,
			String amount) {
		this.accountId = accountId;
		this.transactionId = transactionId;
		this.transactionInformation = transactionInformation;
		this.addressLine = addressLine;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return String.format(
				"Transactions[accountId=%s, transactionId='%s', transactionInformation='%s', addressLine='%s', amount='%s']",
				accountId, transactionId, transactionInformation, addressLine, amount);
	}
}
