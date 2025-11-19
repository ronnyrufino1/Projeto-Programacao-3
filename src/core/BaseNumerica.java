package core;

public class BaseNumerica {
    public static String toBase(double valor, int base) {
        if (base < 2) {
            throw new IllegalArgumentException("Base numérica deve ser ≥ 2");
        }

        // Converter valor de intensidade em representação de base N
        // Exemplo: 1.5 em base 2 → "1.1" (aproximado)

        if (base == 2) {
            return String.format("%.3f", valor);
        }

        // Para bases maiores, convertemos em inteiro (ex: 1.2 → 120 em base 3)

        int intValue = (int) Math.round(valor * 100);
        return Integer.toString(intValue, base);
    }
}
