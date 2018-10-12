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
        <title>Create task</title>
    </head>
    <body>
        <h1 style="text-align: center; margin-bottom: 50px; margin-top: 50px">
            Create new task
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

                                    if(sb.getMyProjects().get(i).id_project != sb.getSelectedProject().id_project){
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
                                   name="create-new-project-in-CT"
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
                                   value=""/>
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
                                   value="<% out.write(MyTimer.getDay());%>"
                                   size="2"/>
                            /
                            <input type="text" 
                                   name="month" 
                                   value="<% out.write(MyTimer.getMonth());%>"
                                   size="2"/>
                            /
                            <input type="text" 
                                   name="year" 
                                   value="<% out.write(MyTimer.getYear());%>"
                                   size="2"/>
                            &nbsp;&nbsp; - &nbsp;&nbsp;
                            <input type="text" 
                                   name="hour" 
                                   value="<% out.write(MyTimer.getHour());%>"
                                   size="2"/>
                            :
                            <input type="text" 
                                   name="minutes" 
                                   value="<% out.write(MyTimer.getMinutes());%>"
                                   size="2"/>
                        </p>
                    </td>
                </tr>
            </table>
                                  
                                   
            <p style="width: 100%; text-align: center">
                <input type="submit" name="cancel" value="Cancel"/>
                <input type="submit" name="create-task" value="Create task"/>
            </p>
        </form>
    </body>
</html>
