//programa que faz a crifra de vigenere
//c = k ^ m

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Vigenere {
    private static String encripitar(String texto, String chave) {
        String cifra = "";

        for(int i = 0 ; i < texto.length() ; i++){
            int letraTexto = texto.charAt(i);
            int letraChave = chave.charAt(i % chave.length()); //estou pegando o resto da divisão para deixar a chave do mesmo tamanho do texto
            int letraCifra = (letraTexto ^ letraChave);

            String temp = Integer.toHexString(letraCifra);
            if(temp.length()==1) temp = ("0" + temp);

            cifra += temp;
        }

        return cifra;
    }

    private static String decriptar(String cifra, String chave) {
        String texto = "";

        for(int i = 0; i < cifra.length(); i += 2){ //pular de 2 em 2 pois está lendo um hexadecimal
            int letraCifra = Integer.parseInt(
                            cifra.substring(i, i + 2), 16);
            int letraChave = chave.charAt((i / 2) % chave.length());
            int letraTexto = (letraCifra ^ letraChave);
            
            texto += ((char) letraTexto);
        }

        return texto;
    }

    public static void main(String[] args) {
        //declaração de variaveis
        BufferedReader leitor = new BufferedReader(
                                new InputStreamReader(System.in));
        String texto = "";
        String chave = "";
        String cifra = "";

        try {
            //Processo de encriptação
            System.out.println("Digite um texto: ");
            texto = leitor.readLine();

            System.out.println("Digite uma chave: ");
            chave = leitor.readLine();

            cifra = encripitar(texto, chave);
            System.out.println(cifra);

            // Processo de decriptação
            System.out.println("Digite a cifra: ");
            cifra = leitor.readLine();

            System.out.println("Digite a chave: ");
            chave = leitor.readLine();

            texto = decriptar(cifra, chave);
            System.out.println(texto);

        } catch (Exception erro){
            System.out.println("FODEU !!!!" + erro);
        }
    }
}