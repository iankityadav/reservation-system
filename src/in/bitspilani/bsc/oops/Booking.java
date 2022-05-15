package in.bitspilani.bsc.oops;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementation of ticket reservation system. Contains menu driven functions
 * which manages the application and divides the task.
 * 
 * @author ankit_y
 *
 */
public class Booking {
	private static List<Ticket> ticketsBooked;
	private static Scanner in = new Scanner(System.in);
	/**
	 * Default Pattern for dates in the application
	 */
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * Initializing the trains list before the bookings.
	 */
	private static List<Train> trains = new ArrayList<>();
	private static String pnr;
	static {
		ticketsBooked = new ArrayList<>();
		pnr = "1234567890";
		List<Coach> coaches = new ArrayList<>();
		coaches.add(new Coach(CoachType.AC1, "A1", 72));
		coaches.add(new Coach(CoachType.AC2, "B1", 72));
		coaches.add(new Coach(CoachType.AC3, "C1", 72));
		coaches.add(new Coach(CoachType.SL, "S1", 72));
		coaches.add(new Coach(CoachType.S2, "D1", 72));
		trains.add(new Train(12341, "New Delhi-Lucknow Express",
				new Journey(LocalTime.of(10, 20), LocalTime.of(17, 30), "New Delhi", "Lucknow"), coaches));
		trains.add(new Train(12346, "New Delhi-Mumbai Express",
				new Journey(LocalTime.of(7, 20), LocalTime.of(18, 20), "New Delhi", "Mumbai"), coaches));
	}

