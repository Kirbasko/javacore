package lesson7;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        UserInterfaceView userInterfaceView = new UserInterfaceView();

        userInterfaceView.runInterface();
    }
}