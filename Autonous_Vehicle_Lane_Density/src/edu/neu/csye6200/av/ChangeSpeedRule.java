package edu.neu.csye6200.av;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ChangeSpeedRule{

	private final static Logger log = Logger.getLogger(LanePanel.class.getName());
	ArrayList<Integer> speedList = new ArrayList<Integer>();
	
	/*
	 * when user selects the speed rule
	 */
	public List<Integer> changeSpeed(String changeSpeed, ArrayList vehicles, int set_Lane1_Speed , int set_Lane2_Speed, int set_Lane3_Speed)
	{
		if(changeSpeed.equals("Increase speed for Lane 1"))
		{
			for(int i = 0;i<vehicles.size();i++)
			{
				Vehicle myVehicle = (Vehicle) vehicles.get(i);
				
				if (myVehicle.getOriginalLane().equals("lane1"))
				{
					set_Lane1_Speed = set_Lane1_Speed+40; //increase the speed by 40
				}		
				
			}	
		}
		else if(changeSpeed.equals("Decrease speed for Lane 1"))
		{
			for(int i = 0;i<vehicles.size();i++)
			{
				Vehicle myVehicle = (Vehicle) vehicles.get(i);
				
				if (myVehicle.getOriginalLane().equals("lane1") && set_Lane1_Speed >40)
				{
					set_Lane1_Speed = set_Lane1_Speed-40;//decrease the speed by 40
				}					
			}	
		}
		else if(changeSpeed.equals("Increase speed for Lane 2"))
		{
			for(int i = 0;i<vehicles.size();i++)
			{
				Vehicle myVehicle = (Vehicle) vehicles.get(i);
				
				if (myVehicle.getOriginalLane().equals("lane2"))
				{
					set_Lane2_Speed = set_Lane2_Speed+40;
				}		
				
			}	
		}
		else if(changeSpeed.equals("Decrease speed for Lane 2"))
		{
			for(int i = 0;i<vehicles.size();i++)
			{
				Vehicle myVehicle = (Vehicle) vehicles.get(i);
				
				if (myVehicle.getOriginalLane().equals("lane2") && set_Lane2_Speed >40)
				{
					set_Lane2_Speed = set_Lane2_Speed-40;
				}					
			}	
		}
		else if(changeSpeed.equals("Increase speed for Lane 3"))
		{
			for(int i = 0;i<vehicles.size();i++)
			{
				Vehicle myVehicle = (Vehicle) vehicles.get(i);
				
				if (myVehicle.getOriginalLane().equals("lane3"))
				{					
					set_Lane3_Speed = set_Lane3_Speed+40;
				}		
				
			}	
		}
		else if(changeSpeed.equals("Decrease speed for Lane 3"))
		{
			for(int i = 0;i<vehicles.size();i++)
			{
				Vehicle myVehicle = (Vehicle) vehicles.get(i);
				
				if (myVehicle.getOriginalLane().equals("lane3") && set_Lane3_Speed >40)
				{
					set_Lane3_Speed = set_Lane3_Speed-40;
				}					
			}		
		}
		speedList.add(set_Lane1_Speed);
		speedList.add(set_Lane2_Speed);
		speedList.add(set_Lane3_Speed);
		log.info("Speed Changed");
		return speedList;
		
	}

}
