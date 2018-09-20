package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Task {

    public int id_task;
    public String time;
    public String title;
    private int completed;
    public int id_project;
    public String project_title;

    public Task(){
        completed = 0;
    }

    public void insertInDB(){

        //all the String values have to be insert with "\","
        try {
            ConnectionBean.runCommand("INSERT INTO Task VALUES (0, " +
                    "\"" + this.time + "\"," +
                    "\"" + this.title + "\"," +
                    this.completed + "," +
                    this.id_project + ")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Task> fetchTasks(){

        ArrayList<Task> tasks = new ArrayList<>();

        ResultSet r;
        Task t = null;

        r = ConnectionBean.executeQuery("SELECT * FROM task");

        try {
            while(r.next()){

                t = new Task();

                t.id_task = r.getInt("id_task");
                t.time = r.getString("time");
                t.title = r.getString("title");
                t.completed = r.getInt("completed");
                t.id_project = r.getInt("id_project");
                t.project_title = Project.getProjectTitle(t.id_project);

                tasks.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return tasks;
    }

    public void deleteFromDB(){

        try {
            ConnectionBean.runCommand("DELETE FROM Task WHERE id_task=" + this.id_task);
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