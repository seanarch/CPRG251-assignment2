package sait.frms.problemdomain;

public class Reservation {
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost,
			boolean active) {
		super();
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
	}

	/**
	 * Get customer's name
	 * @return customer's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set customer's name
	 * @param name customer's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get citizenship
	 * @return citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * Set citizenship
	 * @param citizenship citizenship
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * The status of reservation
	 * @return the status of reservation
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Set the status of reservation
	 * @param active status of reservation
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Get reservation code 
	 * @return reservation code 
	 */
	public String getCode() {
		return code;
	}

	/**
	 *  Get flight code
	 * @return flight code
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * Get airline name
	 * @return airline name
	 */
	public String getAirline() {
		return airline;
	}
	
	/**
	 * Get the cost of a seat
	 * @return the cost of a seat
	 */
	public double getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return "Reservation [code=" + code + ", flightCode=" + flightCode + ", airline=" + airline + ", name=" + name
				+ ", citizenship=" + citizenship + ", cost=" + cost + ", isActive=" + active + "]";
	}
	
}
