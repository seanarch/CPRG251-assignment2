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
	
	
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flight(String code, String from, String to, String weekday, String time, int seats,
			double costPerSeat) {
		super();
		this.code = code;
		this.airline = code.substring(0,2);
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	public String getCode() {
		return code;
	}

	public String getAirlineName() {
		return airline;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getWeekday() {
		return weekday;
	}

	public String getTime() {
		return time;
	}

	public int getSeats() {
		return seats;
	}

	public double getCostPerSeat() {
		return costPerSeat;
	}

	public boolean isDomestic() {
		if (from.startsWith("Y") || to.startsWith("Y")) {
			return true;
		} else {
			return false;
		}
	}
	
	private void parseCode(String code) {
		
	}

	@Override
	public String toString() {
		return "Flight{" +
				"code='" + code + '\'' +
				", airline='" + airline + '\'' +
				", from='" + from + '\'' +
				", to='" + to + '\'' +
				", weekday='" + weekday + '\'' +
				", time='" + time + '\'' +
				", seats=" + seats +
				", costPerSeat=" + costPerSeat +
				'}';
	}
}
