package sim.ai.command.neat.algorithm;

public class Gene {
    private double input;
    private double output;
    private double weight;
    private boolean enabled;
    private int innovation;

    public Gene() {
        this.enabled = true;
    }

    public double getInput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getInnovation() {
        return innovation;
    }

    public void setInnovation(int innovation) {
        this.innovation = innovation;
    }
}
