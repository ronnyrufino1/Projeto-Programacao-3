package factory;

import core.FeixeLuz;

public class FeixeLuzFactory {
    public static FeixeLuz createBeam(String type, double intensidade, double comprimentoDeOnda, String polaridade, double angulo) {
        switch (type.toLowerCase()) {
            case "laser":
                return new FeixeLuz(intensidade, comprimentoDeOnda, polaridade, angulo);
            case "led":
                return new FeixeLuz(intensidade, comprimentoDeOnda, polaridade, angulo);
            case "solar":
                return new FeixeLuz(intensidade, comprimentoDeOnda, polaridade, angulo);
            default:
                throw new IllegalArgumentException("Tipo de feixe desconhecido: " + type);
        }
    }
}
