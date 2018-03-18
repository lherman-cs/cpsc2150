package cpsc2150.labs.lab2;

/**
 * Created by Lukas Herman (lukash) and Jacob Sonshein (jsonshe) on 1/29/2018.
 */
public class MortgageApp {

    public static void main(String [] args)
    {
        Customer cust = new Customer(1200, 105000, 720, "John Campbell");
        cust.applyForLoan(8000, 145000, 30);
        System.out.println(cust.toString());

    }
}
