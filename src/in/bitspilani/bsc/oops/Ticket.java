package in.bitspilani.bsc.oops;

import java.util.Date;
import java.util.List;

/**
 * The <code>Ticket</code> entity class stores the info after successful booking
 * and generate ticket for {@link Passenger}
 * 
 * @author ankit_y
 *
 */
public class Ticket {
	private String pnr;
	private List<Passenger> passengers;
	private double fare;
	private Train train;
	private String start;
	private String destination;
	private int numberOfPassengers;
	private Date dateOfJourney;

	public Ticket() {
	}

	public Ticket(String pnr, List<Passenger> passengers, double fare, Train train, String start, String destination,
			int numberOfPassengers, Date dateOfJourney) {
		this.pnr = pnr;
		this.passengers = passengers;
		this.fare = fare;
		this.train = train;
		this.start = start;
		this.destination = destination;
		this.numberOfPassengers = numberOfPassengers;
		this.dateOfJourney = dateOfJourney;
	}

	public String getPNR() {
		return pnr;
	}

	public void setPNR(String pnr) {
		this.pnr = pnr;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public Date getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	@Override
	public String toString() {
		return "Ticket [pnr=" + pnr + ", passengers=" + passengers + ", fare=" + fare + ", train=" + train + ", start="
				+ start + ", destination=" + destination + ", numberOfPassengers=" + numberOfPassengers + "]";
	}
}
