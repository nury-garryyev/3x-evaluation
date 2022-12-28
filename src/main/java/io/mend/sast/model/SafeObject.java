package io.mend.sast.model;

public class SafeObject {

    private long max;
    private long min;

    public SafeObject() {
    }

    public SafeObject(long max, long min) {
        this.max = max;
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "SafeObject{" +
                "max=" + max +
                ", min=" + min +
                '}';
    }
}
