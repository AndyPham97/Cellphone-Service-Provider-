public class CellPhone {
    private String model;
    private String manufacturer;
    private int monthsWarranty;
    private float price;

    public CellPhone() {
        model = "Unknown";
        manufacturer = "Unknown";
        monthsWarranty = 0;
        price = 0;
    }
    public CellPhone(String m, String m2, int m3, float p) {
        model = m;
        manufacturer = m2;
        monthsWarranty = m3;
        price = p;
    }

    public String getModel() {
        return model;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public int getMonthsWarranty() {
        return monthsWarranty;
    }
    public float getPrice() {
        return price;
    }

    public void setModel(String m) {
        model = m;
    }
    public void setManufacturer(String m) {
        manufacturer = m;
    }
    public void setMonthsWarranty(int m) {
        monthsWarranty = m;
    }
    public void setPrice(float p) {
        price = p;
    }

}
