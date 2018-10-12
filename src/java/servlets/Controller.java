/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.SessionBean;
import data.Project;
import data.Task;
import data.TaskOrder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import other.MyStrings;
import other.MyTimer;

/**
 *
 * @author tmp-sda-1176
 */
public class Controller extends HttpServlet {
    
    private SessionBean sb;

    

    // <editor-fold desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        sb = (SessionBean) request.getSession(false).getAttribute("sb");
        
        
        if(request.getParameter("go-to-rename-project") != null){
            
            sb.setError("");
            
            response.sendRedirect(MyStrings.PAGE_RENAME_PROJECT);
            return;
        }

        if(request.getParameter("go-to-delete-project") != null){
            
            response.sendRedirect(MyStrings.PAGE_DELETE_PROJECT);
            return;
        }
       
        if(request.getParameter("show-all-tasks") != null){
            
            sb.to.setList_dim(4);
            
            sb.setMyTasks(Task.fetchAllTasks(sb, null));
            sb.setSelectedProject(null);
            response.sendRedirect(MyStrings.PAGE_HOME);
            return;
        }

        if(request.getParameter("show-tasks-by-project") != null){
            
            int project_index = -1;
            
            sb.to.setList_dim(2);
            sb.to.changeSelectedIndex(TaskOrder.VAL_DATE_ASC);
            
            try{
                project_index = Integer.parseInt(request.getParameter("project-index"));
            }
            catch(NumberFormatException n){
                
                sb.setMyTasks(Task.fetchAllTasks(sb, null));
                sb.setSelectedProject(null);
                response.sendRedirect(MyStrings.PAGE_HOME);
                return;
            }
            
            sb.setSelectedProject(sb.getMyProjects().get(project_index));
            
            sb.setMyTasks(Task.fetchTasksByProject(sb, sb.getSelectedProject().id_project, null));
            response.sendRedirect(MyStrings.PAGE_HOME);
            return;
        }
        
        if(request.getParameter("sort-tasks-by") != null){
            
            String sort_type = request.getParameter("sort-tasks-by");
            
            sb.to.changeSelectedIndex(sort_type);
            
            //no project selected
            if(sb.getSelectedProject().id_project < 0){
                switch(sort_type){
                    case "date-asc": sb.setMyTasks(Task.fetchAllTasks(sb, "time ASC")); break;
                    case "date-desc": sb.setMyTasks(Task.fetchAllTasks(sb, "time DESC")); break;
                    case "project-asc": sb.setMyTasks(Task.fetchAllTasks(sb, "project_title ASC")); break;
                    case "project-desc": sb.setMyTasks(Task.fetchAllTasks(sb, "project_title DESC")); break;
                }
            }
            else{
                switch(sort_type){
                    case "date-asc": sb.setMyTasks(Task.fetchTasksByProject(sb, sb.getSelectedProject().id_project, "time ASC")); break;
                    case "date-desc": sb.setMyTasks(Task.fetchTasksByProject(sb, sb.getSelectedProject().id_project, "time DESC")); break;
                }
            }
            
            response.sendRedirect(MyStrings.PAGE_HOME);
            return;
        }
        
        
        
