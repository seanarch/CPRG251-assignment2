package sait.frms.manager;

import java.util.ArrayList;

import sait.frms.problemdomain.*;

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
	
	
	public FlightManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Flight> getFlights() {
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