	private static void menu() {
		int res = 0;
		do {
			lines(20);
			System.out.format("%10s%30s%d\n", "Menu", "Tickets booked : ", ticketsBooked.size());
			lines();
			System.out.println("1. Book Ticket");
			System.out.println("2. Print Ticket by PNR");
			System.out.println("3. Exit");
			System.out.print("Enter response: ");
			try {
				res = Integer.parseInt(in.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid Number !!");
				continue;
			}
			switch (res) {
			case 1:
				book();
				break;
			case 2:
				System.out.print("Enter PNR: ");
				String pnr = in.nextLine();
				ticket(pnr);
				break;
			case 3:
				System.exit(0);
			default:
				System.out.println("Invalid Option !!");
			}
		} while (res != 3);
	}

	private static Map<List<Passenger>, Double> seats(List<Passenger> passengers, Train train, Coach coach) {
		Optional<Coach> coachOfTrain = train.getCoach().stream()
				.filter(c -> c.getName().equalsIgnoreCase(coach.getName())).findFirst();
		Coach coachOpted = coachOfTrain.get();
		int seatNumber = coachOpted.getAvailableSeats();
		if (passengers.size() > seatNumber) {
			System.out.println("Tickets not available in " + coachOpted.getType().getType());
			return null;
		}
		double fare = 0;
		for (Passenger passenger : passengers) {
			Seat seat = new Seat(seatNumber, coachOpted);
			passenger.setSeat(seat);
			coachOpted.setAvailableSeats(--seatNumber);
			fare += coachOpted.getFare();
		}
		System.out.format("Total fare : %.2f\n", fare);
		Map<List<Passenger>, Double> seats = new HashMap<List<Passenger>, Double>();
		seats.put(passengers, fare);
		return seats;
	}

	private static Ticket ticket(Date dateOfJourney, Train train, Coach coach) {
		System.out.println("Enter number of passengers: ");
		int numberOfPassengers = 0;
		try {
			numberOfPassengers = Integer.parseInt(in.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid Number !!");
			ticket(dateOfJourney, train, coach);
		}
		Ticket ticket = new Ticket();
		ticket.setNumberOfPassengers(numberOfPassengers);
		ticket.setDateOfJourney(dateOfJourney);
		List<Passenger> passengers = new ArrayList<>();
		while (numberOfPassengers-- > 0) {
			Passenger passenger = Passenger.getPassengerDetails(in);
			passengers.add(passenger);
		}
		Map<List<Passenger>, Double> seats = seats(passengers, train, coach);
		if (seats == null) {
			return null;
		}
		for (Map.Entry<List<Passenger>, Double> seat : seats.entrySet()) {
			passengers = seat.getKey();
			ticket.setFare(seat.getValue());
		}
		ticket.setPassengers(passengers);
		ticket.setTrain(train);
		ticket.setPNR(generatePNR());
		return ticket;
	}

	/**
	 * Overloaded method
	 * 
	 * @param pnr - PNR number of ticket
	 */
	private static void ticket(String pnr) {
		if (pnr.length() != 10) {
			System.out.println("Incorrect Number!!");
			return;
		}
		Optional<Ticket> ticket = ticketsBooked.stream().filter(t -> t.getPNR().equals(pnr)).findFirst();
		if (ticket.isPresent())
			print(ticket.get());
		else {
			System.out.println("No ticket found with this number!");
			return;
		}
	}

	private static void print(Ticket ticket) {
		lines(20);
		System.out.println("Ticket : " + ticket.getPNR());
		System.out.println("Date : " + sdf.format(ticket.getDateOfJourney()));
		System.out.println("Train Number: " + ticket.getTrain().getNumber());
		System.out.println("Journey : " + ticket.getStart() + " to " + ticket.getDestination());
		System.out.println("Fare : " + ticket.getFare());
		System.out.format("%-15s%4s%8s%6s%6s\n", "Name", "Age", "Gender", "Seat", "Birth");
		for (Passenger passenger : ticket.getPassengers()) {
			System.out.format("%-15s%4d%8s%6d%6s\n", passenger.getName(), passenger.getAge(), passenger.getGender().toUpperCase(),
					passenger.getSeat().getNumber(), passenger.getSeat().getBerth());
		}
		lines(20);
	}

	private static void book() {
		System.out.print("Enter date of journey (dd-MM-yyyy): ");
		String response = in.nextLine();
		try {
			Date dateOfJourney = sdf.parse(response);
			if (dateOfJourney.before(Date.from(Instant.now()))) {
				System.out.println("Invalid Date!!");
				return;
			}
			System.out.println("Enter start: ");
			String start = in.nextLine();
			System.out.println("Enter destination: ");
			String destination = in.nextLine();
			Train train = trainsAvailable(start, destination);
			if (train == null) {
				return;
			}
			Ticket ticket = ticket(dateOfJourney, train, coach(train));
			if (ticket == null) {
				return;
			}
			ticket.setStart(start);
			ticket.setDestination(destination);
			ticketsBooked.add(ticket);
			System.out.println("Ticket Booked Successfully");
			lines(20);
			print(ticket);
		} catch (ParseException e) {
			System.out.println("Invalid Format of Date !!");
			book();
		}

	}

	/**
	 * Overloaded to work as a default and parameterized function
	 * 
	 * @param n number of characters to print
	 */
	private static void lines(int n) {
		IntStream.range(0, n).forEach(i -> System.out.print("_ "));
		System.out.println();
	}

	private static void lines() {
		lines(11);
	}

	private static Coach coach(Train train) {
		for (Coach c : train.getCoach()) {
			System.out.println(c);
		}
		System.out.println("Enter Coach for booking: ");
		String coachName = in.nextLine();
		Optional<Coach> coach = train.getCoach().stream().filter(c -> c.getName().equalsIgnoreCase(coachName))
				.findFirst();
		if (coach.isPresent()) {
			return coach.get();
		} else {
			return coach(train);
		}
	}

	private static Train trainsAvailable(String start, String destination) {

		List<Train> list = trains.stream().filter(t -> t.getJourney().getStart().equalsIgnoreCase(start)
				&& t.getJourney().getDestination().equalsIgnoreCase(destination)).collect(Collectors.toList());
		if (list.isEmpty()) {
			System.out.println("No trains available for this route.");
			return null;
		}
		lines();
		System.out.println("Trains available for your journey:");
		for (Train t : list) {
			System.out.println(t);
		}
		System.out.println("Enter train number for booking: ");
		try {
			final int number = Integer.parseInt(in.nextLine());
			Optional<Train> train = list.stream().filter(t -> t.getNumber() == number).findFirst();
			if (train.isPresent()) {
				return train.get();
			} else {
				return trainsAvailable(start, destination);
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid Number !!");
		}
		return null;

	}

	/**
	 * Utility method to increment PNR.
	 * 
	 * @param pnr - String
	 * @return updated value after incrementing the string
	 */
	private static String increment(String pnr) {
		String str = "";
		int len = pnr.length();
		int carry = 1;
		for (int i = len - 1; i >= 0; i--) {
			int sum = ((int) (pnr.charAt(i) - '0') + carry);
			str += (char) (sum % 10 + '0');
			carry = sum / 10;
		}
		if (carry > 0)
			str += (char) (carry + '0');
		return new StringBuilder(str).reverse().toString();
	}

	private static String generatePNR() {
		pnr = increment(pnr);
		return pnr;
	}

	public static void main(String[] args) {
		System.out.println("\tTicket Reservation System");
		try {
			menu();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error! exiting ...");
		} finally {
			in.close();
		}
	}
}
