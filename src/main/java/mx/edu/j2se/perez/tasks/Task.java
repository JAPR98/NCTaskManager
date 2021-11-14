package mx.edu.j2se.perez.tasks;

public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean isActive;
    private boolean isRepetitive;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.isActive = false;
        this.isRepetitive = false;
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.isActive = false;
        this.isRepetitive = true;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public int getTime(){
        if (this.isRepetitive) {
            return this.start;
        } else {
            return this.time;
        }
    }

    public void setTime(int time) {
        if (this.isRepetitive) {
            this.isRepetitive = false;
            this.start = 0;
            this.end = 0;
            this.interval = 0;
        }
        this.time = time;
    }

    public int getStartTime() {
        if (this.isRepetitive) {
            return this.start;
        } else {
            return this.time;
        }
    }

    public int getEndTime() {
        if (this.isRepetitive) {
            return this.end;
        } else {
            return this.time;
        }
    }

    public int getRepeatInterval() {
        if (this.isRepetitive) {
            return this.interval;
        } else {
            return 0;
        }
    }

    public void setTime(int start, int end, int interval) {
        if (this.isRepetitive) {;
            this.start = start;
            this.end = end;
            this.interval = interval;
        } else {
            this.isRepetitive = true;
            this.time = 0;
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
    }

    public boolean isRepeated(){
        return this.isRepetitive;
    }

    public int nextTimeAfter(int current){
        if (!this.isRepetitive || !this.isActive || (current >= this.end) ) {
            return -1;
        } else {
            return 1;
        }
    }
}
