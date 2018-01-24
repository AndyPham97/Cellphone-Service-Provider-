public class PhonePlan {
    private int minutesAllowed;
    private int minutesUsed;
    private int dataAllowed;
    private int dataUsed;
    private boolean planType;

    public PhonePlan(int m, int d, boolean p) {
        minutesAllowed = m;
        dataAllowed = d;
        planType = p;
        minutesUsed = 0;
        dataUsed = 0;
    }

    public int getMinutesAllowed() {
        return minutesAllowed;
    }
    public int getMinutesUsed() {
        return minutesUsed;
    }
    public int getDataAllowed() {
        return dataAllowed;
    }
    public int getDataUsed() {
        return dataUsed;
    }
    public boolean isPlanType() {
        return planType;
    }

    public void setMinutesAllowed(int m) {
        minutesAllowed = m;
    }
    public void setMinutesUsed(int m) {
        minutesUsed = m;
    }
    public void setDataAllowed(int d) {
        dataAllowed = d;
    }
    public void setDataUsed(int d) {
        dataUsed = d;
    }
    public void setPlanType(boolean p) {
        planType = p;
    }

    public int getMinutesRemaining() {
        int minsRemaining = minutesAllowed - minutesUsed; //Calculates minutes remaining
        return minsRemaining;
    }
    public int getDataRemaining() {
        int dataRemaining = dataAllowed - dataUsed; //Calculates data remaining
        return dataRemaining;
    }

    public String toString() {
        String result;
        float data = (float)getDataAllowed();

        if (planType)
            result = "Pay-as-you-go Plan with " + getMinutesRemaining() + " minutes and " + getDataRemaining() + "KB remaining";
        else
            result = "Regular (" + getMinutesAllowed() + " minute, " + String.format("%1.1f", data/1000000) +
                    "GB data) Monthly plan with " + getMinutesRemaining() + " minutes remaining and " + getDataRemaining() + "KB remaining";
   return result;
    }
}
