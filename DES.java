import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES {
    private static String encript(String texto, String chave) throws Exception{
        
        Cipher objCifra = Cipher.getInstance("DESede"); //inicialização da cifra

        SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "DESede"); //expecificação e expanção da chave

        objCifra.init(Cipher.ENCRYPT_MODE, objChave);

        byte[] cifra = objCifra.doFinal(texto.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(cifra);
    }

    private static String decript(String cifra, String chave) throws Exception{
        
        Cipher objCifra = Cipher.getInstance("DESede"); //inicialização da cifra

        SecretKey objChave = new SecretKeySpec(chave.getBytes("UTF-8"), "DESede"); //expecificação e expanção da chave

        objCifra.init(Cipher.DECRYPT_MODE, objChave);

        byte[] texto = objCifra.doFinal(Base64.getDecoder().decode(cifra));

        return new String(texto, "UTF-8");
    }

    public static void main(String[] args) {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Digite um texto: ");
            String texto = leitor.readLine();

            System.out.print("Digite uma chave: ");
            String chave = leitor.readLine();

            String cifra = encript(texto, chave);
            System.out.println(cifra);
            System.out.println(decript(cifra, chave));

        } catch (Exception erro) {
            System.out.println(erro);
        }
    }
}