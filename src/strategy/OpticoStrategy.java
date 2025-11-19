package strategy;

import core.FeixeLuz;

public interface OpticoStrategy {
    void apply(FeixeLuz feixeLuz);
    String getName();
}
