
public class Car {
	private String model;
	private double tankSize;

	public Car(String model, double tankSize) {
		this.model = model;
		this.tankSize = tankSize;
	}
	
	public double calculateFullTankPrice(double pricePerLitre) {
		return tankSize * pricePerLitre;
	}
	
	public String toString() {
		return model + "," + tankSize;
	}

}
