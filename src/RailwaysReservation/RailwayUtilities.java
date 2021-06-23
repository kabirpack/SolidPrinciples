package RailwaysReservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class RailwayUtilities {

    public String generatePnrNumber(String ticketCategory){
        if(ticketCategory.equals("WL")){
            return "WLPNR" +(long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        }
        return "PNR" +(long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
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
        return format2.format(dt1);
    }

    public void twoCaseIntValidation(int choice, boolean done, Callable fun1,Callable fun2){
        Scanner sc = new Scanner(System.in);
        while(!done){
            try{
                choice = sc.nextInt();
                if(choice > 2){
                    throw new InputMismatchException("invalid");
                }else{
                    switch (choice) {
                        case 1: {
                            fun1.call();
                            done = true;
                            break;
                        }
                        case 2:{
                            fun2.call();
                            done = true;
                            break;
                        }
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter correct choice");
                sc.next();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
