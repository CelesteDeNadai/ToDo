<%-- 
    Document   : create-project
    Created on : Oct 6, 2018, 6:10:01 PM
    Author     : tmp-sda-1176
--%>
<jsp:useBean id="sb" class="beans.SessionBean" scope="session" />
<%@page import="data.Project"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create project</title>
    </head>
    <body>
        <h1 style="text-align: center; margin-bottom: 50px; margin-top: 50px">
            Create new project
        </h1>
        
        <p class="error">
            <jsp:getProperty name="sb" property="error" />
        </p>
        
        <form method="POST" action="Controller" style="width: 100%">
            <p style="text-align: center; width: 100%">
            <input type="text" 
                   name="new_project_title" 
                   value=""
                   style="font-size: 1.5em"/>

            <br><br><br>

            <input type="submit" name="cancel" value="Cancel"/>
            <input type="submit" name="create_project" value="Create" style="font-size: 2em"/>
            </p>
        </form>
    </body>
</html>
