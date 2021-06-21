import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.*;
import java.text.ParseException;


public class ClockIn {
    public static void main(String[] args){
        System.out.println("Enter 'Hours' if you're here to check your total time!");
        System.out.println("Enter 'Other' for other options!");
        Scanner scn = new Scanner(System.in);
        String answer = scn.nextLine();
        if(answer.equalsIgnoreCase("Hours")){
            readFile();
            calTime();
        }
        else{
            addToFile(formatTime());
        }


    }
    public static Date start;
    public static Date end;
    public static long timeElapsed;
    static ArrayList<String> trackTime = new ArrayList<>();
    


    // Method to Read the file
    public static void readFile(){
        Scanner objs = new Scanner(System.in);
        System.out.println("Enter the date you want to find the hours for (MM-DD-YYYY): ");
        String date = objs.nextLine();
        try{
            // Creates objects to read file
            File obj = new File("C:\\Users\\Aanuj Sharma\\OneDrive\\Documents\\Other\\UA\\TimeSheet.txt");
            Scanner Reader = new Scanner(obj);

            while(Reader.hasNextLine()){
                String data = Reader.nextLine();
                if (data.contains(date)){
                    String[] values = data.split(" ");
                    if (values.length < 8){
                        trackTime.add(values[4]);

                    }
                }
            }
            Reader.close();
        }
        //Checks for errors
        catch(FileNotFoundException e){
            System.out.println("An error occurred with file.");
            e.printStackTrace();
        }
    }

    // Method to Add Time to the File
    public static void addToFile(String x){
        System.out.println("Enter 'In' to Clock In or 'Out' to Clock Out");
        Scanner times = new Scanner(System.in);
        SimpleDateFormat date_format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        try {
            FileWriter myWrite = new FileWriter("C:\\Users\\Aanuj Sharma\\OneDrive\\Documents\\Other\\UA\\TimeSheet.txt", true);
            while(true){
                String user_entry = times.nextLine();
                if("In".equalsIgnoreCase(user_entry.trim())){
                    myWrite.write("Clock In Time: " + x + "\n");
                    System.out.println("Welcome Stupid!");
                    try {
                        start = date_format.parse(x);
                    //    trackTime.add(date_format.parse(x));
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                if("Out".equalsIgnoreCase(user_entry.trim())){
                    myWrite.write("Clock Out Time: " + x + "\n");
                    System.out.println("Get out!");
                    try {
                        end = date_format.parse(x);
                     //   trackTime.add(date_format.parse(x));
                     //   timeElapsed = end.getTime() - start.getTime();
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                        }
                else{
                    System.out.println("You're useless enter one of the options!!");
                    break;
                }
                    break;
                }
            myWrite.close();
            }
        catch (IOException e) {
            e.printStackTrace();
        }
        }


    /* Method that gets the time when Clock in or Out
    public static Timestamp timeStamps(){
        Date date = new Date();
        long time = date.getTime();
        //System.out.println(ts);
        return new Timestamp(time);
    }

     */

    // Method for Formatting Time in American Time standard
    public static String formatTime(){
        Date date = new Date();
        SimpleDateFormat date_format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
       // System.out.println(date_format.format(timeStamp));
        return date_format.format(date);
    }

    public static void calTime(){
        SimpleDateFormat date_format = new SimpleDateFormat("HH:mm:ss");
        try {
            Date ends = date_format.parse(trackTime.get(1));
            Date starts = date_format.parse(trackTime.get(0));
            timeElapsed = ends.getTime() - starts.getTime();
            long milliseconds = timeElapsed;
            int seconds = (int) (milliseconds / 1000) % 60 ;
            int minutes = (int) ((milliseconds / (1000*60)) % 60);
            int hours   = (int) ((milliseconds / (1000*60*60)) % 24);
            System.out.println("The total time you worked is: " + hours + " hours " + minutes + " minutes " + seconds + " seconds!");
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
