package command;

import core.BaseNumerica;
import core.FeixeLuz;
import core.SistemaOptico;
import observer.LogObserver;

public class SimuladorCommand {
    private FeixeLuz feixeLuz;
    private SistemaOptico sistema;
    private String baseNumerica;
    private MotorSimulacao motor;
    private long tempoInicial;

    public SimuladorCommand(FeixeLuz feixeLuz, SistemaOptico sistema, String baseNumerica) {
        this.feixeLuz = feixeLuz;
        this.sistema = sistema;
        this.baseNumerica = baseNumerica;
        this.motor = new MotorSimulacao();
        this.tempoInicial = System.nanoTime();
    }

    public void execute() {
        // Executa a simulação
        sistema.processo(feixeLuz);

        // Calcula o tempo
        long tempoFinal = System.nanoTime();
        double duration = (tempoFinal - tempoInicial) * 1e-9;

        // Calcula velocidade de propagação (m/s)
        double speedOfLight = motor.calculateSpeedOfLightInMedium(1.33); // água
        // Velocidade de transmissão (bits/s)
        double transmissionSpeed = motor.calculateBitsPerSecond(feixeLuz, Integer.parseInt(baseNumerica), 1.0, duration);

        // Gera representação numérica
        String numericRepresentation = BaseNumerica.toBase(feixeLuz.getIntensidade(), Integer.parseInt(baseNumerica));

        // Registra log
        LogObserver.getInstance().log(String.format(
                "Feixe %s processado em %.6fs. Velocidade: %.2f m/s. Transmissão: %.0f bits/s. Representação: %s",
                feixeLuz.toString(), duration, speedOfLight, transmissionSpeed, numericRepresentation
        ));
    }
}
