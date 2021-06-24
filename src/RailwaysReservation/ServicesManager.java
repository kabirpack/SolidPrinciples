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
        train.setActive(false);
        System.out.println("Train holded successfully");
    }

    public void resumeService(PassengerTrain train){
        train.setActive(true);
        System.out.println("Train resumed successfully");
    }

    public ArrayList<PassengerTrain> getInactiveTrains(){
        ArrayList<PassengerTrain> availableTrains = new ArrayList<>();
        for(PassengerTrain train : trainList){
            if(!train.isActive()){
                availableTrains.add(train);
            }

        }
        return availableTrains;
    }


    public ArrayList<PassengerTrain> showAvailableServices(String from, String to, String day){
        ArrayList<PassengerTrain> availableTrains = new ArrayList<>();
        for(PassengerTrain train : trainList){
            if(train.isActive() && train.isAvailable(day)){
//                for(String route : train.getRoutes()) {
//                    if(route.contains(from) || route.contains(to)){
                        availableTrains.add(train);
//                    }
//                }
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
                return train1;
            }
        }
        PassengerTrain selectedTrain = new PassengerTrain("", "", "", "");
        return selectedTrain;
    }

    public boolean isStationAvailable(String station){
        if(station.length() >= 4) {
            for (PassengerTrain train : trainList) {
                if (train.isActive()) {
                    for (String route : train.getRoutes()) {
                        if (route.contains(station)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String getStationFromSubstring(String station){
        if(station.length() >= 4) {
            for (PassengerTrain train : trainList) {
                if (train.isActive()) {
                    for (String route : train.getRoutes()) {
                        if (route.contains(station)) {
                            return route;
                        }
                    }
                }
            }
        }
        return "";
    }

    public void printFromLocations(){
        for(PassengerTrain train : trainList){
            if(train.isActive()){
                for(int i=0; i<train.getRoutes().size()-1;i++){
                    System.out.print(train.getRoutes().get(i) + ",");
                    System.out.println(" ");
                }
            }
        }
    }

    public void printToLocations(){
        for(PassengerTrain train : trainList){
            if(train.isActive()){
                for(int i=1; i<train.getRoutes().size();i++){
                    System.out.print(train.getRoutes().get(i) + ",");
                    System.out.println(" ");
                }
            }
        }
    }


}
