
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;




public class TP04Q02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String caminhoArquivo = "/tmp/pokemon.csv";
        List<Pokemo> pokemos = Pokemo.ler(caminhoArquivo);
        Arvore arvore =new Arvore();
        Lista seguencia = new Lista();
        
        int [] vetor =  {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12 ,14};
        for(int i= 0 ;i < vetor.length;i++){
            arvore.inserir(vetor[i]);
        }

        String input;
        while (!(input = scanner.nextLine()).equals("FIM")) {
            int po = Integer.parseInt(input);
            for (Pokemo produto : pokemos) {
                if (produto.getid() == po) {
                    seguencia.inserirFim(produto);

                }
            }
        }
        for(int i=0;i<seguencia.N;i++){
            arvore.inserirpokemon(seguencia.list[i]);
        }
        while (!(input = scanner.nextLine()).equals("FIM")) {
            System.out.println(input);
            Pokemo poke = new Pokemo();
            for (Pokemo produto : pokemos) {
                if (produto.getnome() == input) {
                    poke= produto;

                }
            }
            
           String resultado = arvore.consultarduas(poke) ? "SIM" : "NAO";
           System.out.println(resultado); 
            
        }


    

       scanner.close();
    }
}
class No{
    No esq,dir;
    int elemento;
    Arvoredois subarvore;

   No(int elemento){
        this(elemento,null,null,null);
    }
     No(int element,No di, No es,Arvore arv){
        this.elemento = element;
        this.dir =di;
        this.esq = es;
    }
}
class NoSub{
    NoSub esq,dir;
    Pokemo elemento;


    NoSub(Pokemo elemento){
        this(elemento,null,null);
    }
    NoSub(Pokemo element,NoSub di, NoSub es){
        this.elemento = element;
        this.dir =di;
        this.esq = es;
        
    }
}


class Arvore{
    No raiz;
    Arvore(){raiz = null;}

    void inserir(int x){
        raiz = inserir(x,raiz);
    }
    No inserir(int x, No i){
        if(i==null){
            i = new No(x);
        }
        else{

         if( x<i.elemento){
            i.esq =inserir(x, i.dir);

        }
        else if(x>i.elemento ){
            i.dir =inserir(x, i.esq);

        }
        
    }
        return i;
    }
    



    void inserirpokemon(Pokemo nome){
        int capture = nome.getcapture()%15;
        No insere = achano(capture);
        if (insere == null) {
            System.out.println("no = null");
            
        }
        else{
        insere.subarvore.inserir(nome);
    }}
    No achano(int nome){
        System.out.print("=>raiz ");
        return achano(nome,raiz);

    }
    No achano(int nome,No i){
        No resp = i;
        if (i==null) {
            resp = null;
        }
        else {
       
        if(nome>i.elemento){
           achano(nome,i.esq);
        }
        
        else if(nome>i.elemento) {
         achano(nome,i.dir);
            
        }}
    return resp;}

    boolean consultarduas(Pokemo pokemo){
        boolean resp = false;
        int capture = pokemo.getcapture()%15;
        No insere = achano(capture);
        if(consutar(capture)){
            resp = insere.subarvore.consutar(pokemo.getnome());
        }
    



        return resp;
    }

    boolean consutar(int nome){
        System.out.print("=>raiz ");
        return consutar(nome,raiz);

    }
    boolean consutar(int nome,No i){
        boolean resp = false;
        if (i==null) {
            resp = false;
        }
        else {
       
         if(nome == i.elemento){
            resp =true;
        }
        else if(nome>i.elemento){
            System.out.print("esq ");
            resp = consutar(nome,i.esq);
        }
        
        else {
            System.out.print("dir ");
            resp = consutar(nome,i.dir);
            
        }
        
        }
        return resp;
    }


}

class Arvoredois{
    NoSub raiz;
    Arvoredois(){raiz = null;}

    void inserir(Pokemo x){
        raiz = inserir(x,raiz);
    }
    NoSub inserir(Pokemo x, NoSub i){
        if(i==null){
            i =new NoSub(x);
        }
        else{
        int resultado = x.getnome().compareTo(i.elemento.getnome());

         if(resultado < 0){
            i.esq =inserir(x, i.esq);

        }
        else if(resultado > 0 ){
            i.dir =inserir(x, i.dir);

        }
        if (resultado == 0) {
        }
    }
        return i;
    }

