package strategy;

import core.EstruturaOpticaAbstract;
import core.FeixeLuz;
import utils.ConstantesFisicas;

public class Reflexao extends EstruturaOpticaAbstract implements OpticoStrategy {
    private double n1 = ConstantesFisicas.INDICE_VIDRO;
    private double n2;

    public Reflexao(double n2) {
        this.n2 = n2;
    }

    @Override
    public void apply(FeixeLuz feixeLuz) {
        double i = Math.toRadians(feixeLuz.getAngulo());
        double cosI = Math.cos(i);

        // Coeficiente de reflexão
        double r = ((n2 * cosI) - (n1 * cosI)) / ((n2 * cosI) + (n1 * cosI));
        double reflectance = r * r;

        feixeLuz.setIntensidade(feixeLuz.getIntensidade() * reflectance);
        feixeLuz.setState("refletido");

        // Atualiza ângulo de reflexão
        feixeLuz.setAngulo(180 - feixeLuz.getAngulo());
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public void processo(FeixeLuz feixeLuz) {

    }

    @Override
    public String getTipo() {
        return "";
    }

    @Override
    public String getNome() {
        return "Reflexão";
    }
}
