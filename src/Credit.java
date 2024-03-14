import java.text.DecimalFormat;
import java.util.Objects;

public class Credit
{
    private final double sum; // Сумма кредита
    private final double rate; // Годовая ставка
    private final int term; // Срок кредита в месяцах
    DecimalFormat formatter = new DecimalFormat("0.00");

    public Credit(double sum, double rate, int term)
    {
        this.sum = sum;
        this.rate = rate/100;
        this.term = term;
        System.out.println("Сумма кредита: " + sum);
        System.out.println("Ставка: " + rate + " %");
        System.out.println("Срок: " + term + " месяцев");
        calculation_credit();
    }
    public void calculation_credit()
    {
        double monthlyPayment = calculateMonthlyPayment(sum, rate, term);
        double remainingPrincipal = sum;

        System.out.println(
                "Месяц  " + "|" +
                        " Ежемесячный платеж " + "|" +
                        " Основной долг " + "|" +
                        " Долг по процентам " + "|" +
                        " Остаток основного долга "
        );
        for (int i = 1; i <= term; i++) {
            double interest = remainingPrincipal * rate / 12;
            double principal = monthlyPayment - interest;
            remainingPrincipal -= principal;
            if(Objects.equals(formatter.format(remainingPrincipal), formatter.format(-0.00)))
            {
                remainingPrincipal = 0.00;
            }
            System.out.println(
                     i +
                            " | " +  formatter.format(monthlyPayment) +
                             " | " + formatter.format(principal) +
                             " | " + formatter.format(interest) +
                             " | " + formatter.format(remainingPrincipal)
            );
        }

    }

    public static double calculateMonthlyPayment(double sum,double rate, int term) {
        double monthlyInterestRate = rate / 12;
        return sum * (monthlyInterestRate *Math.pow(1 + monthlyInterestRate, term) ) /
                ( Math.pow(1 + monthlyInterestRate, term) - 1);

    }

}
