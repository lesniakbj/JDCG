package sim.domain;

import sim.domain.enums.MunitionType;

public class MunitionStockpile {
    private MunitionType stockpile;
    private int totalInStock;

    public MunitionStockpile(MunitionType stockpile, int totalInStock) {
        this.stockpile = stockpile;
        this.totalInStock = totalInStock;
    }

    @Override
    public String toString() {
        return "MunitionStockpile{" +
                "stockpile=" + stockpile +
                ", totalInStock=" + totalInStock +
                '}';
    }
}
