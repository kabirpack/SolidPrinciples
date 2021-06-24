package RailwaysReservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class RailwayUtilities {

    public String generatePnrNumber(String ticketCategory){
        if(ticketCategory.equals("WL")){
            return "WLPNR" +(long) Math.floor(Math.random() * 9_000_000_000L);
        }
        return "PNR" +(long) Math.floor(Math.random() * 9_000_000_000L);
    }

    public String cmdPrompt(String msg){
        System.out.println(msg);
        Scanner sc = new Scanner(System.in);
        String userChoice = sc.nextLine().toUpperCase(Locale.ROOT);
        return userChoice;
    }

    public String getStringInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String getDayByDate(String date) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        Date dt1 = format1.parse(date);
        DateFormat format2 = new SimpleDateFormat("EEEE");
        return format2.format(dt1).toUpperCase(Locale.ROOT);
    }


    public String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public String getNextDay(){
        DayOfWeek dayOfWeek = LocalDate.now().plusDays(1).getDayOfWeek();
        String day = dayOfWeek.toString().toUpperCase(Locale.ROOT);
        return day;
    }

    public ArrayList<String> generateSeats(int count){
        ArrayList<String> seatList = new ArrayList<>();
        for(int i=1;i<= count ; i++){
            seatList.add(Integer.toString(i));
        }
        return seatList;
    }

}
