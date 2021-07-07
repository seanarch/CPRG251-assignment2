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
	private final String FILE_PATH_AIRPORT = "res/airports.csv";
	private Scanner in;
	
	
	public FlightManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Flight> getFlights() throws FileNotFoundException {
		in = new Scanner(new File(FILE_PATH));

		while (in.hasNext()) {
			String[] fields = in.nextLine().split(",");

			//Flight Code,Departing Airport Code,Arrival Airport Code,Weekday,Time,Seats,Cost Per Seat
			//i.e.: CA-8346,YYC,ATL,Thursday,20:15,174,501.00
			String code = fields[0];

			//String airlineName = fields[0];
			String from = fields[1];
			String to = fields[2];
			String weekday = fields[3];
			String time = fields[4];
			int seats = Integer.parseInt(fields[5]);
			double costPerSeat = Double.parseDouble(fields[6]);

			flights.add(new Flight(code,  from, to, weekday, time, seats, costPerSeat));

		}
		in.close();
		return flights;
	}

	public ArrayList<String> getAirports() throws FileNotFoundException {
		in = new Scanner(new File(FILE_PATH_AIRPORT));

		while (in.hasNext()) {
			String[] fields = in.nextLine().split(",");
			String code = fields[0];
			String airport = fields[1];
			String line = code + "," + airport;			
			airports.add(line);
		}
		in.close();
		return airports;
	}
	
	public String findAirportByCode(String code) {
		
	}
	
	public Flight findFlightByCode(String code) {
		for (Flight f:flights) {
			if (f.getCode().equalsIgnoreCase(code)) {
				return f;
			}
		}
		throw new RuntimeException("The code you input doesn't exist in the system. Please enter a new code and try again.");
	}
	
	public ArrayList<Flight> findFlights (String from, String to, String weekday) {
		
	}
	
	private void populateFlights() {
		
	}
	
	private void populateAirports() {
		
	}
}
