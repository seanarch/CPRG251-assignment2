package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase {
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;

	private DefaultListModel<Flight> flightsModel;

	JComboBox comboboxTo;
	JComboBox comboboxFrom;
	JComboBox comboboxDay;
	JPanel textArea;
	JTextArea result;
	ArrayList<Flight> flightObject = null;
	ArrayList<String> airportObject = null;
	Reservation reservations = null;
	TextField textFlight;
	TextField textAirline;
	TextField textDay;
	TextField textTime; 
	TextField textCost;
	TextField textName;
	TextField textCitizenship;


	/**
	 * Creates the components for flights tab.
	 */
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager      Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;

		panel.setLayout(new BorderLayout());

		panel.add(createNorthPanel(), BorderLayout.NORTH);
		panel.add(createCenterPanel(), BorderLayout.CENTER);
		panel.add(createSouthPanel(), BorderLayout.SOUTH);
		panel.add(createEastPanel(), BorderLayout.EAST);
	}

	private JPanel createSouthPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel labelTitle = new JLabel("Flight Finder", SwingConstants.CENTER);
		labelTitle.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(labelTitle, BorderLayout.NORTH);

		JPanel gridbag = new JPanel();
		gridbag.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel labelFrom = new JLabel("From:");
		c.gridx = 0;
		c.gridy = 0;
		gridbag.add(labelFrom, c);
		comboboxFrom = new JComboBox();

		c.gridx = 1;
		c.gridy = 0;
		gridbag.add(comboboxFrom, c);

		JLabel labelTo = new JLabel("To:");
		c.gridx = 0;
		c.gridy = 1;
		gridbag.add(labelTo, c);
		comboboxTo = new JComboBox();
		try {
			airportObject = flightManager.getAirports();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String ao : airportObject) {
			comboboxTo.addItem(ao);
			comboboxFrom.addItem(ao);
		}

		c.gridx = 1;
		c.gridy = 1;
		gridbag.add(comboboxTo, c);

		JLabel labelDay = new JLabel("Day:");
		c.gridx = 0;
		c.gridy = 2;
		gridbag.add(labelDay, c);

		String[] Day = { "Any", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		comboboxDay = new JComboBox(Day);
		c.gridx = 1;
		c.gridy = 2;
		gridbag.add(comboboxDay, c);

		panel.add(gridbag, BorderLayout.CENTER);
		JButton findFlightsButton = new JButton("Find Flights");
		panel.add(findFlightsButton, BorderLayout.SOUTH);
		findFlightsButton.addActionListener(new ButtonListener());

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

		JLabel labelFlight = new JLabel("Flight:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 0;
		gridbag.add(labelFlight, c);

		textFlight = new TextField(10);
		textFlight.setEditable(false);
		c.gridx = 1;
		c.gridy = 0;
		gridbag.add(textFlight, c);

		JLabel labelAirline = new JLabel("Airline:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 1;
		gridbag.add(labelAirline, c);

		textAirline = new TextField(10);
		textAirline.setEditable(false);
		c.gridx = 1;
		c.gridy = 1;
		gridbag.add(textAirline, c);

		JLabel labelDay = new JLabel("Day:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		gridbag.add(labelDay, c);

		textDay = new TextField(10);
		textDay.setEditable(false);
		c.gridx = 1;
		c.gridy = 2;
		gridbag.add(textDay, c);

		JLabel labelTime = new JLabel("Time:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 3;
		gridbag.add(labelTime, c);

		textTime = new TextField(10);
		textTime.setEditable(false);
		c.gridx = 1;
		c.gridy = 3;
		gridbag.add(textTime, c);

		JLabel labelCost = new JLabel("Cost:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 4;
		gridbag.add(labelCost, c);

		textCost = new TextField(10);
		textCost.setEditable(false);
		c.gridx = 1;
		c.gridy = 4;
		gridbag.add(textCost, c);

		JLabel labelName = new JLabel("Name:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 5;
		gridbag.add(labelName, c);

		textName = new TextField(10);
		c.gridx = 1;
		c.gridy = 5;
		gridbag.add(textName, c);

		JLabel labelCitizenship = new JLabel("Citizenship:", SwingConstants.RIGHT);
		c.gridx = 0;
		c.gridy = 6;
		gridbag.add(labelCitizenship, c);

		textCitizenship = new TextField();
		c.gridx = 1;
		c.gridy = 6;
		gridbag.add(textCitizenship, c);

		panel.add(gridbag, BorderLayout.CENTER);

		JButton reserveButton = new JButton("Reserve");
		panel.add(reserveButton, BorderLayout.SOUTH);
		reserveButton.addActionListener(new ReserveButtonListener());

		return panel;
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		textArea = new JPanel();
		textArea.setLayout(new BorderLayout());
		textArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);
		
		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);

		flightsList.addListSelectionListener(new MyListSelectionListener());

		textArea.add(scrollPane);

		return textArea;
	}

	private class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			textFlight.setText(flightsList.getSelectedValue().getCode());
			textAirline.setText(flightsList.getSelectedValue().getAirlineName());
			textDay.setText(flightsList.getSelectedValue().getWeekday());
			textTime.setText(flightsList.getSelectedValue().getTime());
			textCost.setText("$"+flightsList.getSelectedValue().getCostPerSeat()); 
		}

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String flightFrom = (String) comboboxFrom.getSelectedItem();
			String flightTo = (String) comboboxTo.getSelectedItem();
			String flightDay = (String) comboboxDay.getSelectedItem();
			result = new JTextArea();
			
			try {
				flightObject = flightManager.getFlights();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			for (Flight fo : flightObject) {
				
				if (fo.getFrom() == flightFrom && fo.getTo() == flightTo && fo.getWeekday() == flightDay) {
					flightsModel.addElement(fo);
					result.setText(fo.toString());
					textArea.add(result);			
				}
							
				flightsList.setModel(flightsModel);
				
			}
		}
	}
	
	private class ReserveButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String flightCode = textFlight.getText();
			String name = textName.getText();
			String citizenship = textCitizenship.getText();
			try {
				reservations = reservationManager.makeReservation(flightsList.getSelectedValue(), name, citizenship);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Reservation created. Your code is "+ reservations.getCode());
		}
	}

}
