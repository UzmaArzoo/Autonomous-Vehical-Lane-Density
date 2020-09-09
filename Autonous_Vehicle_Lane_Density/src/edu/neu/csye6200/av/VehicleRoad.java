package edu.neu.csye6200.av;

import java.awt.Color;
import java.awt.Graphics;

public class VehicleRoad extends Vehicle {
	
	Color vehicleColor;

	/*
	 * Constructor call
	 */
	public VehicleRoad(int x, int y, Color color, int speed, String originalLane) 
	{
		//calling the parent class constructor
		super(x, y,color,speed,originalLane);
		this.vehicleColor =color;
	}
	
	/*
	 * Method call for vehicle color and shape
	 */
	public void paintMyVehicle(Graphics g)
	{
		    Color c1 =Color.ORANGE;
	        Color c2 =vehicleColor;
	        g.setColor(c1);	       
	        g.setColor(c2);
	        g.fillRoundRect(x,y+20,100,40,5,5);
	        g.fillArc(x+90,y+20,20,40,270,180);
	        g.setColor(c1);	     
	        g.fillRoundRect(x+10,y,70,25,10,10);
	        g.setColor(Color.white);
	        g.fillRect(x+20,y+5,20,25);
	        g.fillRect(x+50,y+5,20,25);
	        g.setColor(Color.black);
	        g.fillRoundRect(x+55,y+10,10,20,10,10);
	        g.fillOval(x+10,y+50,25,25);
	        g.fillOval(x+60,y+50,25,25);
	        g.setColor(Color.white);
	        g.fillOval(x+15,y+55,10,10);
	        g.fillOval(x+65,y+55,10,10);	
	}	
}
