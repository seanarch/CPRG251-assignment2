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
			flights = new ArrayList<>();
			airports = new ArrayList<>();
			populateFlights();
			populateAirports();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Read all airports and flights from csv files
	 */
	public ArrayList<Flight> getFlights() throws FileNotFoundException {
		return flights;
	}
	/**
	 * Get all flight names
	 * @return all flight names
	 * @throws FileNotFoundException if flights.csv file doesn't exist
	 */
	public ArrayList<String> getAirports() throws FileNotFoundException {
		return airports;
	}
	/**
	 * Find airport by code
	 * @param code	flight code
	 * @return		airport name
	 */
	public String findAirportByCode(String code) {
		for (int i = 0; i < airports.size(); i++) {
			if (airports.get(i).equals(code)) {
				return airports.get(i+1);
			}
		}
		return "";
	}
	/**
	 * Find flight by flight code
	 * @param code	flight code
	 * @return		found flight object
	 */
	public Flight findFlightByCode(String code) {
		for (Flight f:flights) {
			if (f.getCode().equalsIgnoreCase(code)) {
				return f;
			}
		}
		throw new RuntimeException("The code you input doesn't exist in the system. Please enter a new code and try again.");
	}
	/**
	 * Find flights by user selected options
	 * @param from 		departure airport
	 * @param to 		destination airport
	 * @param weekday 	departure date
	 * @return			those flights meet the specific criteria
	 */
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
	/**
	 * Populate flights from flights.csv file
	 * @throws FileNotFoundException if flights.csv file doesn't exist
	 */
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
	/**
	 * Populate airports from airports.csv file
	 * @throws FileNotFoundException if the csv file doesn't exist
	 */
	private void populateAirports() throws FileNotFoundException {
		Scanner in = new Scanner(new File(FILE_PATH_AIRPORT));
		while (in.hasNext()) {
			String[] fields = in.nextLine().split(",");
			String code = fields[0];
			airports.add(code);
		}
	}
}
