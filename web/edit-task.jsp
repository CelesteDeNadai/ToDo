<%-- 
    Document   : create-project
    Created on : Oct 6, 2018, 6:10:01 PM
    Author     : tmp-sda-1176
--%>

<%@page import="other.MyTimer"%>
<%@page import="data.Task"%>
<%@page import="data.Project"%>
<jsp:useBean id="sb" class="beans.SessionBean" scope="session" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit task</title>
    </head>
    <body>
        <h1 style="text-align: center; margin-bottom: 50px; margin-top: 50px">
            Edit task
        </h1>
        <p class="error">
            <jsp:getProperty name="sb" property="error" />
        </p>
        <form method="POST" action="Controller" style="width: 100%">
            <table style="width: 100%">
                <tr>
                    <td style="width: 50%">
                        <p style="text-align: right; margin-right: 10px">Select project: </p>
                    </td>
                    <td style="width: 50%">
                        <p style="text-align: left; margin-left: 10px">
                            <select name="id-project">
                                <option value="<% out.write(sb.getSelectedProject().id_project + "");%>">
                                    <% out.write(sb.getSelectedProject().project_title); %>
                                </option>

                                <%
                                for(int i=0; i<sb.getMyProjects().size(); i++){

                                    if(sb.getMyProjects().get(i).id_project != sb.getSelectedTask().id_project){
                                        out.println("<option value=\"" + 
                                                        sb.getMyProjects().get(i).id_project + "\">" + 
                                                        sb.getMyProjects().get(i).project_title + 
                                                    "</option>");
                                    }
                                }
                                %>
                            </select> 
                        </p>
                    </td>
                </tr>
                <tr>
                    <td style="width: 50%">
                        <p style="text-align: right; margin-right: 10px">Or create a new project: </p>
                    </td>
                    <td style="width: 50%">
                        <p style="text-align: left; margin-left: 10px">

                            <input type="text" 
                                   name="new-project-title" 
                                   value=""/>

                            <input type="submit"
                                   name="create-new-project-in-ET"
                                   value="Create" />
                        </p>
                    </td>
                </tr>
                <tr>
                    <td style="width: 50%">
                        <p style="text-align: right; margin-right: 10px">Task title: </p>
                    </td>
                    <td style="width: 50%">
                        <p style="text-align: left; margin-left: 10px">
                            <input type="text" 
                                   name="task-title" 
                                   value="<% out.write(sb.getSelectedTask().title); %>"/>
                            
                            <% out.write(sb.getSelectedTask().time); %>
                        </p>
                    </td>
                </tr>
                <tr>
                    <td style="width: 50%">
                        <p style="text-align: right; margin-right: 10px">Due date: </p>
                    </td>
                    <td style="width: 50%">
                        <p style="text-align: left; margin-left: 10px">
                            <input type="text" 
                                   name="day" 
                                   value="<% out.write(MyTimer.getFieldFromDate(sb.getSelectedTask().time, MyTimer.DAY));%>"
                                   size="2"/>
                            /
                            <input type="text" 
                                   name="month" 
                                   value="<% out.write(MyTimer.getFieldFromDate(sb.getSelectedTask().time, MyTimer.MONTH));%>"
                                   size="2"/>
                            /
                            <input type="text" 
                                   name="year" 
                                   value="<% out.write(MyTimer.getFieldFromDate(sb.getSelectedTask().time, MyTimer.YEAR));%>"
                                   size="2"/>
                            &nbsp;&nbsp; - &nbsp;&nbsp;
                            <input type="text" 
                                   name="hour" 
                                   value="<% out.write(MyTimer.getFieldFromDate(sb.getSelectedTask().time, MyTimer.HOUR));%>"
                                   size="2"/>
                            :
                            <input type="text" 
                                   name="minutes" 
                                   value="<% out.write(MyTimer.getFieldFromDate(sb.getSelectedTask().time, MyTimer.MINUTES));%>"
                                   size="2"/>
                        </p>
                    </td>
                </tr>
            </table>           
            <p style="width: 100%; text-align: center">
                <input type="submit" name="cancel" value="Cancel"/>
                <input type="submit" name="confirm-edit-task" value="Submit changes"/>
            </p>
        </form>
    </body>
</html>





















<!--
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="text-align: center; margin-bottom: 50px; margin-top: 50px">
            Edit current task
        </h1>
            <form method="POST" action="Controller" style="width: 100%">
                <p style="text-align: center; width: 100%">
                <input type="text" 
                       name="edited_task_title" 
                       value="<% out.write(sb.getSelectedTask().title); %>"
                       style="font-size: 1.5em"/>
                
                <br><br><br>
                
                <input type="submit" name="final_edit_task" value="Edit" style="font-size: 1.5em"/>
                </p>
            </form>
    </body>
</html>
-->