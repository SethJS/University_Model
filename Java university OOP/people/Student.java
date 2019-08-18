package org.university.people;

import java.io.Serializable;

import org.university.hardware.Department;
import org.university.software.Course;

public class Student extends Person  implements Serializable {
	//Fields for a student
		//private String name;
		private Department department;
		private int unitsNeeded;
		private int unitsCompleted;
		
		
		//Constructors
		public Student() {
			//name = "unknown";
			this.department = new Department();
			unitsNeeded = 0;
			unitsCompleted = 0;
			
		}
		
		
		
		//getters and setters
		
		public Department getDepartment() {
			return this.department;
		}
		public void setDepartment(Department d) {
			//if(this.getDepartment().getDepartmentName() != d.getDepartmentName()) {
				department = d;
				d.addStudent(this);
			//}
		}
		
		public void addCourse(Course c) {
			if(detectConflict(c) == false) {
					CourseList.add(c);
					c.setStudentRoster(this);
			}
		}
		
		public int requiredToGraduate() {
			return unitsNeeded - unitsCompleted;
		}
	
		public void dropCourse(Course aCourse) {
			for(int i = 0; i < CourseList.size(); i++) {
				if(aCourse == CourseList.get(i)) {
					aCourse.dropStudentRoster(this);
					CourseList.remove(i);
					return;
				}
			}
			System.out.println("The course " + aCourse.getDepartment().getDepartmentName() + "" + aCourse.getCourseNumber() + " could not be dropped because " + this.getName() +" is not enrolled in " + aCourse.getDepartment().getDepartmentName() + "" + aCourse.getCourseNumber() + ".");
			return;
		}



		



		public void setRequiredCredits(int i) {
			unitsNeeded = i;
		}



		public void setCompletedUnits(int i) {
			unitsCompleted = i;
			
		}
}
