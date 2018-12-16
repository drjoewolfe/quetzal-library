package org.jwolfe.quetzal.library.general;

public class Activity {
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    int start;
    int finish;

    public Activity(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }
}
