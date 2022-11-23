package Grammar;

public class ProductionRule {

    private final String leftSide;
    private final String rightSide;

    public ProductionRule(String leftSide, String rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public String getLeftSide() {
        return leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }

    @Override
    public String toString() {
        return leftSide + " => " +  rightSide;
    }
}
