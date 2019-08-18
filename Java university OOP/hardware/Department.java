package org.university.hardware;

import java.io.Serializable;
import java.util.ArrayList;

import org.university.people.Professor;
import org.university.people.Staff;
import org.university.people.Student;
import org.university.software.Course;

public class Department  implements Serializable {
	//Fields for a Person
			private String name;
			private ArrayList<Course> CourseList;
			private ArrayList<Student> StudentList;
			private ArrayList<Staff> StaffList;
			private ArrayList<Professor> ProfessorList;
			//Constructors
			
			public Department() {
				name = "unknown";
				CourseList=new ArrayList<Course>();
				StudentList=new ArrayList<Student>();
				StaffList=new ArrayList<Staff>();
				ProfessorList=new ArrayList<Professor>();
			}

			
			//getters
			public String getDepartmentName() {
				return this.name; }
			
			public ArrayList<Student> getStudentList() {
				return StudentList; }

			public ArrayList<Professor> getProfessorList() {
				return ProfessorList; }
						
			public String getName() {
				return this.name; }

			public ArrayList<Staff> getStaffList() {
				return StaffList; }

			public ArrayList<Course> getCourseList() {
				return CourseList; }

			

			
			
			//setters
			public void setDepartmentName(String dname) {
				this.name = dname; }
			
			public void addCourse(Course c) {
				CourseList.add(c);
				c.setDepartment(this); }
			
			public void addStudent(Student s) {
				//StudentList.add(s);
				if(s.getDepartment() != this) {
					StudentList.add(s);
					s.setDepartment(this); }//end if 
				}//end
			
			public void addStaff(Staff s) {
				
				if(s.getDepartment() != this) {
					StaffList.add(s);
					s.setDepartment(this);}//end if 
				}//end
			
			public void addProfessor(Professor p) {
				ProfessorList.add(p);
				if(p.getDepartment() != this) {
					p.setDepartment(this);}//end if 
				}//end
			
			
			
			
			
			
			
			
			
			//functions
			public void printStudentList() {
				// TODO Auto-generated method stub
				for(Student i: StudentList)
					System.out.println(i.getName());
				
			}

			
			public void printProfessorList() {
				// TODO Auto-generated method stub
				for(Professor i: ProfessorList)
					System.out.println(i.getName());
			}


			public void printStaffList() {
				// TODO Auto-generated method stub
				for(Staff i: StaffList)
					System.out.println(i.getName());
			}


			public void printCourseList() {
				// TODO Auto-generated method stub
				for(Course i: CourseList)
					System.out.println(i.getDepartment().getDepartmentName() + i.getCourseNumber());
			}


			
}
