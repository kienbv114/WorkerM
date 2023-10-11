package common;

import java.util.ArrayList;
import java.util.Scanner;
import model.WorkerModel;

public class Validation {

    Scanner scanner = new Scanner(System.in);

    public int checkInputIntLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(scanner.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    public String checkInputString() {
        while (true) {
            String result = scanner.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public boolean checkIdExist(ArrayList<WorkerModel> workers, String id) {
        for (WorkerModel worker : workers) {
            if (id.equalsIgnoreCase(worker.getId())) {
                return true;
            }
        }
        return false;
    }

    public int checkInputSalary() {
        while (true) {
            try {
                int result = Integer.parseInt(scanner.nextLine().trim());
                if (result < 0) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Salary must be greater than 0");
                System.out.print("Enter again: ");
            }
        }
    }

    public boolean checkWorkerExist(ArrayList<WorkerModel> workers, String id, String name, int age, int salary, String workLocation) {
        for (WorkerModel worker : workers) {
            if (id.equalsIgnoreCase(worker.getId())
                    && name.equalsIgnoreCase(worker.getName())
                    && age == worker.getAge()
                    && salary == worker.getSalary()) {
                return false;
            }
        }
        return true;
    }
}
