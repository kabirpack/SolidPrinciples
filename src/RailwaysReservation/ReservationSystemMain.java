package RailwaysReservation;

import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationSystemMain{
    boolean done;
    RailwayUtilities utility = new RailwayUtilities();
    Authentication auth = new Authentication();
    ServicesManager sm = new ServicesManager();
    BookingManager bm = new BookingManager();
    UserAccount user;
    Scanner sc = new Scanner(System.in);
    int authFactor = 0;
    ArrayList<PassengerTrain> availableTrains;
    String userName;
    String passWord;

    public void landingMenu(){
        System.out.println("1.Register\n2.Login");
        int authenticationChoice = 0;
        this.done = false;
        while(!this.done){
            try{
                authenticationChoice = sc.nextInt();
                if(authenticationChoice > 2){
                    throw new InputMismatchException("invalid");
                }else{
                    switch (authenticationChoice) {
                        case 1: {
                            this.userRegister();
                            this.done = true;
                            break;
                        }
                        case 2:{
                            this.login();
                            this.done = true;
                            break;
                        }
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter correct choice");
//                sc.next();
            }
        }
    }

    public void userRegister(){
        System.out.println("User Registration");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username");
        String userName = utility.getStringInput();
        System.out.println("Enter password");
        String passWord = utility.getStringInput();
        auth.register(userName,passWord);
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
            if(authFactor == 1) {
                user = new UserAccount(userName, passWord);
            }
        }
        if(authFactor == 1 && auth.isAdmin() == true){

                System.out.println("1.Manage Service\n2.Manage Ticket\n3.Logout");
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
                                    this.manageService();
                                    this.done = true;
                                    return;
                                }
                                case 2:{
                                    this.welcomeMenu(user);
                                    this.done = true;
                                    return;
                                }
                                case 3:{
                                    this.logOut();
                                    this.done = true;
                                    return;
                                }
                            }
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Please enter correct choice");
//                        sc.next();
                    }
                }
