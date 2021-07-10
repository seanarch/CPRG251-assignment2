package sait.frms.manager;

import java.io.*;
import java.util.ArrayList;
import sait.frms.problemdomain.*;
import sait.frms.manager.*;

public class ReservationManager{

	private static final String SAVE_TO_FILE = "res/reservation.dat";
	private static ArrayList<Reservation> reservations;
	private final String MODE = "rw";
	private RandomAccessFile raf;
	final int RERSERVATION_BYTE_SIZE = 331;

	public ReservationManager() throws Exception {
		reservations = new ArrayList<Reservation>();
		populateFromBinary();
		raf = new RandomAccessFile(SAVE_TO_FILE, MODE);
	}

	public Reservation makeReservation(Flight flight, String name, String citizenship) throws IOException {
		// check the flight code is valid
		if (this.getAvailableSeats(flight) > 0) {
			if (name == "") {
				System.out.println("Name is required");
				return null;
			} else if (citizenship == "") {
				System.out.println("citizenship is required");
				return null;
			} else {
				// Reservation record will have Reservation code, Flight code, Airline, Name,
				// Citizenship, Cost, active
				String generatedCode = generateReservationCode(flight);
				System.out.println("Reservation created. Your code is " + generatedCode + ".");
				
				// Available seat - 1
				flight = new Flight(flight.getCode(), flight.getFrom(), flight.getTo(), flight.getWeekday(), flight.getTime(), this.getAvailableSeats(flight)-1, flight.getCostPerSeat());
				Reservation rsv = new Reservation(generatedCode, flight.getCode(), flight.getAirlineName(), name,
						citizenship, flight.getCostPerSeat(), false);
				
				// write reservation info to binary file
				reservations.add(rsv);
				persist();
				return rsv;
			}
		} else {
			System.out.println("This flight is not available. No available seats.");
			return null;
		}
	}

	/**
	 * A travel agent can find existing flight reservations using the reservation
	 * code, airline, and traveler name. The criteria can match any combination of
	 * the three fields.
	 * 
	 * @param code
	 * @param airline
	 * @param name
	 * @return
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name) throws IOException {
		ArrayList<Reservation> findMatchReservation = new ArrayList<>();
		Reservation reservationsRecord;

		try {
			// read record to reservation object
			for (int position = 0; position < raf.length(); position += RERSERVATION_BYTE_SIZE) {
				raf.seek(position);
				String generatedCodeBinary = raf.readUTF().trim();
				String flightCodeBinary = raf.readUTF().trim();
				String airLineBinary = raf.readUTF().trim();
				String nameBinary = raf.readUTF().trim();
				String citizenshipBinary = raf.readUTF().trim();
				double costPerSeat = raf.readDouble();
				boolean isActive = raf.readBoolean();
				reservationsRecord = new Reservation(generatedCodeBinary, flightCodeBinary, airLineBinary, nameBinary,
						citizenshipBinary, costPerSeat, isActive);

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
		try {
			raf.seek(0);
			for (long position = 0; position < this.raf.length(); position += RERSERVATION_BYTE_SIZE) {
				if (this.raf.readUTF().trim().equals(code)) {
					return new Reservation(this.raf.readUTF().trim(), this.raf.readUTF().trim(),
							this.raf.readUTF().trim(), this.raf.readUTF().trim(), this.raf.readUTF().trim(),
							this.raf.readDouble(), this.raf.readBoolean());
				}
			}
		} catch (IOException e) {
			System.out.println("End Of File");
		}
		return null;
	}

	public void persist() throws IOException {
		for (int i = 0; i < reservations.size(); i++) {
			String generatedCodeBinary = String.format("%-5s", reservations.get(i).getCode()); // LDDDD (i.e.: I1234) 7
																								// bytes
			raf.writeUTF(generatedCodeBinary);

			String flightcode = String.format("%-7s", reservations.get(i).getFlightCode()); // LL-DDDD (I.e.: GA-1234) 9
																							// bytes
			raf.writeUTF(flightcode);

			String airline = String.format("%-100s", reservations.get(i).getAirline()); // 102bytes
			raf.writeUTF(airline);

			String name = String.format("%-100s", reservations.get(i).getName());// 102bytes
			raf.writeUTF(name);

			String citizenship = String.format("%-100s", reservations.get(i).getCitizenship());// 102bytes
			raf.writeUTF(citizenship);

			raf.writeDouble(reservations.get(i).getCost()); // 8 bytes
			raf.writeBoolean(reservations.get(i).isActive()); // 1byte
		}
	}

	private int getAvailableSeats(Flight flight) {
		return flight.getSeats();
	}

	/**
	 * The travel agent can make a flight reservation for a traveler. A reservation
	 * code will be generated and assigned to the traveler’s name and citizenship.
	 * The reservation code must use the format LDDDD, where L is either D for
	 * Domestic or I for International and DDDD is a random number between 1000 and
	 * 9999.
	 * 
	 * @param flight
	 * @return
	 */
	private String generateReservationCode(Flight flight) {
		String generatedCode = "";
		int number = (int) (Math.random() * 9000 + 1000);
		if (flight.getFrom().startsWith("Y") && flight.getTo().startsWith("Y")) {
			generatedCode = "D" + number;
		} else {
			generatedCode = "I" + number;
		}
		return generatedCode;
	}

	private void populateFromBinary() throws IOException {
		boolean endOfFile = false;
		while (!endOfFile) {
			try {
				for (int position = 0; position < raf.length(); position += RERSERVATION_BYTE_SIZE) {
					raf.seek(position);
					String generatedCodeBinary = raf.readUTF().trim();
					String flightCodeBinary = raf.readUTF().trim();
					String airLineBinary = raf.readUTF().trim();
					String nameBinary = raf.readUTF().trim();
					String citizenshipBinary = raf.readUTF().trim();
					double costPerSeat = raf.readDouble();
					reservations.add(new Reservation(generatedCodeBinary, flightCodeBinary, airLineBinary, nameBinary,
							citizenshipBinary, costPerSeat, true));
				}
			} catch (Exception e) {
				endOfFile = true;
			}

		}
	}

}
