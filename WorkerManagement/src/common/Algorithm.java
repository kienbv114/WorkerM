package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import model.SalaryHistoryModel;
import model.WorkerModel;

public class Algorithm {

    Validation validation = new Validation();
    ArrayList<WorkerModel> workers= new ArrayList<>();
    ArrayList<SalaryHistoryModel> histories = new ArrayList<>();

    public void addWorker() {
        System.out.println("--------- Add Worker ----------");
        System.out.print("Enter code: ");
        String id = validation.checkInputString();
        System.out.print("Enter name: ");
        String name = validation.checkInputString();
        System.out.print("Enter age: ");
        int age = validation.checkInputIntLimit(18, 50);
        System.out.print("Enter salary: ");
        int salary = validation.checkInputSalary();
        System.out.print("Enter work location: ");
        String workLocation = validation.checkInputString();
        if (!validation.checkWorkerExist(workers, id, name, age, salary, workLocation)) {
            System.err.println("Duplicate.");
        } else {
            workers.add(new WorkerModel(id, name, age, salary, workLocation));
            System.err.println("Add success.");
        }
    }

    public void changeSalary(int status) {
        if (workers.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.println("------- Up/Down Salary --------");
        System.out.print("Enter code: ");
        String id = validation.checkInputString();
        WorkerModel worker = getWorkerByCode(id);
        if (worker == null) {
            System.err.println("Not exist worker.");
        } else {
            int salaryCurrent = worker.getSalary();
            int salaryUpdate;
            if (status == 1) {
                System.out.print("Enter salary: ");
                while (true) {
                    salaryUpdate = validation.checkInputSalary();
                    if (salaryUpdate <= salaryCurrent) {
                        System.err.println("Must be greater than current salary.");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                histories.add(new SalaryHistoryModel("UP", getCurrentDate(), worker.getId(),
                        worker.getName(), worker.getAge(), salaryUpdate,
                        worker.getWorkLocation()));
            } else {
                System.out.print("Enter salary: ");
                while (true) {
                    salaryUpdate = validation.checkInputSalary();
                    if (salaryUpdate >= salaryCurrent) {
                        System.err.println("Must be smaller than current salary.");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                histories.add(new SalaryHistoryModel("DOWN", getCurrentDate(), worker.getId(),
                        worker.getName(), worker.getAge(), salaryUpdate,
                        worker.getWorkLocation()));
            }
            worker.setSalary(salaryUpdate);
            System.err.println("Update success");
        }
    }

    public void printListHistory() {
        if (histories.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.println("--------------------Display Information Salary-----------------------");
        System.out.printf("%-5s%-15s%-5s%-10s%-10s%-20s\n", "Code", "Name", "Age",
                "Salary", "Status", "Date");
        Collections.sort(histories);
        for (SalaryHistoryModel history : histories) {
            printHistory(history);
        }
    }

    public WorkerModel getWorkerByCode(String id) {
        for (WorkerModel worker : workers) {
            if (id.equalsIgnoreCase(worker.getId())) {
                return worker;
            }
        }
        return null;
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    public void printHistory(SalaryHistoryModel history) {
        System.out.printf("%-5s%-15s%-5d%-10d%-10s%-20s\n", history.getId(),
                history.getName(), history.getAge(), history.getSalary(),
                history.getStatus(), history.getDate());
    }
}
