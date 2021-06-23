package RailwaysReservation;

import java.util.ArrayList;

public class ServicesManager {
    private ArrayList<PassengerTrain> trainList = new ArrayList<>();


    public void addService(PassengerTrain train){
        trainList.add(train);
//        System.out.println("Train added successfully");   // train has to be made from user input
    }

    public void removeService(PassengerTrain train){
        trainList.remove(train);
        System.out.println("Train removed successfully");
    }

    public void holdService(PassengerTrain train){
        for(PassengerTrain availableTrain : trainList){
            if(availableTrain.getTrainNumber().equals(train.getTrainNumber())){
                availableTrain.setActive(false);
            }
        }
    }


    public ArrayList<PassengerTrain> showAvailableServices(String day){
        ArrayList<PassengerTrain> availableTrains = new ArrayList<>();
        for(PassengerTrain train : trainList){
            if(train.isActive() && train.isAvailable(day)){
                availableTrains.add(train);
            }
        }
        return availableTrains;
    }

    public ArrayList<PassengerTrain> getTrainList() {
        return trainList;
    }

    public PassengerTrain getTrainByNumber(String number){
        for(PassengerTrain train : trainList){
            if(train.getTrainNumber().equals(number)){
                PassengerTrain train1 = train;
            }
        }
        PassengerTrain selectedTrain = new PassengerTrain("", "", "", "");
        return selectedTrain;
    }
}
