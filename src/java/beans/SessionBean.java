/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import data.Project;
import data.Task;
import data.TaskOrder;
import java.util.ArrayList;

/**
 *
 * @author tmp-sda-1176
 */
public class SessionBean {
    
    private ArrayList<Project> myProjects;
    private ArrayList<Task> myTasks;
    private Task selectedTask;
    private Project selectedProject;
    
    private String error;
    public TaskOrder to;
    
    public SessionBean(){
        
        myProjects = Project.fetchProjects();
        myTasks = Task.fetchAllTasks(this, null);
        to = new TaskOrder();
        error = "";
    }
    
    

    /**
     * @return the myProjects
     */
    public ArrayList<Project> getMyProjects() {
        return myProjects;
    }

    /**
     * @param myProjects the myProjects to set
     */
    public void setMyProjects(ArrayList<Project> myProjects) {
        this.myProjects = myProjects;
    }

    /**
     * @return the myTasks
     */
    public ArrayList<Task> getMyTasks() {
        return myTasks;
    }

    /**
     * @param myTasks the myTasks to set
     */
    public void setMyTasks(ArrayList<Task> myTasks) {
        this.myTasks = myTasks;
    }

    /**
     * @return the selectedTask
     */
    public Task getSelectedTask() {
        return selectedTask;
    }

    /**
     * @param selectedTask the selectedTask to set
     */
    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    /**
     * @return the selectedProject
     */
    public Project getSelectedProject() {
        
        if(selectedProject == null){
            selectedProject = new Project(-1, "None");
        }
        
        return selectedProject;
    }

    /**
     * @param selectedProject the selectedProject to set
     */
    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
