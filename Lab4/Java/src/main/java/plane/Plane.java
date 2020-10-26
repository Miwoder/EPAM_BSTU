package plane;

import java.util.Objects;

public abstract class Plane {
    private String planeModel;
    private int maximumSpeed;
    private int maximumFlightDistance;
    private int maximumLoadCapacity;

    public Plane(String planeModel, int maximumPlaneSpeed, int maximumPlaneFlightDistance, int maximumPlaneLoadCapacity) {
        this.planeModel = planeModel;
        this.maximumSpeed = maximumPlaneSpeed;
        this.maximumFlightDistance = maximumPlaneFlightDistance;
        this.maximumLoadCapacity = maximumPlaneLoadCapacity;
    }

    public String getPlaneModel() {
        return planeModel;
    }

    public int getMaximumPlaneSpeed() {
        return maximumSpeed;
    }

    public int getMaximumSpeed() {
        return maximumSpeed;
    }

    public int getMaximumFlightDistance() {
        return maximumFlightDistance;
    }

    public int getPlaneMaximumFlightDistance() {
        return maximumFlightDistance;
    }

    public int getMaximumLoadCapacity() {
        return this.maximumLoadCapacity;
    }

    @Override
    public String toString() {
        return "Plane: " +
                "model='" + planeModel +
                ", maxSpeed=" + maximumSpeed +
                ", maxFlightDistance=" + maximumFlightDistance +
                ", maxLoadCapacity=" + maximumLoadCapacity +
                '}';
    }

    @Override
    public boolean equals(Object plane) {
        if (this == plane) return true;
        if (!(plane instanceof Plane)) return false;
        Plane currentPlane = (Plane) plane;
        return maximumSpeed == currentPlane.maximumSpeed &&
                maximumFlightDistance == currentPlane.maximumFlightDistance &&
                maximumLoadCapacity == currentPlane.maximumLoadCapacity &&
                Objects.equals(planeModel, currentPlane.planeModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planeModel, maximumSpeed, maximumFlightDistance, maximumLoadCapacity);
    }
}
