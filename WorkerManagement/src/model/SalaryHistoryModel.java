package model;

public class SalaryHistoryModel extends WorkerModel implements Comparable<SalaryHistoryModel> {

    private String status;
    private String date;

    public SalaryHistoryModel() {
    }

    public SalaryHistoryModel(String status, String date, String id, String name, int age, int salary, String workLocation) {
        super(id, name, age, salary, workLocation);
        this.status = status;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(SalaryHistoryModel t) {
        return this.getId().compareTo(t.getId());
    }
}
