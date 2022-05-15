package in.bitspilani.bsc.oops;

/**
 * The <code>CoachType</code> represents the group of coaches in the
 * {@link Train}
 * 
 * @author ankit_y
 *
 */
enum CoachType {
	AC1("1A - AC 1 Tier"), AC2("2A - AC 2 Tier"), AC3("3A - AC 3 Tier"), SL("SL - Sleeper"), S2("2S - Second Seating");

	private final String type;

	CoachType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}

public class Coach {
	private CoachType type;
	private String name;
	private Double fare;
	private int numberOfSeats;
	private int availableSeats;

	public Coach() {
	}

	public Coach(CoachType type, String name, Double fare, int numberOfSeats, int availableSeats) {
		this.type = type;
		this.name = name;
		this.fare = fare;
		this.numberOfSeats = numberOfSeats;
		this.availableSeats = availableSeats;
	}

	public Coach(CoachType type, Double fare, int numberOfSeats, int availableSeats) {
		this.type = type;
		this.fare = fare;
		this.numberOfSeats = numberOfSeats;
		this.availableSeats = availableSeats;
	}

	public Coach(CoachType type, String name, int numberOfSeats) {
		this.type = type;
		this.name = name;
		this.numberOfSeats = numberOfSeats;
		availableSeats = numberOfSeats;
	}

	public CoachType getType() {
		return type;
	}

	public void setType(CoachType type) {
		this.type = type;
	}

	public Double getFare() {
		if (fare != null)
			return fare;

		switch (type) {
		case AC1:
			setFare(1100.0);
			break;
		case AC2:
			setFare(900.0);
			break;
		case AC3:
			setFare(700.0);
			break;
		case SL:
			setFare(300.0);
			break;
		case S2:
			setFare(200.0);
			break;
		default:
			setFare(null);
			break;
		}
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Coach [type=" + type + ", name=" + name + "]";
	}

}
