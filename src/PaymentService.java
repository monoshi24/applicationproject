import java.time.LocalDate;
public class PaymentService {
	public boolean isValidRepaymentDate(LocalDate selectedDate) {
		LocalDate today = LocalDate.now();
		return !selectedDate.isBefore(today) && !selectedDate.isAfter(today.plusDays(30));
	}

	public PaymentService() {
		// TODO Auto-generated constructor stub
	}

}
