package aluno;

import java.util.ArrayList;
import java.util.Scanner;

public class CofrinhoMain {
    public static void main(String[] args) {
        ArrayList<Moeda> cofrinho = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int escolha;
		do {
            // Menu de escolha
            System.out.println("========== COFRINHO ==========");
            System.out.println("1. Adicionar moeda");
            System.out.println("2. Remover moeda");
            System.out.println("3. Listar moedas");
            System.out.println("4. Calcular total em reais");
            System.out.println("0. Encerrar");
            System.out.print("Digite o que você deseja: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    // Adicionar moedas ao cofrinho
                    System.out.println("Digite quanto deseja colocar no cofrinho:");
                    double valor = scanner.nextDouble();
                    System.out.println("Escolha o tipo de moeda:");
                    System.out.println("1. Dolar");
                    System.out.println("2. Euro");
                    System.out.println("3. Real");
                    int tipo = scanner.nextInt();
                    Moeda moeda;
                    switch (tipo) {
                        case 1:
                            moeda = new MoedaDolar(valor);
                            break;
                        case 2:
                            moeda = new MoedaEuro(valor);
                            break;
                        case 3:
                            moeda = new MoedaReal(valor);
                            break;
                        default:
                            System.out.println("ERRO. Moeda não adicionada.");
                            continue;
                    }
                    cofrinho.add(moeda);
                    System.out.println("Moeda adicionada");
                    break;
                case 2:
                    // Remoção de moedas
                    System.out.println("Digite qual moeda deseja remover:");
                    listarMoedas(cofrinho); // moedas disponíveis
                    int indiceRemover = scanner.nextInt();
                    if (indiceRemover >= 0 && indiceRemover < cofrinho.size()) {
                        cofrinho.remove(indiceRemover);
                        System.out.println("Moeda removida");
                    } else {
                        System.out.println("ERRO. Nenhuma moeda foi removida.");
                    }
                    break;
                case 3:
                    // Listagem de todas as moedas
                    listarMoedas(cofrinho);
                    break;
                case 4:
                    // Calculo do total de moedas em reais
                    double totalReais = calcularTotalEmReais(cofrinho);
                    System.out.println("Total em reais é: R$" + totalReais);
                    break;
                case 0:
                    // Opção para encerrar o programa
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (escolha != 0);

        scanner.close();
    }

    // Listagem de todas as moedas 
    private static void listarMoedas(ArrayList<Moeda> cofrinho) {
        if (cofrinho.isEmpty()) {
            System.out.println("O cofrinho está vazio.");
        } else {
            System.out.println("Moedas no cofrinho:");
            for (int i = 0; i < cofrinho.size(); i++) {
                System.out.println(i + ". " + cofrinho.get(i));
            }
        }
    }

    // Método para calcular o total em reais de todas as moedas
    private static double calcularTotalEmReais(ArrayList<Moeda> cofrinho) {
        double total = 0;
        for (Moeda moeda : cofrinho) {
            total += moeda.getValorEmReal();
        }
        return total;
    }
}

// Classe abstrata Moeda representando uma moeda genérica
abstract class Moeda {
    protected String moeda;
    protected double valor;

    public Moeda(String pais, double valor) {
        this.moeda = pais;
        this.valor = valor;
    }

    public abstract double getValorEmReal();

    @Override
    public String toString() {
        return "[" + moeda + ": " + valor + "]";
    }
}

// Subclasses representando moedas específicas
class MoedaDolar extends Moeda {
    public MoedaDolar(double valor) {
        super("Dolar", valor);
    }

    @Override
    public double getValorEmReal() {
        // Considerando que 1 dólar equivale a 5 reais
        return valor * 5;
    }
}

class MoedaEuro extends Moeda {
    public MoedaEuro(double valor) {
        super("Euro", valor);
    }

    @Override
    public double getValorEmReal() {
        // Considerando que 1 euro equivale a 6 reais
        return valor * 6;
    }
}

class MoedaReal extends Moeda {
    public MoedaReal(double valor) {
        super("Real", valor);
    }

    @Override
    public double getValorEmReal() {
        return valor; //
    }
}

