package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static void main(String[] args) {

		// Chamando o Método Listar todas as Contas
		ContaController contas = new ContaController();

		Scanner Leia = new Scanner(System.in);

		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		while (true) {

			System.out.println(Cores.TEXT_YELLOW
					+ "\n**********************************************************************************");
			System.out.println("$$$$$$$\\                                             $$$$$$\\                    \r\n"
					+ "$$  __$$\\                                           $$  __$$\\                   \r\n"
					+ "$$ |  $$ |$$$$$$\\ $$$$$$$\\  $$$$$$$\\ $$$$$$\\        $$ /  \\__|$$$$$$\\ $$$$$$$\\  \r\n"
					+ "$$$$$$$\\ |\\____$$\\$$  __$$\\$$  _____$$  __$$\\       $$ |$$$$\\$$  __$$\\$$  __$$\\ \r\n"
					+ "$$  __$$\\ $$$$$$$ $$ |  $$ $$ /     $$ /  $$ |      $$ |\\_$$ $$$$$$$$ $$ |  $$ |\r\n"
					+ "$$ |  $$ $$  __$$ $$ |  $$ $$ |     $$ |  $$ |      $$ |  $$ $$   ____$$ |  $$ |\r\n"
					+ "$$$$$$$  \\$$$$$$$ $$ |  $$ \\$$$$$$$\\\\$$$$$$  |      \\$$$$$$  \\$$$$$$$\\$$ |  $$ |\r\n"
					+ "\\_______/ \\_______\\__|  \\__|\\_______|\\______/        \\______/ \\_______\\__|  \\__|  ");
			System.out.println("                                                                                   ");
			System.out.println("**********************************************************************************");
			System.out.println("                                                                                  ");
			System.out.println("                         1- Criar Conta                                           ");
			System.out.println("                         2- Listar todas as contas                                ");
			System.out.println("                         3- Buscar Conta por número                               ");
			System.out.println("                         4- Atualizar Dados da conta                              ");
			System.out.println("                         5- Apagar Conta                                          ");
			System.out.println("                         6- Sacar                                                 ");
			System.out.println("                         7- Depositar                                             ");
			System.out.println("                         8- Transferir Valores entre Contas                       ");
			System.out.println("                         9- Sair                                                  ");
			System.out.println("                                                                                  ");
			System.out.println("                                                                                  ");
			System.out.println("**********************************************************************************"
					+ Cores.TEXT_RESET);
			System.out.println("Digite a opção desejada:                                                          ");
			System.out.println("                                                                                  ");

			try {
				opcao = Leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				Leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out
						.println(Cores.TEXT_YELLOW_BOLD + "\nBanco Gen - O seu futuro começa aqui!" + Cores.TEXT_RESET);
				Leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println("Criar Conta\n\n");

				System.out.println("Digite o Número da Agência: ");
				agencia = Leia.nextInt();
				System.out.println("Digite o Nome do Titular:");
				Leia.skip("\\R?");
				titular = Leia.nextLine();

				do {
					System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
					tipo = Leia.nextInt();
				} while (tipo < 1 && tipo > 2);

				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = Leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o Limite de Crédito (R$): ");
					limite = Leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversário da Conta: ");
					aniversario = Leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}

				keyPress();
				break;
			case 2:
				System.out.println("Listar todas as Contas\n\n");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println("Consultar dados da Conta por número\n\n");

				System.out.println("Digite o número da conta: ");
				numero = Leia.nextInt();

				contas.procurarPorNumero(numero);

				keyPress();
				break;
			case 4:
				System.out.println("Atualizar dados da Conta\n\n");

				System.out.println("Digite o número da Conta: ");
				numero = Leia.nextInt();

				if (contas.buscarNaCollection(numero) != null) {

					System.out.println("Digite o Número da Agência: ");
					agencia = Leia.nextInt();
					System.out.println("Digite o Nome do Titular: ");
					Leia.skip("\\R?");
					titular = Leia.nextLine();

					System.out.println("Digite o Saldo da Conta (R$): ");
					saldo = Leia.nextFloat();

					tipo = contas.retornaTipo(numero);

					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o Limite de Crédito (R$): ");
						limite = Leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversário da Conta: ");
						aniversario = Leia.nextInt();
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("Tipo de conta inválido!");
					}
					}

				} else
					System.out.println("\nConta não econtrada!!");

				keyPress();
				break;
			case 5:
				System.out.println("Apagar a Conta\n\n");

				System.out.println("Digite o número da conta: ");
				numero = Leia.nextInt();

				contas.deletar(numero);

				keyPress();
				break;
			case 6:
				System.out.println("Saque\n\n");

				System.out.println("Digite o Numero da conta: ");
				numero = Leia.nextInt();

				do {
					System.out.println("Digite o Valor do Saque (R$): ");
					valor = Leia.nextFloat();
				} while (valor <= 0);

				contas.sacar(numero, valor);

				keyPress();
				break;
			case 7:
				System.out.println("Depósito\n\n");

				System.out.println("Digite o Numero da conta: ");
				numero = Leia.nextInt();

				do {
					System.out.println("Digite o Valor do Depósito (R$): ");
					valor = Leia.nextFloat();
				} while (valor <= 0);

				contas.depositar(numero, valor);

				keyPress();
				break;
			case 8:
				System.out.println("Transferência entre Contas\n\n");

				System.out.println("Digite o Numero da Conta de Origem: ");
				numero = Leia.nextInt();
				System.out.println("Digite o Numero da Conta de Destino: ");
				numeroDestino = Leia.nextInt();

				do {
					System.out.println("Digite o Valor da Transferência (R$): ");
					valor = Leia.nextFloat();
				} while (valor <= 0);

				contas.transferir(numero, numeroDestino, valor);

				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				keyPress();
				break;

			}

		}

	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();
		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");
		}

	}

}
