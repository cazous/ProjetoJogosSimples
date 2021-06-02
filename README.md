# Projeto JogosSimples

O projeto consiste em três jogos simples: **jogo da forca, jogo da velha e batalha naval**. Utilizados para treinar conceitos básicos de programação estruturada utilizando JAVA.


## Conteúdo

**Jogo da Forca:** 
> O jogo consiste em completar/acertar a palavra gerada em 6 tentativas. Para cada letra errada, uma parte do "bonequinho" será printada até esgotar a quantidade de tentativas. Em caso de acerto, as letras preencherão os sublinhados. Também é permitido adivinhar a palavra completa.

**Jogo da Velha:**
> O jogo se trata de conseguir marcar três símbolos iguais (**"X"** ou **"O"**) em sequência. Ao começarmos o jogo, decidimos quem irá primeiro. Cada jogador dará as coordenadas da matriz onde deseja inserir o seu símbolo. Ganha quem completar a sequência primeiro.
>
> *Empates serão informados quando não houver mais nenhuma coordenada livre e nenhuma sequência formada.*

**Batalha Naval:**
>O objetivo do jogo é afundar os navios no tabuleiro adversário. Ambos receberão uma matriz de 10x10 para posicionar os seus navios. Cada jogador dará as coordenadas na matriz que desejam posicionar seus navios e, posteriormente ao posicionamento, darão as coordenadas que desejam acertar na matriz do adversário. Ganha quem acertar todos os navios adversários primeiro. 



## Como jogar:
O projeto se trata de jogos simples para serem jogados em console. Sendo o jogo da forca "single-player" e os demais para dois jogadores. Apenas digite o número correspondente ao jogo no menu inicial e a partida começara.

**Jogo da Forca**

 - Digite uma letra ou uma palavra. Em caso de erro, um membro do bonequinho será printado. 
 - Em caso de acerto, a letra aparecerá no lugar dos sublinhados. Palavras inteiras podem ser tentadas.
 
**Jogo da Velha** 
- Escolha quem será o primeiro jogador (**X** ou **O**)
- Digite as coordenadas que deseja marcar o seu símbolo na matriz **3x3**
- Complete uma sequência de três símbolos em qualquer direção (vertical, horizontal ou diagonal) antes que o adversário para vencer.

**Batalha Naval**
- Cada jogador receberá uma matriz 10x10 como seu tabuleiro para posicionar os seus navios
- Informe a primeira coordenada que deseja por o seu navio, depois informe a segunda.
*(O código verificará se há espaços disponíveis para colocar os navios e completará automaticamente as posições restantes se disponíveis)*
- Após posicionados os navios, o tabuleiro adversário aparecerá vazio. Escolha uma coordenada para acertar. Caso acerte, a mensagem **"Você acertou uma embarcação!"** e será marcado um **"O"** no tabuleiro adversário. Caso erre, a mensagem **"Você atirou na água! =/"** aparecerá e será marcado um **"~"** no tabuleiro adversário.
- Ganha quem acertar todas as posições dos navios adversários primeiro.

## Autor
 **Caio Leal**
 - https://www.linkedin.com/in/caio-leal-de-souza-pinheiro-852a7b1a1/
 - caiolspinheiro@gmail.com