    boolean consutar(String nome){
        System.out.print("=>raiz ");
        return consutar(nome,raiz);

    }
    boolean consutar(String nome,NoSub i){
        boolean resp = false;
        if (i==null) {
            resp = false;
        }
        else {
        int resultado = nome.compareTo(i.elemento.getnome());
         if(resultado== 0){
            resp =true;
        }
        else if(resultado < 0){
            System.out.print("esq ");
            resp = consutar(nome,i.esq);
        }
        
        else {
            System.out.print("dir ");
            resp = consutar(nome,i.dir);
            
        }
        
        }
        return resp;
    }


}

class Matriz {
    private int linhas;
    private int colunas;
    private int[][] dados;

    // Construtor
    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.dados = new int[linhas][colunas];
    }

    // Método para ler os dados da matriz
    public void lerDados(Scanner scanner) {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                dados[i][j] = scanner.nextInt();
            }
        }
    }

    // Soma de matrizes
    public Matriz soma(Matriz outra) {
        if (linhas != outra.linhas || colunas != outra.colunas) {
            throw new IllegalArgumentException("Matrizes incompatíveis para soma.");
        }
        Matriz resultado = new Matriz(linhas, colunas);
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                resultado.dados[i][j] = dados[i][j] + outra.dados[i][j];
            }
        }
        return resultado;
    }

    // Multiplicação de matrizes
    public Matriz multiplicacao(Matriz outra) {
        if (colunas != outra.linhas) {
            throw new IllegalArgumentException("Matrizes incompatíveis para multiplicação.");
        }
        Matriz resultado = new Matriz(linhas, outra.colunas);
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < outra.colunas; j++) {
                for (int k = 0; k < colunas; k++) {
                    resultado.dados[i][j] += dados[i][k] * outra.dados[k][j];
                }
            }
        }
        return resultado;
    }

    // Mostrar diagonal principal
    public void mostrarDiagonalPrincipal() {
        for (int i = 0; i < Math.min(linhas, colunas); i++) {
            System.out.print(dados[i][i] + " ");
        }
        System.out.println();
    }

    // Mostrar diagonal secundária
    public void mostrarDiagonalSecundaria() {
        for (int i = 0; i < Math.min(linhas, colunas); i++) {
            System.out.print(dados[i][colunas - i - 1] + " ");
        }
        System.out.println();
    }

    // Imprimir matriz
    public void imprimir() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(dados[i][j] + " ");
            }
            System.out.println();
        }
    }
}


class Lista {
    public Pokemo[] list;
    public int N; 

    public Lista() {
        list = new Pokemo[802]; 
        this.N = 0; 
    }

    public void inserirFim(Pokemo pokemon) {
        if (N < list.length) {
            list[N++] = pokemon;
        } else {
            System.out.println("Lista cheia.");
        }
    }

    public void inserirInicioPosicao(Pokemo pokemon, int n) {
        if (n >= 0 && N < list.length) {
            for (int i = N; i > n; i--) { // Mudei para N ao invés de list.length
                list[i] = list[i - 1]; // Remaneja os elementos
            }
            list[n] = pokemon; // Insere o novo Pokémon na posição n
            N++;
        } else {
            System.out.println("Posição inválida ou lista cheia.");
        }
    }

    public void inserirInicio(Pokemo pokemon) {
        if (N < list.length) {
            for (int i = N; i > 0; i--) {
                list[i] = list[i - 1]; // Remaneja os elementos
            }
            list[0] = pokemon; // Insere o novo Pokémon no início
            N++;
        } else {
            System.out.println("Lista cheia.");
        }
    }

    public Pokemo removerInicio() {
        if (N == 0) {
            System.out.println("Lista vazia.");
            return null;
        }

        Pokemo primeiro = list[0];
        for (int i = 1; i < N; i++) {
            list[i - 1] = list[i];
        }
        // list[--N] = null; // Opcional: limpa a referência
        N--;
        return primeiro;
    }

    public Pokemo remover(int posicao) {
        if (posicao < 0 || posicao >= N) {
            System.out.println("Posição inválida.");
            return null;
        }

        Pokemo removido = list[posicao];
        for (int i = posicao + 1; i < N; i++) {
            list[i - 1] = list[i];
        }
        N--;
        // list[--N] = null; // Opcional: limpa a referência
        return removido;
    }

    public Pokemo removerFim() {
        if (N == 0) {
            System.out.println("Lista vazia.");
            return null;
        }

        Pokemo ultimo = list[--N];
        // list[N] = null; // Opcional: limpa a referência
        return ultimo;
    }

    public int size() {
        return N;
    }

    public void imprimir() {
        for (int i = 0; i < N; i++) {
            System.out.print("[" + i + "] ");
            System.out.println(list[i].getnome());
        }
    }
    
}

