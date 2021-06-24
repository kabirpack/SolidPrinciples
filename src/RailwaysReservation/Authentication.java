package RailwaysReservation;

import java.util.HashMap;

public class Authentication {
    private HashMap<String, String> credential = new HashMap<>();
    private boolean isAdmin;

    public Authentication() {
        credential.put("admin","admin");
    }

    public void register(String username, String password){
        credential.put(username,password);

    }

    public int authenicate(String username, String password){
        isAdmin = false;
        for(String key : credential.keySet()){
            System.out.println(key);

            if(key.equals(username)){
                if(credential.get(username).equals(password)){
                    if(username.equals("admin")){
                        isAdmin = true;
                    }
                    return 1;
                }
            }
        }
        return 2;
    }

    public HashMap<String, String> getCredential() {
        return credential;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
