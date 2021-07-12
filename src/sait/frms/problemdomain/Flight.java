package sait.frms.problemdomain;

public class Flight {
	private String code;
	private String airline;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;
	static final String AIRLINE_NAME_OA = "Otto Airlines";
	static final String AIRLINE_NAME_CA = "Conned Air";
	static final String AIRLINE_NAME_TB = "Try a Bus Airways";
	static final String AIRLINE_NAME_VA = "Vertical Airways";
	
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flight(String code, String from, String to, String weekday, String time, int seats,
			double costPerSeat) {
		super();
		this.code = code;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	/**
	 * Get flight code
	 * @return flight code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Get airline name
	 * @return airline name
	 */
	public String getAirlineName() {
		parseCode(this.code);
		return airline;
	}

	/**
	 * Get departure airport
	 * @return departure airport
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Get destination airport
	 * @return destination airport
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Get departure date
	 * @return departure date
	 */
	public String getWeekday() {
		return weekday;
	}

	/**
	 * Get departure time 
	 * @return departure time 
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Get available seats
	 * @return available seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Get cost of per seat
	 * @return cost of per seat
	 */
	public double getCostPerSeat() {
		return costPerSeat;
	}

	/**
	 * Is domestic trip
	 * @return
	 */
	public boolean isDomestic() {
		if (from.startsWith("Y") && to.startsWith("Y")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Parse code from flight code
	 * @param code flight code
	 */
	private void parseCode(String code) {
		switch(this.code.split("-")[0]) {
		case "OA":
			this.airline = AIRLINE_NAME_OA;
			break;
		case "CA":
			this.airline = AIRLINE_NAME_CA;
			break;
		case "TB":
			this.airline = AIRLINE_NAME_TB;
			break;
		case "VA":
			this.airline = AIRLINE_NAME_VA;
			break;
		default:
			// Invalid code
			break;
		}
	}

	@Override
	public String toString() {
		return  this.code + ", From:" + this.from + ", To:" +
				 this.to+ ", Day:" + this.weekday +", Cost:" + this.costPerSeat;
	}
}
