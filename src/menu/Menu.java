package menu;

import command.MotorSimulacao;
import command.SimuladorCommand;
import core.SistemaOptico;
import core.*;
import factory.FeixeLuzFactory;
import observer.LogObserver;
import strategy.Absorcao;
import strategy.Reflexao;
import strategy.Refracao;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static MotorSimulacao motor = new MotorSimulacao();
    private static FeixeLuz feixeAtual;
    private static SistemaOptico sistemaAtual;

    public static void mainMenu() {
        System.out.println("=== SIMULAÇÃO DE COMPUTADOR FOTÔNICO ===");
        System.out.println("1. Criar Feixe de Luz");
        System.out.println("2. Adicionar Estrutura Óptica");
        System.out.println("3. Executar Simulação");
        System.out.println("4. Mostrar Logs");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // consome \n

            switch (choice) {
                case 1:
                    criarFeixe();
                    break;
                case 2:
                    adicionarEstruturaOptica();
                    break;
                case 3:
                    executarSimulacao();
                    break;
                case 4:
                    mostrarLogs();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Por favor, digite um número válido.");
            scanner.nextLine(); // limpa entrada inválida
        }

        mainMenu(); // recursivo
    }

    private static void criarFeixe() {
        System.out.println("\n=== CRIAR FEIXE DE LUZ ===");
        System.out.print("Intensidade (W/m² ou relativo, ex: 1,0): ");
        double intensidade = getDouble();

        System.out.print("Comprimento de onda (nm, ex: 500.0 para verde): ");
        double comprimentoDeOnda = getDouble();


        System.out.println("\n Escolha a polarização (afeta reflexão e refração):");
        System.out.println("   - 'horizontal' (H) → vibração horizontal");
        System.out.println("   - 'vertical' (V) → vibração vertical");
        System.out.println("   - 'circular' (C) → rotação em espiral (útil em modulação)");
        System.out.print("Polarização: ");

        String entrada = scanner.nextLine().trim().toLowerCase();

        // Mapeia abreviações
        String polaridade = entrada;
        if (polaridade.equals("h")) polaridade = "horizontal";
        if (polaridade.equals("v")) polaridade = "vertical";
        if (polaridade.equals("c")) polaridade = "circular";

        // Valida se a polaridade final é uma das aceitas
        if (!polaridade.equals("horizontal") &&
                !polaridade.equals("vertical") &&
                !polaridade.equals("circular")) {
            System.out.println("Polarização inválida. Usando 'horizontal' por padrão.");
            polaridade = "horizontal";
        }

        System.out.print("Ângulo de incidência (graus, ex: 30.0): ");
        double angulo = getDouble();

        FeixeLuz feixeLuz = FeixeLuzFactory.createBeam("laser", intensidade, comprimentoDeOnda, polaridade, angulo);
        System.out.println("Feixe criado: " + feixeLuz);
        System.out.println("💡 Dica: A polarização afeta como o feixe é refletido ou refratado.");

        // Armazenar feixe em variável global
        Menu.feixeAtual = feixeLuz;
    }

    private static void adicionarEstruturaOptica() {
        System.out.println("\n=== ADICIONAR ESTRUTURA ÓPTICA ===");
        System.out.println("1. Espelho (Reflexão Fresnel) — reflete o feixe com perda de intensidade");
        System.out.println("2. Prisma (Refração Snell) — muda o ângulo e a intensidade");
        System.out.println("3. Filtro (Absorção Beer-Lambert) — reduz intensidade (ex: absorção em vidro)");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (Menu.feixeAtual == null) {
                System.out.println("Erro: Nenhum feixe criado. Crie um primeiro.");
                return;
            }

            SistemaOptico system = new SistemaOptico();

            switch (choice) {
                case 1:
                    System.out.print("Índice de refração (ex: 1,5 para vidro): ");
                    double n = getDouble();
                    system.add(new Reflexao(n));
                    System.out.println("Espelho adicionado com índice de refração " + n);
                    break;
                case 2:
                    System.out.print("Índice de refração (ex: 1,5 para vidro): ");
                    n = getDouble();
                    system.add(new Refracao(n));
                    System.out.println("Prisma adicionado com índice de refração " + n);
                    break;
                case 3:
                    System.out.print("Atenuação (dB/m, ex: 0.1 para vidro): ");
                    double atenuacao = getDouble();
                    system.add(new Absorcao(atenuacao));
                    System.out.println("Filtro adicionado com atenuação " + atenuacao + " dB/m");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    return;
            }

            Menu.sistemaAtual = system;
            System.out.println("Estrutura óptica adicionada ao sistema.");
        } catch (InputMismatchException e) {
            System.out.println("Erro: Por favor, digite um número válido.");
            scanner.nextLine();
        }
    }

    private static void executarSimulacao() {
        if (Menu.feixeAtual == null) {
            System.out.println("Erro: Nenhum feixe criado. Crie um primeiro.");
            return;
        }

        if (Menu.sistemaAtual == null) {
            System.out.println("Erro: Nenhum sistema óptico adicionado. Adicione uma estrutura primeiro.");
            return;
        }

        System.out.println("\n=== EXECUTAR SIMULAÇÃO ===");
        System.out.print("Base numérica (2, 3, 4, ...): ");
        try {
            int base = scanner.nextInt();
            scanner.nextLine();

            if (base < 2) {
                System.out.println("Base numérica deve ser ≥ 2. Usando base 2 por padrão.");
                base = 2;
            }

            SimuladorCommand command = new SimuladorCommand(Menu.feixeAtual, Menu.sistemaAtual, String.valueOf(base));
            command.execute();

            System.out.println("Simulação concluída.");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Por favor, digite um número inteiro válido.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void mostrarLogs() {
        System.out.println("\n=== LOGS DE SIMULAÇÃO ===");
        LogObserver.getInstance().printLogs();
    }

    private static double getDouble() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
                scanner.nextLine(); // limpa a entrada
            }
        }
    }
}
