package sim.domain;

import java.util.List;
import sim.domain.enums.MunitionType;

public class MunitionStockpile {
    private MunitionType stockpile;
    private int totalInStock;

    public MunitionStockpile(MunitionType stockpile, int totalInStock) {
        this.stockpile = stockpile;
        this.totalInStock = totalInStock;
    }
}
