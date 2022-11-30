package Application.Generator.Grammar;


public class ProductionRule {

    private final char leftSide;
    private final String rightSide;

    public ProductionRule(final char leftSide, final String rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public char getLeftSide() {
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
