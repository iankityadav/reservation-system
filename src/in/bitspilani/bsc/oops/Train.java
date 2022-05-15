package in.bitspilani.bsc.oops;

import java.util.List;

/**
 * Entity to store the Train information.
 * 
 * @author ankit_y
 *
 */
public class Train {
	private int number;
	private String name;
	private Journey journey;
	private List<Coach> coach;

	public Train() {
	}

	public Train(int number, String name, Journey journey, List<Coach> coach) {
		this.number = number;
		this.name = name;
		this.journey = journey;
		this.coach = coach;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	public List<Coach> getCoach() {
		return coach;
	}

	public void setCoach(List<Coach> coach) {
		this.coach = coach;
	}

	@Override
	public String toString() {
		return "Train [number=" + number + ", name=" + name + "]";
	}
	
	
}