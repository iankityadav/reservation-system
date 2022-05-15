package in.bitspilani.bsc.oops;

import java.time.LocalTime;

/**
 * Timings and route of the train
 * Considering that Journey starts and completes on the same day
 * @author ankit_y
 *
 */
public class Journey {
	private LocalTime departure;
	private LocalTime arrival;
	private String start;
	private String destination;

	public Journey() {
	}

	public Journey(LocalTime departure, LocalTime arrival, String start, String destination) {
		this.departure = departure;
		this.arrival = arrival;
		this.start = start;
		this.destination = destination;
	}

	public LocalTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalTime departure) {
		this.departure = departure;
	}

	public LocalTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalTime arrival) {
		this.arrival = arrival;
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
}