//            UserAccount user = new UserAccount(userName, passWord);
        }
        else if(authFactor == 1 && !auth.isAdmin()){
            this.welcomeMenu(user);
        }
        else if(authFactor == 2){
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
                                return;
                            }
                            case 2:{
                                this.userRegister();
                                this.done = true;
                                return;
                            }
                        }
                    }
                }catch(InputMismatchException e){
                    System.out.println("Please enter correct choice");
//                    sc.next();
                }
            }

        }

    }

    public void welcomeMenu(UserAccount user){
        System.out.println("1.Book Ticket\n2.Cancel Ticket\n3.Modify Ticket\n4.My Tickets\n5.Logout");
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        this.done = false;
        while(!this.done){
            try{
                choice = sc.nextInt();
                if(choice > 5){
                    throw new InputMismatchException("invalid");
                }else{
                    switch (choice) {
                        case 1: {
                            this.showAvailableServices(user);
                            this.done = true;
                            return;
                        }
                        case 2:{
                            this.cancelTicket(user);
                            this.done = true;
                            return;
                        }
                        case 3:{
                            System.out.println("Modify ticket is a future enhancement");
                            this.welcomeMenu(user);
                            this.done = true;
                            return;
                        }
                        case 4:{
                            this.showTickets(user);
                            this.done = true;
                            return;
                        }
                        case 5:{
                            this.logOut();
                            this.done = true;
                            return;
                        }
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter correct choice");
//                sc.next();
            }
        }
    }


    public void manageService(){
        System.out.println("1.Add Service\n2.Remove Service\n3.Hold Service\n4.Resume Holded Service\n5.Show All Services\n6.Logout");
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        this.done = false;
        while(!this.done){
            try{
                choice = sc.nextInt();
                if(choice > 6){
                    throw new InputMismatchException("invalid");
                }else{
                    switch (choice) {
                        case 1: {
                            this.addService();
                            this.done = true;
                            return;
                        }
                        case 2:{
                            this.removeService();
                            this.done = true;
                            return;
                        }
                        case 3:{
                            this.holdService();
                            this.done = true;
                            return;
                        }
                        case 4:{
                            this.resumeService();
                            this.done = true;
                            return;
                        }
                        case 5:{
                            this.showAllServices();
                            this.done = true;
                            return;
                        }
                        case 6:{
                            this.logOut();
                            this.done = true;
                            return;
                        }
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter correct choice");
//                sc.next();
            }
        }
    }

    public void showAllServices(){
        availableTrains = sm.getTrainList();
        int siNo = 1;
        for (PassengerTrain train : availableTrains) {
            System.out.print(siNo + ". " + train.getName() + " ");
            System.out.print(train.getTrainSchedule() + " ");
            System.out.print(train.getRoutes() + " ");
            System.out.println();
            siNo++;
        }
        this.login();
    }

    public void removeService(){
        this.showAllServices();
        this.done = false;
        while (!this.done) {
            try {
                System.out.println("choose train");
                int choice = sc.nextInt();
                if (choice > availableTrains.size()) {
                    throw new InputMismatchException("");
                }
                String prompt = utility.cmdPrompt("Are you sure to remove").toUpperCase(Locale.ROOT);
                if(prompt.equals("YES")){
                    sm.removeService(availableTrains.get(choice-1));
                }
                this.login();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
            }
        }
    }

    public void holdService(){
        this.showAllServices();
        this.done = false;
        while (!this.done) {
            try {
                System.out.println("choose train");
                int choice = sc.nextInt();
                if (choice > availableTrains.size()) {
                    throw new InputMismatchException("");
                }
                String prompt = utility.cmdPrompt("Are you sure to hold").toUpperCase(Locale.ROOT);
                if(prompt.equals("YES")){
                    sm.holdService(availableTrains.get(choice-1));
                }
                this.login();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
            }
        }
    }

    public void resumeService(){
        availableTrains = sm.getInactiveTrains();
        int siNo = 1;
        for (PassengerTrain train : availableTrains) {
            System.out.print(siNo + ". " + train.getName() + " ");
            System.out.print(train.getTrainSchedule() + " ");
            System.out.print(train.getRoutes() + " ");
            System.out.println();
            siNo++;
        }
        this.done = false;
        while (!this.done) {
            try {
                System.out.println("choose train");
                int choice = sc.nextInt();
                if (choice > availableTrains.size()) {
                    throw new InputMismatchException("");
                }
                String prompt = utility.cmdPrompt("Are you sure to resume?").toUpperCase(Locale.ROOT);
                if(prompt.equals("YES")){
                    sm.resumeService(availableTrains.get(choice-1));
                }
                    this.login();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
            }
        }

    }

    public void showAvailableServices(UserAccount user){
        if(sm.getTrainList().size() == 0){
            System.out.println("No trains available, contact admin");
            this.logOut();
        }else {
            Scanner sc = new Scanner(System.in);
            this.done = false;
            while (!this.done) {
                try {
                    System.out.println("From");
                    String from = utility.getStringInput().toUpperCase(Locale.ROOT);
                    if(!sm.isStationAvailable(from)){
                        System.out.println("only following stations available");
                        sm.printFromLocations();
                        throw new InputMismatchException("");
                    }else{
                        from = sm.getStationFromSubstring(from);
                    }

                    System.out.println("To");
                    String to = utility.getStringInput().toUpperCase(Locale.ROOT);
                    if(!sm.isStationAvailable(to)){
                        System.out.println("only following stations available");
                        sm.printToLocations();
                        throw new InputMismatchException("");
                    }else{
                        to = sm.getStationFromSubstring(to);
                    }

                    if(from.equals(to)){
                        throw new InputMismatchException("from and to cannot be same");
                    }

                    System.out.println("1.Today\n2.Tomorrow\n3.Enter Date Manually");
                    int todayFlag = 0;
                    int choice = 0;

                    choice = sc.nextInt();
                    if (choice > 3) {
                        throw new InputMismatchException("invalid");
                    } else {
                        switch (choice) {
                            case 1: {
                                todayFlag = 1;
                                availableTrains = sm.showAvailableServices(from,to,user.getSessionDay());
                                if (availableTrains.size() > 0) {
                                    int siNo = 1;
                                    for (PassengerTrain train : availableTrains) {
                                        System.out.print(siNo + ". " + train.getName() + " ");
                                        System.out.print("Departure" + train.getSchedulebyDay(user.getSessionDay()) + " ");
                                        System.out.print(train.getRoutes() + " ");
                                        System.out.println();
                                        siNo++;
                                    }
                                    Ticket ticket = new Ticket(utility.getCurrentDate(), from, to);
                                    this.bookTicket(availableTrains, user, ticket, todayFlag);
                                } else {
                                    System.out.println("Sorry, No trains available on selected day, try another day");
                                    this.showAvailableServices(user);
                                }
                                this.done = true;
                                return;
                            }
                            case 2: {
                                todayFlag = 0;
                                availableTrains = sm.showAvailableServices(from,to,utility.getNextDay());
                                if (availableTrains.size() > 0) {
                                    int siNo = 1;
                                    for (PassengerTrain train : availableTrains) {
                                        System.out.print(siNo + ". " + train.getName() + " ");
                                        System.out.print("Departure" + train.getSchedulebyDay(utility.getNextDay()) + " ");
                                        System.out.print(train.getRoutes() + " ");
                                        System.out.println();
                                        siNo++;
                                    }
                                    Ticket ticket = new Ticket(utility.getCurrentDate(), from, to);
                                    this.bookTicket(availableTrains, user, ticket, todayFlag);
                                } else {
                                    System.out.println("Sorry, No trains available on selected day, try another day");
                                    this.showAvailableServices(user);
                                }
                                this.done = true;
                                return;
                            }
                            case 3: {
                                todayFlag = 0;
                                done = false;
                                System.out.println("Enter date(format dd/mm/yyyy)");
                                while (!this.done) {
                                    try {
                                        String travelDate = utility.getStringInput();
                                        if (!travelDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                                            throw new InputMismatchException("invalid");
                                        } else {
                                            availableTrains = sm.showAvailableServices(from,to,utility.getDayByDate(travelDate));
                                            if (availableTrains.size() > 0) {
                                                int siNo = 1;
                                                for (PassengerTrain train : availableTrains) {
                                                    System.out.print(siNo + ". " + train.getName() + " ");
                                                    System.out.print("Departure" + train.getSchedulebyDay(user.getSessionDay()) + " ");
                                                    System.out.print(train.getRoutes() + " ");
                                                    System.out.println();
                                                    siNo++;
                                                }
                                                Ticket ticket = new Ticket(utility.getCurrentDate(), from, to);
                                                this.bookTicket(availableTrains, user, ticket, todayFlag);
                                            } else {
                                                System.out.println("Sorry, No trains available, contact admin");
                                                this.showAvailableServices(user);
                                            }
                                            done = true;
                                            return;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid date format, Enter again");
//                                    sc.next();
                                    }
                                }
                                this.done = true;
                                return;
                            }
                        }
                    }
                } catch (InputMismatchException | ParseException e) {
                    System.out.println("Please enter correct choice");
//                sc.next();
                }
            }
        }
        }


    public void addService() {
        this.done = false;
        while (!this.done) {
            try{
            System.out.println("Enter train number");
            Scanner sc = new Scanner(System.in);
            String trainNum = utility.getStringInput();
            System.out.println("Enter train name");
            String trainName = sc.nextLine().toUpperCase(Locale.ROOT);
            System.out.println("Enter Train Capacity");
            int trainCapacity = sc.nextInt();
            ArrayList<String> trainSeats = utility.generateSeats(trainCapacity);
            System.out.println("Enter starting leg");
            String startLeg = utility.getStringInput();
            System.out.println("Enter ending leg");
            String endLeg = utility.getStringInput();
            PassengerTrain newTrain = new PassengerTrain(trainName, trainNum, startLeg, endLeg);
            newTrain.setAvailableSeats(trainSeats);
            System.out.println("Enter number of days availabe in a week");
            int daysCount = sc.nextInt();
            HashMap<String, String> timeTable = new HashMap<>();
            int i = 1;
            while (i < daysCount + 1) {
                System.out.println("Enter day");
                String day = utility.getStringInput().toUpperCase(Locale.ROOT);
                System.out.println("Enter time (HH:mm)");
                String time = utility.getStringInput();

                String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(time);
                if(!m.matches()){
                    throw new InputMismatchException("time format wrong");
                }
//                if (!time.matches("(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]")) {
//                    throw new InputMismatchException("time format wrong");
//                }
                timeTable.put(day, time);
            i++;
            }
            newTrain.setTotalSeats(trainCapacity);
            newTrain.setTrainSchedule(timeTable);
            System.out.println("Enter no of Station stops");
            int routesCount = sc.nextInt();
            ArrayList<String> routes = new ArrayList<>();
            i = 1;
            while (i < routesCount + 1) {
                System.out.println("Enter route");
                String route = utility.getStringInput().toUpperCase(Locale.ROOT);
                routes.add(route);
                i++;
            }
            newTrain.setRoutes(routes);
            sm.addService(newTrain);
            System.out.println("Train added successfully");
            this.done = true;
            this.login();
        }catch(InputMismatchException e){
                System.out.println("Please enter valid input");
            }
    }
    }

    public void showTickets(UserAccount user){
        if(user.getMyTickets().size() > 0) {
            ArrayList<Ticket> myTicket = user.getMyTickets();
            int index = 1;
            for (Ticket ticket : myTicket) {
                System.out.print(index + ". " + ticket.getPnrNumber() + " ");
                System.out.print(ticket.getTrainName() + " ");
                System.out.print(ticket.getTicketDate() + " ");
                System.out.print(ticket.getFrom() + "to" + ticket.getTo());
                System.out.print(ticket.getDepartureTime() + " ");
                System.out.print(ticket.getPassengerList() + " ");
                System.out.print(ticket.getSeatMap()+ " ");
                System.out.println(" ");
                index++;
                // have to print seat number also;
//                this.welcomeMenu(user);
            }
            this.welcomeMenu(user);
        }else{
            System.out.println("No active tickets");
            this.welcomeMenu(user);
        }
    }

    public void logOut(){
        authFactor = 0;
        this.landingMenu();
    }


    public void cancelTicket(UserAccount user){
        if(user.getMyTickets().size() > 0) {
            this.showTickets(user);
            System.out.println("Choose ticket to cancel");
            int choice = sc.nextInt();
            Ticket ticketToCancel = user.getMyTickets().get(choice - 1);
            if (ticketToCancel.getStatus().equals("CONFIRMED")) {
                PassengerTrain trainToCancel = sm.getTrainByNumber(ticketToCancel.getTrainNumber());
                user.removeTickets(choice - 1);
                bm.cancelTicket(trainToCancel, ticketToCancel, ticketToCancel.getPassengerList().size());
            } else {
                System.out.println("Waiting List tickets cannot be cancelled");
            }
            this.welcomeMenu(user);
        }else{
            System.out.println("No tickets to cancel.");
            this.welcomeMenu(user);
        }
    }

    public void bookTicket(ArrayList<PassengerTrain> availableTrains, UserAccount user, Ticket ticket,int flag) throws ParseException {

        System.out.println("choose a service");
        int arrayLength = availableTrains.size();
        int choice = 1;
        try{
            choice = sc.nextInt();
            if(choice > arrayLength){
                throw new InputMismatchException("invalid");
            }
        }
        catch(InputMismatchException e){
            System.out.println("please input correct choice");
//            sc.next();
        }
            PassengerTrain chosenTrain = availableTrains.get(choice - 1);
            ArrayList<Ticket> finalTickets = bm.bookTicket(chosenTrain,user,ticket,flag);
            System.out.println(finalTickets.size());
            user.setMyTickets(finalTickets);
            int index = 1;
            for(Ticket myTicket : finalTickets){
                System.out.print(index+". " + ticket.getPnrNumber() + " ");
                System.out.print(ticket.getTrainName() + " ");
                System.out.print(ticket.getFrom() + "to" + ticket.getTo());
                System.out.print(ticket.getTicketDate() + " ");
                System.out.print(ticket.getDepartureTime() + " ");
                System.out.print(ticket.getPassengerList() +" ");
                System.out.print(ticket.getSeatMap()+ " ");
                System.out.println(" ");
                index++;
            }
            this.welcomeMenu(user);

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        RailwayUtilities utility = new RailwayUtilities();
        ReservationSystemMain rs = new ReservationSystemMain();

        // static train added ##############################################################
        PassengerTrain train = new PassengerTrain("JanShathapthi","342325345","mayiladuthurai","coimbatore");
        ArrayList<String> routes = new ArrayList<>();
        routes.add("MAYILADUTHURAI");
        routes.add("KUMBAKONAM");
        routes.add("TANJORE");
        routes.add("TRICHY");
        routes.add("ERODE");
        routes.add("COIMBATORE");
        train.setRoutes(routes);
        train.setAvailableSeats(utility.generateSeats(20));
        train.setTotalSeats(20);
        HashMap<String,String> schedule = new HashMap<>();
        schedule.put("MONDAY","15:30");
        schedule.put("TUESDAY","15:30");
        schedule.put("WEDNESDAY","15:30");
        schedule.put("THURSDAY","15:30");
        schedule.put("FRIDAY","15:30");
        schedule.put("SATURDAY","15:30");
        schedule.put("SUNDAY","15:30");
        train.setTrainSchedule(schedule);
        rs.sm.addService(train);
        // static codes ##############################################################

        rs.landingMenu();

    }


}
