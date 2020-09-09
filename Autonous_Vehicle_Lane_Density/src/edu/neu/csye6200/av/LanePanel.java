package edu.neu.csye6200.av;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import edu.neu.csye6200.av.ArzooApp;

public class LanePanel extends JPanel implements Observer 
{
	private Simulation sim = null;
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();	
	ArrayList<Integer> speedList = new ArrayList<Integer>();
	protected JPanel mainPanel;
	protected int set_Lane1_Speed=80;
	protected int set_Lane2_Speed=60;
	protected int set_Lane3_Speed=40;
	private final static Logger log = Logger.getLogger(LanePanel.class.getName());

	/*
	 * method to add the list of vehicle
	 */
	public void addMyVehicle(Vehicle vehicle)
	{
		vehicles.add(vehicle);
	}
	
	/*
	 * paint the main panel
	 */
	public void paint(Graphics g) 
	{		
		super.paintComponent(g);
		
		//adding background image
		try 
		{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("Land_image.jpg");
			BufferedImage bg = ImageIO.read(input);
			g.drawImage(bg, 200, 0, getWidth(), getHeight(),this);
			g.setColor(Color.BLACK);
			log.info("Background image uploaded");
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			log.log(Level.SEVERE, "Exception while saving file", e);
			e.printStackTrace();
		}
		
		// adding the boundry of lanes
		g.fillRect(200, 220, getWidth(), 300);
		g.setColor(Color.WHITE);
		for(int a= 220; a<220*4;a=a+100)
		{
			for(int b=200;b<getWidth();b=b+40)
			{
				g.fillRect(b,a,30,5);
			}
		}	
		
		for(int i = 0; i< vehicles.size();i++)
		{
			vehicles.get(i).paintMyVehicle(g);
		}		
	}

	/*
	 * update call to move the vehicle and repaint it
	 */
	@Override
	public void update(Observable o, Object arg) 
	{
		if (arg instanceof Simulation) 
		{
			sim = (Simulation) arg;
		    nextMove();//updating the vehicle movement
			this.mainPanel.repaint();
		}		
	}
	
	/*
	 *  getting the JPanel which is to be repaint to move the vehicle
	 */
	public void getMainPanel(JPanel mainPanel)
	{
		this.mainPanel= mainPanel;
	}
 
	/*
	 * method which is calculating the next location of vehicle for each lane
	 */
	public void nextMove() 
	{
		// TODO Auto-generated method stub
		System.out.println("call here in nextMove");
		for(int i = 0;i<vehicles.size();i++)
		{
			Vehicle myVehicle = vehicles.get(i);
				
			if (myVehicle.getOriginalLane().equals("lane1"))
			{
				myVehicle.setX(myVehicle.getX()+set_Lane1_Speed);//move forward with certain speed
			}
			else if (myVehicle.getOriginalLane().equals("lane2"))
			{
				myVehicle.setX(myVehicle.getX()+set_Lane2_Speed);
			}
			else if (myVehicle.getOriginalLane().equals("lane3"))
			{
				myVehicle.setX(myVehicle.getX()+set_Lane3_Speed);	
			}			
		}		
	}
	
	/*
	 *  When stop button is clicked, clear the array list as well so that a fresh start can be made
	 */
	public void stopMoving(JPanel mainPanel) 
	{
		mainPanel = new JPanel();
		vehicles.removeAll(vehicles);//clear the list when Stop is clicked
		log.info("Thread has been stopped and list has been cleared");
	}
	
	/*
	 * Change Lane Rule method is called
	 */
	public void changeLaneRule(String laneRule)
	{
		ChangeLaneRule changeLaneRule = new ChangeLaneRule();
		changeLaneRule.swapLane(laneRule, vehicles);
		this.mainPanel.repaint();
	}	
	/*
	 *  Change Speed rule method is called
	 */
	public void changeSpeedRule(String speedRule)
	{
		ChangeSpeedRule changeSpeedRule = new ChangeSpeedRule();
		speedList = (ArrayList<Integer>) changeSpeedRule.changeSpeed(speedRule,vehicles,set_Lane1_Speed,set_Lane2_Speed, set_Lane3_Speed);
		set_Lane1_Speed = speedList.get(0);
		set_Lane2_Speed = speedList.get(1);
		set_Lane3_Speed = speedList.get(2);
		this.mainPanel.repaint();
	}
}
