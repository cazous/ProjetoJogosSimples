package br.ucsal.jogos;

import java.util.Random;
import java.util.Scanner;

public class JogosComentados {

	// JOGO DA VELHA

	// Variaveis do tabuleiro
	public static int maxVelha = 3;
	public static int[][] tabuleiro = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } }; // Vetor jogadas no

	// Variaveis do jogador
	public static Scanner leiaVelha = new Scanner(System.in);
	public static String jogador1 = "";
	public static String jogador2 = "";

	// Variaveis das Jogadas
	public static int vez_Velha = 0;
	public static boolean jogadaValidada = false;

	// Variaveis Vencedor
	public static String jogadorVencedor = "";

	// JOGO DA FORCA

	// Variáveis do gráfico
	public static int qntErros = 0;
	public static String[] desenho = new String[] { " ", " ", " ", "  ", " ", "  " };
	public static String[] desenhobck = new String[] { "O", "|", "/", "\\", "/", "\\" };

	// Variáveis do sublinhado
	public static String palavraEscolhida = "";
	public static String[] sublinhado = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
	public static int tentativas = 0;
	public static int indice = 0;

	// Variáveis das Jogadas
	public static String[] letrasUsadas = new String[] { "", "", "", "", "", "", "", "", "", "", "" };
	public static int contador = 0;
	public static boolean letraDigitada = false;
	public static boolean ePalavra = false;
	public static String letraPalavraEscolhida = "";

	// Variáveis de Validação
	public static boolean validado = false;

	// Variáveis de Vencedor e Perdedor
	public static int numTentativas = 0;
	public static boolean acertouPalavra = false;

	// BATALHA NAVAL

	// Variaveis Tabuleiro
	public static int max = 10;
	public static int[][] tabuleiroJogador1 = new int[max][max];
	public static int[][] tabuleiroJogador2 = new int[max][max];

	// Variaveis PosicionarNavios
	public static Scanner leiaNaval = new Scanner(System.in);
	public static int qntNaviosJogador1 = 4;
	public static int qntNaviosJogador2 = 4;

	// Variaveis Espacos Horizontal e Vertical
	public static boolean existeEspacoHorizontal = false;
	public static boolean existeEspacoVertical = false;

	// Variaveis Iniciar Navios
	public static String[] existenciaNavio = new String[] { "1 - Porta-Aviões      (5 slots)",
			"2 - Navio-Tanque      (4 slots)", "3 - Contratorpedeiro  (3 slots)", "4 - Submarino         (2 slots)" };
	public static int[] naviosJaUsadosJogador1 = new int[4];
	public static int indiceNaviosUsadosJogador1 = 0;
	public static int[] naviosJaUsadosJogador2 = new int[4];
	public static int indiceNaviosUsadosJogador2 = 0;

	// Variaveis Efetuar Jogada
	public static int vez = 1;

	// Variaveis Verificar Vencedor
	public static int jogador1Acertos = 14;
	public static int jogador2Acertos = 12;
	public static boolean jogador1Venceu = false;
	public static boolean jogador2Venceu = false;

	// Variaveis Autopreenchimento
	public static boolean autoPreenchidoSucesso = false;

	// Variaveis lado jogada
	public static boolean direita = false;
	public static boolean esquerda = false;
	public static boolean cima = false;
	public static boolean baixo = false;
	public static boolean direitaEMCIMA = false;
	public static boolean esquerdaEMCIMA = false;
	public static boolean baixoNADIREITA = false;
	public static boolean baixoNAESQUERDA = false;
	public static boolean acimaUmaCasa = false;

	public static void main(String[] args) {
		menu();
	}

	public static void menu() {
		System.out.println("-------------- BEM VINDO AO MENU --------------");
		System.out.println();
		System.out.println("[1]     ►►►      Jogo da Forca      ◄◄◄");
		System.out.println("[2]     ►►►      Jogo da Velha      ◄◄◄");
		System.out.println("[3]     ►►►      Batalha Naval      ◄◄◄");
		System.out.println("[4]     ►►► Minecraft (Em produção) ◄◄◄");
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println();
		System.out.println("Escolha seu jogo: ");
		
		int jogo = 0;//Variável de escolha do jogo

		do {
			jogo = leiaNaval.nextInt();
			switch (jogo) {
			case 1:
				forcaOJOGO();
				break;

			case 2:
				velhaOJOGO();
				break;

			case 3:
				batalhaNavalOJOGO();
				break;
				
			default:
				System.out.println("Opcao Invalida!, Digite novamente.");
				break;
			}
		}while(jogo>3);
	}

	public static void batalhaNavalOJOGO() {
		for (int i = 0; i < 30; i++) { //Printa linhas para limpar o console
			System.out.println();
		}

		inicializarJogadores();
		comecarJogo();
	}

	public static void forcaOJOGO() {

		for (int i = 0; i < 30; i++) { //Printa linhas para limpar o console
			System.out.println();
		}
		
		String jogadaForca = "";
		Scanner entrada = new Scanner(System.in);
		efetuarJogada("a", true);//Apenas validação
		gerarSublinhado(letraDigitada, letraPalavraEscolhida);
		
		while (verificarTentativas() == true && acertouPalavra == false) {
			if (numTentativas == 5) {
				System.out.print("\nQual Palavra?: ");
			} else {
				System.out.print("\nR: ");
			}
			jogadaForca = entrada.next();
			efetuarJogada(jogadaForca, false);
			gerarSublinhado(letraDigitada, letraPalavraEscolhida);
		}
		entrada.close();
	}

	public static void velhaOJOGO() {

		for (int i = 0; i < 30; i++) { //Printa linhas para limpar o console
			System.out.println();
		}

		// Iniciamos o jogo chamando o metodo para definir quem vai comecar
		// esse metodo so sera chamado uma vez.
		jogadorUmJogadorDois();

		// Variaveis pra pegar a jogada do jogador, qual linha e coluna ele quer jogar.
		int linha = 0, coluna = 0;

		// Enquanto nao tiver vencedor e nao tiver empate, o jogo vai acontecer.
		while (verificarVencedorVelha() == false && verificarEmpate() == false) {
			System.out.println("\nJogador --> " + vez_Velha + " <--");
			System.out.println("Linha (0 ~ 2): ");
			linha = leiaVelha.nextInt();
			System.out.println("Coluna (0 ~ 2): ");
			coluna = leiaVelha.nextInt();

			// Metodo efetuar jogada que vai fazer algumas verificacoes
			// antes de efetuar propriamente dita.
			efetuarJogadaVelha(vez_Velha, linha, coluna);

			// Apos a jogada ser efetuada, nos construimos o tabuleiro do jogo.
			construirTabuleiroVelha();
		}
	}

	// BATALHA NAVAL

	public static void efetuarJogada(int _jogador, int _linha, int _coluna) {
		int linha; // Linha da coordenada
		int coluna; // Coluna da coordenada
		if (validarJogada(_linha, _coluna) == true) {// Verificamos se a jogada foi validada
			if (_jogador == 1) {// Instrucoes para o jogador 1

				// Caso o valor nessa coordenada seja maior que 0
				// significa que tem embarcação, portanto, acertou e
				// setamos para -1
				if (tabuleiroJogador2[_linha][_coluna] > 0) {
					tabuleiroJogador2[_linha][_coluna] = -1;
					System.out.println("Voce acertou uma embarcação!");
					jogador1Acertos++;
					vez = 2;

					// Caso seja igual a 0 significa que nao tem embarcação
					// logo, acertou na agua, setamos para -2
				} else if (tabuleiroJogador2[_linha][_coluna] == 0) {
					tabuleiroJogador2[_linha][_coluna] = -2;
					System.out.println("Voce atirou na agua!  =/");
					vez = 2;

					// caso nao tenha sido nem acerto nem erro, entao
					// o usuario pode ter tentando acertar uma jogada
					// ja feita antes.
				} else {
					System.out.println("Jogada ja feita anteriormente. Tente Novamente");
				}
			} else {
				if (tabuleiroJogador1[_linha][_coluna] > 0) {
					tabuleiroJogador1[_linha][_coluna] = -1;
					System.out.println("Voce acertou uma embarcação!");
					jogador2Acertos++;
					vez = 1;
				} else if (tabuleiroJogador1[_linha][_coluna] == 0) {
					tabuleiroJogador1[_linha][_coluna] = -2;
					System.out.println("Voce atirou na agua!  =/");
					vez = 1;
				} else {
					System.out.println("Jogada ja feita anteriormente. Tente Novamente");
				}
			}
		} else {// Caso nao tenha sido validada
			// Nos obrigamos o usuario a digitar novamente linha e coluna
			System.out.println("Linha e Coluna invalidos. Tente novamente");
			System.out.println("Linha(0 ~ 9):");
			linha = leiaNaval.nextInt();
			System.out.println("Coluna(0 ~ 9):");
			coluna = leiaNaval.nextInt();

			// Chamando essa mesma função e passando como argumento os proprios parametros
			efetuarJogada(_jogador, linha, coluna);
		}
	}

	public static void comecarJogo() {// Jogo inicia
		int linha;
		int coluna;

		// O Loop acontece enquanto nenhum jogador vencer
		while (jogador1Venceu == false && jogador2Venceu == false) {
			System.out.println();
			System.out.println();
			System.out.println("   ----------- HORA DO JOGO -----------   ");
			System.out.println("             Vez do Jogador " + vez + " ");
			System.out.println("    Tabuleiro do outro Jogador abaixo     ");
			System.out.println("            Faça a sua Jogada             ");
			System.out.println("   ------------------------------------   ");
			// Printamos o tabuleiro inimigo sem mostrar as pecas.
			tabuleiro(vez);
			System.out.println("Linha(0 ~ 9):");
			linha = leiaNaval.nextInt();
			System.out.println("Coluna(0 ~ 9):");
			coluna = leiaNaval.nextInt();

			// Uma vez digitada as coordenadas, nos efetuamos a jogada
			efetuarJogada(vez, linha, coluna);

			// Sempre que uma jogada e feita, verificamos se houve vencedor.
			verificarVencedor();
		}
	}

	public static void tabuleiro(int _jogador) {
		if (_jogador == 2) { // Jogada do jogador 1
			System.out.println();

			for (int i = 0; i < 10; i++) {// Um loop para mostrar os numeros das linhas
				System.out.print("  " + i + " ");
			}
			System.out.println("\n ——————————————————————————————————————— ");

			// Aqui nos criamos o tabuleiro, de acordo com o valor
			// Nos printamos se foi acerto, agua ou nada.
			for (int linha = 0; linha < max; linha++) {
				for (int coluna = 0; coluna < max; coluna++) {

					if (tabuleiroJogador1[linha][coluna] == -1) {
						System.out.print("| O ");
					}
					if (tabuleiroJogador1[linha][coluna] == -2) {
						System.out.print("| ~ ");
					}

					// Se nao for acerto, nem agua entao nos printamos espaco em branco.
					if (tabuleiroJogador1[linha][coluna] != -1 && tabuleiroJogador1[linha][coluna] != -2) {
						System.out.print("|   ");
					}

					// Apenas para printar a ultima coluna com tracinhos.
					if (coluna == (max - 1)) {
						System.out.print("| " + (linha));
					}
				}
				System.out.println();
			}
			System.out.println(" ——————————————————————————————————————— ");
		} else {// Jogador 2
			System.out.println();

			for (int i = 0; i < 10; i++) {
				System.out.print("  " + i + " ");
			}
			System.out.println("\n ——————————————————————————————————————— ");

			for (int linha = 0; linha < max; linha++) {
				for (int coluna = 0; coluna < max; coluna++) {

					if (tabuleiroJogador2[linha][coluna] == -1) {
						System.out.print("| X ");
					}
					if (tabuleiroJogador2[linha][coluna] == -2) {
						System.out.print("| ~ ");
					}
					if (tabuleiroJogador2[linha][coluna] != -1 && tabuleiroJogador2[linha][coluna] != -2) {
						System.out.print("|   ");
					}

					if (coluna == (max - 1)) {
						System.out.print("| " + (linha));
					}
				}
				System.out.println();
			}
			System.out.println(" ——————————————————————————————————————— ");
		}
	}

	// Nesse metodo nos iniciamos o jogo, para cada jogador
	// setar os seus navios nos tabuleiros.
	public static void inicializarJogadores() {
		// enquanto o jogador tiver navios para posicionar
		// o loop vai ficar ativo.
		while (qntNaviosJogador1 > 0) {
			inicializarNavios(1);
		}

		// Reseto as opcoes dos navios que vao sendo removidas
		// conforme o jogador vai usando os navios para por
		// no tabuleiro.
		resetarOpcoesNavios();

		while (qntNaviosJogador2 > 0) {
			inicializarNavios(2);
		}
	}

	public static void resetarOpcoesNavios() {
		existenciaNavio = new String[] { "1 - Porta-Aviões      (5 slots)", "2 - Navio-Tanque      (4 slots)",
				"3 - Contratorpedeiro  (3 slots)", "4 - Submarino         (2 slots)" };
	}

	// Apenas um metodo pra facilitar a fase de testes.
	public static void gerarNavios() {
		tabuleiroJogador1[0][0] = 1;
		tabuleiroJogador1[0][1] = 1;
		tabuleiroJogador1[0][2] = 1;
		tabuleiroJogador1[0][3] = 1;
		tabuleiroJogador1[0][4] = 1;

		tabuleiroJogador1[1][0] = 2;
		tabuleiroJogador1[1][1] = 2;
		tabuleiroJogador1[1][2] = 2;
		tabuleiroJogador1[1][3] = 2;

		tabuleiroJogador1[2][0] = 3;
		tabuleiroJogador1[2][1] = 3;
		tabuleiroJogador1[2][2] = 3;

		tabuleiroJogador1[3][0] = 4;
		tabuleiroJogador1[3][1] = 4;

		// p2
		tabuleiroJogador2[0][0] = 1;
		tabuleiroJogador2[0][1] = 1;
		tabuleiroJogador2[0][2] = 1;
		tabuleiroJogador2[0][3] = 1;
		tabuleiroJogador2[0][4] = 1;

		tabuleiroJogador2[1][0] = 2;
		tabuleiroJogador2[1][1] = 2;
		tabuleiroJogador2[1][2] = 2;
		tabuleiroJogador2[1][3] = 2;

		tabuleiroJogador2[2][0] = 3;
		tabuleiroJogador2[2][1] = 3;
		tabuleiroJogador2[2][2] = 3;

		tabuleiroJogador2[3][0] = 4;
		tabuleiroJogador2[3][1] = 4;
	}

	// Aqui precisamos pegar do usuario qual o navio que ele
	// quer adicionar no tabuleiro.
	public static void inicializarNavios(int _jogador) {
		int qntNavios = 0;

		// chamamos o metodo de construcao do tabuleiro para exibir
		// os espacos que sao possiveis adicionar.
		construirTabuleiro(_jogador);

		int navioEscolhido = 0;
		System.out.println();

		System.out.println("Escolha qual navio adicionar");

		// esse for apenas exibe quais navios temos disponiveis para o usuario
		// utilizar e por no tabuleiro.
		for (int i = 0; i < 4; i++) {
			if (!existenciaNavio[i].equals("")) {
				System.out.println(existenciaNavio[i]);
			}
		}
		System.out.println("\nDigite sua escolha(1 ~ 4): ");
		navioEscolhido = leiaNaval.nextInt();

		if (_jogador == 1) {
			// Verificamos se o usuario esta tentando setar um navio
			// que ele ja tentou setar antes, pois ele deve
			// por apenas 1 navio de cada tipo.
			for (int i = 0; i < 4; i++) {
				if (naviosJaUsadosJogador1[i] == navioEscolhido) {
					qntNavios++;
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (naviosJaUsadosJogador2[i] == navioEscolhido) {
					qntNavios++;
				}
			}
		}

		// Enquanto o usuario estiver tentando por um navio que ele ja adicionou antes
		// Entao obrigamos ele a digitar novamente uma opcao valida.
		while (qntNavios > 0) {
			System.out.println("Voce ja escolheu esse navio, tente outro");
			System.out.println("\nDigite sua escolha(1 ~ 4): ");
			navioEscolhido = leiaNaval.nextInt();
			qntNavios = 0;
			if (_jogador == 1) {
				for (int i = 0; i < 4; i++) {
					if (naviosJaUsadosJogador1[i] == navioEscolhido) {
						qntNavios++;
					}
				}
			} else {
				for (int i = 0; i < 4; i++) {
					if (naviosJaUsadosJogador2[i] == navioEscolhido) {
						qntNavios++;
					}
				}
			}
		}

		// Armazenamos a opcao que o usuario escolheu para no futuro
		// conseguirmos identificar se ele ja usou ou nao o navio.
		if (_jogador == 1) {
			naviosJaUsadosJogador1[indiceNaviosUsadosJogador1] = navioEscolhido;
			indiceNaviosUsadosJogador1++;
		} else {
			naviosJaUsadosJogador2[indiceNaviosUsadosJogador2] = navioEscolhido;
			indiceNaviosUsadosJogador2++;
		}

		posicionarNavios(_jogador, navioEscolhido);
	}

	// Aqui e o metodo onde colocamos os slots do navio escolhido
	// dentro do tabuleiro, esse metodo e o principal do inicio
	// do jogo
	public static void posicionarNavios(int _jogador, int _navio) {
		String nomeNavio = "";

		// Variavel que usamos pra saber se os slots do barco
		// foram preenchidos com sucesso.
		autoPreenchidoSucesso = false;
		int slots = 0;
		if (_navio == 1) {
			// Setamos o nome do barco para usar um pouco abaixo
			// apenas para exibir mesmo, nada especial
			nomeNavio = "Porta-Avi�es";
			slots = 5;// pegamos a quantidade de slots dele.
		} else if (_navio == 2) {
			nomeNavio = "Navio-Tanque";
			slots = 4;
		} else if (_navio == 3) {
			nomeNavio = "Contratorpedeiro";
			slots = 3;
		} else if (_navio == 4) {
			nomeNavio = "Submarino";
			slots = 2;
		}

		System.out.println("Escolha a posicao do navio " + nomeNavio + "");
		int linha = 0;// linha digitada
		int coluna = 0;// coluna digitada
		int primeiraLinha = 0;// primeira linha digitada
		int primeiraColuna = 0;// primeira coluna digitada

		// Esse for vai correr enquanto houver slots pra preencher e nao tiver
		// sido preenchido automaticamente.
		for (int i = 0; i < slots && autoPreenchidoSucesso == false; i++) {
			System.out.println("Linha(0 ~ 9):");
			linha = leiaNaval.nextInt();
			System.out.println("Coluna(0 ~ 9):");
			coluna = leiaNaval.nextInt();

			// Verificar os espacos na horizontal e vertical para ter um controle
			// sobre a jogada do usuario, atraves desse metodo nos sabemos os limites da
			// jogada
			// se o usuario pode jogar na horizontal e/ou vertical.
			if (i == 0) {
				verificarEspacosHoriVertTabuleiro(_jogador, linha, coluna, slots, _navio);

				// Salvamos sempre a primeira linha e coluna na primeira rodada, logo na
				// primeira
				// vez que o usuario jogada.
				primeiraLinha = linha;
				primeiraColuna = coluna;
			}

			// - Se o jogador foi pra baixo, cima, esquerda ou direita
			// - Se o jogador foi pra superior-direito, superior-esquerdo, inferior-direito
			// ou inferior-esquerdo
			verificarLadoJogadaEslots(_jogador, linha, coluna, primeiraLinha, primeiraColuna);

			// Sabendo os dados que o metodo acima armazena nas variaveis globais, nos temos
			// total controle do que o usuario esta tentando fazer e impedir toda/qualquer ação
			// indesejada

			// Enquanto a coordenada que o usuario esta tentando jogar, tiver um navio
			// esse while vai repetir, porem, caso nao possua um navio existem outras
			// possibilidades que vao fazer o usuario ter que digitar novamente
			// a linha e coluna, sao elas:
			// ► Se nao existe espaco na horizontal E ele esta tentando ir pra direita
			// ► Se nao existe espaco na horizontal E ele esta tentando ir pra esquerda
			// ► Se nao existe espaco na vertical E ele esta tentando ir pra baixo
			// ► Se nao existe espaco na vertical E ele esta tentando ir pra cima
			// ► Se o usuario esta tentando jogar no canto inferior direito
			// ► Se o usuario esta tentando jogar no canto inferior esquerdo
			// ► Se o usuario esta tentando jogar no canto superior direito
			// ► Se o usuario esta tentando jogar no canto superior esquerdo
			// ► Se a coordenada do usuario esta acima de 1 casa.
			while (verificarExistenciaNavio(_jogador, linha, coluna) == true
					|| existeEspacoHorizontal == false && direita == true
					|| existeEspacoHorizontal == false && esquerda == true
					|| existeEspacoVertical == false && baixo == true || existeEspacoVertical == false && cima == true
					|| baixoNADIREITA == true || direitaEMCIMA == true || esquerdaEMCIMA == true
					|| baixoNAESQUERDA == true || acimaUmaCasa == true) {

				if (existeEspacoHorizontal == false && direita == true || esquerda == true) {
					System.out.println("Nao existe espaco suficiente na HORIZONTAL");
				} else if (existeEspacoVertical == false && baixo == true || cima == true) {
					System.out.println("Nao existe espaco suficiente na VERTICAL");
				} else if (baixoNADIREITA == true || direitaEMCIMA == true || esquerdaEMCIMA == true
						|| baixoNAESQUERDA == true) {
					System.out.println("Nao é permitido jogar nas diagonais!");
				} else if (acimaUmaCasa == true) {
					System.out.println("Voce esta colocando casas a mais");
				}

				System.out.println("Linha(0 ~ 9):");
				linha = leiaNaval.nextInt();
				System.out.println("Coluna(0 ~ 9):");
				coluna = leiaNaval.nextInt();
				verificarLadoJogadaEslots(_jogador, linha, coluna, primeiraLinha, primeiraColuna);

				if (i == 0) {
					verificarEspacosHoriVertTabuleiro(_jogador, linha, coluna, slots, _navio);
					primeiraLinha = linha;
					primeiraColuna = coluna;
				}
			}

			if (_jogador == 1) {// Se for o jogador 1

				if (i == 0) {
					// Na primeira vez nos ja setamos o valor sem muito questionar
					tabuleiroJogador1[linha][coluna] = _navio;
				}

				if (i == 1) {
					// Na jogada seguinte nos ja preenchemos automaticamente os slots do navio
					autoPreenchimentoNavios(_jogador, linha, coluna, primeiraLinha, primeiraColuna, _navio, slots);
				}

				// Aqui e apenas pra remover um bug que tava exibindo um tabuleiro a mais
				// sem necessidade.
				if (autoPreenchidoSucesso == true) {

				} else {
					construirTabuleiro(1);
				}

			} else {// jogador 2

				if (i == 0) {
					tabuleiroJogador2[linha][coluna] = _navio;
				}

				if (i == 1) {
					autoPreenchimentoNavios(_jogador, linha, coluna, primeiraLinha, primeiraColuna, _navio, slots);
				}

				if (autoPreenchidoSucesso == true) {

				} else {
					construirTabuleiro(2);
				}
			}
		}

		existenciaNavio[(_navio) - 1] = ""; // Quando o navio for adicionado, removemos ele do menu

		if (_jogador == 1) {// Cada navio adicionado, o usuario perde um navio na opcao.
			qntNaviosJogador1--;
		} else {
			qntNaviosJogador2--;
		}
	}

	// Esse metodo aqui valida o lado da jogada e a quantidade de casas
	// que o usuario tentou pular, pois so permitimos a distancia de 1 casa
	public static void verificarLadoJogadaEslots(int _jogador, int _linha, int _coluna, int _primeiraLinha,
			int _primeiraColuna) {

		// Setamos tudo pra false pra garantir integridade.
		direita = false;
		esquerda = false;
		cima = false;
		baixo = false;
		baixoNADIREITA = false;
		direitaEMCIMA = false;
		esquerdaEMCIMA = false;
		baixoNAESQUERDA = false;
		acimaUmaCasa = false;

		// Caso a linha digitada seja maior que a primeira linha e a coluna digitada
		// seja igual a coluna anterior, significa que o usuario esta tentando
		// ir para baixo, o mesmo serve para os seguintes, basta seguir a mesma logica.
		if (_linha > _primeiraLinha && _coluna == _primeiraColuna) {
			baixo = true;
		} else if (_linha < _primeiraLinha && _coluna == _primeiraColuna) {
			cima = true;
		} else if (_coluna > _primeiraColuna && _linha == _primeiraLinha) {
			direita = true;
		} else if (_coluna < _primeiraColuna && _linha == _primeiraLinha) {
			esquerda = true;
		} else if (_linha > _primeiraLinha && _coluna > _primeiraColuna) {
			baixoNADIREITA = true;
		} else if (_linha < _primeiraLinha && _coluna > _primeiraColuna) {
			direitaEMCIMA = true;
		} else if (_linha < _primeiraLinha && _coluna < _primeiraColuna) {
			esquerdaEMCIMA = true;
		} else if (_linha > _primeiraLinha && _coluna < _primeiraColuna) {
			baixoNAESQUERDA = true;
		}

		// Aqui verificamos seu o usuario esta mantendo um limite de 1 casa
		// de distancia nas jogadas.

		// Math.abs Torna o valor negativo em positivo, apenas isso, so pra garantir
		// que nao vai acontecer besteira.
		if (Math.abs(_linha) - Math.abs(_primeiraLinha) > 1) {
			acimaUmaCasa = true;// Caso ultrapasse 1 casa, ja setamos pra true a variavel global.
		} else if (Math.abs(_coluna) - Math.abs(_primeiraColuna) > 1) {
			acimaUmaCasa = true;
		}
	}

	// Validamos a jogada para impedir que o usuario quebre nosso vetor
	// e consequentemente o jogo.
	public static boolean validarJogada(int _linha, int _coluna) {
		if (_linha >= 0 && _linha <= 9) {
			if (_coluna >= 0 && _coluna <= 9) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// Verificamos se o local onde o usuario esta tentando jogar, ja nao existe um
	// navio nela
	// Caso nao tenha, retorna falso. Se houver, retorna true.
	public static boolean verificarExistenciaNavio(int _jogador, int _linha, int _coluna) {
		if (_jogador == 1) {
			if (tabuleiroJogador1[_linha][_coluna] != 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (tabuleiroJogador2[_linha][_coluna] != 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	// Aqui nos preenchemos automaticamente os slots do navio de acordo com a
	// segunda jogada do usuario
	public static void autoPreenchimentoNavios(int _jogador, int _linha, int _coluna, int _primeiraLinha,
			int _primeiraColuna, int _navio, int _slots) {

		// Eu ja sei que o usuario jogou a primeira vez, portanto subtraio 1 jogada dos
		// slots
		// assim se o navio tem 5 slots eu sei que me restam 4.
		int slots = (_slots) - 1;

		if (_jogador == 1) {

			// Caso a linha digitada seja maior que a primeira linha já sabemos
			// que o usuario esta tentando ir pra baixo, entao eu preencho automaticamente
			// os slots nos lugares possiveis.
			if (_linha > _primeiraLinha) {
				for (int linhaBaixo = _linha; slots > 0; linhaBaixo++) {
					if (linhaBaixo < 10 && tabuleiroJogador1[linhaBaixo][_coluna] == 0) {
						tabuleiroJogador1[linhaBaixo][_coluna] = _navio;

						slots--;// Cada slot adicionado eu removo aqui na variavel.
					} else {
						// POREM, se chegar em uma situacao que nao tem mais espacos entao
						// o codigo volta para a primeira jogada e segue na direcao oposta
						for (int linhaCima = (_primeiraLinha) - 1; slots > 0; linhaCima--) {
							if (linhaCima >= 0 && tabuleiroJogador1[linhaCima][_coluna] == 0) {
								tabuleiroJogador1[linhaCima][_coluna] = _navio;

								slots--;

								if (slots == 0) {
									break;
								}
							}
						}
						break;
					}
				}
				autoPreenchidoSucesso = true;

				// Mesma logica da parte de cima so que em outro sentido.
			} else if (_linha < _primeiraLinha) { // Preenchimento pra cima.
				for (int linhaCima = _linha; slots > 0; linhaCima--) {
					if (linhaCima >= 0 && tabuleiroJogador1[linhaCima][_coluna] == 0) {
						tabuleiroJogador1[linhaCima][_coluna] = _navio;

						slots--;
					} else {
						for (int linhaBaixo = (_primeiraLinha) + 1; slots > 0; linhaBaixo++) {
							if (linhaBaixo >= 0 && tabuleiroJogador1[linhaBaixo][_coluna] == 0) {
								tabuleiroJogador1[linhaBaixo][_coluna] = _navio;

								slots--;

								if (slots == 0) {
									break;
								}
							}
						}
						break;
					}
				}
				autoPreenchidoSucesso = true;
			} else if (_coluna > _primeiraColuna) {
				for (int colunaDireita = _coluna; slots > 0; colunaDireita++) {
					if (colunaDireita < 10 && tabuleiroJogador1[_linha][colunaDireita] == 0) {
						tabuleiroJogador1[_linha][colunaDireita] = _navio;

						slots--;
					} else {
						for (int colunaEsquerda = (_primeiraColuna) - 1; slots > 0; colunaEsquerda--) {
							if (colunaEsquerda >= 0 && tabuleiroJogador1[_linha][colunaEsquerda] == 0) {
								tabuleiroJogador1[_linha][colunaEsquerda] = _navio;

								slots--;

								if (slots == 0) {
									break;
								}
							}
						}
						break;
					}
				}
				autoPreenchidoSucesso = true;
			} else if (_coluna < _primeiraColuna) {
				for (int colunaEsquerda = _coluna; slots > 0; colunaEsquerda--) {
					if (colunaEsquerda >= 0 && tabuleiroJogador1[_linha][colunaEsquerda] == 0) {
						tabuleiroJogador1[_linha][colunaEsquerda] = _navio;

						slots--;
					} else {
						for (int colunaDireita = (_primeiraColuna) + 1; slots > 0; colunaEsquerda++) {
							if (colunaEsquerda < 10 && tabuleiroJogador1[_linha][colunaEsquerda] == 0) {
								tabuleiroJogador1[_linha][colunaEsquerda] = _navio;

								slots--;

								if (slots == 0) {
									break;
								}
							}
						}
						break;
					}
				}
			}
			autoPreenchidoSucesso = true;
		} else {// Jogador 2
			// Auto preenchimento na vertical - pra baixo.
			if (_linha > _primeiraLinha) {
				for (int linhaBaixo = _linha; slots > 0; linhaBaixo++) {
					if (linhaBaixo < 10 && tabuleiroJogador2[linhaBaixo][_coluna] == 0) {
						tabuleiroJogador2[linhaBaixo][_coluna] = _navio;

						slots--;
					} else {
						for (int linhaCima = (_primeiraLinha) - 1; slots > 0; linhaCima--) {
							if (linhaCima >= 0 && tabuleiroJogador2[linhaCima][_coluna] == 0) {
								tabuleiroJogador2[linhaCima][_coluna] = _navio;

								slots--;

								if (slots == 0) {
									break;
								}
							}
						}
						break;
					}
				}
				autoPreenchidoSucesso = true;
			} else if (_linha < _primeiraLinha) { // Preenchimento pra cima.
				for (int linhaCima = _linha; slots > 0; linhaCima--) {
					if (linhaCima >= 0 && tabuleiroJogador2[linhaCima][_coluna] == 0) {
						tabuleiroJogador2[linhaCima][_coluna] = _navio;

						slots--;
					} else {
						for (int linhaBaixo = (_primeiraLinha) + 1; slots > 0; linhaBaixo++) {
							if (linhaBaixo >= 0 && tabuleiroJogador2[linhaBaixo][_coluna] == 0) {
								tabuleiroJogador2[linhaBaixo][_coluna] = _navio;

								slots--;

								if (slots == 0) {
									break;
								}
							}
						}
						break;
					}
				}
				autoPreenchidoSucesso = true;
			} else if (_coluna > _primeiraColuna) {
				for (int colunaDireita = _coluna; slots > 0; colunaDireita++) {
					if (colunaDireita < 10 && tabuleiroJogador2[_linha][colunaDireita] == 0) {
						tabuleiroJogador2[_linha][colunaDireita] = _navio;

						slots--;
					} else {
						for (int colunaEsquerda = (_primeiraColuna) - 1; slots > 0; colunaEsquerda--) {
							if (colunaEsquerda >= 0 && tabuleiroJogador2[_linha][colunaEsquerda] == 0) {
								tabuleiroJogador2[_linha][colunaEsquerda] = _navio;

								slots--;

								if (slots == 0) {
									break;
								}
							}
						}
						break;
					}
				}
				autoPreenchidoSucesso = true;
			} else if (_coluna < _primeiraColuna) {
				for (int colunaEsquerda = _coluna; slots > 0; colunaEsquerda--) {
					if (colunaEsquerda >= 0 && tabuleiroJogador2[_linha][colunaEsquerda] == 0) {
						tabuleiroJogador2[_linha][colunaEsquerda] = _navio;

						slots--;
					} else {
						for (int colunaDireita = (_primeiraColuna) + 1; slots > 0; colunaEsquerda++) {
							if (colunaEsquerda < 10 && tabuleiroJogador2[_linha][colunaEsquerda] == 0) {
								tabuleiroJogador2[_linha][colunaEsquerda] = _navio;

								slots--;

								if (slots == 0) {
									break;
								}
							}
						}
						break;
					}
				}
			}
			autoPreenchidoSucesso = true;
		}
	}

	// Esse metodo me garante que existe espaco na vertical ou horizontal ou que nao
	// existe espaco.
	public static void verificarEspacosHoriVertTabuleiro(int _jogador, int _linha, int _coluna, int _slots,
			int _navio) {

		existeEspacoHorizontal = false;
		existeEspacoVertical = false;
		int slotsPraPorNavioHORI = 0;
		int slotsMaxHORI = 0;
		int slotsPraPorNavioVERT = 0;
		int slotsMaxVERT = 0;
		if (_jogador == 1) { // Jogador 1
			// Percorrer a linha inteira pra ver se tem navio nela
			// Se existe, agora vou contar se tem slots suficientes
			// na horiz para por um navio.
			for (int col = 0; col < max; col++) {
				if (tabuleiroJogador1[_linha][col] == 0 || tabuleiroJogador1[_linha][col] == _navio) {
					// Cada slot vazio eu incremento o valor da variavel
					slotsPraPorNavioHORI++;

					// Aqui eu armazeno sempre o maior valor que eu consegui
					if (slotsMaxHORI < slotsPraPorNavioHORI) {
						slotsMaxHORI = slotsPraPorNavioHORI;
					}
				} else {
					slotsPraPorNavioHORI = 0;
				}
			}

			for (int lin = 0; lin < max; lin++) {
				if (tabuleiroJogador1[lin][_coluna] == 0 || tabuleiroJogador1[lin][_coluna] == _navio) {
					slotsPraPorNavioVERT++;
					if (slotsMaxVERT < slotsPraPorNavioVERT) {
						slotsMaxVERT = slotsPraPorNavioVERT;
					}
				} else {
					slotsPraPorNavioVERT = 0;
				}
			}
		} else {

			for (int col = 0; col < max; col++) {
				if (tabuleiroJogador2[_linha][col] == 0 || tabuleiroJogador2[_linha][col] == _navio) {
					slotsPraPorNavioHORI++;
					if (slotsMaxHORI < slotsPraPorNavioHORI) {
						slotsMaxHORI = slotsPraPorNavioHORI;
					}
				} else {
					slotsPraPorNavioHORI = 0;
				}
			}

			for (int lin = 0; lin < max; lin++) {
				if (tabuleiroJogador2[lin][_coluna] == 0 || tabuleiroJogador2[lin][_coluna] == _navio) {
					slotsPraPorNavioVERT++;
					if (slotsMaxVERT < slotsPraPorNavioVERT) {
						slotsMaxVERT = slotsPraPorNavioVERT;
					}
				} else {
					slotsPraPorNavioVERT = 0;
				}
			}
		}

		if (slotsMaxHORI >= _slots) {
			existeEspacoHorizontal = true;
		}

		if (slotsMaxVERT >= _slots) {
			existeEspacoVertical = true;
		}
	}

	// Construcao do tabuleiro inicial
	public static void construirTabuleiro(int _jogador) {

		if (_jogador == 1) {
			System.out.println();
			System.out.println("------------> VEZ DO JOGADOR 1 <------------");
			for (int i = 0; i < max; i++) {
				System.out.print("  " + i + " ");
			}
			System.out.println();
			for (int linha = 0; linha < max; linha++) {
				for (int coluna = 0; coluna < max; coluna++) {

					// Cada valor e um barco diferente, entao printamos valores
					// diferentes.
					if (tabuleiroJogador1[linha][coluna] == 1) {
						System.out.print("| 1 ");
					}
					if (tabuleiroJogador1[linha][coluna] == 2) {
						System.out.print("| 2 ");
					}
					if (tabuleiroJogador1[linha][coluna] == 3) {
						System.out.print("| 3 ");
					}
					if (tabuleiroJogador1[linha][coluna] == 4) {
						System.out.print("| 4 ");
					}
					if (tabuleiroJogador1[linha][coluna] == 0) {
						System.out.print("|   ");
					}
					if (coluna == (max - 1)) {
						System.out.print("|  " + linha);

					}
				}
				System.out.println();
			}

		} else {
			System.out.println();
			System.out.println("------------> VEZ DO JOGADOR 2 <------------");
			for (int i = 0; i < max; i++) {
				System.out.print("  " + i + " ");
			}
			System.out.println();
			for (int linha = 0; linha < max; linha++) {
				for (int coluna = 0; coluna < max; coluna++) {
					if (tabuleiroJogador2[linha][coluna] == 1) {
						System.out.print("| 1 ");
					}
					if (tabuleiroJogador2[linha][coluna] == 2) {
						System.out.print("| 2 ");
					}
					if (tabuleiroJogador2[linha][coluna] == 3) {
						System.out.print("| 3 ");
					}
					if (tabuleiroJogador2[linha][coluna] == 4) {
						System.out.print("| 4 ");
					}
					if (tabuleiroJogador2[linha][coluna] == 0) {
						System.out.print("|   ");
					}
					if (coluna == (max - 1)) {
						System.out.print("|  " + linha);
					}
				}
				System.out.println();
			}
		}
	}

	// Verificamos se o usuario conseguiu atingir os 14 acertos, que e o total
	// de slots de embarcacao
	public static boolean verificarVencedor() {
		if (jogador1Acertos == 14) {

			for (int i = 0; i < 10; i++) {
				System.out.println();
			}

			System.out.println("  ____    _   __     _______ _   _  ____ _____ _   _ \r\n"
					+ " |  _ \\  / |  \\ \\   / / ____| \\ | |/ ___| ____| | | |\r\n"
					+ " | |_) | | |   \\ \\ / /|  _| |  \\| | |   |  _| | | | |\r\n"
					+ " |  __/  | |    \\ V / | |___| |\\  | |___| |___| |_| |\r\n"
					+ " |_|     |_|     \\_/  |_____|_| \\_|\\____|_____|\\___/ ");
			jogador1Venceu = true;
			return true;
		} else if (jogador2Acertos == 14) {

			for (int i = 0; i < 10; i++) {
				System.out.println();
			}

			System.out.println("  ____    ____    __     _______ _   _  ____ _____ _   _ \r\n"
					+ " |  _ \\  |___ \\   \\ \\   / / ____| \\ | |/ ___| ____| | | |\r\n"
					+ " | |_) |   __) |   \\ \\ / /|  _| |  \\| | |   |  _| | | | |\r\n"
					+ " |  __/   / __/     \\ V / | |___| |\\  | |___| |___| |_| |\r\n"
					+ " |_|     |_____|     \\_/  |_____|_| \\_|\\____|_____|\\___/ ");
			jogador2Venceu = true;
			return true;
		} else {
			jogador1Venceu = false;
			jogador2Venceu = false;
			return false;
		}
	}

	//JOGO DA FORCA

	// Nesse metodo a gente verifica o tempo todo se o jogador ja atingiu
	// o limite de tentativas que ele tem (6) pois quando esse metodo retornar
	// false, signifa que nao tem mais tentativas.
	public static boolean verificarTentativas() {
		if (numTentativas <= 5) {
			return true;
		} else {
			System.out.println("\nMAXIMO DE TENTATIVAS \nVOCÊ PERDEU!!! ");
			return false;
		}

	}

	// Nesse metodo nos verificamos se o jogador acertou a palavra inteira
	// caso ele tenha acertado, nos exibimos que o jogador venceu e
	// atribuimos "true" a variavel: acertouPalavra
	// que nos usamos em outro momento para saber quando finalizar o jogo.
	public static void idVencedorPerdedor(boolean venceu) {
		if (venceu == true) {
			acertouPalavra = true;
			System.out.println("ACERTOU A PALAVRA, VENCEU!!");
		} else {
			acertouPalavra = false;
			System.out.println("ERROU A PALAVRA, PERDEU");
		}
	}

	// Aqui nos validamos a jogada, impedimos do jogador digitar
	// qualquer caractere invalido, como numeros, caracteres especiais e etc...
	public static boolean validarJogada(String _letra) {
		if (_letra.substring(0, _letra.length()).matches("[A-Z]*")) {
			validado = true; // confirmamos que a letra ou palavra está valida.
			return true;
		} else {
			validado = false; // confirmamos que a letra ou palavra é invalida
			return false;
		}
	}

	// Nesse metodo nos efetuamos a jogada, recebemos a jogada validada por outro
	// metodo.Em seguida nos tornamos as letras sempre maiusculas para manter um padrao..
	// neste mesmo metodo nos identificamos se é uma letra ou uma palavra, caso seja
	// letra nos armazenamos ela para impedir o usuario de digitar a mesma letra em outra
	// jogada
	public static void efetuarJogada(String _letra, boolean _primeiraJogada) {
		if (_primeiraJogada == true) { 
			validado = true;

		} else {
			_letra = _letra.toUpperCase();// Deixando os caracteres sempre maiusculso
			if (validarJogada(_letra) == false) {
				System.out.println("Jogada invalida");
			} else {
				int qtdLetra = _letra.length();
				letraDigitada = false; // Deixando a variavel sempre False no inicio do metodo
				if (qtdLetra > 1) { // Verificando se a variavel tem mais de um caracter
					ePalavra = true; // Deixando a variavel bool true
				} else {
					for (int i = 0; i < 6; i++) {

						if (_letra.equals(letrasUsadas[i])) {// Verificando se o caracter já esta armazenado
							letraDigitada = true;
							System.out.println("LETRA JÁ ULTILIZADA ");
							break;
						}
					}
					if (letraDigitada == false) {
						letrasUsadas[contador] = _letra;// Armazenando caracteres na variavel de indice "contador"
						contador++;
					}
				}
				letraPalavraEscolhida = _letra;
			}
		}

	}

	// Este metodo e responsavel por gerar a palavra, comparar se a letra existe
	// e tambem identificar se a palavra digitada pelo jogador esta igual a palavra
	// gerada
	// caso seja igual, o jogador venceu e chamamos o metodo responsavel por lidar
	// com a vitoria e derrota.
	public static void gerarSublinhado(boolean letraExiste, String jogada) {

		if (validado == true) {
			// Vetor responsavel por armazenar os tracos iniciais
			String[] tracos = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
			// Vetor responsavel por armazenar as palavras que vao ser geradas pelo usuario.
			String[] palavras = new String[] { "ESPOSA", "HUMANO", "INSETO", "ANALOGIA", "CONCEITO", "FUNDAMENTO",
					"METICULOSO", "FUNCIONALIDADE", "PECULIARIDADE" };
			Random escolhedor = new Random();

			// Tentativa só vai ser zero uma unica vez entao nos printamos a primeira vez a palavra apenas
			// com o sublinhado.
			if (tentativas == 0) {
				indice = escolhedor.nextInt(5);// Gerando o indice de uma palavra qualquer no vetor de palavras
				palavraEscolhida = palavras[indice]; // escolher palavra do vetor aleatoriamente
				construirForca(true); // construir forca sem o desenho
				for (int i = 0; i < palavraEscolhida.length(); i++) {
					tracos[i] = "_ ";
					System.out.print(tracos[i]); // construindo sublinhado
				}
				sublinhado = tracos;
				System.out.println("");
			} else {// caso nao seja a primeira vez que executamos.
				if (ePalavra == true) {// se for uma palavra e nao uma letra
					if (letraPalavraEscolhida.equals(palavras[indice])) {// verificamos se a palavra digitada é correta.
						idVencedorPerdedor(true);// chamamos o metodo dizendo que o jogador acertou e venceu.
						numTentativas = 5;// acabam-se as tentativas, entao atribuimos ao maximo.
					} else {
						idVencedorPerdedor(false);
						numTentativas = 5;// acabam-se as tentativas, entao atribuimos ao maximo.
					}
				} else {// se nao for uma palavra e sim uma letra.
					int qtdCaracter = palavraEscolhida.length();
					for (int j = 0; j < qtdCaracter; j++) {
						String letra = palavraEscolhida.substring(j, (j + 1)); // Atribuindo substring na posição a
																				// variavel
						if (jogada.equals(letra)) { // se a letra jogada for igual a letra na posição J do vetor,
													// executa o bloco
							sublinhado[j] = jogada + " "; // substitui tracinho por letra
							letraExiste = true;
						}
					}
					if (letraExiste == true) {// Caso a letra exista
						numTentativas++;
						construirForca(true); // chamamos o metodo da forca normalmente
					} else {// caso nao exista a letra, ou seja, jogador errou.
						numTentativas++;
						construirForca(false);// chamamos o metodo da forca mas pedindo pra ele acrescentar
												// um membro no corpo do boneco
					}

					for (int k = 0; k < qtdCaracter; k++) {
						System.out.print(sublinhado[k]); // printa o sublinhado com as letras que o jogador acertou
															// caso nao tenha acertado, ele printa os tracos de antes.
					}
				}
			}
			tentativas++;// independente de acertar ou errar, perde-se uma tentativa.
		}
	}

	// Metodo para exibir os textos que ficam em cima da forca
	public static void titulo() {
		System.out.println("           <<<< JOGO DA FORCA >>>>\n");
		System.out.println("- Você deverar acertar a palavra em 6 tentativas");
		System.out.println("- A Qualquer momento voce pode chutar a palavra");
		if (numTentativas == 6) {// caso o numero de tentativas se esgote, printamos que acabou.
			System.out.println("Tentativa: ACABOU!! ");
		} else {// caso ainda tenha tentativas, printamos o numero dela +1, porque
				// a variavel comeca em zero.
			System.out.println("\nTentativa: " + (numTentativas + 1));
		}
	}

	// Metodo responsavel por construi a forca de acordo com a necessidade
	public static void construirForca(boolean _acertou) {
		// Caso o usuario tenha errado o palpite
		if (_acertou == false) {
			desenho[qntErros] = desenhobck[qntErros];// pegamos o membro do nosso vetor de membros
														// e colocamos dentro do vetor que vai ser printado

			// Sequencia de print pra limpar a tela
			for (int j = 0; j < 10; ++j)
				System.out.println();

			// Exibimos o titulo o tempo inteiro.
			titulo();

			// Sequencia de print que busca no vetor os membros ja adicionados ao vetor de
			// membros.
			System.out.println("  ______                                                      ");
			System.out.println(" |      |                                                     ");
			System.out.println(" |      " + desenho[0] + "                                    ");
			System.out.println(" |     " + desenho[2] + "" + desenho[1] + "" + desenho[3] + " ");
			System.out.println(" |     " + desenho[4] + " " + desenho[5] + "                  ");
			System.out.println(" |                                                            ");
			System.out.println("_|                                                            ");
			qntErros++; // como ele errou, aumentamos a quantidade de erros.
		} else {
			// Mesma coisa do que ta acima porem, sem adicionar membros nem aumentar a
			// quantidade de erros.
			for (int j = 0; j < 10; ++j)
				System.out.println();

			titulo();

			System.out.println("  ______");
			System.out.println(" |      |                                         ");
			System.out.println(" |      " + desenho[0] + "                            ");
			System.out.println(" |     " + desenho[2] + "" + desenho[1] + "" + desenho[3] + " ");
			System.out.println(" |     " + desenho[4] + " " + desenho[5] + "              ");
			System.out.println(" |                                                ");
			System.out.println("_|                                                ");
		}
	}

	//JOGO DA VELHA

	public static boolean verificarVencedorVelha() {
		boolean vencer = false;

		// Esse metodo vai verificar por cada linha onde as 3 jogadas forem iguais
		// quando os 3 valores forem iguais, significa que temos um vencedor
		for (int linha = 0; linha < 3; linha++) {
			if (tabuleiro[linha][0] == 1 && tabuleiro[linha][1] == 1 && tabuleiro[linha][2] == 1) {
				jogadorVencedor = "X";// setamos o jogador vencedor
				vencer = true;// retornamos true.
			}

			if (tabuleiro[linha][0] == 2 && tabuleiro[linha][1] == 2 && tabuleiro[linha][2] == 2) {
				jogadorVencedor = "O";
				vencer = true;
			}
		}

		// Mesma coisa do FOR acima so que para as colunas, funciona da mesma forma
		// quando os 3 valores nas colunas forem iguais, temos um vencedor.
		for (int coluna = 0; coluna < 3; coluna++) {

			if (tabuleiro[0][coluna] == 1 && tabuleiro[1][coluna] == 1 && tabuleiro[2][coluna] == 1) {
				jogadorVencedor = "X";
				vencer = true;
			}
			if (tabuleiro[0][coluna] == 2 && tabuleiro[1][coluna] == 2 && tabuleiro[2][coluna] == 2) {
				jogadorVencedor = "O";
				vencer = true;
			}
		}

		// verificamos as diagonais, se elas possuem o valor 1
		// quer dizer que o vencedor foi o primeiro jogador
		// caso nao, o vencedor foi o O.
		if (tabuleiro[0][0] == 1 && tabuleiro[1][1] == 1 && tabuleiro[2][2] == 1) {
			jogadorVencedor = "X";
			vencer = true;
		}
		if (tabuleiro[0][0] == 2 && tabuleiro[1][1] == 2 && tabuleiro[2][2] == 2) {
			jogadorVencedor = "O";
			vencer = true;
		}
		if (tabuleiro[0][2] == 1 && tabuleiro[1][1] == 1 && tabuleiro[2][0] == 1) {
			jogadorVencedor = "X";
			vencer = true;
		}
		if (tabuleiro[0][2] == 2 && tabuleiro[1][1] == 2 && tabuleiro[2][0] == 2) {
			jogadorVencedor = "O";
			vencer = true;
		}

		return vencer;
	}

	public static boolean validarJogadorVelha(String _jogador) {
		// Apenas retornamos true se a jogada foi X ou O, sao os unicos
		// inputs que nos aceitamos.
		if (_jogador.equals("X") || _jogador.equals("O")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validarJogadaVelha(int _linha, int _coluna) {
		// Aqui verificamos se o jogador nao digitou nenhum valor que estoura
		// o limite do nosso vetor(3) pra nao quebrar o jogo
		if (_linha < maxVelha && _coluna < maxVelha) {

			// Ja aqui verificamos se a jogada esta sendo feita em um espaco
			// que nao tem jogada nenhuma.
			if (tabuleiro[_linha][_coluna] == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static void efetuarJogadaVelha(int _jogador, int _linha, int _coluna) {

		// Toda vez que entrar nesse metodo, a jogada recebe false na validacao
		// pois ela so vai ser validada depois de passar pelo if.
		jogadaValidada = false;
		if (validarJogadaVelha(_linha, _coluna) == true) {

			// Caso a jogada tenha sido validada, nos atribuimos true pra essa variavel.
			jogadaValidada = true;

			if (_jogador == 1) {// Se quem jogou foi o primeiro jogador
				tabuleiro[_linha][_coluna] = 1;// Atribuimos o valor dele dentro da matriz.
				vez_Velha = 2;// Passamos a vez para o jogador seguinte

				// Apos passar a vez, nos verificamos sempre apos cada jogada
				// se houve um vencedor ou o jogo teve um empate
				verificarVencedorVelha();
				verificarEmpate();

			} else if (_jogador == 2) {// Mesmo conceito do anterior mas para o jogador 2
				tabuleiro[_linha][_coluna] = 2;
				vez_Velha = 1;
				verificarVencedorVelha();
				verificarEmpate();
			} else {
				tabuleiro[_linha][_coluna] = 0;
				verificarVencedorVelha();
				verificarEmpate();
			}
		} else {// Jogada invalida caso o jogador tenha entrado com um dado nao permitido.
			System.out.println("Jogada Invalida!\nLinha/Coluna ja usada ou valor invalido");
		}
	}

	public static void jogadorUmJogadorDois() {

		// Apenas exibicao para o usuario digitar quem inicia o jogo
		System.out.println("Jogo da Velha");
		System.out.println("Quem inicia o jogo X ou O");
		jogador1 = leiaVelha.next().toUpperCase();// Tornamos maiusculo.

		// Enquanto a entrada do usuario nao for valida, ele vai ter que digitar o
		// jogador ate que seja validado.
		while (validarJogadorVelha(jogador1) == false) {
			System.out.println("Jogo da Velha");
			System.out.println("Quem inicia o jogo X ou O");
			System.out.println("Jogador Invalido, digite novamente!");
			jogador1 = leiaVelha.next().toUpperCase();
		}

		// Se for X entao o jogador 2 vai ser O, caso contrario o jogador 2 vai ser X
		if (jogador1.equals("X")) {
			jogador2 = "O";
			vez_Velha = 1;
		} else {
			jogador2 = "X";
			vez_Velha = 2;
		}
		System.out.println("Jogador '" + jogador1 + "' inicia o jogo!!!");

	}

	public static void construirTabuleiroVelha() {

		// O Metodo efetuar jogada vai setar essa variavel para esse metodo caso
		// a jogada tenha sido validada, a partir dai esse metodo vai ou nao
		// construir o tabuleiro novamente.
		if (jogadaValidada == true) {

			for (int i = 0; i < 10; i++) {
				System.out.println();
			}
			System.out.println();

			// Construcao do tabuleiro
			for (int linha = 0; linha < maxVelha; linha++) {
				for (int coluna = 0; coluna < maxVelha; coluna++) {

					// Caso exista o valor 1 nessa linha/coluna
					// Printamos um X
					if (tabuleiro[linha][coluna] == 1) {
						System.out.print("| X ");
					}

					// Mesma coisa mas com o jogador 2
					if (tabuleiro[linha][coluna] == 2) {
						System.out.print("| O ");
					}

					// Caso o campo nao tenha jogadas, printamos espaco vazio.
					if (tabuleiro[linha][coluna] == 0) {
						System.out.print("|   ");
					}

					// Aqui e apenas para printar a ultima coluna.
					if (coluna == (maxVelha - 1)) {
						System.out.print("|");
					}
				}
				System.out.println();
			}

			if (verificarVencedorVelha() == true) {
				System.out.println("Jogador: '" + jogadorVencedor + "' Venceu o jogo!");
			} else if (verificarEmpate() == true) {
				System.out.println("O Jogo deu Empate!");
			}
		}
	}

	private static boolean verificarEmpate() {
		boolean empate = false;
		int qntJogadas = 0;

		for (int linha = 0; linha < maxVelha; linha++) {
			for (int coluna = 0; coluna < maxVelha; coluna++) {
				if (tabuleiro[linha][coluna] != 0) {
					// a matriz posui 9 espacos para jogadas, cada vez que um espaco desse
					// for diferente de 0, ou seja, é um espaço que ja existe uma jogada
					// a qntJogadas acrescenta em 1, entao quando chegar a 9 significa
					// que nao tem mais espaco para jogar, deu empate.
					qntJogadas++;
				}
			}
		}

		if (qntJogadas == 9) {// Chegar a 9, deu empate.
			empate = true;
		} else {
			empate = false;
		}
		return empate;
	}
}
