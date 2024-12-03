package tps.entrada_e_saida;

import java.io.*;
import java.util.*;

public class CompararArquivos {

    public static void main(String[] args) {
        String arquivoEsperado = "pub.out";      
        String arquivoGerado = "pub.out";        

        // Listas para armazenar as linhas de cada arquivo
        List<String> linhasEsperadas = new ArrayList<>();
        List<String> linhasGeradas = new ArrayList<>();

        // Lendo o arquivo de saída esperado
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoEsperado))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhasEsperadas.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo esperado: " + e.getMessage());
            return;
        }

        // Lendo o arquivo gerado pelo código
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoGerado))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhasGeradas.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo gerado: " + e.getMessage());
            return;
        }

        // Comparando as duas listas linha por linha
        int totalLinhas = Math.max(linhasEsperadas.size(), linhasGeradas.size());
        int acertos = 0;
        int erros = 0;

        System.out.println("Comparando as linhas...");

        for (int i = 0; i < totalLinhas; i++) {
            String linhaEsperada = (i < linhasEsperadas.size()) ? linhasEsperadas.get(i) : "";
            String linhaGerada = (i < linhasGeradas.size()) ? linhasGeradas.get(i) : "";

            if (linhaEsperada.equals(linhaGerada)) {
                acertos++;
            } else {
                erros++;
                System.out.println("Erro na linha " + (i + 1) + ": Esperado: \"" + linhaEsperada + "\" | Gerado: \"" + linhaGerada + "\"");
            }
        }

        // Calculando a porcentagem de acerto
        double porcentagemAcerto = (acertos / (double) totalLinhas) * 100;

        System.out.println("\nResultados da comparação:");
        System.out.println("Total de linhas comparadas: " + totalLinhas);
        System.out.println("Acertos: " + acertos);
        System.out.println("Erros: " + erros);
        System.out.printf("Porcentagem de acerto: %.2f%%\n", porcentagemAcerto);
    }
}

