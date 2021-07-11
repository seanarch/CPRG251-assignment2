package sait.frms.manager;

import java.io.*;
import java.util.*;
import sait.frms.problemdomain.*;

public class FlightManager {
	static final String WEEKDAY_ANY = "ANY";
	static final String WEEKDAY_SUNDAY = "Sunday";
	static final String WEEKDAY_MONDAY = "Monday";
	static final String WEEKDAY_TUESDAY = "Tuesday";
	static final String WEEKDAY_WEDNESDAY = "Wednesday";
	static final String WEEKDAY_THURSDAY = "Thursday";
	static final String WEEKDAY_FRIDAY = "Friday";
	static final String WEEKDAY_SATURDAY = "Saturday";
	private ArrayList<Flight> flights;
	private ArrayList<Flight> flightsFound = new ArrayList<>();
	private ArrayList<String> airports;
	private final String FILE_PATH = "res/flights.csv";
	private final String FILE_PATH_AIRPORT = "res/airports.csv";
	private Scanner in;
	
	
	public FlightManager() {
		super();
		// TODO Auto-generated constructor stub
		try {
			//getFlights();
			//getAirports();
			flights = new ArrayList<>();
			airports = new ArrayList<>();
			populateFlights();
			populateAirports();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Flight> getFlights() throws FileNotFoundException {
		/*
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
		*/
		return flights;
	}

	public ArrayList<String> getAirports() throws FileNotFoundException {
		/*
		in = new Scanner(new File(FILE_PATH_AIRPORT));

		while (in.hasNext()) {
			String[] fields = in.nextLine().split(",");
			String code = fields[0];
			String airport = fields[1];
			String line = code + "," + airport;			
			airports.add(code);
		}
		in.close();
		*/
		return airports;
	}
	
	public String findAirportByCode(String code) {
		for (int i = 0; i < airports.size(); i++) {
			if (airports.get(i).equals(code)) {
				return airports.get(i+1);
			}
		}
		return "";
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
		if (weekday == WEEKDAY_ANY) {
			for (Flight f : flights) {
				if (f.getFrom().equalsIgnoreCase(from) && f.getTo().equalsIgnoreCase(to)) {
					flightsFound.add(f);
				}
			}
		} else {
			for (Flight f : flights) {
				if (f.getFrom().equalsIgnoreCase(from) &&
						f.getTo().equalsIgnoreCase(to) &&
						f.getWeekday().equalsIgnoreCase(weekday)) {
					flightsFound.add(f);
				}
			}
		}
		return flightsFound;
	}
	
	private void populateFlights() throws FileNotFoundException {
		Scanner in = new Scanner(new File(FILE_PATH));
		while (in.hasNext()) {
			String[] fields = in.nextLine().split(",");
			String code = fields[0];
			String from = fields[1];
			String to = fields[2];
			String weekday = fields[3];
			String time = fields[4];
			int seats = Integer.parseInt(fields[5]);
			double costPerSeat = Double.parseDouble(fields[6]);
			flights.add(new Flight(code, from, to, weekday, time, seats, costPerSeat));
		}
	}
	
	private void populateAirports() throws FileNotFoundException {
		Scanner in = new Scanner(new File(FILE_PATH_AIRPORT));
		while (in.hasNext()) {
			String[] fields = in.nextLine().split(",");
			String code = fields[0];
			airports.add(code);
			//String airport = fields[1];
			//airports.add(airport);
		}
	}
}
