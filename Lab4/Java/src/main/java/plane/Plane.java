package plane;

import java.util.Objects;

public abstract class Plane {
    private String model;
    private int maximumSpeed;
    private int maximumFlightDistance;
    private int maximumLoadCapacity;

    public Plane(String planeModel, int maximumPlaneSpeed, int maximumPlaneFlightDistance, int maximumPlaneLoadCapacity) {
        this.model = planeModel;
        this.maximumSpeed = maximumPlaneSpeed;
        this.maximumFlightDistance = maximumPlaneFlightDistance;
        this.maximumLoadCapacity = maximumPlaneLoadCapacity;
    }

    public String getModel() {
        return model;
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
                "model='" + model +
                ", maxSpeed=" + maximumSpeed +
                ", maxFlightDistance=" + maximumFlightDistance +
                ", maxLoadCapacity=" + maximumLoadCapacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return maximumSpeed == plane.maximumSpeed &&
                maximumFlightDistance == plane.maximumFlightDistance &&
                maximumLoadCapacity == plane.maximumLoadCapacity &&
                Objects.equals(model, plane.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, maximumSpeed, maximumFlightDistance, maximumLoadCapacity);
    }
}
