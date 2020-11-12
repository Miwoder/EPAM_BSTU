package plane;

import java.util.Objects;

public class PassengerPlane extends Plane{

    private int passengersCapacity;

    public PassengerPlane(String planeModel, int maximumSpeed, int maximumFlightDistance, int maximumLoadCapacity, int passengersCapacity) {
        super(planeModel, maximumSpeed, maximumFlightDistance, maximumLoadCapacity);
        this.passengersCapacity = passengersCapacity;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    @Override
    public String toString()
    {
        return super.toString().replace("}",
                ", passengersCapacity=" + passengersCapacity +
                '}');
    }

    @Override
    public boolean equals(Object plane) {
        if (this == plane) return true;
        if (!(plane instanceof PassengerPlane)) return false;
        if (!super.equals(plane)) return false;
        PassengerPlane currentPlane = (PassengerPlane) plane;
        return passengersCapacity == currentPlane.passengersCapacity;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), passengersCapacity);
    }

}
