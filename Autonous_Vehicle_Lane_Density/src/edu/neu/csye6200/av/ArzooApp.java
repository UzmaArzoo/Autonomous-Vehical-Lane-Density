package edu.neu.csye6200.av;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import AbstractClass.AVApp;

/*
 * Main class
 * 
 */
public class ArzooApp extends AVApp implements ActionListener{
	
    private Logger log = Logger.getLogger(ArzooApp.class.getName());
    
    private LanePanel lanePanel;
    private ChangeLaneRule changeLaneRule;
    private ChangeSpeedRule changeSpeedRule;
    protected JPanel mainPanel;
    protected JPanel topPanel;
    
	private JButton startBtn;
	private JButton pauseBtn;
	private JButton stopBtn;
	
	private JButton lane1;
	private JButton lane2;
	private JButton lane3;

	private JComboBox<String> ruleComboBox;
	private JComboBox<String> speedComboBox;	

	private Simulation sim = null;
	
	/*
	 * 
	 * Constructor
	 */
	@SuppressWarnings("deprecation")
	public ArzooApp() {
		
	 	frame.setSize(500, 400);
		frame.setTitle("ArzooApp");
		
		menuMgr.createDefaultActions(); // default menu items
		
		initialiseSim(); 

		showUI(); // dispatch thread to display the JFrame
		//observer subscription
		sim.addObserver(lanePanel); // panel to hear simulation events		
	}
	
	/*
	 * Initializing the simulation
	 */
	private void initialiseSim() {
		sim = new Simulation();
	}
	
	/*
	 *  Top Panel of the frame
	 */
	public JPanel getTopPanel() {
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
				
		//Lane change combo box
		JLabel rule1 = new JLabel("Lane Rule:");
		ruleComboBox = new JComboBox<String> ();
		ruleComboBox.addItem(" ");
		ruleComboBox.addItem("Change Lane 1 and Lane 2");
		ruleComboBox.addItem("Change Lane 2 and Lane 3");
		ruleComboBox.addItem("Change Lane 1 and Lane 3");	
		ruleComboBox.setEnabled(false);
		
		//event listener
		ruleComboBox.addActionListener(
			    new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			            JComboBox combo = (JComboBox)e.getSource();
			            String val = (String)combo.getSelectedItem();
			            lanePanel.changeLaneRule(val);
			        }
			    }            
			);
		
		JLabel rule2 = new JLabel("Speed Rule:");
		speedComboBox = new JComboBox<String> ();
		speedComboBox.addItem(" ");
		speedComboBox.addItem("Increase speed for Lane 1");
		speedComboBox.addItem("Decrease speed for Lane 1");
		speedComboBox.addItem("Increase speed for Lane 2");
		speedComboBox.addItem("Decrease speed for Lane 2");
		speedComboBox.addItem("Increase speed for Lane 3");	
		speedComboBox.addItem("Decrease speed for Lane 3");
		speedComboBox.setEnabled(false);
		
		speedComboBox.addActionListener(
			    new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			            JComboBox combo = (JComboBox)e.getSource();
			            String val = (String)combo.getSelectedItem();
			            lanePanel.changeSpeedRule(val);
			        }
			    }            
			);
				
		startBtn = new JButton("Start");
		
	    startBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start was pressed");
				ruleComboBox.setEnabled(true);
				speedComboBox.setEnabled(true);
				lanePanel.getMainPanel(mainPanel);
				sim.startSim();
				sim.setRunning(true);				
				resetButtons();
			}	    	
	    });
		
	    pauseBtn = new JButton("Pause");
	    pauseBtn.setEnabled(false); 
	    pauseBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pause was pressed");
				ruleComboBox.setEnabled(true);
				speedComboBox.setEnabled(true);
				sim.pauseSim();
				resetButtons();
			}
			
		});
	    
		stopBtn = new JButton("Stop");
		stopBtn.setEnabled(false);  
		stopBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Stop was pressed");
				ruleComboBox.setEnabled(false);
				speedComboBox.setEnabled(false);
				sim.stopSim();
				sim.setRunning(false); 
				lanePanel.stopMoving(mainPanel);
				resetStopButtons();
			}
			
		});
		
		// Add everything to the top panel
		topPanel.add(rule1);
		topPanel.add(ruleComboBox);
		
		topPanel.add(rule2);
		topPanel.add(speedComboBox);
		
		topPanel.add(startBtn);
		topPanel.add(pauseBtn);
		topPanel.add(stopBtn);		
		
		return topPanel;
		
	}
	
	/*
	 *  reset button to enable Start, Stop and Pause button respectively
	 *  when you start the App
	 */
	private void resetButtons() 
	{
		if (sim == null) return;		
		startBtn.setEnabled(!sim.isRunning());
		pauseBtn.setEnabled(sim.isPausable());
		stopBtn.setEnabled(sim.isRunning());
	}
	
	/*
	 * Reset rule after clicking the stop button
	 */
	private void resetStopButtons() 
	{
		if (sim == null) return;		
		startBtn.setEnabled(!sim.isRunning());
		pauseBtn.setEnabled(sim.isPausable());
		stopBtn.setEnabled(sim.isRunning());
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("We received an ActionEvent " + e);
	}		

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {	
	}

	@Override
	public void windowActivated(WindowEvent e) {	
	}

	@Override
	public void windowDeactivated(WindowEvent e) {	
	}

	/*
	 * MainPanel of the frame
	 */
	@Override
	public JPanel getMainPanel() {	
				
		mainPanel = new JPanel();		
		lane1 = new JButton("Add Lane1 car"); //adding lane 1
    	lane1.setBounds(30, 300, 150, 40);
    	lane1.addActionListener(new ActionListener() 
    	{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				VehicleRoad vr = new VehicleRoad(230, 250,Color.RED,80,"lane1");//add vehicle 1 for lane 1
		    	lanePanel.addMyVehicle(vr);
		    	mainPanel.repaint();
			}
		});
    	mainPanel.add(lane1);
    	
    	lane2 = new JButton("Add Lane2 car"); //adding lane 2
    	lane2.setBounds(30, 400, 150, 40);
    	lane2.addActionListener(new ActionListener() 
    	{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				VehicleRoad vr = new VehicleRoad(230, 350,Color.BLUE,60,"lane2");//adding vehicle 2 for lane 2
		    	lanePanel.addMyVehicle(vr);
		    	mainPanel.repaint();				
			}
		});
    	mainPanel.add(lane2);
    	
    	lane3 = new JButton("Add Lane3 car");//adding lane 3
    	lane3.setBounds(30, 500, 150, 40);
    	lane3.addActionListener(new ActionListener() 
    	{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				VehicleRoad vr = new VehicleRoad(230, 450,Color.YELLOW,40,"lane3");//adding vehicle 3 for lane 3
		    	lanePanel.addMyVehicle(vr);
		    	mainPanel.repaint();				
			}
		});
    	mainPanel.add(lane3);
		
		mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(BorderLayout.NORTH, getTopPanel());   	
    	
    	lanePanel = new LanePanel();
    	mainPanel.add(BorderLayout.CENTER, lanePanel);   
    	
		return mainPanel;
	}
	
	public static void main(String[] args) {
		ArzooApp myApp = new ArzooApp(); //main app
        System.out.println("ArzooApp ends here");             
	}	
}
