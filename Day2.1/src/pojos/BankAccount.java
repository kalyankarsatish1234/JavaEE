package pojos;

public class BankAccount {
	//id | name | type | bal
	private int acctNo;
	private String name;
	private String accType;
	private double balance;
	
	public BankAccount() {
		
	}

	public int getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(int acctNo) {
		this.acctNo = acctNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankAccount [acctNo=" + acctNo + ", name=" + name + ", accType=" + accType + ", balance=" + balance
				+ "]";
	}
	
	

}
