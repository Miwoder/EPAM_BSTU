// version: 1.1
// made by Vitali Shulha
// 4-Jan-2019
import plane.ExperimentalPlane;
import models.MilitaryType;
import plane.MilitaryPlane;
import plane.PassengerPlane;
import plane.Plane;

import java.util.*;

public class Airport {
    private List<? extends Plane> listOfAllPlanes;

    public Airport(List<? extends Plane> planes)
    {
        this.listOfAllPlanes = planes;
    }

    public List<PassengerPlane> getPassengerPlanesList()
    {
        List<? extends Plane> listOfAllPlanes = this.listOfAllPlanes;
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        for (Plane plane : listOfAllPlanes)
        {
            if (plane instanceof PassengerPlane)
            {
                passengerPlanes.add((PassengerPlane) plane);
            }
        }
        return passengerPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanes()
    {
        List<MilitaryPlane> militaryPlanesList = new ArrayList<>();
        for (Plane plane : listOfAllPlanes)
        {
            if (plane instanceof MilitaryPlane)
            {
                militaryPlanesList.add((MilitaryPlane) plane);
            }
        }
        return militaryPlanesList;
    }

    public PassengerPlane getPassengerPlaneWithMaximumPassengersCapacity()
    {
        List<PassengerPlane> passengerPlanesList = getPassengerPlanesList();
        PassengerPlane planeWithMaxCapacity = passengerPlanesList.get(0);
        for (PassengerPlane passengerPlane : passengerPlanesList) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }
        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes()
    {
        List<MilitaryPlane> transportMilitaryPlanesList = new ArrayList<>();
        List<MilitaryPlane> militaryPlaneList = getMilitaryPlanes();
        for (MilitaryPlane plane : militaryPlaneList) {
            if (plane.getMilitaryType() == MilitaryType.TRANSPORT) {
                transportMilitaryPlanesList.add(plane);
            }
        }
        return transportMilitaryPlanesList;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes()
    {
        List<MilitaryPlane> bomberMilitaryPlanesList = new ArrayList<>();
        List<MilitaryPlane> militaryPlaneList = getMilitaryPlanes();
        for (MilitaryPlane plane : militaryPlaneList) {
            if (plane.getMilitaryType() == MilitaryType.BOMBER) {
                bomberMilitaryPlanesList.add(plane);
            }
        }
        return bomberMilitaryPlanesList;
    }

    public List<ExperimentalPlane> getExperimentalPlanes()
    {
        List<ExperimentalPlane> experimentalPlanesList = new ArrayList<>();
        for (Plane plane : listOfAllPlanes)
        {
            if (plane instanceof ExperimentalPlane)
            {
                experimentalPlanesList.add((ExperimentalPlane) plane);
            }
        }
        return experimentalPlanesList;
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
        for (Plane plane : collection) {
            System.out.println(plane);
        }
    }

    @Override
    public String toString()
    {
        return "Airport{" +
                "Planes=" + listOfAllPlanes.toString() +
                '}';
    }



}
