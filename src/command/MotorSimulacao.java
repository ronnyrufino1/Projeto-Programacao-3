package command;

import core.FeixeLuz;
import utils.ConstantesFisicas;

public class MotorSimulacao {
    private static final double VELOCIDADE_DA_LUZ = ConstantesFisicas.VELOCIDADE_DA_LUZ;

    public double calculateSpeedOfLightInMedium(double n) {
        return VELOCIDADE_DA_LUZ / n;
    }

    public double calculateBitsPerSecond(FeixeLuz feixeLuz, int base, double distancia, double tempoEmSegundos) {
        // Cálculo de bits por pulso (logaritmo da base)
        double bitsPerSymbol = Math.log(feixeLuz.getIntensidade() * 10) / Math.log(base);
        double speed = distancia / tempoEmSegundos;

        return speed * bitsPerSymbol;
    }

    public double calculateTransmissionSpeed(double distance, double timeInSeconds) {
        return distance / timeInSeconds;
    }
}
