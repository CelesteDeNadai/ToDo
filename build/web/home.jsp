<%@page import="data.TaskOrder"%>
<%@page import="data.Task"%>
<%@page import="data.Project"%>
<jsp:useBean id="sb" class="beans.SessionBean" scope="session" />

<!DOCTYPE html>

<html>
    <head>
        <title> List</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css">
        
        <!-- function used to send automatically a request
             to the server when clicking an entry in the ordering menu -->
        <script type="text/javascript">

            function changeTaskSort() {

            var selectedValue = task_sorting.options[task_sorting.selectedIndex].value;
            location.href='Controller?sort-tasks-by=' + selectedValue;
            }

        </script>
    </head> 
    <body>
        <h1 style="background-color:antiquewhite; text-align: center; margin-bottom: 50px; margin-top: 50px">
            To Do List
        </h1>
        
        <table style="width: 100%">
            <tr>
                <!-- PROJECTS COLUMN -->
                <td style="width: 20%; vertical-align: top">
                    <table style="width: 100%">
                        <tr valign="top">
                            <td style="width: 100%; 
                                border-style: solid; 
                                border-width: 1px 0px 1px 0px; 
                                border-color: #DDDDDD;
                                padding: 15px 0px 15px 0px;">
                                <a href="create-project.jsp" style="text-decoration: none; color: black">
                                    + New Project
                                </a>
                            </td>
                        </tr>
                        
                        <tr valign="top">
                            <td style="width: 100%; 
                                border-style: solid; 
                                border-width: 1px 0px 1px 0px; 
                                border-color: #DDDDDD;
                                padding: 15px 0px 15px 0px;">
                                <a href="Controller?show-all-tasks=1" style="text-decoration: none; color: black">
                                    Show all tasks
                                </a>
                            </td>
                        </tr>
                        <% 
                        int i=0;
                        for(Project p : sb.getMyProjects()){
                        %>
                        <tr valign="top">
                            <td style="width: 100%; 
                                border-style: solid; 
                                border-width: 1px 0px 1px 0px; 
                                border-color: #DDDDDD;
                                padding: 10px 0px 10px 0px;">
                               
                                <a href="Controller?show-tasks-by-project=1&project-index=<%out.write(i + "");%>" 
                                <%if(p.equals(sb.getSelectedProject())){ %> style="font-weight: bold; text-decoration: none; color: brown" <% } 
                                else { %> style="text-decoration: none; color: black;"<%}%> >
                                    <%
                                    out.write(p.project_title);
                                    %>
                                </a>
                                    <% if (p.equals(sb.getSelectedProject())){
                                    %>
                                    <form method="GET" action="Controller" style="padding: 10px 0px 0px 0px;">
                                            <input type="submit" name="go-to-rename-project" value="Rename" />
                                            <input type="submit" name="go-to-delete-project" value="Delete" />
                                    </form>
                                    <%
                                    }
                                    %>
                            </td>
                        </tr>
                        <%
                            i++;
                        }
                        %>
                    </table>
                </td> <!-- END PROJECTS COLUMN -->
                
                
                <!-- TASKS COLUMN -->
  
                <td style="width: 80%; vertical-align: top">
                    <table style="width: 100%">
                        <tr>
                            <td style="
                                border-style: solid; 
                                border-width: 1px 0px 1px 0px; 
                                border-color: #DDDDDD;
                                padding: 15px 5px 15px 5px"
                                colspan="2">
                                
                                <a href="create-task.jsp" style="text-decoration: none; color: black">
                                    + New Task
                                </a>
                            </td>
                            
                            <!-- TASK ORDERING MENU -->
                            
                            <td colspan="3" style="border: 0px; padding-right: 5%">
                                <p style="text-align: right">
                                    Sort tasks by:
                                    <select id="task_sorting" onchange="changeTaskSort()">
                                        <option value="<% out.write(sb.to.getValues()[sb.to.getSelected_index()]); %>">
                                            <% out.write(sb.to.getLabels()[sb.to.getSelected_index()]); %>
                                        </option>
                                        <%
                                        for(i=0; i<sb.to.getList_dim(); i++){
                                            if(i != sb.to.getSelected_index()){
                                                %>
                                                <option value="<% out.write(sb.to.getValues()[i]); %>">
                                                    <% out.write(sb.to.getLabels()[i]); %>
                                                </option>
                                                <%
                                            }
                                        }  
                                        %>
      
                                    </select> 
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 10%">Status<br><i>click to change</i></th>
                            <th style="width: 10%">Due Date<br><i>dd/mm/yy - hh:mm</i></th> 
                            <%if(sb.getSelectedProject().id_project <= 0 || sb.getSelectedProject()==(null)){ %>
                                <th style="width: 15%">Project</th>    
                                <th colspan="3" style="width: 40%">Task</th>
                            <%
                            } else {
                            %>
                                <th colspan="3" style="width: 60%">Task</th>
                            <%
                            }
                            %>

                            
                        </tr>
                        <% 
                        i=0;
                        for(Task t : sb.getMyTasks()){
                        %>
                        <tr>
                            <td style="width: 14%;">
                                <%
                                if(t.isCompleted()){
                                %>
                                    <a href="Controller?mark-task-as-not-completed=1&task-index=<% out.write(i + ""); %>" style="font-size: 0.9em; font-weight: normal; text-decoration: none; color: green">
                                        Completed
                                    </a>
                                <%
                                }
                                else{
                                %>
                                    <a href="Controller?mark-task-as-completed=1&task-index=<% out.write(i + ""); %>" style="font-size: 0.9em; font-weight: normal; text-decoration: none; color: red">
                                        Not completed
                                    </a>
                                <%
                                }
                                %>
                            </td>
                            <td style="width: 14%;">
                                <%
                                out.write(t.time);
                                %>
                            </td>
                            <%if(sb.getSelectedProject().id_project < 0){ %>
                                <td style="text-transform: uppercase; font-size: 0.9em; width: 15%; text-align:center">
                                    <%
                                    out.write(t.project_title);
                                    %>
                                </td>
                                <td style="width: 40%; text-align:left">
                                    <%
                                    out.write(t.title);
                                    %>
                                </td>
                            <%
                            } else {
                            %>
                                <td style="width: 60%; text-align:left">
                                    <%
                                    out.write(t.title);
                                    %>
                                </td>
                            <%
                            }
                            %>    
                            <td style="width: 6%; text-align:center">
                                <form method="POST" action="Controller">
                                    <input type="hidden" value="<%out.write(i + "");%>" name="index-selected-task" />
                                    <input type="submit" name="go-to-edit-task" value="Edit" />
                                </form>
                            </td>
                            <td style="width: 6%; text-align:center">
                                <form method="POST" action="Controller" >
                                    <input type="hidden" value="<%out.write(i + "");%>" name="index-selected-task" />
                                    <input type="submit" name="delete-task" value="Delete" />
                                </form>
                            </td>
                        </tr>
                        <%
                            i++;
                            }
                        %>
                    </table>
                </td> <!-- END TASKS COLUMN -->
            </tr>
        </table>   
        <p>
        </p>
    </body>
</html>
