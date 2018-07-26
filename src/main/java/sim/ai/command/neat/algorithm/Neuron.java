package sim.ai.command.neat.algorithm;

public class Neuron {
    private Neuron incoming;
    private double value;

    public Neuron() {
        value = 0.0;
    }

    public Neuron getIncoming() {
        return incoming;
    }

    public void setIncoming(Neuron incoming) {
        this.incoming = incoming;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
