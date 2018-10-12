/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author papa
 */
public class TaskOrder {
    
    public final static String VAL_DATE_ASC = "date-asc";
    public final static String VAL_DATE_DESC = "date-desc";
    public final static String VAL_PROJECT_ASC = "project-asc";
    public final static String VAL_PROJECT_DESC = "project-desc";
    
    public final static String LAB_DATE_ASC = "Due date &uarr;";
    public final static String LAB_DATE_DESC = "Due date &darr;";
    public final static String LAB_PROJECT_ASC = "Project &uarr;";
    public final static String LAB_PROJECT_DESC = "Project &darr;";
    
    private int list_dim;
    
    
    private String[] values;
    private String [] labels;
    private int selected_index;
    
    public TaskOrder(){
        
        list_dim = 4;
        
        values = new String[list_dim];
        
        values[0] = VAL_DATE_ASC;
        values[1] = VAL_DATE_DESC;
        values[2] = VAL_PROJECT_ASC;
        values[3] = VAL_PROJECT_DESC;
        
        labels = new String[list_dim];
        
        labels[0] = LAB_DATE_ASC;
        labels[1] = LAB_DATE_DESC;
        labels[2] = LAB_PROJECT_ASC;
        labels[3] = LAB_PROJECT_DESC;
        
        selected_index = 0;
        
    }


    /**
     * @return the values
     */
    public String[] getValues() {
        return values;
    }

    /**
     * @return the labels
     */
    public String[] getLabels() {
        return labels;
    }

    /**
     * @return the selected_index
     */
    public int getSelected_index() {
        return selected_index;
    }


    public void changeSelectedIndex(String sort_value) {
        
        switch(sort_value){
            case VAL_DATE_ASC: selected_index = 0; break;
            case VAL_DATE_DESC: selected_index = 1; break;
            case VAL_PROJECT_ASC: selected_index = 2; break;
            case VAL_PROJECT_DESC: selected_index = 3; break;
        }
        
    }

    /**
     * @return the list_dim
     */
    public int getList_dim() {
        return list_dim;
    }

    /**
     * @param list_dim the list_dim to set
     */
    public void setList_dim(int list_dim) {
        this.list_dim = list_dim;
    }
    

    
}
