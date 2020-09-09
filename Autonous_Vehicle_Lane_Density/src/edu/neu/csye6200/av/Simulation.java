package edu.neu.csye6200.av;

import java.util.ArrayList;
import java.util.Observable;

import edu.neu.csye6200.av.LanePanel;
public class Simulation extends Observable implements Runnable  {

	private Thread thread = null; // the thread that runs my simulation
	private boolean paused = false;
	private boolean done = false; // set true to end the simulation loop
	private boolean running = false; // set true if the simulation is running
	LanePanel lanePanel  =new LanePanel();
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();	 
	
	public void startSim() {
		System.out.println("Starting the simulation");
		if (thread != null) return; // A thread is already running
		
		thread = new Thread(this); // Create a worker thread
		paused = false;
		done = false;
		thread.start();		
	}
	
	
	public void pauseSim() {
		paused = !paused;
		System.out.println("Pause the simulation: " + paused);
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public boolean isPausable() {
		if (!running) return false;
		if (done) return false;
		return true;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public boolean getRunning()
	{
		return this.running;
	}
	
	public void stopSim() {	
		System.out.println("Stop the simulation");
		if (thread == null) return; //in case the thread is null
		done = true;		
	}

	@Override
	public void run() {		
		runSimLoop();
		thread = null; // flag that the simulation thread is finished
	}
	
    private void runSimLoop() {
    	running = true;
    	while(!done) {
    		if (!paused)
    		    updateSim();
    		sleep(500L); 
    		
    	}
    	running = false;
    }
	
    private void sleep(long millis) {
    	try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    private void updateSim() {
    	System.out.println("Call here in updateSim ");
    	setChanged();
    	notifyObservers(this); // Send a copy of the simulation
    }
    
}
