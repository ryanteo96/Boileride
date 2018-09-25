public class RidePreferences {
    public int nseats;
    public int nluggage;
    public boolean smoking;
    public boolean eating;
    public boolean pet;
    public boolean ac;

    public RidePreferences(int nseats, int nluggages, boolean smoking, boolean eating, boolean pet, boolean ac) {
        this.nseats = nseats;
        this.nluggage = nluggages;
        this.smoking = smoking;
        this.eating = eating;
        this.pet = pet;
        this.ac = ac;
    }

    public String toString() {
        return "number of seats: " + nseats +
                ", number of luggage: " + nluggage +
                ", smoking: " + smoking +
                ", eating: " + eating +
                ", pet: " + pet +
                ", ac: " + ac;
    }
}
