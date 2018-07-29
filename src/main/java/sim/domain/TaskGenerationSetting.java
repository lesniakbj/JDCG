package sim.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sim.domain.enums.MajorTaskType;

public class TaskGenerationSetting {
    private MajorTaskType taskType;
    private double generationChance;
    private int generationPriority;

    public TaskGenerationSetting(MajorTaskType taskType, double generationChance, int generationPriority) {
        this.taskType = taskType;
        this.generationChance = generationChance;
        this.generationPriority = generationPriority;
    }

    public MajorTaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(MajorTaskType taskType) {
        this.taskType = taskType;
    }

    public double getGenerationChance() {
        return generationChance;
    }

    public void setGenerationChance(double generationChance) {
        this.generationChance = generationChance;
    }

    public int getGenerationPriority() {
        return generationPriority;
    }

    public void setGenerationPriority(int generationPriority) {
        this.generationPriority = generationPriority;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }
}