class Pokemo {
    private int id;
    private int gen;
    private String Nome;
    private String Descricao;
    private String[] tipes;
    private String[] vetorhabilidade;
    private float peso;
    private float altura;
    private int capture;
    private boolean lengd;
    private LocalDate data;
    // private Lista lista;

    public Pokemo(int id, int gen, String Nome, String Descricao, String[] tipes, String[] vetorhabilidade, float peso,
            float altura, int capture, boolean lengd, LocalDate data) {
        this.id = id;
        this.Nome = Nome;
        this.Descricao = Descricao;
        this.tipes = tipes;
        this.vetorhabilidade = vetorhabilidade;
        this.peso = peso;
        this.altura = altura;
        this.capture = capture;
        this.lengd = lengd;
        this.gen = gen;
        this.data = data;

    }

    public Pokemo() {
    }

    public int getid() {
        return id;
    }

    public int getgen() {
        return gen;
    }

    public String getnome() {
        return Nome;
    }

    public String getdescricao() {
        return Descricao;
    }

    public float getpeso() {
        return peso;
    }

    public float getaltura() {
        return altura;
    }

    public int getcapture() {
        return capture;
    }

    public boolean getlengend() {
        return lengd;
    }

    public LocalDate getdata() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public void setTipes(String[] tipes) {
        this.tipes = tipes;
    }

    public void setVetorHabilidade(String[] vetorhabilidade) {
        this.vetorhabilidade = vetorhabilidade;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setCapture(int capture) {
        this.capture = capture;
    }

    public void setLengend(boolean lengd) {
        this.lengd = lengd;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Pokemo cloned = (Pokemo) super.clone();
        // Clonar os arrays para evitar referências compartilhadas
        cloned.tipes = tipes.clone();
        cloned.vetorhabilidade = vetorhabilidade.clone();
        return cloned;
    }

    public static void medidor(long startTime, int comparacoes) {
        long endTime = System.nanoTime(); // Finaliza contagem do tempo
        long duration = endTime - startTime; // Tempo em nanossegundos
        double seconds = (double) duration / 1_000_000_000.0; // Converte para segundos

        // Cria o arquivo de log
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("matricula_selecao.txt"))) {
            writer.write("Matrícula: 861222\tTempo: " + seconds + "s\tComparações: " + comparacoes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Pokemo> ler(String caminhoArquivo) {
        List<Pokemo> pokemos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            // Pula o cabeçalho
            linha = br.readLine(); // Lê e descarta a linha do cabeçalho

            while ((linha = br.readLine()) != null) {
                // Divide a linha por vírgulas, considerando aspas
                String[] colunas = linha.split(",(?=(?:[^\"]|\"[^\"]*\")*$)");

                // Verifica se obteve todas as colunas necessárias
                if (colunas.length < 12) {
                    // System.out.println("Dados incompletos para Pokémon, linha atual: " + linha);
                    continue; // Pula para o próximo Pokémon se os dados estiverem incompletos
                }

                // Processa as colunas
                int id = Integer.parseInt(colunas[0].trim());
                int gen = Integer.parseInt(colunas[1].trim());
                String nome = colunas[2].trim();
                String descricao = colunas[3].trim();

                // Ajusta para tipos, considerando que podem ter 1 ou 2 tipos
                List<String> tipesList = new ArrayList<>();
                tipesList.add(colunas[4].trim()); // Primeiro tipo
                if (colunas.length > 5) {
                    tipesList.add(colunas[5].trim()); // Segundo tipo, se existir
                }
                String[] tipes = tipesList.toArray(new String[0]); // Converte para array

                // Limpeza das habilidades
                String habilidadesStr = colunas[6].replaceAll("\\[|\\]|\"", "").trim();
                String[] vetorhabilidade = habilidadesStr.split(",\\s*");
                // ... (código anterior permanece igual)

                float peso = 0;
                if (!colunas[7].trim().isEmpty()) {
                    peso = Float.parseFloat(colunas[7].trim());
                }

                float altura = 0;
                if (!colunas[8].trim().isEmpty()) {
                    altura = Float.parseFloat(colunas[8].trim());
                }

                int capture = Integer.parseInt(colunas[9].trim());
                boolean lengd = Integer.parseInt(colunas[10].trim()) != 0; // Conversão para boolean
                String dataString = colunas[11].trim(); // Obter a string da coluna
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(dataString, formatter);

                // Criação do Pokémon
                Pokemo pokemo = new Pokemo(id, gen, nome, descricao, tipes, vetorhabilidade, peso, altura, capture,
                        lengd,
                        data);
                pokemos.add(pokemo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokemos;
    }

    public static void ordenanomes(ArrayList<Pokemo> nome) {
        int n = nome.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                // Comparar nomes dos nome para ordenar em ordem alfabética
                if (nome.get(j).getnome().compareTo(nome.get(j + 1).getnome()) > 0) {
                    // Trocar nome[j] e nome[j+1]
                    Pokemo temp = nome.get(j);
                    nome.set(j, nome.get(j + 1));
                    nome.set(j + 1, temp);
                }
            }

        }
    }

    public static void imprimihab(String[] list) {
        int tam = list.length;
        int i = 0;
        for (String habilidade : list) {
            if (i > 0 && i != tam) {
                System.out.print(", ");

            }
            System.out.print(habilidade);
            i++;
        }
    }

    public static void imprimipo(String[] list) {
        int tam = list.length;
        for (int i = 0; i < tam; i++) {
            if (list[i] != "") {
                System.out.print("'");
                System.out.print(list[i]);
                System.out.print("'");
            }

            if (i + 1 < tam && list[i + 1] != "") {
                System.out.print(", ");
            }

        }
    }

    public static void imprimir(String str, List<Pokemo> pokemos) {
        int codigo = Integer.parseInt(str);
        // saida no formato do verde
        for (Pokemo p : pokemos) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = p.getdata().format(formatter);
            if (p.getid() == codigo) {
                System.out.print("[#" + p.getid() + " -> " + p.getnome() + ": " + p.getdescricao() + " - ");
                System.out.print("[");
                imprimipo(p.tipes);
                System.out.print("]");
                System.out.print(" - [");
                imprimihab(p.vetorhabilidade);
                System.out.print("] - ");
                System.out.print(p.getpeso() + "kg - " + p.getaltura() + "m - " + p.getcapture() + "%" +
                        " - " + (p.getlengend() ? "true" : "false") +
                        " - " + p.getgen() + " gen] - " + dataFormatada);
                System.out.println();
            }
        }
    }

    public static void imprimirsequencia(Lista pokemos) {
        for(int i =0;i<pokemos.N;i++){
            System.out.println("["+i+"] "+pokemos.list[i].toString());
        }


    }

    public static boolean comparaStrings(String s1, String s2) {
        // Verifica se os tamanhos são diferentes
        if (s1.length() != s2.length()) {
            return false; // As strings são diferentes
        }

        // Compara cada caractere
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                return false; // As strings são diferentes
            }
        }

