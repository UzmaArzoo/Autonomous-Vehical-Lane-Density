package edu.neu.csye6200.av;

import java.awt.Color;
import java.awt.Graphics;

public class Vehicle {
	
	int x;
	int y;
	Color color;
	int speed = 0;
	String originalLane;
	
	/*
	 * Constructor
	 */
	public Vehicle(int x, int y, Color color, int speed, String originalLane)
	{
		this.x=x;
		this.y=y;
		this.color = color;
		this.speed = speed;
		this.originalLane = originalLane;
	}
	
	/*
	 *  Method to draw shape and color of the vehicle
	 */
	public void paintMyVehicle(Graphics g)
	{

	}

	/*
	 * Getter and setter methods
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}	
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getOriginalLane() {
		return originalLane;
	}	
}
