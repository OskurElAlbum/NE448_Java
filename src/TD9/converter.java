package TD9;

public class converter {
    public static void main(String[] args) {
        String str = "MONTANT=15EURO";
        // Utilisation d'une expression régulière pour extraire les chiffres
        String numberStr = str.replaceAll("\\D+", "");
        
        // Conversion de la chaîne de caractères en entier
        int number = Integer.parseInt(numberStr);
        
        System.out.println(number);
    }
}