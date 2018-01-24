public class Customer {
    private String name;
    private CellPhone cellPhone;
    private PhonePlan plan;
    private int callsMade;
    private float balance;

    public Customer(String n, CellPhone c, PhonePlan p) {
        name = n;
        cellPhone = c;
        plan = p;
        if (plan.isPlanType())
            balance = 0.40f*(plan.getMinutesAllowed());
        else
            balance = 0;
    }

    public String getName() {
        return name;
    }
    public CellPhone getCellPhone() {
        return cellPhone;
    }
    public PhonePlan getPlan() {
        return plan;
    }

    public void setName(String n) {
        name = n;
    }
    public void setCellPhone(CellPhone c) {
        cellPhone = c;
    }
    public void setPlan(PhonePlan p) {
        plan = p;
    }

    public String toString() {
        String result;

        result = name + " with a " + cellPhone.getManufacturer() + " " + cellPhone.getModel() + " on a " + plan.toString();
        return result;
    }

    public void phone(Customer c, int callLength) {
        int mins1 = this.plan.getMinutesUsed(); //Holds minutes used for the person calling
        int mins2 = c.plan.getMinutesUsed(); //Holds minutes used for the person recieving the call
        if (this.plan.isPlanType() && c.plan.isPlanType()) {
            if (this.plan.getMinutesRemaining() >= callLength && c.plan.getMinutesRemaining() >= callLength) {
                mins1 += callLength;
                this.plan.setMinutesUsed(mins1);
                mins2 += callLength;
                c.plan.setMinutesUsed(mins2);

                this.callsMade++;
                c.callsMade++;
            }
        }
        else if (!this.plan.isPlanType() && c.plan.isPlanType()) {
            if (c.plan.getMinutesRemaining() >= callLength) {
                mins1 += callLength;
                this.plan.setMinutesUsed(mins1);
                mins2 += callLength;
                c.plan.setMinutesUsed(mins2);

                this.callsMade++;
                c.callsMade++;
            }
        }
        else if (this.plan.isPlanType() && !c.plan.isPlanType()) {
            if (this.plan.getMinutesRemaining() >= callLength) {
                mins1 += callLength;
                this.plan.setMinutesUsed(mins1);
                mins2 += callLength;
                c.plan.setMinutesUsed(mins2);

                this.callsMade++;
                c.callsMade++;
            }
        }
        else {
            mins1 += callLength;
            this.plan.setMinutesUsed(mins1);
            mins2 += callLength;
            c.plan.setMinutesUsed(mins2);

            this.callsMade++;
            c.callsMade++;
        }
    }

    public void buyMinutes(int minsPurchased) {
        int minsIncreased = this.plan.getMinutesAllowed(); //Holds for # of current minutes

        if (this.plan.isPlanType()) {
            balance += 0.40f*minsPurchased;
            minsIncreased += minsPurchased;
            this.plan.setMinutesAllowed(minsIncreased);
        }
    }

    public void accessInternet(int kbUsed) {
        int dataUsed = this.plan.getDataUsed();

        if (!this.plan.isPlanType()) {
            dataUsed += kbUsed;
            this.plan.setDataUsed(dataUsed);
        }
        else {
            if (this.plan.getDataRemaining() >= kbUsed) {
                dataUsed += kbUsed;
                this.plan.setDataUsed(dataUsed);
            }

            else if (this.plan.getDataRemaining() < kbUsed && this.plan.getDataRemaining() > 0) {
                this.plan.setDataUsed(this.plan.getDataRemaining());
            }
        }
    }

    public void printMonthlyStatement() {
        int overCharge= 0; //Holds over charged data or minutes
        float overMinutes= 0; //Calculates amount for over charged minutes
        float overData = 0; //Calculates amount for over charged data
        float hst = 0;
        float dataPlan = (float)this.plan.getDataAllowed()/1000000;

        if (!this.plan.isPlanType()) {
            System.out.println(String.format("%-25s", "Name:") + this.name);
            System.out.println(String.format("%-25s", "Plan Type:") + "Regular Monthly" + "(" +
                    this.plan.getMinutesAllowed() + " minutes, " + String.format("%1.1f", dataPlan) + "GB data)");
            System.out.println(String.format("%-25s", "Minutes Used:") + this.plan.getMinutesUsed());
            System.out.println(String.format("%-25s", "Data Used:") + this.plan.getDataUsed());
            System.out.println(String.format("%-25s", "Calls Made:") + this.callsMade);

            if (this.plan.getMinutesAllowed() == 100) {
                this.balance += 15.00f + 10.00f*dataPlan;
                System.out.println(String.format("%-25s", "Monthly Charges:") + String.format("$%1.2f", this.balance));
            }
            else {
                this.balance += 25.00f + 10.00f*dataPlan;
                System.out.println(String.format("%-25s", "Monthly Charges:") + String.format("$%1.2f", this.balance));
            }

            if (this.plan.getMinutesUsed() > this.plan.getMinutesAllowed()) {
                overCharge = this.plan.getMinutesUsed() - this.plan.getMinutesAllowed();
                overMinutes = 0.15f*overCharge;
                this.balance += overMinutes;
                System.out.println(String.format("%-25s", "Voice Overtime Charges:") + String.format("$%1.2f", overMinutes));
            }
            else {
                overMinutes = 0.00f;
                System.out.println(String.format("%-25s", "Voice Overtime Charges:") + String.format("$%1.2f", overMinutes));
            }

            if (this.plan.getDataUsed() > this.plan.getDataAllowed()) {
                overCharge = this.plan.getDataUsed() - this.plan.getDataAllowed();
                overData = 0.00005f*overCharge;
                this.balance += overData;
                System.out.println(String.format("%-25s", "Data Overusage Charges:") + String.format("$%1.2f", overData));
            }
            else {
                overData = 0.00f;
                System.out.println(String.format("%-25s", "Data Overusage Charges:") + String.format("$%1.2f", overData));
            }

            hst = 0.13f*this.balance;
            System.out.println(String.format("%-25s", "HST:") + String.format("$%1.2f", hst));

            this.balance += hst;
            System.out.println(String.format("%-25s", "Total Due:") + String.format("$%1.2f", this.balance) + "\n");
        }

        else {
            System.out.println(String.format("%-25s", "Name:") + this.name);
            System.out.println(String.format("%-25s", "Plan Type:") + "Pay-as-you-go");
            System.out.println(String.format("%-25s", "Minutes Used:") + this.plan.getMinutesUsed());
            System.out.println(String.format("%-25s", "Minutes Remaining:") + this.plan.getMinutesRemaining());
            System.out.println(String.format("%-25s", "Data Used:") + this.plan.getDataUsed());
            System.out.println(String.format("%-25s", "Data Remaining:") + this.plan.getDataRemaining());
            System.out.println(String.format("%-25s", "Calls Made:") + this.callsMade);
            System.out.println(String.format("%-25s", "Monthly Charges:") + String.format("$%1.2f", this.balance));

            hst = 0.13f*this.balance;
            System.out.println(String.format("%-25s", "HST:") + String.format("$%1.2f", hst));

            this.balance += hst;
            System.out.println(String.format("%-25s", "Total Due:") + String.format("$%1.2f", this.balance) + "\n");
        }
    }
}
