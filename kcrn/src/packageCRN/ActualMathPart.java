package packageCRN;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ActualMathPart {

    public static String[] Rozlisit(String startIn, String endIn, String moneyIn){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/L/y", Locale.GERMANY);

        LocalDate startDate = LocalDate.parse(startIn, formatter);

        LocalDate endDate = LocalDate.parse(endIn, formatter);

        int money = Integer.parseInt(moneyIn);

        int days = Math.toIntExact(java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate)) + 1;

        int startYear = startDate.getYear();
        int endYear = endDate.getYear();

        int[] pole = new int[endYear - startYear + 1];

        pole[0] = java.time.Year.of(startYear).length() - startDate.getDayOfYear() + 1;

        for (int i = startYear + 1; i < endYear; i++){
            pole[i - startYear] = java.time.Year.of(i).length();
        }

        pole[pole.length - 1] = endDate.getDayOfYear();

        double moneyPerDay = ((double) money) / days;

        double sum = 0;

        String[] returning = new String[(endYear - startYear + 2 ) * 3];
        
        for (int i = 0; i < pole.length; i++){
            double thisMoney = Math.round(moneyPerDay * pole[i] * 100d) * 0.01d;
            sum += thisMoney;

            returning[i * 3] = Integer.toString(startYear + i);
            returning[i * 3 + 1] = Integer.toString(pole[i]);
            returning[i * 3 + 2] = String.format("%.2f", thisMoney);
        }

        returning[returning.length - 3] = "Celkem: ";
        returning[returning.length - 2] = Integer.toString(days);
        returning[returning.length - 1] = String.format("%.2f", sum);

        return returning;
    }

}
