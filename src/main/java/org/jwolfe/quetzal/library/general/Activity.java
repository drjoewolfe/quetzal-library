package org.jwolfe.quetzal.library.general;

public class Activity {
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


    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    int profit;

    public Activity(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }

    public Activity(int start, int finish, int profit) {
        this(start, finish);
        this.profit = profit;
    }
}
