// version: 1.1
// made by Vitali Shulha
// 4-Jan-2019
import plane.ExperimentalPlane;
import models.MilitaryType;
import plane.MilitaryPlane;
import plane.PassengerPlane;
import plane.Plane;

import java.util.*;
import java.util.stream.Collectors;

public class Airport {
    private List<? extends Plane> listOfAllPlanes;

    public Airport(List<? extends Plane> planes)
    {
        this.listOfAllPlanes = planes;
    }

    public List<PassengerPlane> getPassengerPlanesList()
    {
        return listOfAllPlanes.stream().filter(p -> p instanceof PassengerPlane).map(p -> (PassengerPlane)p).collect(Collectors.toList());
    }

    public List<MilitaryPlane> getMilitaryPlanes()
    {
        return listOfAllPlanes.stream()
                .filter(p -> p instanceof MilitaryPlane)
                .map(p -> (MilitaryPlane)p)
                .collect(Collectors.toList());
    }

    public Optional<PassengerPlane> getPassengerPlaneWithMaxPassengersCapacity()
    {
        return listOfAllPlanes.stream()
                .filter(p -> p instanceof PassengerPlane)
                .map(p -> (PassengerPlane)p)
                .max(Comparator.comparingInt(PassengerPlane::getPassengersCapacity));
    }

    public PassengerPlane getListPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (PassengerPlane passengerPlane : passengerPlanes) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }
        return planeWithMaxCapacity;
    }

    private List<PassengerPlane> getPassengerPlanes() {
        return listOfAllPlanes.stream().filter(p -> p instanceof PassengerPlane)
                .map(p -> (PassengerPlane)p)
                .collect(Collectors.toList());
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes()
    {
        return listOfAllPlanes.stream()
                .filter(p -> p instanceof MilitaryPlane)
                .map(p -> (MilitaryPlane)p)
                .filter(p -> p.getMilitaryType() == MilitaryType.TRANSPORT)
                .collect(Collectors.toList());
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes()
    {
        return listOfAllPlanes.stream().filter(p -> p instanceof MilitaryPlane)
                .map(p -> (MilitaryPlane)p)
                .filter(p->p.getMilitaryType() == MilitaryType.BOMBER)
                .collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes()
    {
        return listOfAllPlanes.stream().filter(p -> p instanceof ExperimentalPlane)
                .map(p -> (ExperimentalPlane)p)
                .collect(Collectors.toList());
    }

    public List<? extends Plane> getListOfAllPlanes()
    {
        return listOfAllPlanes;
    }

    public Airport sortByMaxDistance()
    {
        listOfAllPlanes.sort(Comparator.comparingInt(Plane::getPlaneMaximumFlightDistance));
        return this;
    }

    public Airport sortByMaxSpeed()
    {
        listOfAllPlanes.sort(Comparator.comparingInt(Plane::getMaximumPlaneSpeed));
        return this;
    }

    public Airport sortByMaxLoadCapacity()
    {
        listOfAllPlanes.sort(Comparator.comparingInt(Plane::getMaximumLoadCapacity));
        return this;
    }

    private void print(Collection<? extends Plane> collection) {
        listOfAllPlanes.forEach(p -> System.out.println(p.toString()));
    }

    @Override
    public String toString()
    {
        return "Airport{" +
                "Planes=" + listOfAllPlanes.toString() +
                '}';
    }
}
