package org.university.software;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.university.hardware.Classroom;
import org.university.hardware.Department;

public class University implements Serializable {
	// fields for University
	public ArrayList<Department> departmentList;
	public ArrayList<Classroom> classroomList;
	/*public ArrayList<Student> studentList;
	public ArrayList<Professor> professorList;
	public ArrayList<Staff> staffList;
	public ArrayList<Course> courseList;*/
	public static String[]  Week  ={"Mon","Tue","Wed","Thu","Fri"};
	public static String[]  Slot  ={"8:00am to 9:15am",
	                	     "9:30am to 10:45am",
	                         "11:00am to 12:15pm" ,
	                         "12:30pm to 1:45pm",
		                     "2:00pm to 3:15pm",
					         "3:30pm to 4:45pm" };
	

	//Constructor
	public University() {
		departmentList = new ArrayList<Department>();
		classroomList = new ArrayList<Classroom>();
		/*studentList = new ArrayList<Student>();
		professorList = new ArrayList<Professor>();
		staffList = new ArrayList<Staff>();
		courseList = new ArrayList<Course>();*/
	}//end University constructor
	
	
	
	//functions
	public static void saveData(University univ) {
		//Taken from lab 3, replaced employee with university
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut= null;

		try 
		{
			fileOut = new FileOutputStream( "university.ser" );		//the Employee object makes its way to serial data in the file Employee.ser
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(univ);
			objOut.close();
			fileOut.close();
	     }	
		
		catch(IOException i)
	    {
			i.printStackTrace();
	    }		
	}//end saveData
	
	public static University loadData() {
		// taken from lab 3, changed employee to university
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		University univ = null;
		
		try
		{
			fileIn = new FileInputStream("university.ser");
			objIn = new ObjectInputStream(fileIn);
			univ = (University) objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}  
		return univ;
	}// end loadData
	
	public void printAll() {
		int i = 0;
		int j = 0;
		int k = 0;
		//printing department names
		System.out.println("\nList of departments:");
		for(i = 0; i < departmentList.size(); i++) {
			System.out.println(departmentList.get(i).getName());
		}// done printing departments
		
		//printing classrooms
		System.out.println("\nClassroom list:");
		for(i = 0; i < classroomList.size(); i++) {
			System.out.println(classroomList.get(i).getRoomNumber());
		}// done printing classrooms
		
		//printing professor list
		for(j = 0; j < departmentList.size(); j++) {
			System.out.println("\nThe professor list for department " + departmentList.get(j).getName());
			for(i = 0; i < departmentList.get(j).getProfessorList().size(); i++) {
				System.out.println(departmentList.get(j).getProfessorList().get(i).getName());
			}
		}// done printing professors
		
		//printing course list
		for(j = 0; j < departmentList.size(); j++) {
			System.out.println("\nThe course list for department " + departmentList.get(j).getName());
			for(i = 0; i < departmentList.get(j).getCourseList().size(); i++) {
				System.out.println(departmentList.get(j).getName() + departmentList.get(j).getCourseList().get(i).getCourseNumber());
			}
		}// done printing courses
		
		//printing classroom schedules
		for(i = 0; i < classroomList.size(); i++) {
			System.out.println("\nThe schedule for classroom " + classroomList.get(i).getRoomNumber());
			classroomList.get(i).printSchedule();
		}// done printing classroom schedules
		
		//department
		//print professor schedules
		//print student schedules
		//print staff schedules
		//print student rosters for department courses
		for(i = 0; i < departmentList.size(); i++) {
			System.out.println("\nDepartment " + departmentList.get(i).getName());
			
			System.out.println("\nPrinting Professor schedules:");
			for(j = 0; j < departmentList.get(i).getProfessorList().size(); j++) {
				System.out.println("\nThe schedule for Prof. " + departmentList.get(i).getProfessorList().get(j).getName() + ":");
				departmentList.get(i).getProfessorList().get(j).printSchedule();
			}//done printing prof schedules
			
			System.out.println("\nPrinting Student Schedules:");
			for(j = 0; j < departmentList.get(i).getStudentList().size(); j++) {
				System.out.println("\nThe schedule for Student " + departmentList.get(i).getStudentList().get(j).getName() + ":");
				departmentList.get(i).getStudentList().get(j).printSchedule();
			}//done printing student schedules
			
			System.out.println("\nPrinting Staff Schedules:");
			for(j = 0; j < departmentList.get(i).getStaffList().size(); j++) {
				System.out.println("\nThe schedule for Employee " + departmentList.get(i).getStaffList().get(j).getName() + ":");
				departmentList.get(i).getStaffList().get(j).printSchedule();
				if(departmentList.get(i).getStaffList().get(j).earns() != 0)
				System.out.printf("\n\nStaff : " + departmentList.get(i).getStaffList().get(j).getName() + " earns $" + "%.2f" + " this month\n",departmentList.get(i).getStaffList().get(j).earns());
			}//done printing staff info
			
			
			System.out.println("\nThe rosters for courses offered by " + departmentList.get(i).getDepartmentName());
			for(j = 0; j < departmentList.get(i).getCourseList().size(); j++) {
				System.out.println("\nThe roster for course " + departmentList.get(i).getDepartmentName() + departmentList.get(i).getCourseList().get(j).getCourseNumber());
				for(k = 0; k < departmentList.get(i).getCourseList().get(j).getStudentRoster().size(); k++) {
					System.out.println(departmentList.get(i).getCourseList().get(j).getStudentRoster().get(k).getName());
				}// student name
			}//done printing course rosters
		}// done printing department info
		
		
	}//end printAll
	
	
}//end University class
