package com.company;


import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Main extends Application implements ActionListener {

    private static final JButton butAddTask = new JButton("Add Task");
    private static final JButton butViewTasks = new JButton("View Tasks");
    private static final JButton butDeleteTask = new JButton("Delete Task");
    public static JTextArea textTasks = new JTextArea(20,120);

    //-------------------------------------------
    public static ArrayList<Task> myTasks;
    public static ArrayList<Project> myProjects;
    //-------------------------------------------


    public static void main(String[] args) {

        myProjects = Project.fetchProjects();

        JFrame f = initGraphics();

        MyController mc = new MyController();
        butAddTask.addActionListener(mc);
        butViewTasks.addActionListener(mc);
        butDeleteTask.addActionListener(mc);

        myTasks = Task.fetchTasks();


    }

    private static JFrame initGraphics(){



        JFrame f = new JFrame("TODO");
        f.setSize(1200,700);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panMain = new JPanel();
        panMain.setLayout(new BorderLayout());
        f.setContentPane(panMain);

        JPanel panNord = new JPanel();
        panNord.setLayout(new FlowLayout(FlowLayout.CENTER));

        panNord.add(butAddTask);
        panNord.add(butViewTasks);
        panNord.add(butDeleteTask);


        panMain.add(panNord, BorderLayout.NORTH);

        JPanel panCenter = new JPanel();
        panCenter.setLayout(new GridLayout(1,1));

        panCenter.add(textTasks);
        panMain.add(panCenter, BorderLayout.CENTER);

        f.setVisible(true);

        return f;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
