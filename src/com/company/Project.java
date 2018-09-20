package com.company;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Project {

    public int id_project;
    public String project_title;

    public Project(){

        id_project = 0;
    }

    public static String getProjectTitle(int id_project){

        for(Project p : Main.myProjects){
            if(id_project == p.id_project){
                return p.project_title;
            }
        }

        return "Unknown";
    }


    public static ArrayList<Project> fetchProjects(){

        ArrayList<Project> projects = new ArrayList<>();

        ResultSet r;
        Project p = null;

        r = ConnectionBean.executeQuery("SELECT * FROM project ORDER BY project_title");

        try {
            while(r.next()){

                p = new Project();

                p.id_project = r.getInt("id_project");
                p.project_title = r.getString("project_title");

                projects.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return projects;
    }


    public void deleteFromDB(){

        try {
            ConnectionBean.runCommand("DELETE FROM Project WHERE id_project=" + this.id_project);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int insertInDB(){

        int id_new_project = -1;

        //TUTTI I VALORI STRINGA DEVONO SEMPRE ESSERE INSERITI CON ESCAPE DELLE VIRGOLETTE
        try {
            id_new_project = ConnectionBean.insertAndRetreiveIntKey("INSERT INTO Project VALUES (0, \"" + this.project_title + "\")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id_new_project;

    }
}





