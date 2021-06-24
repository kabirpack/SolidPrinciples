package RailwaysReservation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class BookingManager {
//    PassengerTrain selectedTrain;
    RailwayUtilities utilitiy = new RailwayUtilities();
    String prompt;

    public ArrayList<Ticket> bookTicket(PassengerTrain selectedTrain, UserAccount user, Ticket ticket, int todayFlag) throws ParseException {
        boolean isAvailableToday = true;
        ArrayList<Ticket> resultArray = new ArrayList<>();
        if (todayFlag == 1){
           isAvailableToday = selectedTrain.isBookingOpen(utilitiy.getDayByDate(ticket.getTicketDate()), user.getSessionTime());
        }
        if(isAvailableToday){
            Scanner sc = new Scanner(System.in);
            ticket.setTrainName(selectedTrain.getName());
            ticket.setTrainNumber(selectedTrain.getTrainNumber());
            ticket.setDepartureTime(selectedTrain.getSchedulebyDay(utilitiy.getDayByDate(ticket.getTicketDate())));
            int availableSeats = selectedTrain.getAvailableSeats().size();
//            int availableSeats = 100;
            System.out.println("Enter Number of passengers");
            int passengerCount = sc.nextInt();

            if(availableSeats == 0){
                if(selectedTrain.getWlSeats() >= passengerCount){
                    System.out.println("Only Waiting list available");
                    System.out.println("Waiting List position" + selectedTrain.getWaitingList().size() + 1 + " available");
                    prompt = utilitiy.cmdPrompt("Do you wish to continue?");
                    if(prompt.equals("Y") || prompt.equals("YES")){
                        resultArray.add(bookWlTickets(selectedTrain,passengerCount,ticket));
                        return resultArray;
                    }

                }else{
                    prompt = utilitiy.cmdPrompt("Only " + selectedTrain.getWlSeats() + "Waiting list ticket available\nDo you wish to continue?");
                    if(prompt.equals("Y") || prompt.equals("YES")){
                        resultArray.add(bookConfirmedTicket(selectedTrain,passengerCount,ticket));
                        return resultArray;
                    }
                }

            }else if(availableSeats == 0  && selectedTrain.getWlSeats() == 0){
                System.out.println("All tickets are booked, sorry.");
            }
            if(availableSeats < passengerCount){
                int potentialWL = Math.abs(availableSeats - passengerCount);
                System.out.println("only " + availableSeats + "seats are available, remaining "+ potentialWL + "seats can be booked in waiting list");
                System.out.println("Do you wish to continue?\n1.Book All Ticket\n2.Book Only Confirmed Ticket");
                int ticketChoice = sc.nextInt();
                switch (ticketChoice){
                    case 1:{
                        resultArray.add(bookConfirmedTicket(selectedTrain,availableSeats,ticket));
                        resultArray.add(bookWlTickets(selectedTrain,potentialWL,ticket));
                        return resultArray;
                    }
                    case 2:{
                        resultArray.add(bookConfirmedTicket(selectedTrain,availableSeats,ticket));
                        return resultArray;
                    }
                }
            }else{
                resultArray.add(bookConfirmedTicket(selectedTrain,passengerCount,ticket));
                return resultArray;
            }
        }
        else{
            System.out.println("Booking is closed as Chart is already prepared");
        }
        return resultArray;
    }

    public Ticket bookWlTickets(PassengerTrain train,int psngCount, Ticket ticket){
        HashMap<String,String> passengers = new HashMap<>();
        System.out.println("Enter details for waiting List ticket");
        for(int i=0; i<psngCount ; i++){
            System.out.println("Passenger Name");
            String passengerName = utilitiy.getStringInput();
            System.out.println("Passenger Age");
            String passengerAge = utilitiy.getStringInput();
            passengers.put(passengerName, passengerAge);
        }
        ticket.setPassengerList(passengers);
        ticket.setPnrNumber(utilitiy.generatePnrNumber("WL"));
        ticket.setStatus("WAITINGLIST");
        train.addWaitingList(ticket.getPnrNumber());
        train.decrementWlSeats(psngCount);
        return ticket;
    }

    public Ticket bookConfirmedTicket(PassengerTrain train, int psngCount, Ticket ticket){
        HashMap<String,String> passengers = new HashMap<>();
        System.out.println("Enter details for confirmed List ticket");
        for(int i=0; i<psngCount ; i++){
            System.out.println("Passenger Name");
            String passengerName = utilitiy.getStringInput();
            System.out.println("Passenger Age");
            String passengerAge = utilitiy.getStringInput();
            passengers.put(passengerName, passengerAge);
        }
        ticket.setPnrNumber(utilitiy.generatePnrNumber("BK"));
        ArrayList<String> pnrSeatMap = new ArrayList<>();
        HashMap<String,String> seatMap = new HashMap<>();
        int i=0;
        for(String psngr : passengers.keySet()){
            seatMap.put(psngr,train.getAvailableSeats().get(i));
            pnrSeatMap.add(train.getAvailableSeats().get(i));
            i=i+1;
        }
        int length = passengers.keySet().size();
        for(int j=0; j<length;  j++){
            train.removeAvailableSeat(j);
            length--;
        }
        ticket.setSeatMap(seatMap);
        ticket.setPassengerList(passengers);
        ticket.setStatus("CONFIRMED");
        train.addBookedSeats(ticket.getPnrNumber(),pnrSeatMap);
        train.decrementTotalSeats(psngCount);
        return ticket;
    }

    public void cancelTicket(PassengerTrain train, Ticket ticket, int passengerCount){
        System.out.println("before cancelling booked pnr" + train.getBookedSeats());
        train.removeBookedSeats(ticket.getPnrNumber());
        System.out.println("after cancelling booked pnr" + train.getBookedSeats());
        System.out.println("before cancelling total seats" + train.getTotalSeats());
        train.incrementTotalSeats(passengerCount);
        System.out.println("after cancelling total seats" + train.getTotalSeats());
        System.out.println("before cancelling available seats" + train.getAvailableSeats());
        for(String seat : ticket.getSeatMap().values()){
            train.getAvailableSeats().add(seat);
        }
        System.out.println("after cancelling available seats" + train.getAvailableSeats());
    }
}
