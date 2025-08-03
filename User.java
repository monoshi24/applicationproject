
public class User {
	private String name;
	private String idNumber;
	private String address;
	private String carReg;

	public User(String name, String idNumber, String adress, String carReg) {
		this.name = name;
		this.idNumber = idNumber;
		this.address = address;
		this.carReg = carReg;
		
	}
	
	public String toString() {
		return name + "," + idNumber + "," + address + "," + carReg;
	}

}
