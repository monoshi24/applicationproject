
public class FuelService {
	public double calculateDiscountedPrice(double basePrice, int purchaseNumber) {
		double discount = 0;
		
		if (purchaseNumber == 1) {
			return basePrice * 1.25;
		} else if (purchaseNumber <= 13) {
			discount = 0.0625 * (purchaseNumber - 1);
			return basePrice * (1.25 - discount);
		} else {
			return basePrice * 0.25;
		}
	}

}