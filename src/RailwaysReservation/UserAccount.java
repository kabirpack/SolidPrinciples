package RailwaysReservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UserAccount {
    private String userName;
    private String password;
    private ArrayList<Ticket> myTickets = new ArrayList<>();

    public UserAccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getSessionTime() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String strTime = dateFormat.format(date);
        return strTime;
    }

    public String getSessionDay() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        String day = dayOfWeek.toString().toUpperCase(Locale.ROOT);
        return day;
    }

    public ArrayList<Ticket> getMyTickets() {
        return myTickets;
    }

    public void setMyTickets(ArrayList<Ticket> myTickets) {
        for(Ticket ticket : myTickets){
            this.myTickets.add(ticket);
        }
    }

    public void removeTickets(int index){
        myTickets.remove(index);
    }
}
