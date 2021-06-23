package RailwaysReservation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class BookingManager {
    PassengerTrain selectedTrain;
    RailwayUtilities utilitiy = new RailwayUtilities();
    String prompt;

    public ArrayList<Ticket> bookTicket(String trainNumber, UserAccount user, ArrayList<PassengerTrain> trainList, Ticket ticket) throws ParseException {
        for(PassengerTrain train : trainList){
            if(train.getTrainNumber().equals(trainNumber)){
                 selectedTrain = train;
            }
        }
        ArrayList<Ticket> resultArray = new ArrayList<>();
        if(selectedTrain.isBookingOpen(user.getSessionDay(), user.getSessionTime()) && selectedTrain.isAvailable(user.getSessionDay())){
            Scanner sc = new Scanner(System.in);
            System.out.println("Please Choose Starting Location");
            ArrayList<String> routes = selectedTrain.getRoutes();
            for(int i =0; i<routes.size()-1;i++){
                System.out.println(i+1+"."+ routes.get(i));
            }
            int startingLocationChoice = sc.nextInt();
            String startingLocation = routes.get(startingLocationChoice-1);

            System.out.println("Please Choose Destination");
            for(int i =startingLocationChoice+1; i<routes.size();i++){
                int index = 1;
                System.out.println((index++)+"."+ routes.get(i));
            }
            int destinationChoice = sc.nextInt();
            String destination = routes.get(destinationChoice-1);

            int availableSeats = selectedTrain.getAvailableSeats().size();
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
        ticket.setPassengerList(passengers);
        ticket.setPnrNumber(utilitiy.generatePnrNumber("BK"));
        train.addBookedSeats(ticket.getPnrNumber());
        train.decrementTotalSeats(psngCount);
        return ticket;
    }

    public void cancelTicket(PassengerTrain train, String pnr){
        train.removeBookedSeats(pnr);
    }
}
