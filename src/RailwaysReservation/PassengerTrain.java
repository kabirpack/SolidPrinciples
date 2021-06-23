package RailwaysReservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PassengerTrain extends Train{

    private int totalSeats;
    private int acSeats;
    private int wlSeats;
//    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private ArrayList<String> bookedSeats = new ArrayList<>();  // may change to hash map to map pnr to seats
    private ArrayList<String> availableSeats = new ArrayList<>();
    private ArrayList<String> waitingList = new ArrayList<>(); // contain pnr number
    private final int thresholdBookingTime = 5;
    public PassengerTrain(String name, String trainNumber, String startLeg, String endLeg) {
        super(name, trainNumber, startLeg, endLeg);
    }

    public boolean isBookingOpen(String day, String time) throws ParseException {
        if(isAvailable(day)){

            String scheduleTimeStr = getTrainSchedule().get(day);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            // may need try catch
            Date scheduleTime = simpleDateFormat.parse(scheduleTimeStr);
            Date currentTime = simpleDateFormat.parse(time);
            long timeDifferenceMS = Math.abs(scheduleTime.getTime() - currentTime.getTime());
            long timeDifference = (timeDifferenceMS / (60 * 60 * 1000));

            if(timeDifference < thresholdBookingTime){
                return false;
            }
        }else if(this.wlSeats == 0){
            return false;
        }
        return true;
    }

    public boolean isSeatAvailable(String seat){
        for(String bookedSeat : bookedSeats){
            if (seat.equals(bookedSeat)){
                return false;
            }
        }
        return true;
    }

    public void decrementTotalSeats(int seat) {
        this.totalSeats = totalSeats-seat;
    }

    public void incrementTotalSeats(int seat) {
        this.totalSeats = totalSeats+seat;
    }


    public void setAcSeats(int acSeats) {
        this.acSeats = acSeats;
    }

    public void decrementWlSeats(int seats) {
        this.wlSeats = wlSeats-seats ;
    }

    public void incrementWlSeats() {
        this.wlSeats = wlSeats+1 ;
    }


    public void addBookedSeats(String pnr) {
        this.bookedSeats.add(pnr);
    }

    public void removeBookedSeats(String pnr) {
        this.bookedSeats.remove(pnr);
    }


    public void setAvailableSeats(ArrayList<String> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void addWaitingList(String pnr) {
        this.waitingList.add(pnr);
    }


    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAcSeats() {
        return acSeats;
    }

    public int getWlSeats() {
        return wlSeats;
    }

    public ArrayList<String> getBookedSeats() {
        return bookedSeats;
    }

    public ArrayList<String> getAvailableSeats() {
        return availableSeats;
    }

    public int getThresholdBookingTime() {
        return thresholdBookingTime;
    }

    public ArrayList<String> getWaitingList() {
        return waitingList;
    }
}
