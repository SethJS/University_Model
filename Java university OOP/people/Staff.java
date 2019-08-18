package org.university.people;

import java.io.Serializable;

import org.university.hardware.Department;
import org.university.software.Course;

public class Staff extends Employee  implements Serializable {
	//fields for employee
		private int hoursWorked;
		private Department department;
		//private Course course;
		
		
		
		//Constructor
			public Staff() {
				hoursWorked = 0;
				this.department = new Department();
				//this.course = new Course();
			}
		
		//getters and setters
			public Department getDepartment() {
				return this.department;
			}
			public void setDepartment(Department d) {
				//if(this.getDepartment().getDepartmentName() != d.getDepartmentName()) {
					department = d;
					d.addStaff(this);
				//}
			}
			
			public int getMonthlyHours(){
				return hoursWorked;
			}
	
			public void setMonthlyHours(int x) {
				this.hoursWorked = x;
			}
		
			
		
		
		//Abstract functions from Person
		
		public double earns() {		
			return hoursWorked * payRate;
		}

		public void addCourse(Course aCourse) {
			if(CourseList.isEmpty() != true)
				if(CourseList.get(0).getName() != "unknown") {    // prints error message if already signed up for a course
					System.out.println(CourseList.get(0).getDepartment().getDepartmentName() +""+ CourseList.get(0).getCourseNumber() +
							" is removed from " + this.getName() + "'s schedule(Staff can only take one class at a time). " + 
							aCourse.getDepartment().getDepartmentName() +""+ aCourse.getCourseNumber() + " has been added to " + 
							this.getName() + "'s Schedule.");
				}//end if
			if(CourseList.isEmpty())
				CourseList.add(aCourse);
			else {
				CourseList.clear();
				CourseList.add(aCourse);
			}
			aCourse.setStudentRoster(this);
			//for(int i = 0; aCourse.getSchedule().size() > i; i++) {
				Schedule= aCourse.getSchedule();	
			//}
			return;
		}
		
		/*public void dropCourse(Course aCourse) {
			if(aCourse == course) {
				this.course = new Course();
			}
			else {
				System.out.println("The course " + aCourse.getName() + " could not be dropped because " + this.getName() +" is not enrolled in " + aCourse.getName() + ".\n");
			}
		return;
		}*/
}
