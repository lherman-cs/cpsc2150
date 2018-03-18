package cpsc2150.labs.lab2;

/**
 * Created by Lukas Herman (lukash) and Jacob Sonshein (jsonshe) on 1/29/2018.
 */

/**
 * @invariant houseCost > 0 and downPayment > 0 
 *            and percentDown = downPayment / houseCost * 100
 *            and years is in {15, 20, 25, 30} 
 *            and interestRate = the value calculated by calcRate()
 *            and payment = the value calculated by calcPayment() 
 *            and customer is not null.
 */
public class Mortgage {
    private double houseCost;
    private double downPayment;
    private double percentDown;
    private int years;
    private double interestRate;
    private double payment;
    private Customer customer;

    /**
     * Sets the house cost, down payment, years and customer. Calculates the percent down, rate, and payment
     *
     * @param cost is the houseCost
     * @param dp is the downPayment
     * @param y is the years
     * @param cust is the customer
     * @requires cost > 0 
     *           and dp > 0 
     *           and y is in {15, 20, 25, 30} 
     *           and cust is not null
     * @ensures houseCost = cost 
     *          and downPayment = dp 
     *          and years = y 
     *          and customer = cust
     *          and percentDown = downPayment / houseCost * 100 
     *          and interestRate = the value calculated by calcRate() 
     *          and payment = the calculated by calcPayment()
     */
    Mortgage(double cost, double dp, int y, Customer cust) {
        houseCost = cost;
        downPayment = dp;
        years = y;
        customer = cust;
        percentDown = downPayment / houseCost * 100;
        calcRate();
        calcPayment();
    }

    /**
     * Calculates the interest rate to use.
     * The rate starts at the base rate of 2.5%
     * If the loan is for 30 years, add the normal addition of 1%
     * If the loan is for for less than 30 years, add the good addition of .5%
     * The loan must be for 15, 20, 25 or 30 years 
     * 
     * Add the rate for the credit score based on the following table
     * Credit rating | Credit score | add to rate
     * Bad           | 0 - 499      | 10%
     * Fair          | 500 - 599    | 5%
     * Good          | 600 - 699    | 1%
     * Great         | 700 - 749    | .5%
     * Excellent     | 750 - 850    | 0%
     * 
     * If the down payment is less than 20% of the price of the house, add .5%
     * 
     * @requires years is in {15, 20, 25, 30} 
     *           and 0 <= customer's credit score <= 850
     * @ensures 0.03 <= interestRate <= 0.14 All properties except interestRate
     *          stay the same.
     */
    private void calcRate() {
        int creditScore = customer.getCreditScore();

        // Check years
        interestRate = years == 30 ? 3.5 : 3;

        // Check credit score
        if(creditScore <= 499) interestRate += 10;
        else if(creditScore <= 599) interestRate += 5;
        else if(creditScore <= 699) interestRate += 1;
        else if(creditScore <= 749) interestRate += 0.5;

        // Check down payment
        if(downPayment < houseCost / 5) interestRate += 0.5;

        interestRate /= 100;
    }

    /**
     * Calculate the monthly payment
     * terms:
     *  r - monthly rate - the interest rate divided by 12
     *  p - principal balance - the cost of the house minus the down payment
     *  n - number of payments - the total number of monthly payments
     * 
     * monthly payment is (rp)/(1-(1+r)^-n)
     * 
     * @requires interestRate = the value calculated by calcRate() 
     *           and houseCost > 0
     *           and 0 < downPayment < houseCost 
     *           and years is in {15, 20, 25, 30}
     * @ensures payment = ((interestRate / 12) * (houseCost - downPayment)) / (1 - (1 + (interestRate / 12) ^ -(years * 12) )))
     */
    private void calcPayment() {
        double r = interestRate / 12;
        double p = houseCost - downPayment;
        double n = years * 12;

        payment = (r * p) / (1 - Math.pow((1 + r), -n));
    }

    /**
     * If the interest rate is too high (10% or higher) the loan is denied
     * If the down payment is less than 3.5% of the price of the house then the loan is denied
     * 
     * If the Debt to income ratio is above 40% the loan is denied
     * The debt to income ratio is the total monthly debt payments (including the mortgage payment) / monthly income
     * 
     * Otherwise the loan is approved
     * 
     * @return false iff interestRate >= 0.1
     *         or downPayment < 3.5 * houseCost / 100
     *         or (customer's monthly debt payments + payment)/ customer's monthly income > 0.4.
     *         Else, true.
     * @requires interestRate = the value calculated by calcRate()
     *           payment = the value calculated by calcPayment()
     *           and houseCost > 0
     *           and 0 < downPayment < houseCost 
     *           and customer's monthly debt payments > 0 
     *           and customer's monthly income > 0
     * @ensures all the properties will stay the same 
     *          and return true if the loan is approved
     *          or false if otherwise
     */
    public boolean loanApproved() {
        if(interestRate >= 0.1) return false;
        if(downPayment < 3.5 * houseCost/ 100) return false;
        if((customer.getMonthlyDebtPayments() + payment) / (customer.getIncome() / 12) > 0.4) return false;
        return true;
    }

    /**
     * A getter for payment
     * 
     * @requires downPayment > 0 and interestRate > 0 and payment > 0
     * @return the monthly payment on the loan
     * @ensures all the properties will stay the same 
     *          and return payment's value
     */
    public double getPayment() {
        return payment;
    }

    /**
     * A getter for interestRate
     * 
     * @requires interestRate > 0
     * @return the interest rate on the loan
     * @ensures all the properties will stay the same 
     *          and return interestRate's value
     */
    public double getRate() {
        return interestRate;
    }

    /**
     * Generate a human-readable description about the instance 
     * 
     * @return the instance's human-readable description string
     * @ensures all the properties will stay the same
     *          and return the description 
     * 
     */
    @Override
    public String toString() {
        //this function is provided
        //DO you need contracts for this?
        String str = "";
        if (loanApproved()) {
            str += "Principal Amount: $" + (houseCost - downPayment) + "\n";
            str += "Interest Rate: " + (interestRate * 100) + "%\n";
            str += "Term: " + years + " years\n";
            str += "Monthly Payment: $" + payment + "\n";
        } else {
            str += "Loan was not approved";
        }
        return str;
    }

}
