package RailwaysReservation;

import java.text.ParseException;
import java.util.*;

public class ReservationSystemMain {
    boolean done;
    RailwayUtilities utility = new RailwayUtilities();
    Authentication auth = new Authentication();
    ServicesManager sm = new ServicesManager();
    Scanner sc = new Scanner(System.in);
    int authFactor = 0;
    ArrayList<PassengerTrain> availableTrains;
    String userName;
    String passWord;

    public void userRegister(){
        System.out.println("User Registration");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username");
        String userName = utility.getStringInput();
        System.out.println("Enter password");
        String passWord = utility.getStringInput();
        auth.register(userName,passWord);
        System.out.println(auth.getCredential());
        this.login();
    }

    public void login(){
        if(authFactor == 0) {
            System.out.println("User Login");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter username");
            userName = utility.getStringInput();
            System.out.println("Enter password");
            passWord = utility.getStringInput();
            authFactor = auth.authenicate(userName, passWord);
        }
        else{
            authFactor = 1;
        }
        if(authFactor == 1){
            if(auth.isAdmin()){
                UserAccount user = new UserAccount("admin", "admin");
//                this.serviceManager(user);
                System.out.println("1.Manage Service\n2.Book Ticket");
                int choice = 0;
                this.done = false;
                while(!this.done){
                    try{
                        choice = sc.nextInt();
                        if(choice > 2){
                            throw new InputMismatchException("invalid");
                        }else{
                            switch (choice) {
                                case 1: {
                                    this.manageService();
                                    System.out.println("service menu");
                                    this.done = true;
                                    break;
                                }
                                case 2:{
                                    this.welcomeMenu(user);
                                    this.done = true;
                                    break;
                                }
                            }
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Please enter correct choice");
                        sc.next();
                    }
                }
            }
            UserAccount user = new UserAccount(userName, passWord);
            this.welcomeMenu(user);
        }
        else if(authFactor == 2){
            System.out.println("Password is incorrect, try again");
            this.login();
        }else if(authFactor == 3){
            System.out.println("username doesnt exist\n1.TryAgain\n2.Register new account");
            int choice = 0;
            this.done = false;
            while(!this.done){
                try{
                    choice = sc.nextInt();
                    if(choice > 2){
                        throw new InputMismatchException("invalid");
                    }else{
                        switch (choice) {
                            case 1: {
                                this.login();
                                this.done = true;
                                break;
                            }
                            case 2:{
                                this.userRegister();
                                this.done = true;
                                break;
                            }
                        }
                    }
                }catch(InputMismatchException e){
                    System.out.println("Please enter correct choice");
                    sc.next();
                }
            }

        }

    }

    public void manageService(){
        System.out.println("What would you like to do\n1.Add Service\n2.Remove Service\n3.Modify Service");
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        this.done = false;
        while(!this.done){
            try{
                choice = sc.nextInt();
                if(choice > 3){
                    throw new InputMismatchException("invalid");
                }else{
                    switch (choice) {
                        case 1: {
                            this.addService();
                            this.done = true;
                            return;
                        }
                        case 2:{
                            System.out.println("");
                            this.done = true;
                            return;
                        }
                        case 3:{
                            System.out.println("s");
                            this.done = true;
                            return;
                        }
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter correct choice");
                sc.next();
            }
        }
    }

    public void welcomeMenu(UserAccount user){
        System.out.println("Welcome! " + user.getUserName());
        System.out.println("What would you like to do\n1.Book Ticket\n2.Cancel Ticket\n3.Modify Ticket");
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        this.done = false;
        while(!this.done){
            try{
                choice = sc.nextInt();
                if(choice > 3){
                    throw new InputMismatchException("invalid");
                }else{
                    switch (choice) {
                        case 1: {
                            this.bookTicket(user);
                            this.done = true;
                            return;
                        }
                        case 2:{
                            System.out.println("cancel menu");
                            this.done = true;
                            return;
                        }
                        case 3:{
                            System.out.println("Modify ticket menu");
                            this.done = true;
                            return;
                        }
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter correct choice");
                sc.next();
            }
        }
    }

    public void bookTicket(UserAccount user){
        Scanner sc = new Scanner(System.in);
        System.out.println("From");
        String from = utility.getStringInput().toUpperCase(Locale.ROOT);
        System.out.println("To");
        String to = utility.getStringInput().toUpperCase(Locale.ROOT);
        System.out.println("1.Today\n2.Enter Date Manually");
        int choice = 0;
        this.done = false;
        while(!this.done){
            try{
                choice = sc.nextInt();
                if(choice > 2){
                    throw new InputMismatchException("invalid");
                }else{
                    switch (choice) {
                        case 1: {
                            availableTrains = sm.showAvailableServices(user.getSessionDay());
                            System.out.println("available service");
                            this.done = true;
                            break;
                        }
                        case 2:{
                            boolean dateFormat = false;
                            while(!dateFormat) {
                                try {
                                    String travelDate = utility.getStringInput();
                                    if (!travelDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                                        throw new InputMismatchException("invalid");
                                    }else{
                                        dateFormat = true;
                                        break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid date format");
                                    sc.next();
                                }
                            }
                            this.done = true;
                            break;
                        }
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter correct choice");
                sc.next();
            }
        }

        }


    public void addService(){
        System.out.println("Enter train number");
        Scanner sc = new Scanner(System.in);
        String trainNum = sc.nextLine();
        System.out.println("Enter train name");
        String trainName = sc.nextLine();
        System.out.println("Enter starting leg");
        String startLeg = sc.nextLine();
        System.out.println("Enter ending leg");
        String endLeg = sc.nextLine();
        PassengerTrain newTrain = new PassengerTrain(trainName,trainNum,startLeg,endLeg);
        System.out.println("Enter number of days availabe in a week");
        int daysCount = sc.nextInt();
        HashMap<String,String> timeTable = new HashMap<>();
        int i = 1;
        while(i < daysCount+1){
            System.out.println("Enter day");
            String day = utility.getStringInput();
            System.out.println("Enter time");
            String time = utility.getStringInput();
            timeTable.put(day,time);
            i++;
        }
        newTrain.setTrainSchedule(timeTable);
        System.out.println("Enter no of Station stops");
        int routesCount = sc.nextInt();
        ArrayList<String> routes = new ArrayList<>();
        i = 1;
        while(i < routesCount+1){
            System.out.println("Enter route");
            String route = utility.getStringInput();
            routes.add(route);
            i++;
        }
        newTrain.setRoutes(routes);
        sm.addService(newTrain);
        System.out.println("Train added successfully");
        this.login();
    }


    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        RailwayUtilities utility = new RailwayUtilities();
        ReservationSystemMain rs = new ReservationSystemMain();

        // pack in to a function

        System.out.println("1.Register\n2.Login");
        int authenticationChoice = 0;
         rs.done = false;
        while(!rs.done){
            try{
                authenticationChoice = sc.nextInt();
                if(authenticationChoice > 2){
                    throw new InputMismatchException("invalid");
                }else{
                    switch (authenticationChoice) {
                        case 1: {
                            rs.userRegister();
                            rs.done = true;
                            break;
                        }
                        case 2:{
                            rs.login();
                            rs.done = true;
                            break;
                        }
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter correct choice");
                sc.next();
            }
        }


//        String day;
//        String time;
//
//        Authentication auth = new Authentication();
//        auth.register("kabir", "123");
//        System.out.println(auth.authenicate("kabir","123"));
//
//        //new train and add it to the service
//
//        System.out.println("Enter train number");
//        Scanner sc = new Scanner(System.in);
//        String trainNum = sc.nextLine();
//        System.out.println("Enter starting leg");
//        String startLeg = sc.nextLine();
//        System.out.println("Enter ending leg");
//        String endLeg = sc.nextLine();
//
//        PassengerTrain chatapthi = new PassengerTrain("chathapthi",trainNum,startLeg,endLeg);
//
//        System.out.println("Enter number of days availabe in a week");
//        int daysCount = sc.nextInt();
//
//        HashMap<String,String> timeTable = new HashMap<>();
//        int i = 1;
//        while(i < daysCount+1){
//            System.out.println("Enter day");
//            day = getDay();
//            System.out.println("Enter time");
//            time = getTime();
//            timeTable.put(day,time);
//            i++;
//        }
//
//
//        chatapthi.setTrainSchedule(timeTable);
//        System.out.println(chatapthi.getTrainSchedule());
//
//        ServicesManager sm = new ServicesManager();
//        sm.addService(chatapthi);
//
//        ArrayList<PassengerTrain> trainList = sm.getTrainList();
//
//        for(PassengerTrain train : trainList){
//            System.out.print(train.getName()+ " ");
//            System.out.print(train.getSchedulebyDay("monday")+ " ");
//            System.out.print(train.getRoutes()+ " ");
//            System.out.println();
//
//        }

//        String scheduleTimeStr = "20:30";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
//        // may need try catch
//        Date scheduleTime = simpleDateFormat.parse(scheduleTimeStr);
//        Date currentTime = simpleDateFormat.parse("17:30");
//        long timeDifferenceMS = Math.abs(scheduleTime.getTime() - currentTime.getTime());
//        long timeDifference = (timeDifferenceMS / (60 * 60 * 1000));
//
//        System.out.println(timeDifference);


    }
}
