package org.jwolfe.quetzal.library.general;

import java.util.Objects;

public class Activity {
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    String activityId;


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    int start;


    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    int finish;


    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    int deadline;


    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    int profit;

    public Activity() {

    }

    public Activity(String id) {
        this();

        this.activityId = id;
    }

    public Activity(String id, int deadline, int profit) {
        this(id);

        this.deadline = deadline;
        this.profit = profit;
    }

    public Activity(int start, int finish) {
        this();

        this.start = start;
        this.finish = finish;
    }

    public Activity(int start, int finish, int profit) {
        this(start, finish);
        this.profit = profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(activityId, activity.activityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId='" + activityId + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", deadline=" + deadline +
                ", profit=" + profit +
                '}';
    }
}
