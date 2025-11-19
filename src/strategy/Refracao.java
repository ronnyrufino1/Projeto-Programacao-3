package strategy;

import core.FeixeLuz;
import utils.ConstantesFisicas;

public class Refracao implements OpticoStrategy {
    private double n1 = ConstantesFisicas.INDICE_VIDRO;
    private double n2;

    public Refracao(double n2) {
        this.n2 = n2;
    }

    @Override
    public void apply(FeixeLuz feixeLuz) {
        double i = Math.toRadians(feixeLuz.getAngulo());
        double sinI = Math.sin(i);
        double n = n2 / n1;

        // Verificar reflexão total
        if (n * sinI > 1.0) {
            feixeLuz.setState("reflexão total");
            return;
        }

        double sinR = sinI / n;
        double r = Math.asin(sinR);

        // Atualiza ângulo de refração
        feixeLuz.setAngulo(Math.toDegrees(r));

        // Atualiza intensidade (lei de Fresnel para transmissão)
        double t = Math.sqrt((2 * n1 * n2 * Math.cos(i)) / (n1 * n1 + n2 * n2));
        feixeLuz.setIntensidade(feixeLuz.getIntensidade() * t * t);

        feixeLuz.setState("refratado");
    }

    @Override
    public String getName() {
        return "Refração";
    }
}
