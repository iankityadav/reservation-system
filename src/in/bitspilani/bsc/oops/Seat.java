package in.bitspilani.bsc.oops;

/**
 * Group of berth types in a seat of coach of <code>Train</code>.
 * 
 * @author ankit_y
 *
 */
enum Berth {
	L("Lower"), M("Middle"), U("Upper"), SL("Side Lower"), SU("Side Upper");

	private final String berth;

	Berth(String berth) {
		this.berth = berth;
	}

	public String getberth() {
		return berth;
	}
}

public class Seat {
	private int number;
	private Coach coach;
	private Berth berth;

	public Seat() {
	}

	public Seat(int number, Coach coach, Berth berth) {
		this.number = number;
		this.coach = coach;
		this.berth = berth;
	}
	
	public Seat(int number, Coach coach) {
		this.number = number;
		this.coach = coach;
		this.berth = getBerth();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	/**
	 * Sets the berth of seat on the basis of seat number.
	 * 
	 * @implNote A coach of train contains the seats in a set of eight seats which
	 *           includes two of each lower, middle and upper berth on opposite of
	 *           each other and other two on the side namely side lower and side
	 *           upper.
	 * @return berth - Berth type of the seat
	 */
	public Berth getBerth() {
		if (number > 0 && number <= coach.getNumberOfSeats()) {
			if (number % 8 == 1 || number % 8 == 4)
				setBerth(Berth.L);
			else if (number % 8 == 2 || number % 8 == 5)
				setBerth(Berth.M);
			else if (number % 8 == 3 || number % 8 == 6)
				setBerth(Berth.U);
			else if (number % 8 == 7)
				setBerth(Berth.SL);
			else
				setBerth(Berth.SU);
		} else
			return null;
		return berth;
	}

	public void setBerth(Berth berth) {
		this.berth = berth;
	}

	@Override
	public String toString() {
		return "Seat [number=" + number + ", berth=" + berth + "]";
	}
	

}
