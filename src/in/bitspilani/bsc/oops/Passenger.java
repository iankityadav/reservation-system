package in.bitspilani.bsc.oops;

import java.util.Scanner;

/**
 * The <code>Passenger</code> class represents the information of traveling
 * person in the {@link Train}.
 * 
 * @author ankit_y
 *
 */
public class Passenger {
	private String name;
	private int age;
	private String gender;
	private Seat seat;

	public Passenger() {
	}

	public Passenger(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	public static Passenger getPassengerDetails(Scanner in) {
		System.out.println("Enter Name: ");
		String name = in.nextLine();
		System.out.println("Enter Age: ");
		int age = Integer.parseInt(in.nextLine());
		System.out.println("Enter Gender: ");
		String gender = in.nextLine();
		return new Passenger(name, age, gender.toUpperCase().substring(0, 1));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "Passenger [name=" + name + ", age=" + age + ", gender=" + gender + ", seat=" + seat + "]";
	}
	
}
