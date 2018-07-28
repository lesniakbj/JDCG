package sim.ai.command.neat.algorithm;

import sim.ai.actions.AIActionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PopulationPool {
    private List<Species> species;
    private double generation;
    private List<AIActionType> innovation;
    private int currentSpecies;
    private int currentGenome;
    private int currentTurn;
    private double maxFitness;

    public PopulationPool() {
        species = new ArrayList<>();
        generation = 0;
        innovation.addAll(Arrays.asList(AIActionType.values()));
        currentSpecies = 1;
        currentGenome = 1;
        currentTurn = 1;
        maxFitness = 0;
    }

    public List<Species> getSpecies() {
        return species;
    }

    public void setSpecies(List<Species> species) {
        this.species = species;
    }

    public double getGeneration() {
        return generation;
    }

    public void setGeneration(double generation) {
        this.generation = generation;
    }

    public List<AIActionType> getInnovation() {
        return innovation;
    }

    public void setInnovation(List<AIActionType> innovation) {
        this.innovation = innovation;
    }

    public int getCurrentSpecies() {
        return currentSpecies;
    }

    public void setCurrentSpecies(int currentSpecies) {
        this.currentSpecies = currentSpecies;
    }

    public int getCurrentGenome() {
        return currentGenome;
    }

    public void setCurrentGenome(int currentGenome) {
        this.currentGenome = currentGenome;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public double getMaxFitness() {
        return maxFitness;
    }

    public void setMaxFitness(double maxFitness) {
        this.maxFitness = maxFitness;
    }
}
