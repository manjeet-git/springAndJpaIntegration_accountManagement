package com.stbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="account")

public class Account {

	@Id
	@Column(name="account_no")
	@GeneratedValue( strategy = GenerationType.AUTO,generator = "gen_seq")
	@TableGenerator(name = "gen_seq",initialValue = 100000000,allocationSize = 100)
	private int accountNo;
	
	@Column(name="account_name")
	@NotEmpty(message = "Please provide the account Holder name")
	@NotNull(message = "Account holder name should not be null")
	@Size(min = 2,max = 30, message = "Name should havind length between 2 to 30")
	private String accountHolderName;
	
	
	@Column(name="account_type")
	@NotEmpty(message = "Account type should not be empty")
	@NotNull(message = "Account holder name should not be null")
	private String accountType;
	
	
	@Column(name="current_amount")
	private double currentAmount;

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accountHolderName=" + accountHolderName + ", accountType="
				+ accountType + ", currentAmount=" + currentAmount + "]";
	}

	
	

}
