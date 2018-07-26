package sim.ai.command.neat.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Genome {
    private List<Gene> genes;
    private double fitness;
    private double adjustedFitness;
    private List<Neuron> network;
    private double maxNeuron;
    private double globalRank;
    private Map<String, Double> mutationRates;

    public Genome() {
        genes = new ArrayList<>();
        fitness = 0;
        adjustedFitness = 0;
        network = new ArrayList<>();
        globalRank = 0;
        mutationRates = new HashMap<>();

        // Populate default mutation rates...
    }

    public List<Gene> getGenes() {
        return genes;
    }

    public void setGenes(List<Gene> genes) {
        this.genes = genes;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getAdjustedFitness() {
        return adjustedFitness;
    }

    public void setAdjustedFitness(double adjustedFitness) {
        this.adjustedFitness = adjustedFitness;
    }

    public List<Neuron> getNetwork() {
        return network;
    }

    public void setNetwork(List<Neuron> network) {
        this.network = network;
    }

    public double getMaxNeuron() {
        return maxNeuron;
    }

    public void setMaxNeuron(double maxNeuron) {
        this.maxNeuron = maxNeuron;
    }

    public double getGlobalRank() {
        return globalRank;
    }

    public void setGlobalRank(double globalRank) {
        this.globalRank = globalRank;
    }
}
