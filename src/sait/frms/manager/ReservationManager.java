package sait.frms.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import sait.frms.problemdomain.*;
import sait.frms.manager.*;
public class ReservationManager{

	private final String SAVE_TO_FILE = "res/reservation.txt";
	private ArrayList<Reservation> reservations;

	public ReservationManager() throws Exception {
		reservations = new ArrayList<Reservation>();
	}
	
	public Reservation makeReservation(Flight flight, String name, String citizenship) {
		// check the flight code is valid
		if (flight.getSeats() > 0 ) {
			if (name == "") {
				System.out.println("Name is required");
				return null;
			} else if (citizenship == "") {
				System.out.println("citizenship is required");
				return null;
			} else {
				// Reservation record will have Reservation code, Flight code, Airline, Name, Citizenship, Cost, active
				String generatedCode = generateReservationCode(flight);
				System.out.println("Reservation created. Your code is " + generatedCode + ".");
				return new Reservation(generatedCode, flight.getCode(), flight.getAirlineName(), name, citizenship, flight.getCostPerSeat(), false);
			}
		} else {
			System.out.println("This flight is not available. No available seats.");
			return null;
		}
	}
	
	/**
	 * A travel agent can find existing flight reservations using the reservation code, airline, and traveler name. 
	 * The criteria can match any combination of the three fields.
	 * @param code reservation code searched by client
	 * @param airline airline name searched by client
	 * @param name traveler’s full name searched by client
	 * @return findMatchReservation ArrayList of matching reservation
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name) throws IOException {
        ArrayList<Reservation> findMatchReservation = new ArrayList<>();
        final int CHAR_SIZE = 2; // LDDDD : 10 bytes CHAR_SIZE; aireline: 4 bytes eg: OA – Otto Airline; name: 100 bytes
        boolean endOfFile = true;
        String codeRecord = "";
        String airelineRecord = "";
        String nameRecord = "";
        // open the file to read
        RandomAccessFile randomFile = new RandomAccessFile("res/reservations.dat", "r");
        while (!endOfFile) {
            try {
                randomFile.seek(0);
                codeRecord = randomFile.readUTF();
                randomFile.seek(CHAR_SIZE * 5);
                airelineRecord = randomFile.readUTF();
                randomFile.seek(CHAR_SIZE * 2);
                nameRecord = randomFile.readUTF();

 

                for (Reservation r : reservations) {
                    if (code.toUpperCase() == codeRecord.toUpperCase()
                            || airline.toUpperCase() == airelineRecord.toUpperCase()
                            || name.toUpperCase() == nameRecord.toUpperCase()) {
                        findMatchReservation.add(r);
                    }
                }
            } catch (IOException e) {
                endOfFile = true;
            }
        }
        return findMatchReservation;
    }
	
	public Reservation findReservationByCode(String code) {
		
	}
	
	public void persist() throws IOException {
		RandomAccessFile file = new RandomAccessFile(SAVE_TO_FILE, "rw");
		//byte[] bytes = "Hello World".getBytes("UTF-8");
		//file.write(bytes, 2, 5);
	}
	
	public int getAvailableSeats(Flight flight) {
		return flight.getSeats();
	}
	
	/**
	 * The travel agent can make a flight reservation for a traveler. 
	 * A reservation code will be generated and assigned to the traveler’s name and citizenship. 
	 * The reservation code must use the format LDDDD, where L is either D for Domestic or I for International 
	 * and DDDD is a random number between 1000 and 9999.
	 * @param flight
	 * @return
	 */
	private String generateReservationCode(Flight flight) {
		String generatedCode = "";
		int number = (int)(Math.random() * 9000 + 1000);
		if (flight.getFrom().startsWith("Y") && flight.getTo().startsWith("Y")) {
			generatedCode = "D" + number;
		} else {
			generatedCode = "I" + number;
		}
		return generatedCode;
	}

	private void populateFromBinary() {
		
	}

}