        if(request.getParameter("mark-task-as-completed") != null){
            
            int task_index = Integer.parseInt(request.getParameter("task-index"));
            Task t = sb.getMyTasks().get(task_index);
            
            try {
                t.updateStatus(Task.COMPLETED);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            
            response.sendRedirect(MyStrings.PAGE_HOME);
            return;
        }

        if(request.getParameter("mark-task-as-not-completed") != null){
            
            int task_index = Integer.parseInt(request.getParameter("task-index"));
            Task t = sb.getMyTasks().get(task_index);
            
            try {
                t.updateStatus(Task.NOT_COMPLETED);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            
            response.sendRedirect(MyStrings.PAGE_HOME);
            return;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        sb = (SessionBean) request.getSession(false).getAttribute("sb");

        if(request.getParameter("create_project") != null){
            
            String new_project_title = request.getParameter("new_project_title");
            Project p = new Project();
            p.project_title = new_project_title;
            
            if(correctLength(p.project_title, 30)){
                if(!p.alreadyExists()){
                    p.id_project = p.insertInDB();
                    sb.setMyProjects(Project.fetchProjects());
                    sb.setSelectedProject(p);
                    sb.setError("");
                    response.sendRedirect(MyStrings.PAGE_HOME);
                } 
                else{
                    sb.setError("Project " + p.project_title.toUpperCase() + " already exists");
                    response.sendRedirect(MyStrings.PAGE_CREATE_PROJECT);
                }
            }
            else {
                sb.setError("The maximum length accepted for the project title is 30 characters");
                response.sendRedirect(MyStrings.PAGE_CREATE_PROJECT);
            }
            
            return;
        }

        if(request.getParameter("create-new-project-in-CT") != null){
            
            String new_project_title = request.getParameter("new-project-title");
            
            Project p = new Project();
            p.project_title = new_project_title;
            
            if(correctLength(p.project_title, 30)){
                if(!p.alreadyExists()){
                    p.id_project = p.insertInDB();
                    sb.setMyProjects(Project.fetchProjects());
                    sb.setSelectedProject(p);
                    sb.setError("");

                } 
                else{
                    sb.setError("Project " + p.project_title.toUpperCase() + " already exists");

                }
            }
            else {
                sb.setError("The maximum length accepted for the project title is 30 characters");
            }
            
            response.sendRedirect(MyStrings.PAGE_CREATE_TASK);
            return;
        }

        if(request.getParameter("create-new-project-in-ET") != null){
            
            String new_project_title = request.getParameter("new-project-title");
            
            Project p = new Project();
            p.project_title = new_project_title;
            
            if(correctLength(p.project_title, 30)){
                if(!p.alreadyExists()){
                    p.id_project = p.insertInDB();
                    sb.setMyProjects(Project.fetchProjects());
                    sb.setSelectedProject(p);
                    sb.setError("");
                } 
                else{
                    sb.setError("Project " + p.project_title.toUpperCase() + " already exists");
                }
            }
            else {
                sb.setError("The maximum length accepted for the project title is 30 characters");
            }
            
            response.sendRedirect(MyStrings.PAGE_EDIT_TASK);
            return;
        }
        
        
        
        if(request.getParameter("rename-project") != null){
            
            sb.getSelectedProject().project_title = request.getParameter("project-title");
            
            if(correctLength(sb.getSelectedProject().project_title, 30)){
                if(!sb.getSelectedProject().alreadyExists()){
                    sb.getSelectedProject().renameInDB();
                    sb.setMyProjects(Project.fetchProjects());
                    sb.setMyTasks(Task.fetchAllTasks(sb, null));
                    sb.setError("");
                    
                    sb.setSelectedProject(new Project());
                    response.sendRedirect(MyStrings.PAGE_HOME);
                } 
                else{
                    sb.setError("Project " + sb.getSelectedProject().project_title.toUpperCase() + " already exists");
                    response.sendRedirect(MyStrings.PAGE_RENAME_PROJECT);
                }
            }
            else {
                if(sb.getSelectedProject().project_title.isEmpty()){
                    sb.setError("New project name can't be empty");
                }
                else{
                    sb.setError("The maximum length accepted for the project title is 30 characters");
                }
 
                response.sendRedirect(MyStrings.PAGE_RENAME_PROJECT);
            }            
            return;
        }
        
        
        
        if(request.getParameter("delete-project") != null){
            sb.getSelectedProject().deleteFromDB();
            
            sb.setMyProjects(Project.fetchProjects());
            sb.setMyTasks(Task.fetchAllTasks(sb, null));
            
            response.sendRedirect(MyStrings.PAGE_HOME);
            return;
        }
        
        
        
        if(request.getParameter("create-task") != null){
            
            Task newTask = new Task();
            
            newTask.id_project = Integer.parseInt(request.getParameter("id-project"));
            newTask.title = request.getParameter("task-title");
            
            String day = request.getParameter("day");
            String month = request.getParameter("month");
            String year = request.getParameter("year");
            String hour = request.getParameter("hour");
            String minutes = request.getParameter("minutes");
            
            String error = MyTimer.checkTimeInputErrors(day, month, year, hour, minutes);
            if(correctLength(newTask.title, 300)){
                if(error.isEmpty()){
                    newTask.time = MyTimer.formatDateForDB(day, month, year, hour, minutes);
                    newTask.insertInDB();
                    sb.setMyTasks(Task.fetchAllTasks(sb, null));
                    response.sendRedirect(MyStrings.PAGE_HOME);
                }
                else{
                    sb.setError(error);
                    response.sendRedirect(MyStrings.PAGE_CREATE_TASK);
                } 
            } 
            else {
                sb.setError("The maximum length accepted for the task title is 300 characters");
                response.sendRedirect(MyStrings.PAGE_CREATE_TASK);
            }
            return;
        }
        
        
       
        if(request.getParameter("go-to-edit-task") != null){
            
            int index_selected_task = Integer.parseInt(request.getParameter("index-selected-task"));
            
            sb.setSelectedTask(sb.getMyTasks().get(index_selected_task));
            
            response.sendRedirect(MyStrings.PAGE_EDIT_TASK);
            
            return;
        }
        
        
        
        if(request.getParameter("confirm-edit-task") != null){
            
            sb.getSelectedTask().id_project = Integer.parseInt(request.getParameter("id-project"));
            sb.getSelectedTask().title = request.getParameter("task-title");
            
            String day = request.getParameter("day").trim();
            String month = request.getParameter("month").trim();
            String year = request.getParameter("year").trim();
            String hour = request.getParameter("hour").trim();
            String minutes = request.getParameter("minutes").trim();
            
            String error = MyTimer.checkTimeInputErrors(day, month, year, hour, minutes);
            if(sb.getSelectedTask().title.length() < 300){
                if(error.isEmpty()){
                    sb.getSelectedTask().time = MyTimer.formatDateForDB(day, month, year, hour, minutes);
                    sb.getSelectedTask().updateInDB();
                    sb.setMyTasks(Task.fetchAllTasks(sb, null));
                    response.sendRedirect(MyStrings.PAGE_HOME);
                }
                else{
                    sb.setError(error);
                    response.sendRedirect(MyStrings.PAGE_EDIT_TASK);
                } 
            } 
            else {
                sb.setError("The maximum length accepted for the task title is 300 characters");
                response.sendRedirect(MyStrings.PAGE_EDIT_TASK);
            }
            return;
        }
        
        
        
        if(request.getParameter("delete-task") != null){
            
            int index_selected_task = Integer.parseInt(request.getParameter("index-selected-task"));
            
            sb.getMyTasks().get(index_selected_task).deleteFromDB();
            sb.getMyTasks().remove(index_selected_task);
            response.sendRedirect(MyStrings.PAGE_HOME);
            
            return;
        }
        
        
        
        if(request.getParameter("cancel") != null){            
            response.sendRedirect(MyStrings.PAGE_HOME);
            return;
        }
    }

    private boolean correctLength(final String title, final int maximumLength){
        
        try {
            
            if(title.length() > 0 && title.length() < maximumLength ){
                return true;
            } 
            else {
                return false;
            }
            
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
