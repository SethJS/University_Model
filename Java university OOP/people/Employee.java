package org.university.people;

import java.io.Serializable;

public abstract class Employee extends Person  implements Serializable {
	
	//fields for employee
	protected double payRate;
	
	
	//Constructor
		public Employee() {
			payRate = 0.0;
		}
	
	//getters and setters
	public double getPayRate(){
		return payRate;}

	public void setPayRate(double x) {
		this.payRate = x;}
	
	public void raise(double percent) {
		this.payRate = this.payRate + this.payRate * percent * .01; }
	
	
	//Abstract functions from Person
	public abstract double earns();
}
