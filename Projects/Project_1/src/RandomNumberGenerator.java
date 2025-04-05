import java.util.Random;

public class RandomNumberGenerator {

    private static final Random random = new Random();

    /**
     * Genera un número aleatorio con una longitud entre 5 y 8 dígitos.
     * 
     * @return Un String que representa el número aleatorio.
     */
    public static String generateRandomNumber() {
        int length = 5 + random.nextInt(4); // Genera un número entre 5 y 8
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // Agrega un dígito aleatorio (0-9)
        }
        return sb.toString();
    }
}
