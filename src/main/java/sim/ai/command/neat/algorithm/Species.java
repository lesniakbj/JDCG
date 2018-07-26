package sim.ai.command.neat.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Species {
    private double topFitness;
    private double staleness;
    private List<Genome> genomes;
    private double averageFitness;

    public Species() {
        topFitness = 0;
        staleness = 0;
        genomes = new ArrayList<>();
        averageFitness = 0;
    }

    public double getTopFitness() {
        return topFitness;
    }

    public void setTopFitness(double topFitness) {
        this.topFitness = topFitness;
    }

    public double getStaleness() {
        return staleness;
    }

    public void setStaleness(double staleness) {
        this.staleness = staleness;
    }

    public List<Genome> getGenomes() {
        return genomes;
    }

    public void setGenomes(List<Genome> genomes) {
        this.genomes = genomes;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    public void setAverageFitness(double averageFitness) {
        this.averageFitness = averageFitness;
    }
}
