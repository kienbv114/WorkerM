package controller;

import common.Algorithm;
import view.Menu;

public class WorkerController {

    private final String[] MAIN_MENU_ITEMS = {
        " Add worker",
        " Increase salary for worker",
        " Decrease for worker",
        " Show adjusted salary worker information",
        " Exit",};

    Algorithm algorithm = new Algorithm();

    Menu mainMenu = new Menu("======== Worker Management =========", MAIN_MENU_ITEMS) {
        @Override
        public void execute(int choice) {
            switch (choice) {
                case 1:
                    algorithm.addWorker();
                    break;
                case 2:
                    algorithm.changeSalary(1);
                    break;
                case 3:
                    algorithm.changeSalary(0);
                    break;
                case 4:
                    algorithm.printListHistory();
                    break;
                case 5:
                    System.exit(0);
                    break;

            }
        }
    };

    public void run() {
        mainMenu.run();
    }
}
