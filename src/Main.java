import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Quantas equipes estão cadastradas?");
        int qtdEquipes = sc.nextInt();

        int[] equipes = new int[qtdEquipes];
        double[] notasDesign = new double[qtdEquipes];

        //comeco parte 1
        int contador = 0;

        do {
            if (equipes[contador] == 0) { // Verifica se a equipe ja tem numero e a nota é invalida

                System.out.println("Qual o numero da " + (contador + 1) + "° equipe? (Escolha entre 11 e 99)");
                int numero = sc.nextInt();//Se nao tiver define o numero
                if (!(numero > 10 && numero < 100)) {
                    System.err.println("Número inválido, por favor escolha um número entre 11 e 99!");
                } else {
                    boolean numeroRepetido = false;
                    for (int j : equipes) {
                        if (numero == j) {
                            System.out.println("Esse numero ja foi pego, por favor escolha outro");
                            numeroRepetido = true;
                        }
                    }
                    if (!numeroRepetido) {
                        equipes[contador] = numero;
                    }
                }
            }
            if (notasDesign[contador] == 0 && equipes[contador] != 0) {
                System.out.println("Qual a nota de desing da equipe " + equipes[contador] + "?");
                double nota = sc.nextDouble();
                if (nota >= 0 && nota <= 10) {
                    notasDesign[contador] = nota;
                    contador++;
                } else {
                    System.err.println("Nota invalida, por favor insira novamente");
                }
            }
        } while (contador < qtdEquipes);
        System.out.println("equipes: " + Arrays.toString(equipes));
        //fim da parte 1

        int[] pontuacao = new int[qtdEquipes];

        for (int i = 0; i < qtdEquipes; i++) {//duelo todos contra todos
            for (int j = 0; j < qtdEquipes - 1; j++) {
                if (i > j){//garante que uma equipe nao se enfrente nem enfrente outras mais de 1 vez(recomendo desenhar
                            //para entender a lógica.
                    int ptI = random.nextInt(10);
                    int ptJ = random.nextInt(10);

                    System.out.println("Equipe 1: "+ equipes[i] + " " + ptI);
                    System.out.println("Equipe 2: "+ equipes[j] + " " + ptJ);
                    if (ptI > ptJ){// Primeiro time venceu
                        pontuacao[i] += 1;
                    } else if (ptJ > ptI) {// Segundo time venceu
                        pontuacao[j] += 1;
                    } else {//Empate
                        if (notasDesign[i] > notasDesign[j]) pontuacao[i] += 1;//Verifica nota de design pra desempatar
                        else if(notasDesign[i] < notasDesign[j]) pontuacao[j] += 1;

                    }
                }
            }
        }
        //fim da parte 2

        //inicio da parte 3
        for (int i = 0; i < qtdEquipes; i++) {// percorre todas as equipes
            for (int j = 0; j < qtdEquipes; j++) {// compara a pontuacao equipe com todos as outras
                if (i != j) {
                    boolean ehMaior = false;
                    if (pontuacao[i] > pontuacao[j]) {
                        ehMaior = true;
                    } else if (pontuacao[i] == pontuacao[j]) {
                        if (notasDesign[i] > notasDesign[j]) {
                            ehMaior = true;
                        }
                    }
                    if (ehMaior) {
                        trocaPosicao(equipes, i, j);
                        trocaPosicao(pontuacao, i, j);

                        double aux = notasDesign[i];
                        notasDesign[i] = notasDesign[j];
                        notasDesign[j] = aux;
                    }
                }
            }
            //fim da parte 3
        }

        for (int i = 0; i < equipes.length; i++) {
            System.out.println((i + 1) + "° colocado(a): Equipe " + equipes[i] + " com a pontuação de " + pontuacao[i] +
                    (pontuacao[i] > 1 ? " pontos." : " ponto."));
        }

    }

    private static void trocaPosicao(int[] vetor, int i, int j) {
        int aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }
}