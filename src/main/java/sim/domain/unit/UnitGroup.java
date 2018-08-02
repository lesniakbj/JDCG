package sim.domain.unit;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sim.domain.enums.FactionSideType;
import sim.util.IDGenerator;

import java.awt.geom.Point2D;
import java.util.Date;
import java.util.List;

public class UnitGroup<T extends SimUnit> extends SimUnit {
    private long id;
    private FactionSideType side;
    private List<T> groupUnits;
    private boolean shouldGenerate;
    private boolean playerGeneratedGroup;

    private UnitGroup() {}

    public List<T> getGroupUnits() {
        return groupUnits;
    }

    public int getNumberOfUnits() {
        return groupUnits.size();
    }

    public FactionSideType getSide() {
        return side;
    }

    public void setSide(FactionSideType side) {
        this.side = side;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGroupUnits(List<T> groupUnits) {
        this.groupUnits = groupUnits;
    }

    public boolean isShouldGenerate() {
        return shouldGenerate;
    }

    public void setShouldGenerate(boolean shouldGenerate) {
        this.shouldGenerate = shouldGenerate;
    }

    public boolean isPlayerGeneratedGroup() {
        return playerGeneratedGroup;
    }

    public void setPlayerGeneratedGroup(boolean playerGeneratedGroup) {
        this.playerGeneratedGroup = playerGeneratedGroup;
    }

    @Override
    public boolean shouldGenerateMission() {
        return shouldGenerate;
    }

    @Override
    public void updateStep() {
        // Update the entire group here
    }

    @Override
    public void setCurrentCampaignDate(Date date) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnitGroup<?> unitGroup = (UnitGroup<?>) o;

        if (id != unitGroup.id) return false;
        return side == unitGroup.side;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (side != null ? side.hashCode() : 0);
        result = 31 * result + (groupUnits != null ? groupUnits.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

    public static class Builder<T extends SimUnit> {
        private UnitGroup<T> unitGroup;

        public Builder() {
            this.unitGroup = new UnitGroup<>();
            unitGroup.setId(IDGenerator.generateNextId());
        }

        public Builder<T> setUnits(List<T> units) {
            unitGroup.setGroupUnits(units);
            return this;
        }

        public Builder<T> setSide(FactionSideType side) {
            unitGroup.setSide(side);
            return this;
        }

        public Builder<T> setGenerate(boolean generateMission) {
            unitGroup.setShouldGenerate(generateMission);
            return this;
        }

        public Builder<T> setMapLocation(Point2D.Double point) {
            unitGroup.setMapXLocation(point.getX());
            unitGroup.setMapYLocation(point.getY());
            return this;
        }

        public Builder<T> setMapXLocation(double x) {
            unitGroup.setMapXLocation(x);
            return this;
        }

        public Builder<T> setMapYLocation(double y) {
            unitGroup.setMapYLocation(y);
            return this;
        }

        public Builder<T> setSpeed(double speed) {
            unitGroup.setSpeedMilesPerHour(speed);
            return this;
        }

        public Builder<T> setDirection(double dir) {
            unitGroup.setDirection(dir);
            return this;
        }

        public Builder<T> setPlayerGeneratedGroup(boolean isPlayer) {
            unitGroup.setPlayerGeneratedGroup(isPlayer);
            return this;
        }

        public UnitGroup<T> build(){
            unitGroup = validate() ? unitGroup : null;
            return unitGroup;
        }

        private boolean validate() {
            boolean hasUnits = unitGroup.getGroupUnits() != null;
            boolean hasSide = unitGroup.getSide() != null;
            boolean hasMoreThanOne = unitGroup.getNumberOfUnits() > 0;
            boolean hasX = unitGroup.getMapXLocation() != 0;
            boolean hasY = unitGroup.getMapYLocation() != 0;

            return hasUnits && hasSide && hasMoreThanOne && hasX && hasY;
        }
    }
}
