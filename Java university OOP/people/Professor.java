package org.university.people;

import java.io.Serializable;

import org.university.hardware.Department;
import org.university.software.Course;

public class Professor extends Employee  implements Serializable {
	//fields for Professor
	private Department department;
	
	
	
	
	//Constructor
	public Professor() {
		department = new Department();
	}
	
	
	
	//getters and setters
	public Department getDepartment() {
		return this.department;
		
	}

	public void setDepartment(Department department) {
		this.department = department;
		
	}

	public double earns() {
		
		return 200 * payRate;
	}

	public void addCourse(Course aCourse) {
		if(aCourse.getProfessor().getName() != "unknown") {
			System.out.println("The professor cannot be assigned to this course because professsor " +aCourse.getProfessor().getName() +" is already assigned to the course " + aCourse.getName() + ".");
		}
		else if (aCourse.getProfessor().getName() == "unknown" && detectConflict(aCourse) == false) {
			aCourse.setProfessor(this);
			CourseList.add(aCourse);
							//System.out.println("Schedule for " + aCourse.getName() +" is "+ aCourse.getSchedule());
			for(int i = 0; aCourse.getSchedule().size() > i; i++) {
				Schedule.add(aCourse.getSchedule().get(i));	
			}//end for
						//System.out.println("Schedule for " + this.getName() +" is "+ this.getSchedule());
			
		}//end else
		
	}

	



	
	
	/*public void printSchedule() {
		for(int i = 0; this.CourseList.size() > i; i++) {
			for(int j = 0; this.CourseList.get(i).getSchedule().size() < j; j++) {
				System.out.println(University.Week[this.Schedule.get(i)/100-1] + " " +University.Slot[this.Schedule.get(i)%10-1] + " " + this.department.getDepartmentName() + this.CourseList.get(i).getCourseNumber() + "" + this.CourseList.get(i).getName());//dep course# course name
				//System.out.println(Schedule);
			}
		}
		
	
	}*/
	
}
