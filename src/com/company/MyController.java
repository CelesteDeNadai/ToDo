package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class MyController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){

            case "Add Task":
                addTask(); break;
            case "View Tasks":
                updateTextArea(); break;

            case "Delete Task": chooseTaskToDelete(); break;
        }
    }

    private void updateTextArea(){

        JTextArea jt = Main.textTasks;

        jt.setText("");

        for(Task t : Main.myTasks){
            jt.append(t.toString() + "\n");
        }
    }

    private void chooseTaskToDelete(){

        int id_task_tdl = Integer.parseInt(JOptionPane.showInputDialog("ID Task please"));

        for (Iterator<Task> it = Main.myTasks.iterator(); it.hasNext();){

            Task t = it.next();

            if(t.id_task == id_task_tdl){
                t.deleteFromDB();
                JOptionPane.showMessageDialog(null, "Task " + t.title + " deleted");
                Main.myTasks.remove(t);
            }
        }
    }

    private void addTask(){

        String string_projects = "Available Projects are:\n";

        Task t = new Task();

        t.title = JOptionPane.showInputDialog("Title of the task?");
        t.time = JOptionPane.showInputDialog("Time?");

        for(Project p : Main.myProjects){

            string_projects = string_projects + p.id_project + " " + p.project_title + "\n";
        }

        string_projects = string_projects + "\n\nType the id of the chosen project, or type N to create a new one" ;

        String id_project = JOptionPane.showInputDialog(string_projects);

        if(id_project.equalsIgnoreCase("n")){

            Project newProject = new Project();
            newProject.project_title = JOptionPane.showInputDialog("Enter title of the new project");
            t.id_project = newProject.insertInDB();

            Main.myProjects = Project.fetchProjects();
        }
        else{
            try {
                Integer.parseInt(id_project);
            }
            catch(NumberFormatException nfex){
                JOptionPane.showMessageDialog(null, "Please insert a valid ID or type N");
                return;
            }

            t.id_project =Integer.parseInt(id_project);
        }

        t.insertInDB();
        Main.myTasks = Task.fetchTasks();
    }
}
