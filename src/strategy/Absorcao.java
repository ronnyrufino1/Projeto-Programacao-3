package strategy;

import core.FeixeLuz;
import strategy.OpticoStrategy;

public class Absorcao implements OpticoStrategy {
    private double attenuation; // em dB/m

    public Absorcao(double attenuation) {
        this.attenuation = attenuation;
    }

    @Override
    public void apply(FeixeLuz feixeLuz) {

        // Atenuação exponencial: I = I0 * e^(-αx)
        // x = 1 metro (simulação unitária)

        double alpha = attenuation / 10.0; // converter de dB/m para m⁻¹
        feixeLuz.setIntensidade(feixeLuz.getIntensidade() * Math.exp(-alpha));

        feixeLuz.setState("absorvido");
    }

    @Override
    public String getName() {
        return "Absorção";
    }
}
