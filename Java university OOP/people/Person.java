package org.university.people;

import java.io.Serializable;
import java.util.ArrayList;

import org.university.software.Course;
import org.university.software.University;





public abstract class Person  implements Serializable {

	//Fields for a Person
	private String name;
	protected ArrayList<Integer> Schedule; 
	protected ArrayList<Course> CourseList;
	
	
	//Constructor
	public Person() {
		name = "unknown";
		Schedule = new ArrayList<Integer>();
		CourseList = new ArrayList<Course>();}
	
	
	//getters and setters
	public ArrayList<Integer>  getSchedule(){
		return Schedule;}
	
	public String getName(){
		return name;}
	
	public void setName(String aName){
		name = aName;}
	
	public boolean detectConflict(Course c8) {
		boolean flag = false;
		for(int j: c8.getSchedule()) {
			for(Course c: this.CourseList) {
				for(int i: c.getSchedule()) {
					//System.out.println("proffessor's schedule is " + getSchedule().get(i) + " course schedule is " + c8.getSchedule().get(j));
					if (i == j) {
						System.out.println(c8.getDepartment().getDepartmentName() + c8.getCourseNumber() + 
								" course cannot be added to " + this.getName() +"'s Schedule. " + c8.getDepartment().getDepartmentName() +
								c8.getCourseNumber()+" conflicts with "  +c.getDepartment().getDepartmentName() + c.getCourseNumber() +
								". Conflicting time slot is " +University.Week[i/100-1] + " " + University.Slot[i%100-1] +".");
						flag = true;
					}
				}
			}//end for
		}
		return flag;
	}
	
	public void printSchedule() {
		for(int w = 0; w < 5; w++) {
			for(int s = 0; s < 6; s++) {
				for(Course c: this.CourseList) {
					for(int i: c.getSchedule()) {
						if((i/100 - 1) == w && (i % 10 - 1) == s)
							System.out.println(University.Week[i/100-1] + " " +University.Slot[i%10-1] + " " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + " " + c.getName()+"");//dep course# course name
					}
				}
			}
		}
	}
	
	// methods
	public abstract void addCourse(Course aCourse);
}
