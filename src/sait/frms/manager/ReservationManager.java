package sait.frms.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.lang.String;

import sait.frms.problemdomain.*;
import sait.frms.manager.*;

public class ReservationManager{

	private static final SAVE_TO_FILE = "res/reservation.dat";
	private static ArrayList<Reservation> reservations;
	private final MODE = "rw";
	private RandomAccessFile raf;

	public ReservationManager() throws Exception {
		reservations = new ArrayList<Reservation>();
		
		raf = new RandomAccessFile(SAVE_TO_FILE, MODE);
	}
	
	public Reservation makeReservation(Flight flight, String name, String citizenship) {
		Reservation r;
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
				//write reservation info to binary file 
				String generatedCodeBinary = String.format("%-5s", generatedCode); //LDDDD (i.e.: I1234) 7 bytes
				raf.writeUTF(generatedCodeBinary);
				
				String flightCodeBinary = String.format("%-7s", flight.getCode()); //LL-DDDD (I.e.: GA-1234) 9 bytes
				raf.writeUTF(flightCodeBinary);
				
				String airLineBinary = String.format("%-100s", flight.getAirlineName());  //102bytes
				raf.writeUTF(airLineBinary);
				
				String nameBinary = String.format("%-100s", name);//102bytes
				raf.writeUTF(nameBinary);
				
				String citizenshipBinary = String.format("%-100s", citizenship);//102bytes
				raf.writeUTF(citizenshipBinary);
				
				raf.writeDouble(flight.getCostPerSeat()); // 8 bytes
				raf.writeBoolean(false); //1byte
				r = new Reservation(generatedCode, flight.getCode(), flight.getAirlineName(), name, citizenship, flight.getCostPerSeat(), false);
				return r;
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
        final int RERSERVATION_BYTE_SIZE = 331; 
        Reservation reservationsRecord;

            try {
            	//read record to reservation object   	
            	for (int position = 0; position < raf.length(); position += RERSERVATION_BYTE_SIZE) {
            		raf.seek(position);
            		String generatedCodeBinary = raf.readUTF().trim();
            		String flightCodeBinary = raf.readUTF().trim();
            		String airLineBinary = raf.readUTF().trim();
            		String nameBinary = raf.readUTF().trim();
            		String citizenshipBinary = raf.readUTF().trim();
            		double costPerSeat = raf.readDouble();
            		boolean isActive = raf.readBoolean();
            		reservationsRecord = new Reservation (generatedCodeBinary,flightCodeBinary,airLineBinary,nameBinary,citizenshipBinary,costPerSeat,isActive);
            		

                    if (code.toUpperCase() == generatedCodeBinary.toUpperCase()
                            || airline.toUpperCase() == airLineBinary.toUpperCase()
                            || name.toUpperCase() == nameBinary.toUpperCase()) {
                    	findMatchReservation.add(reservationsRecord);
                    }
                }
            } catch (IOException e) {
               System.out.println("End Of File");
            }
        
        return findMatchReservation;
    }
	
	public Reservation findReservationByCode(String code) {
		
		
		Reservation findByCode;
		
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
