package data;

import db.DB_Connector;
import beans.SessionBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import other.MyTimer;

public class Task {

    public int id_task;
    public String time;
    public String title;
    private int completed;
    public int id_project;
    public String project_title;
    
    public static final int COMPLETED = 1;
    public static final int NOT_COMPLETED = 0;

    public Task(){
        
        id_task = 0;
        completed = 0;
    }

    public void insertInDB(){

        //all the String values have to be insert with "\","
        try {
            DB_Connector.runCommand("INSERT INTO Task VALUES (0, " +
                    "\"" + this.time + "\"," +
                    "\"" + this.title + "\"," +
                    this.completed + "," +
                    this.id_project + ")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateInDB(){

        //all the String values have to be insert with "\","
        try {
            DB_Connector.runCommand("UPDATE Task SET time=" +
                    "\"" + this.time + "\"," +
                    "title = \"" + this.title + "\"," +
                    "completed = " + this.completed + ", " +
                    "id_project = " + this.id_project + " " +
                    "WHERE id_task = " + this.id_task);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateStatus(int status) throws Exception{
        
        if(status != Task.COMPLETED && status != Task.NOT_COMPLETED){
            throw new Exception("Task: updateStatus(): parameter STATUS can be only 1 or 0");
        }
        
        this.completed = status;
        
        try {
            DB_Connector.runCommand("UPDATE Task SET completed = " + status + " WHERE id_task=" + this.id_task);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Task> fetchAllTasks(SessionBean sb, final String order_by){
        
        String query;
        
        if(order_by == null){//default ordering
            query = "SELECT * FROM task ORDER BY time ASC";
        }
        else{
            if(order_by.contains("project")){
                query = "SELECT T.id_task, T.time,T.title, T.completed, T.id_project "
                  + "FROM Task T JOIN Project P ON T.id_project = P.id_project "
                  + "ORDER BY P." + order_by;
            }
            else{
                query = "SELECT * FROM task ORDER BY " + order_by;
            }
        }

        ArrayList<Task> tasks = new ArrayList<>();

        ResultSet r;
        Task t = null;

        r = DB_Connector.executeQuery(query);

        try {
            while(r.next()){

                t = new Task();

                t.id_task = r.getInt("id_task");
                t.time = MyTimer.formatDateFromDB(r.getString("time"));
                t.title = r.getString("title");
                t.completed = r.getInt("completed");
                t.id_project = r.getInt("id_project");
                t.project_title = Project.getProjectTitle(sb, t.id_project);

                tasks.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return tasks;
    }
    
    public static ArrayList<Task> fetchTasksByProject(SessionBean sb, int id_project, String order_by){
        
        if(order_by == null){
            order_by = "time ASC";
        }

        ArrayList<Task> tasks = new ArrayList<>();

        ResultSet r;
        Task t = null;

        r = DB_Connector.executeQuery("SELECT * FROM task WHERE id_project=" + id_project + " ORDER BY " + order_by);

        try {
            while(r.next()){

                t = new Task();

                t.id_task = r.getInt("id_task");
                t.time = r.getString("time");
                t.title = r.getString("title");
                t.completed = r.getInt("completed");
                t.id_project = r.getInt("id_project");
                t.project_title = Project.getProjectTitle(sb, t.id_project);

                tasks.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return tasks;
    }

    public void deleteFromDB(){

        try {
            DB_Connector.runCommand("DELETE FROM Task WHERE id_task=" + this.id_task);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isCompleted(){

        return completed > 0;
    }

    public void markAsCompleted(){

        completed = 1;
    }

    public void markAsNotCompleted(){

        completed = 0;
    }

    @Override
    public String toString(){

        String res = this.id_task + ":  " + this.time + "  -  " + this.title + "  -  " + this.project_title;

        if(this.isCompleted()){
            res = res + " V ";
        }
        else{
            res = res + " X ";
        }

        return res;
    }


}