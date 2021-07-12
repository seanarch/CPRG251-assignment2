package sait.frms.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import sait.frms.manager.*;
import sait.frms.problemdomain.*;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	private DefaultListModel<Reservation> reservationsModel;
	private JList<Reservation> reservationsList;
	JTextField textCode;
	JTextField textAirlineSearch;
	JTextField textName;
	TextField textCodeShow;
	TextField textFlight;
	TextField textAirlineShow;
	TextField textCost;
	TextField textNameEdit;
	TextField textcitizenship;
	JComboBox textStatus;
	ArrayList<Reservation> reservationsRecord = new ArrayList<>();
	Reservation reservationbyCodeRecord = new Reservation();
	ArrayList<Flight> flights = new ArrayList<>();

	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel labelTitle = new JLabel("Search", SwingConstants.CENTER);
		labelTitle.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(labelTitle, BorderLayout.NORTH);

		JPanel gridbag = new JPanel();
		gridbag.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel labelCode = new JLabel("Code:");
		c.gridx = 0;
		c.gridy = 0;
		gridbag.add(labelCode, c);

		textCode = new JTextField(50);
		c.gridx = 1;
		c.gridy = 0;
		gridbag.add(textCode, c);

		JLabel labelAirline = new JLabel("Airline:");
		c.gridx = 0;
		c.gridy = 1;
		gridbag.add(labelAirline, c);

		textAirlineSearch = new JTextField(50);
		c.gridx = 1;
		c.gridy = 1;
		gridbag.add(textAirlineSearch, c);

		JLabel labelName = new JLabel("Name:");
		c.gridx = 0;
		c.gridy = 2;
		gridbag.add(labelName, c);

		textName = new JTextField(50);
		c.gridx = 1;
		c.gridy = 2;
		gridbag.add(textName, c);

		panel.add(gridbag, BorderLayout.CENTER);
		JButton findReservationButton = new JButton("Find Reservations");
		panel.add(findReservationButton, BorderLayout.SOUTH);
		findReservationButton.addActionListener(new ButtonListener());

		return panel;
	}

	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

		JLabel labelTitle = new JLabel("Reserve", SwingConstants.CENTER);
		labelTitle.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(labelTitle, BorderLayout.NORTH);

		JPanel gridbag = new JPanel();
		gridbag.setLayout(new GridBagLayout());
		gridbag.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel labelCode = new JLabel("Code:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 0;
		gridbag.add(labelCode, c);

		textCodeShow = new TextField(10);
		textCodeShow.setEditable(false);
		c.gridx = 1;
		c.gridy = 0;
		gridbag.add(textCodeShow, c);

		JLabel labelFlight = new JLabel("Flight:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 1;
		gridbag.add(labelFlight, c);

		textFlight = new TextField(10);
		textFlight.setEditable(false);
		c.gridx = 1;
		c.gridy = 1;
		gridbag.add(textFlight, c);

		JLabel labelAirline = new JLabel("Airline:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		gridbag.add(labelAirline, c);

		textAirlineShow = new TextField(10);
		textAirlineShow.setEditable(false);
		c.gridx = 1;
		c.gridy = 2;
		gridbag.add(textAirlineShow, c);

		JLabel labelCost = new JLabel("Cost:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 3;
		gridbag.add(labelCost, c);

		textCost = new TextField(10);
		textCost.setEditable(false);
		c.gridx = 1;
		c.gridy = 3;
		gridbag.add(textCost, c);

		JLabel labelName = new JLabel("Name:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 4;
		gridbag.add(labelName, c);

		textNameEdit = new TextField(10);
		c.gridx = 1;
		c.gridy = 4;
		gridbag.add(textNameEdit, c);

		JLabel labelCitizenship = new JLabel("Citizenship:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 5;
		gridbag.add(labelCitizenship, c);

		textcitizenship = new TextField(10);
		c.gridx = 1;
		c.gridy = 5;
		gridbag.add(textcitizenship, c);

		JLabel labelStatus = new JLabel("Status:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 6;
		gridbag.add(labelStatus, c);

		String[] isActive = { "Active", "Inactive" };
		textStatus = new JComboBox(isActive);
		c.gridx = 1;
		c.gridy = 6;
		gridbag.add(textStatus, c);

		panel.add(gridbag, BorderLayout.CENTER);
		JButton updateButton = new JButton("Update");
		panel.add(updateButton, BorderLayout.SOUTH);
		updateButton.addActionListener(new UpdateButtonListener());

		return panel;
	}

	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);

		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);

		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);

	}

	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		JScrollPane scrollPane;
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		// panel.add(new JTextArea());
		reservationsModel = new DefaultListModel<>();
		;
		reservationsList = new JList<>(reservationsModel);
		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// User can only select one item at a
																				// time.
		scrollPane = new JScrollPane(this.reservationsList);// Wrap JList in JScrollPane so it is scrollable.
		reservationsList.addListSelectionListener(new MyListSelectionListener());
		panel.add(scrollPane);
		return panel;
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	private class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			textCodeShow.setText(reservationsList.getSelectedValue().getCode());
			textFlight.setText(reservationsList.getSelectedValue().getFlightCode());
			textAirlineShow.setText(reservationsList.getSelectedValue().getAirline());
			textCost.setText("$" + reservationsList.getSelectedValue().getCost());
			textNameEdit.setText(reservationsList.getSelectedValue().getName());
			textcitizenship.setText(reservationsList.getSelectedValue().getCitizenship());
			if (reservationsList.getSelectedValue().isActive() == true) {
				textStatus.setSelectedItem("Active");

			} else {
				textStatus.setSelectedItem("Inactive");
			}

		}

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String reservationCode = textCode.getText().toUpperCase();
			String airline = textAirlineSearch.getText().toUpperCase();
			String name = textName.getText().toUpperCase();
			reservationsModel.clear();
			if (airline.isEmpty() && name.isEmpty()) {
				reservationbyCodeRecord = reservationManager.findReservationByCode(reservationCode);
				reservationsModel.addElement(reservationbyCodeRecord);
			} else {
				try {
					reservationsRecord = reservationManager.findReservations(reservationCode, airline, name);
					for (Reservation rr : reservationsRecord) {
						reservationsModel.addElement(rr);
						System.out.println(rr);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

	private class UpdateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String reservationCode = textCode.getText().toUpperCase();
			String name = textNameEdit.getText().toUpperCase();
			String citizenship = textcitizenship.getText().toUpperCase();
			String flightCode = textFlight.getText().toUpperCase();
			if (!name.isEmpty() && !citizenship.isEmpty()) {
				reservationbyCodeRecord = reservationManager.findReservationByCode(reservationCode);
				reservationbyCodeRecord.setName(name);
				reservationbyCodeRecord.setCitizenship(citizenship);
				if (textStatus.getSelectedItem().equals("Active")) {
					reservationbyCodeRecord.setActive(true);
				} else {
					// inactive as a soft delete,
					// the cancelled reservation will not be included in the number of seats used on
					// a flight
					reservationbyCodeRecord.setActive(false);
					for (Flight f : flights) {
						if (f.getCode().toUpperCase().equals(flightCode)) {
							f = new Flight(f.getCode(), f.getFrom(), f.getTo(), f.getWeekday(), f.getTime(),
									f.getSeats() + 1, f.getCostPerSeat());

						}
					}
				}
				try {

					// update reservation record to binary file
					reservationManager.persist();
					System.out.println("Updated Successfully!");
					JOptionPane.showMessageDialog(null, "Updated Successfully!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Name and Citizenship can not be empty, Please enter again");

			}

		}

	}

}
