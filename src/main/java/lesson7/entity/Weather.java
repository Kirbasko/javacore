package lesson7.entity;

public class Weather {
    private String city;
    private String date;
    private double tempMax;
    private double tempMin;

    public Weather(String city, String date, double tempMax, double tempMin) {
        this.city = city;
        this.date = date;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", tempMax=" + tempMax +
                ", tempMin=" + tempMin +
                '}';
    }
}
