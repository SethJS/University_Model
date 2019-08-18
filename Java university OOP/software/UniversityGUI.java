package org.university.software;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.university.people.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.*;

public class UniversityGUI extends JFrame{

	private University univ;		// the university
	private JMenuBar menuBar;		//the horizontal container
	private JMenu adminMenu;		//JMenu objects are added to JMenuBar objects as the "tabs"
	private JMenu fileMenu;   //added
	private JMenu studentMenu;
	private ArrayList<University> univList;
	private JTextArea textArea;
	
	// File submenus
    
		// Admin
		private JMenuItem adminPrintAll; 		//JMenuItem objects are added to JMenu objects as the drop down selections.
		
		// File
		private JMenuItem fileSave;   //added
		private JMenuItem fileLoad;   //added
		private JMenuItem fileExit;
		
		// Students
		private JMenuItem addCourse;   //added
		private JMenuItem dropCourse;   //added
		private JMenuItem printSchedule;
		
	
	public UniversityGUI(String windowTitle, University u){
		super(windowTitle);
		univList = new ArrayList<University>();
   		univ = new University() ;
   		univ = u;
   		univList.add(univ);
   		

		setSize(600, 300);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(new JLabel("<HTML><center>Welcome to the University." +
				"<BR>Choose an action from the above menus.</center></HTML>"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();	
		setVisible(true);
		//add(textArea);
		
	}// end UniversityGUI
	
	
	public void buildGUI(){
		menuBar = new JMenuBar();
		// University Student Menu
		
        
		adminMenu = new JMenu("Administrators");
		studentMenu = new JMenu("Students");
		fileMenu  = new JMenu("File");   //added
		
		adminPrintAll = new JMenuItem("Print All Info");
		addCourse = new JMenuItem("Add Course");
		dropCourse = new JMenuItem("Drop Course");
		printSchedule = new JMenuItem("Print Schedule");
		fileSave = new JMenuItem("Save");   //added
		fileLoad = new JMenuItem("Load");   //added
		fileExit = new JMenuItem("Exit");
		
		adminPrintAll.addActionListener(new MenuListener());		//The innerclass is implementing ActionListener, so it can take action when the JMenuItem is clicked.
	    addCourse.addActionListener(new MenuListener());
	    dropCourse.addActionListener(new MenuListener());
	    printSchedule.addActionListener(new MenuListener());
		fileSave.addActionListener(new MenuListener());   //added
	    fileLoad.addActionListener(new MenuListener());   //added
	    fileExit.addActionListener(new MenuListener());
		
		adminMenu.add(adminPrintAll);
		
		fileMenu.add(fileSave);   //added
		fileMenu.add(fileLoad);   //added
		fileMenu.add(fileExit);
		studentMenu.add(addCourse);
		studentMenu.add(dropCourse);
		studentMenu.add(printSchedule);
		menuBar.add(fileMenu);   //added
		menuBar.add(studentMenu);
	    menuBar.add(adminMenu);
	
		setJMenuBar(menuBar);
		
		
	}// end buildGUI

	//Implement the below later
	
	private class MenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JMenuItem source = (JMenuItem)(e.getSource());
			
			if(source.equals(adminPrintAll)){
				handleAdminPrint();
			}
			else if(source.equals(addCourse)){
				handleAddCourse();
			}
			else if(source.equals(dropCourse)){
				handleDropCourse();
			}
			else if(source.equals(printSchedule)){
				handlePrintSchedule();
			}
			else if(source.equals(fileSave)){
				handleFileSave();
			}
			else if(source.equals(fileLoad)){
				handleFileLoad();
			}
			else if(source.equals(fileExit)) {
				handleFileExit();
			}
		}
		
		private void handleFileLoad() {
			univ = University.loadData();
		}

		private void handleFileSave() {
			University.saveData(univ);
		}
		
		private void handleFileExit() {
			System.exit(0);
		}
		
		private void handleAddCourse() {
			//do stuff
			//String name = JOptionPane.showInputDialog(null, "Enter your name: ");
			int i = 0, j = 0, err = 0;
			int flag = 0;
			Student s = null;
		    Course c = null;
			
			JTextField StudentName = new JTextField();
			JTextField DepartmentName = new JTextField();
			JTextField CourseName = new JTextField();

			Object[] message = {
			    "Student Name:", StudentName,
			    "Department:", DepartmentName,
			    "Course #:", CourseName,
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Add Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (option == JOptionPane.OK_OPTION)
			{
			    //takes info from user
				String SName = StudentName.getText();
			    String Dep = DepartmentName.getText();
			    int CourseNum = Integer.parseInt(CourseName.getText());
			  
			    
			    //starts testing
			    for(i = 0; i < univ.departmentList.size(); i++) {
			    	for(j = 0; j < univ.departmentList.get(i).getStudentList().size(); j++) {
			    		flag = 1;
			    		if(SName.equals(univ.departmentList.get(i).getStudentList().get(j).getName())) {
			    			flag = 0;
			    			s = univ.departmentList.get(i).getStudentList().get(j); // if the student name is found, save student
			    			break;
			    		}
			    	}
			    	if(flag == 0)
			    		break;
			    }//end student name check
			    
			    if(flag == 1) {
			    	err = 1;
			    	JOptionPane.showMessageDialog(null, "Student ''" + SName + "'' doesn't exist.", "Error adding student to the course", JOptionPane.OK_CANCEL_OPTION);
			    }
			    
			    for(i = 0; i < univ.departmentList.size(); i++) {
			    	flag = 2;
			    	if(Dep.equals(univ.departmentList.get(i).getDepartmentName())) {
			    		flag = 0;
			    		break;
			    	}
			    }//end department name check
			    
			    if(flag == 2) {
			    	err = 1;
			    	JOptionPane.showMessageDialog(null, "Department ''" + Dep + "'' doesn't exist.", "Error adding student to the course", JOptionPane.OK_CANCEL_OPTION);
			    }
			    
			    for(i = 0; i < univ.departmentList.size(); i++) {
			    	for(j = 0; j < univ.departmentList.get(i).getCourseList().size(); j++) {
			    		flag = 3;
			    		if(CourseNum == univ.departmentList.get(i).getCourseList().get(j).getCourseNumber()) {
			    			flag = 0;
			    			c = univ.departmentList.get(i).getCourseList().get(j); // if course is found, save course
			    			break;
			    		}
			    	}
			    	if(flag == 0)
			    		break;
			    }//end course number check
			    
			    if(flag == 3) {
			    	err = 1;
			    	JOptionPane.showMessageDialog(null, "Course ''" + CourseNum + "'' doesn't exist.", "Error adding student to the course", JOptionPane.OK_CANCEL_OPTION);
			    }
			    
			    //end of testing
			    if(err == 0) {  //if an error was not detected
			    	//goto add course
			    	JFrame frame = new JFrame();
			    	JTextArea textArea = new JTextArea(1, 70);
					PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
					System.setOut(printStream);
					System.setErr(printStream);
					
					
					if(s.detectConflict(c)!=true) {
						s.addCourse(c);
						
						/*frame.setSize(500, 300);
						textArea.setEditable(false);
						JScrollPane scroll = new JScrollPane(textArea);
						frame.add(scroll);
						frame.setVisible(true);*/
					}//end if conflicts
					else {
						JOptionPane.showMessageDialog(frame, textArea, "Error adding student to course", JOptionPane.OK_CANCEL_OPTION );
			    	}
			    }//end add course
			    
			}//end if-OK-option
		}//end handle add course
		
		private void handleDropCourse() {
			//do stuff
			//String name = JOptionPane.showInputDialog(null, "Enter your name: ");
			int i = 0, j = 0, err = 0;
			int flag = 0;
			Student s = null;
		    Course c = null;
			
			JTextField StudentName = new JTextField();
			JTextField DepartmentName = new JTextField();
			JTextField CourseName = new JTextField();

			Object[] message = {
			    "Student Name:", StudentName,
			    "Department:", DepartmentName,
			    "Course #:", CourseName};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Drop Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (option == JOptionPane.OK_OPTION){
			    //takes info from user
				String SName = StudentName.getText();
			    String Dep = DepartmentName.getText();
			    int CourseNum = Integer.parseInt(CourseName.getText());
			  
			    
			    //starts testing
			    for(i = 0; i < univ.departmentList.size(); i++) {
			    	for(j = 0; j < univ.departmentList.get(i).getStudentList().size(); j++) {
			    		flag = 1;
			    		if(SName.equals(univ.departmentList.get(i).getStudentList().get(j).getName())) {
			    			flag = 0;
			    			s = univ.departmentList.get(i).getStudentList().get(j); // if the student name is found, save student
			    			break;}
			    	}
			    	if(flag == 0)
			    		break;}//end student name check
			    
			    if(flag == 1) {
			    	err = 1;
			    	JOptionPane.showMessageDialog(null, "Student ''" + SName + "'' doesn't exist.", "Error dropping student from the course", JOptionPane.OK_CANCEL_OPTION);}
			    
			    for(i = 0; i < univ.departmentList.size(); i++) {
			    	flag = 2;
			    	if(Dep.equals(univ.departmentList.get(i).getDepartmentName())) {
			    		flag = 0;
			    		break;}
			    }//end department name check
			    
			    if(flag == 2) {
			    	err = 1;
			    	JOptionPane.showMessageDialog(null, "Department ''" + Dep + "'' doesn't exist.", "Error dropping student from the course", JOptionPane.OK_CANCEL_OPTION);}
			    
			    for(i = 0; i < univ.departmentList.size(); i++) {
			    	for(j = 0; j < univ.departmentList.get(i).getCourseList().size(); j++) {
			    		flag = 3;
			    		if(CourseNum == univ.departmentList.get(i).getCourseList().get(j).getCourseNumber()) {
			    			flag = 0;
			    			c = univ.departmentList.get(i).getCourseList().get(j); // if course is found, save course
			    			break;}
			    	}
			    	if(flag == 0)
			    		break; }//end course number check
			    
			    if(flag == 3) {
			    	err = 1;
			    	JOptionPane.showMessageDialog(null, "Course ''" + CourseNum + "'' doesn't exist.", "Error dropping student from the course", JOptionPane.OK_CANCEL_OPTION);}
			    
			    //end of testing
			    if(err == 0) {  //if an error was not detected
			    	s.dropCourse(c);}//end drop course
			    
			}//end if-OK-option
		}//end handle drop course
		
		private void handlePrintSchedule() {
			//print stuff
			int i = 0, j = 0, flag = 0, err = 0;
			JTextField StudentName = new JTextField();
			Student s = null;
			Object[] message = {
			    "Student Name:", StudentName,
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Print Student Schedule", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (option == JOptionPane.OK_OPTION){
			    String SName = StudentName.getText();
			
				//starts testing
			    for(i = 0; i < univ.departmentList.size(); i++) {
			    	for(j = 0; j < univ.departmentList.get(i).getStudentList().size(); j++) {
			    		flag = 1;
			    		if(SName.equals(univ.departmentList.get(i).getStudentList().get(j).getName())) {
			    			flag = 0;
			    			s = univ.departmentList.get(i).getStudentList().get(j); // if the student name is found, save student
			    			break;
			    		}
			    	}
			    	if(flag == 0)
			    		break;
			    }//end student name check
			    
			    if(flag == 1) {
			    	err = 1;
			    	JOptionPane.showMessageDialog(null, "Student ''" + SName + "'' doesn't exist.", "Error printing student schedule", JOptionPane.OK_CANCEL_OPTION);
			    }
			    
			    else {
			    	JFrame frame = new JFrame();
			    	JTextArea textArea = new JTextArea(1, 20);
					PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
					System.setOut(printStream);
					System.setErr(printStream);
					
					s.printSchedule();
					
					JOptionPane.showMessageDialog(frame, textArea, "Student " + s.getName() + "'s schedule", JOptionPane.OK_CANCEL_OPTION );
			    	
					}
			    
			}//end OK option
		}//end handle print student schedule
		
		private void handleAdminPrint(){
			JTextArea textArea = new JTextArea(150, 100);
			PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
			System.setOut(printStream);
			System.setErr(printStream);
			//textArea.setVisible(true);
			if( univ!=null){
				univ.printAll();
				// need to add an OK button and name the frame "University Info"
				JFrame frame = new JFrame();
				frame.setSize(500, 400);
				textArea.setEditable(false);
				JScrollPane scroll = new JScrollPane(textArea);
				//scroll.setSize(20,20);
				frame.add(scroll);
				//JOptionPane.showMessageDialog(frame, scroll, "University Info", JOptionPane.OK_CANCEL_OPTION );
		    	
				frame.setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null, 
						"No University", 
						"Error", 
						JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		
	}//end Menu Listener
	
	public class CustomOutputStream extends OutputStream {
	    private JTextArea textArea;
	     
	    public CustomOutputStream(JTextArea textArea) {
	        this.textArea = textArea;
	    }
	     
	    @Override
	    public void write(int b) throws IOException {
	        // redirects data to the text area
	        textArea.append(String.valueOf((char)b));
	        // scrolls the text area to the end of data
	        textArea.setCaretPosition(textArea.getDocument().getLength());
	    }
	}
}


