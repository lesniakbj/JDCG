package sim.ai.actions;

import sim.ai.threat.ThreatGrid;

import java.util.List;

public class GenerationResult {
    private List<AIAction> actionsToTake;
    private ThreatGrid results;

    public GenerationResult(List<AIAction> actionsToTake, ThreatGrid results) {
        this.actionsToTake = actionsToTake;
        this.results = results;
    }

    public List<AIAction> getActionsTaken() {
        return actionsToTake;
    }

    public void setActionsTaken(List<AIAction> lastActionsTaken) {
        this.actionsToTake = lastActionsTaken;
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
                + "\"lastActionsTaken\":" + actionsToTake
                + ", \"results\":" + results
                + "}}";
    }
}