        return true; // As strings são iguais
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = getdata().format(formatter);
        return "[#" + getid() + " -> " + getnome() + ": " + getdescricao() + " - " + arrayToStringH(tipes) + " - "
                + arrayToString(vetorhabilidade) + " - " + getpeso() + "kg - " + getaltura() + "m - " + getcapture()
                + "%" +
                " - " + (getlengend() ? "true" : "false") +
                " - " + getgen() + " gen] - " + dataFormatada;
    }

    // private String arrayToString(String[] list) {
    //     StringBuilder sb = new StringBuilder();
    //     sb.append("[");
    //     for (int i = 0; i < list.length; i++) {
    //         if (list[i] != "") {
    //             sb.append("'");
    //             sb.append(list[i]);
    //             // sb.append("'");
    //         }
    //         if (i + 1 < list.length && list[i + 1] != "")
    //             sb.append(", ");
    //     }
    //     sb.append("]");
    //     return sb.toString();
    // }

    private String arrayToString(String[] list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean firstElement = true; // Para controlar a primeira inserção
    
        for (String item : list) {
            if (!item.isEmpty()) { // Verifica se a string não está vazia
                if (!firstElement) {
                    sb.append(", "); // Adiciona vírgula antes de elementos que não são o primeiro
                }
                sb.append("").append(item); // Envolve o item em aspas
                firstElement = false; // Marca que o primeiro elemento foi adicionado
            }
        }
    
        sb.append("]");
        return sb.toString();
    }
    private String arrayToStringH(String[] list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean firstElement = true; // Para controlar a primeira inserção
    
        for (String item : list) {
            if (!item.isEmpty()) { // Verifica se a string não está vazia
                if (!firstElement) {
                    sb.append(", "); // Adiciona vírgula antes de elementos que não são o primeiro
                }
                sb.append("'").append(item).append("'"); // Envolve o item em aspas
                firstElement = false; // Marca que o primeiro elemento foi adicionado
            }
        }
    
        sb.append("]");
        return sb.toString();
    }
    
}