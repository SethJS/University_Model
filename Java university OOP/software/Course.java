package org.university.software;

import java.io.Serializable;
import java.util.ArrayList;

import org.university.hardware.Classroom;
import org.university.hardware.Department;
import org.university.people.Person;
import org.university.people.Professor;

public class Course  implements Serializable {
	//Fields for a Person
			private String CourseName;
			private int CourseNumber;
			private Department department;
			private ArrayList<Integer> Schedule;
			private ArrayList<Person> StudentRoster;
			private Professor prof;
			private Classroom classRoom;
			
			//Constructors
			public Course() {
				CourseName = "unknown";
				CourseNumber = 000;
				//Schedule = 000;
				prof = new Professor();
				department = new Department();
				classRoom = new Classroom();
				Schedule =new ArrayList<Integer>();
				StudentRoster=new ArrayList<Person>(); 
			}
			
			
			
			//getters
			public String getName() {
				return this.CourseName;}
			
			public Professor getProfessor() {
				return this.prof;}
			
			public ArrayList<Integer> getSchedule(){
				return this.Schedule;}
			
			public int getCourseNumber() {
				return this.CourseNumber;}
			
			public ArrayList<Person> getStudentRoster(){
				return this.StudentRoster;}
			
			public Department getDepartment() {
				return this.department;}
			
			public Classroom getRoomAssigned() { // 
				return classRoom;}
			
			
			
			
			// setters
			public void setName(String cname) {
				this.CourseName = cname;}
			
			public void setProfessor(Professor p) {
				this.prof = p;}
			
			public void setSchedule(int num) {
				Schedule.add(num);}
			
			public void setCourseNumber(int num) {
				this.CourseNumber = num;}
			
			public void setStudentRoster(Person s) {
				StudentRoster.add(s);}
			
			public void setDepartment(Department d) {
				this.department = d;}
			
			public void setRoomAssigned(Classroom cr4) { //
				if(detectConflict(cr4) != true) {
					classRoom = cr4;
					cr4.addCourse(this);
					cr4.setSchedule(this);}}
			
			public void dropStudentRoster(Person s) {
				for(int i = 0; i < StudentRoster.size(); i++) {
					if(s == StudentRoster.get(i)) {
						StudentRoster.remove(s);
						return;}}}
			
			

			// functions
			public void printSchedule() {
				for(int w = 0; w < 5; w++) {
					for(int s = 0; s < 6; s++) {
						for(int i: this.getSchedule()) {
							if((i/100 - 1) == w && (i % 10 - 1) == s)
								System.out.println(University.Week[i/100-1] + " " +University.Slot[i%10-1] + " " + this.getRoomAssigned().getRoomNumber()+"");//dep course# course name
						}
					}
				}
				}
			
			
			
			public Boolean detectConflict(Classroom classroom) {
				boolean conflict = false;
				String thiscourse = this.department.getDepartmentName() + this.getCourseNumber();
				String reservedname;
				int conflictsat = 0;
				//System.out.println("checking " + thiscourse + " for " + classroom.getRoomNumber());
				for(int j: this.Schedule) {
					for(Course c: classroom.getCourseList()) {
						//System.out.println(c.getName() + " is already in " + classroom.getRoomNumber());
						for(int i: c.getSchedule())
							if(i==j) {
								conflictsat = i;
								reservedname = c.getDepartment().getDepartmentName() + c.getCourseNumber();
								conflict = true;
								System.out.println(thiscourse + " conflicts with " + reservedname + ". Conflicting time slot " + 
								University.Week[conflictsat/100-1] + " " + University.Slot[conflictsat%100-1] + ". " + thiscourse + 
								" course cannot be added to " + classroom.getRoomNumber() + "'s Schedule.");
							}//end if
					}//end for c
				}//end for j	
				return conflict;
			}
		
}
