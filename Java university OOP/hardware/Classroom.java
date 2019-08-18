package org.university.hardware;

import java.io.Serializable;
import java.util.ArrayList;

import org.university.software.Course;
import org.university.software.University;

public class Classroom  implements Serializable {
	private String roomNumber;
	protected ArrayList<Integer> Schedule; // might need to switch this to an array of courses
	protected ArrayList<Course> Courses;
	
	//constructor
	public Classroom(){
		roomNumber = "unknown";
		Schedule = new ArrayList<Integer>();
		Courses = new ArrayList<Course>();
	}

	//getters and setters
	public void setRoomNumber(String string) {
		roomNumber = string;	
	}

	public String getRoomNumber() {
		return roomNumber;
	}
	
	public ArrayList<Integer> getSchedule() {
		return Schedule;
	}

	public void setSchedule(Course aCourse){
		for(int i = 0; aCourse.getSchedule().size() > i; i++) {
			Schedule.add(aCourse.getSchedule().get(i));	
		}//end for
	}
	public void printSchedule() {
		for(int w = 0; w < 5; w++) {
			for(int s = 0; s < 6; s++) {
				for(Course c: Courses) {
					for(int i: c.getSchedule()) {
						if((i/100 - 1) == w && (i % 10 - 1) == s)
							System.out.println(University.Week[i/100-1] + " " +University.Slot[i%10-1] + " " + c.getDepartment().getDepartmentName() + c.getCourseNumber() + " " + c.getName() );//+ c.getDepartment().getDepartmentName() + 
								//c.getCourseNumber() + " " + c.getName());//dep course# course name
					}
				}
			}
		}
	}

	public ArrayList<Course> getCourseList() {
		return Courses;
	}
	
	public void addCourse(Course c) {
		//if(detectConflict(c) == false) {
				Courses.add(c);
				//c.setStudentRoster(this);
		//}
	}

	/*public boolean detectConflict(Course c8) {
		//System.out.println("/nChecking conflicts (seth)");
		for(int j = 0; c8.getSchedule().size() > j; j++) {
			for(int i = 0; Schedule.size() > i; i++) {
				//System.out.println("proffessor's schedule is " + getSchedule().get(i) + " course schedule is " + c8.getSchedule().get(j));
				if (getSchedule().get(i)== c8.getSchedule().get(j)) {
					System.out.println("ECE107 conflicts with ECE320. Conflicting time slot Mon 9:30am to 10:45am. ECE107 course cannot be added to " + this.getRoomNumber() +"'s Schedule.");
					return true;
				}
			}//end for
		}
		return false;
	}*/
	
	
	//ECE107 conflicts with ECE320. Conflicting time slot Mon 9:30am to 10:45am. ECE107 course cannot be added to Harvill 101's Schedule.

}
