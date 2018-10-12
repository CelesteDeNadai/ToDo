/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

import java.util.Calendar;

/**
 *
 * @author papa
 */
public class MyTimer {
    
    public final static int DAY = 1;
    public final static int MONTH = 2;
    public final static int YEAR = 3;
    public final static int HOUR = 4;
    public final static int MINUTES = 5;
    
    private static Calendar c;
    
    
    public static String getDay(){
        
        String day = "";
        
        c = Calendar.getInstance();
        
        day = c.get(Calendar.DAY_OF_MONTH) + "";
        
        return MyTimer.addZero(day);
    }
    
    public static String getMonth(){
        
        String month = "";
        
        c = Calendar.getInstance();
        
        month = c.get(Calendar.MONTH) + 1 + "";
        
        return MyTimer.addZero(month);
    }
    
    public static String getYear(){
        
        String year = "";
        
        c = Calendar.getInstance();
        
        year = c.get(Calendar.YEAR) + "";
        
        year = year.substring(2);
        
        return MyTimer.addZero(year);
    }
    
    public static String getHour(){
        
        String hour = "";
        
        c = Calendar.getInstance();
        
        hour = c.get(Calendar.HOUR_OF_DAY) + "";
        
        if(hour.equals("00")){
            
            return hour;
        }
        else{
            return MyTimer.addZero(hour);
        }
    }
    
    public static String getMinutes(){
        
        String minutes = "";
        
        c = Calendar.getInstance();
        
        minutes = c.get(Calendar.MINUTE) + "";
        
        int min = Integer.parseInt(minutes);

        return MyTimer.addZero(minutes);
    }
    
    public static String addZero(String value){

        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
  
        
        if(value.length()<2){
            value = "0" + value;
        }
        
        return value;
    }
    
    public static String formatDateForDB(String day,
                                         String month,
                                         String year,
                                         String hour,
                                         String minutes){
        
        
        String formatted_date = MyTimer.addZero(year) + "/" + MyTimer.addZero(month) + "/" + MyTimer.addZero(day) + "-" + hour + ":" + MyTimer.addZero(minutes);  
        
        return formatted_date;
    }
    
    public static String formatDateFromDB(String time){
        
        //yy/mm/gg-hh:mm 
        
        String hour = time.split("-")[1];
        String date = time.split("-")[0];
        
        String year = date.split("/")[0];
        String month = date.split("/")[1];
        String day = date.split("/")[2];
        
        return day + "/" + month + "/" + year + " - " + hour;
    }
    
    public static String checkTimeInputErrors(String day,
                                        String month,
                                        String year,
                                        String hour,
                                        String minutes) {
        
        try{
            int d = Integer.parseInt(day.trim());
            
            if(d < 1 || d > 31){
                return "Day value must be between 1 and 31";
            }
            
        }
        catch(NumberFormatException e){
            return "Day value must be between 1 and 31";
        }
        
        try{
            int m = Integer.parseInt(month.trim());
            
            if(m < 1 || m > 12){
                return "Month value must be between 1 and 12";
            }
        }
        catch(NumberFormatException e){
            return "Month value must be between 1 and 12";
        }
        
        try{
            int y = Integer.parseInt(year.trim());
            
            if(y < 18 || y > 99){
                return "Year value must be between 18 and 99";
            }
        }
        catch(NumberFormatException e){
            return "Year value must be between 18 and 99";
        }
        
        try{
            int h = Integer.parseInt(hour.trim());
            
            if(h < 0 || h > 23){
                return "Hour value must be between 0 and 23";
            }
        }
        catch(NumberFormatException e){
            return "Hour value must be between 0 and 23";
        }
        
        try{
            int m = Integer.parseInt(minutes.trim());
            
            if(m < 0 || m > 59){
                return "Minutes value must be between 0 and 59";
            }
        }
        catch(NumberFormatException e){
            return "Minutes value must be between 0 and 59";
        }
        
        return "";
    }
    
    public static String getFieldFromDate(String date, int field){
        
        //dd/mm/yy-hh:mm
        
        switch(field){
            case DAY: return date.split("-")[0].split("/")[0];
            case MONTH: return date.split("-")[0].split("/")[1];
            case YEAR: return date.split("-")[0].split("/")[2];
            case HOUR: return date.split("-")[1].split(":")[0];
            case MINUTES: return date.split("-")[1].split(":")[1];
        }
        
        return "invalid field value";
    }
    
}
