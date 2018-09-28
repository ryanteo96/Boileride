package DTO;

import org.joda.time.DateTime;

public class RideOfferSearchRequest {
    private String user;
    private String origin;
    private double originProximity;
    private String destination;
    private double destinationProximity;
    private DateTime departure;
    private int departureProximity;
    private int nrides;
    private int passengers;
    private int luggage;
    private boolean smoking;
    private boolean foodndrink;
    private boolean pets;
    private boolean ac;

    public RideOfferSearchRequest() {

    }

    public RideOfferSearchRequest(String user,
                                  String origin,
                                  double originProximity,
                                  String destination,
                                  double destinationProximity,
                                  DateTime departure,
                                  int departureProximity,
                                  int nrides,
                                  int passengers,
                                  int luggage,
                                  boolean smoking,
                                  boolean foodndrink,
                                  boolean pets,
                                  boolean ac) {
        this.user = user;
        this.origin = origin;
        this.originProximity = originProximity;
        this.destination = destination;
        this.destinationProximity = destinationProximity;
        this.departure = departure;
        this.departureProximity = departureProximity;
        this.nrides = nrides;
        this.passengers = passengers;
        this.luggage = luggage;
        this.smoking = smoking;
        this.foodndrink = foodndrink;
        this.pets = pets;
        this.ac = ac;
    }

    public String getUser() {
        return user;
    }

    public String getOrigin() {
        return origin;
    }

    public double getOriginProximity() {
        return originProximity;
    }

    public String getDestination() {
        return destination;
    }

    public double getDestinationProximity() {
        return destinationProximity;
    }

    public DateTime getDeparture() {
        return departure;
    }

    public int getDepartureProximity() {
        return departureProximity;
    }

    public int getNrides() {
        return nrides;
    }

    public int getPassengers() {
        return passengers;
    }

    public int getLuggage() {
        return luggage;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public boolean isFoodndrink() {
        return foodndrink;
    }

    public boolean isPets() {
        return pets;
    }

    public boolean isAc() {
        return ac;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setOriginProximity(double originProximity) {
        this.originProximity = originProximity;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDestinationProximity(double destinationProximity) {
        this.destinationProximity = destinationProximity;
    }

    public void setDeparture(DateTime departure) {
        this.departure = departure;
    }

    public void setDepartureProximity(int departureProximity) {
        this.departureProximity = departureProximity;
    }

    public void setNrides(int nrides) {
        this.nrides = nrides;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public void setFoodndrink(boolean foodndrink) {
        this.foodndrink = foodndrink;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public String toString() {
        return "Origin: " + origin +
                ", Origin Proximity: " + originProximity +
                ", Destination: " + destination +
                ", Destination Proximity: " + destinationProximity +
                ", Departure: " + departure.toString() +
                ", Departure Proximity: " + departureProximity +
                ", Number of Rides: " + nrides +
                ", Number of Passengers: " + passengers +
                ", Number of Luggage: " + luggage +
                ", Allow Smoking: " + smoking +
                ", Allow Food and Drink: " + foodndrink +
                ", Allow Pets: " + pets +
                ", Has AC: " + ac;
    }
}
