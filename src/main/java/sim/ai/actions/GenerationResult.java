package sim.ai.actions;

import sim.ai.threat.ThreatGrid;

public class GenerationResult {
    private CommanderAction lastActionsTaken;
    private ThreatGrid results;

    public GenerationResult(CommanderAction lastActionsTaken, ThreatGrid results) {
        this.lastActionsTaken = lastActionsTaken;
        this.results = results;
    }

    public CommanderAction getActionsTaken() {
        return lastActionsTaken;
    }

    public void setActionsTaken(CommanderAction lastActionsTaken) {
        this.lastActionsTaken = lastActionsTaken;
    }

    public ThreatGrid getResults() {
        return results;
    }

    public void setResults(ThreatGrid results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "{\"GenerationResult\":{"
                + "\"lastActionsTaken\":" + lastActionsTaken
                + ", \"results\":" + results
                + "}}";
    }
}