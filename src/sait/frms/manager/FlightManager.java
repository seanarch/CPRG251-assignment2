package src.sait.frms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import src.sait.frms.problemdomain.*;

public class FlightManager {
	static final String WEEKDAY_ANY;
	static final String WEEKDAY_SUNDAY;
	static final String WEEKDAY_MONDAY;
	static final String WEEKDAY_TUESDAY;
	static final String WEEKDAY_WEDNESDAY;
	static final String WEEKDAY_THURSDAY;
	static final String WEEKDAY_FRIDAY;
	static final String WEEKDAY_SATURDAY;
	private ArrayList<Flight> flights;
	private ArrayList<String> airports;
	private final String FILE_PATH = "res/flights.csv";
	
	
	public FlightManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Flight> getFlights() throws FileNotFoundException {
		Scanner in = new Scanner(new File(FILE_PATH));

		while (in.hasNext()) {
			String[] fields = in.nextLine().split(",");

			//need to figure out what does the code refer to, so use placeholder first
			//String code, String airlineName, String from, String to, String weekday, String time, int seats,
			//double costPerSeat
			String code = "Placeholder";

			String airlineName = fields[0];
			String from = fields[1];
			String to = fields[2];
			String weekday = fields[3];
			String time = fields[4];
			int seats = Integer.parseInt(fields[5]);
			double costPerSeat = Double.parseDouble(fields[6]);

			flights.add(new Flight(code, airlineName, from, to, weekday, time, seats, costPerSeat));

		}
		in.close();
		return flights;
	}

	public ArrayList<String> getAirports() {
		return airports;
	}
	
	public String findAirportByCode(String code) {
		
	}
	
	public Flight findFlightByCode(String code) {
		
	}
	
	public ArrayList<Flight> findFlights (String from, String to, String weekday) {
		
	}
	
	private void populateFlights() {
		
	}
	
	private void populateAirports() {
		
	}
}
