// 
public class Account {
private String name;// instance variable.
private double balance;
//Account constructor that receive two parameters.  
public Account(String name, double balance) {
	this.name=name;

if(balance > 0.0) {
	this.balance=balance;
	}
}
public void deposit (double depositAmount) {
	if (depositAmount > 0.0) {
		balance = balance + depositAmount;
	}
}
public double getBalance() {
	return balance;
}
public void setName(String name) {
	this.name=name;
}
// method to retrieve the name from the object. 
public String getName() {
	return name;
}

}

