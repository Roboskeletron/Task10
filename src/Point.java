import java.util.Locale;
import java.util.Scanner;

public record Point(double x, double y) {

    public static Point parse(String text) {
        Scanner scanner = new Scanner(text);
        scanner.useLocale(Locale.ROOT);

        return new Point(scanner.nextDouble(), scanner.nextDouble());
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
