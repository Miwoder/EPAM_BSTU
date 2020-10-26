package plane;

import models.ClassificationLevel;
import models.ExperimentalTypes;

import java.util.Objects;

public class ExperimentalPlane extends Plane{

    private ExperimentalTypes experimentalType;
    private ClassificationLevel classificationLevel;


    public ExperimentalPlane(String planeModel, int maximumSpeed, int maximumFlightDistance, int maximumLoadCapacity,
                             ExperimentalTypes type, ClassificationLevel classificationLevel) {
        super(planeModel, maximumSpeed, maximumFlightDistance, maximumLoadCapacity);
        this.experimentalType = type;
        this.classificationLevel = classificationLevel;
    }

    public ExperimentalTypes getExperimentalType() {
        return experimentalType;
    }

    public ClassificationLevel getClassificationLevel(){
        return classificationLevel;
    }

    public void setClassificationLevel(ClassificationLevel classificationLevel){
        this.classificationLevel = classificationLevel;
    }

    public void setExperimentalType(ExperimentalTypes experimentalType) {
        this.experimentalType = experimentalType;
    }

    @Override
    public boolean equals(Object plane) {
        if (this == plane) return true;
        if (!(plane instanceof ExperimentalPlane)) return false;
        if (!super.equals(plane)) return false;
        ExperimentalPlane currentPlane = (ExperimentalPlane) plane;
        return experimentalType == currentPlane.experimentalType
                && classificationLevel == currentPlane.classificationLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experimentalType, classificationLevel);
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", experimentalType=" + experimentalType +
                        ", classificationLevel=" + classificationLevel +
                        '}');
    }
}
