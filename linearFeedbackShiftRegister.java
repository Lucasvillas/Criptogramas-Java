import java.io.BufferedReader;
import java.io.InputStreamReader;


public class linearFeedbackShiftRegister {
    
    private static void inicializar(boolean[] registrador){
        BufferedReader leitor = new BufferedReader(
                                                    new InputStreamReader(System.in));
        String chave = "";

        try{
            System.out.println("Digite uma chave de 4 letras: ");
            chave = leitor.readLine();

            for(int i = 0 ; i < 4 ; i++){ //pegar uma letra de cada vez
                int letra = chave.charAt(i);
                String binario = Integer.toBinaryString(letra);

                for(int j = 0 ; j < (8 - binario.length()) ; j++){ //alinhamento dos 8 bits do binario
                    binario = ("0" + binario);
                }

                for(int j = 0 ; j < 8 ; j++){ //incerir valor ao registrador
                    registrador[j + (8 * i)] = (binario.charAt(j) == '1');
                }
            }

        } catch(Exception erro){
            System.out.println(erro);
        }
    }

    private static boolean realocar(boolean[] registrador, boolean tipo){
        boolean retorno = registrador[0];
        boolean novoBit = retorno;

        if(! tipo){
            novoBit = (novoBit ^ registrador[31] ^ registrador[6] ^ registrador[4] ^ registrador[2] ^ registrador[1] ^ registrador[0]);
        } else {
            novoBit = (novoBit ^ registrador[31] ^ registrador[6] ^ registrador[5] ^ registrador[1]);
        }

        for(int i = 0 ; i < 31 ; i++){ // deslocamento propriamente dito, apenas até o penultimo
            registrador[i] = registrador[i+1];
        }
        registrador[31] = novoBit;

        return retorno;
    }

    public static void main(String[] args) {
        //declaração de variaveis

        boolean[] cabeca = new boolean[32];
        boolean[] registrador0 = new boolean[32];
        boolean[] registrador1 = new boolean[32];

        inicializar(cabeca);
        inicializar(registrador0);
        inicializar(registrador1);

        String letra = "";
        while (true){

            boolean bit0 = registrador0[0];
            boolean bit1 = registrador1[0];

            if(!realocar(cabeca, false)){
                bit0 = realocar(registrador0, false);
            } else {
                bit1 = realocar(registrador1, true);
            }

            letra += ((bit0 ^ bit1) ? "1" : "0");
            if (letra.length() == 8) {
                System.out.println((char) Integer.parseInt(letra, 2));
                letra = "";
            }
        }
    }
}