package core;

import utils.ConstantesFisicas;

public class FeixeLuz extends ConstantesFisicas {
    private double intensidade;   // intensidade (W/m² ou relativo)
    private double comprimentoDeOnda; // em nm
    private String polaridade; // "horizontal", "vertical", "circular"
    private double angulo;      // ângulo de incidência (em graus)
    private String estado;      // estado: "inicial", "refletido", "refratado", "absorvido"

    public FeixeLuz(double intensidade, double comprimentoDeOnda, String polaridade, double angulo) {
        this.intensidade = intensidade;
        this.comprimentoDeOnda = comprimentoDeOnda;
        this.polaridade = polaridade;
        this.angulo = angulo;
        this.estado = "inicial";
    }

    // Getters e setters
    public double getIntensidade() { return intensidade; }
    public void setIntensidade(double intensidade) { this.intensidade = intensidade; }

    public double getComprimentoDeOnda() { return comprimentoDeOnda; }
    public void setComprimentoDeOnda(double comprimentoDeOnda) { this.comprimentoDeOnda = comprimentoDeOnda; }

    public String getPolaridade() { return polaridade; }
    public void setPolaridade(String polaridade) { this.polaridade = polaridade; }

    public double getAngulo() { return angulo; }
    public void setAngulo(double angulo) { this.angulo = angulo; }

    public String getEstado() { return estado; }
    public void setState(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return String.format("FeixeLuz{intensidade=%.2f, λ=%.2f nm, polarização=%s, ângulo=%.1f°, estado=%s}",
                intensidade, comprimentoDeOnda, polaridade, angulo, estado);
    }
}
