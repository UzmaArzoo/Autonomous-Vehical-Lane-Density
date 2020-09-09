package edu.neu.csye6200.av;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class ChangeLaneRule {

	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(LanePanel.class.getName());	

	/*
	 *  when user selects change lane rule
	 *  Lanes can be changed by changing the Y-coordinate of the vehicle of a given lane
	 */
	
	public void swapLane(String changeLane, ArrayList vehicles)
	{		
		if(changeLane.equals("Change Lane 1 and Lane 2"))
		{
			for(int i = 0;i<vehicles.size();i++)
			{
				Vehicle myVehicle = (Vehicle) vehicles.get(i);
				
				if (myVehicle.getY() == 350)
				{
					myVehicle.setY(250);	
				}
				else if (myVehicle.getY() == 250)
				{
					myVehicle.setY(350);	
				}				
				
			}	
		}
		else if(changeLane.equals("Change Lane 2 and Lane 3"))
		{
			for(int i = 0;i<vehicles.size();i++)
			{
				Vehicle myVehicle = (Vehicle) vehicles.get(i);
				
				if (myVehicle.getY() == 350)
				{
					myVehicle.setY(450);	
				}
				else if (myVehicle.getY() == 450)
				{
					myVehicle.setY(350);	
				}
				
			}	
		}
		else if(changeLane.equals("Change Lane 1 and Lane 3"))
		{
			for(int i = 0;i<vehicles.size();i++)
			{
				Vehicle myVehicle = (Vehicle) vehicles.get(i);
				System.out.println("myVehicle.getY()="+myVehicle.getY());
				
				if (myVehicle.getY() == 250)
				{
					myVehicle.setY(450);	
				}
				else if (myVehicle.getY() == 450)
				{
					myVehicle.setY(250);	
				}				
			}				
		}
		log.info("Lane Changed");
	}
}
