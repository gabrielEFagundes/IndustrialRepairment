package com.back;

import com.back.service.MainService;
import com.back.service.ParserAuxilier;
import com.back.view.MainView;

public class Main {
    public static void main(String[] args) {

        MainView view = new MainView();
        MainService service = new MainService();

        while(true){
            String choice = view.mainMenu();
            
            int numberedChoice = ParserAuxilier.parseInput(choice);

            service.programMenu(numberedChoice);
        }
    }
}