package data;

import db.DB_Connector;
import beans.SessionBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Project {

    public int id_project;
    public String project_title;

    public Project() {

        id_project = -1;
    }

    public Project(int id_project, String project_title) {

        this.id_project = id_project;
        this.project_title = project_title;
    }

    /**
     *
     * @param sb the SessionBean instance of this Session
     * @param id_project the id_project from whom the project_title is loaded
     * @return the project_title of the id_project
     */
    public static String getProjectTitle(SessionBean sb, int id_project) {

        for (Project p : sb.getMyProjects()) {
            if (id_project == p.id_project) {
                return p.project_title;
            }
        }

        return "Unknown";
    }

    /**
     * 
     * @return all projects contained in the database
     */
    public static ArrayList<Project> fetchProjects() {

        ArrayList<Project> projects = new ArrayList<>();

        ResultSet r;
        Project p = null;

        r = DB_Connector.executeQuery("SELECT * FROM project ORDER BY project_title");

        try {
            while (r.next()) {

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

    public void deleteFromDB() {

        try {
            DB_Connector.runCommand("DELETE FROM Project WHERE id_project=" + this.id_project);
            DB_Connector.runCommand("DELETE FROM Task WHERE id_project=" + this.id_project);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int insertInDB() {

        int id_new_project = -1;

        try {
            id_new_project = DB_Connector.insertAndRetreiveIntKey("INSERT INTO Project VALUES (0, \"" + this.project_title + "\")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id_new_project;

    }

    public void renameInDB() {

        try {
            DB_Connector.runCommand("UPDATE Project "
                    + "SET project_title=\"" + this.project_title + "\" "
                    + "WHERE id_project=" + this.id_project);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * the method avoids having duplicated project titles
     * 
     * @return boolean
     */

    public boolean alreadyExists() {

        String currentProject = this.project_title.toLowerCase();

        Iterator<Project> iterator = Project.fetchProjects().iterator();

        while (iterator.hasNext()) {
            Project project = iterator.next();
            if (project.project_title.toLowerCase().equals(currentProject)) {
                return true;
            }
        }

        return false;
    }
    
   

}
