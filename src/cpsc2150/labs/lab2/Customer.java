package cpsc2150.labs.lab2;

/**
 * Created by Lukas Herman (lukash) and Jacob Sonshein (jsonshe) on 1/29/2018.
 */

/**
 * @invariant name != NULL and monthlyDebtPayments > 0 and income > 0 and 0 <= creditScore <= 850
 *               and loan != NULL
**/
public class Customer {
    private String name;

    private double monthlyDebtPayments;
    private double income;
    private int creditScore;
    private Mortgage loan;

    /** 
     * Set the monthly debt payments, income, credit score and name
     * Credit scores range from 0 - 850
     * 
     * @param debt is monthlyDebtPayments
     * @param inc is income
     * @param score is creditSore
     * @param n is name
     * @requires debt > 0 and inc > 0 and 0 <= score <= 850 and n != NULL
     * @ensures monthlyDebtPayments = debt and income = inc and creditScore = score and name = n
     */
    Customer(double debt, double inc, int score, String n) {
        // Customer constructor
        monthlyDebtPayments = debt;
        income = inc;
        creditScore = score;
        name = n;
    }

    /**
     * Take the parameters and apply for a loan. Save the loan details as the customers current loan
     * Return whether or not the loan was approved
     * 
     * @param downPayment is the down payment
     * @param houseCost is the house cost
     * @param years is years
     * @requires downPayment > 0 and houseCost > 0 and years is in {15, 20, 25 30}
     * @ensures applyForLoan will either be true or false 
     *          and loan = new Mortgage(houseCost, downPayment, years, this)
     *          and monthlyDebtPayments = loan.getPayment()
     * @return return true if the loan is approved and false if it's denied
     */
    public boolean applyForLoan(double downPayment, double houseCost, int years) {
        loan = new Mortgage(houseCost, downPayment, years, this);
        monthlyDebtPayments = loan.getPayment();
        
        
        if(loan.loanApproved()){
          return true;
        }
        else{
          return false;
        }
    }

    /**
     * return the interest rate on the current loan
     * 
     * @requires intrest rate > 0 and years is in {15, 20, 25, 30} and 0 <= creditScore <= 850
     * @ensures That you can get the value of the interest rate
     * @return The interest rate
     */
    public double getRate() {

        return loan.getRate();
    }

    /**
     * return the monthly payment on the current loan
     * 
     * @requires intrestRate > 0 and 0 <= creditScore <= 850
     * @ensures That you can retreive the value for monthly payments
     * @return The monthly payment
     */
    public double getMonthlyPay() {
        return loan.getPayment();
    }

    /**
     * return the customers monthly debt payments
     * 
     * @requires monthlyDebtPayments > 0 and income > 0 and 0 <= creditScore <= 850
     * @ensures That you can retreive the value for monthlyDebtPayments
     * @return the monthlyDebtPayments
     */
    public double getMonthlyDebtPayments() {

        return monthlyDebtPayments;
    }

    /**
     * return the customers income
     * 
     * @requires name != NULL and income > 0
     * @ensures That you can retreive the customers income
     * @return income
     */
    public double getIncome() {
        return income;
    }

    /**
     * return the customers credit score
     * 
     * @requires 0 <= creditScore <= 850
     * @ensures That you can retreive the customers credit score
     * @return creditScore
     */
    public int getCreditScore() {
        return creditScore;
    }

    /**
     * Generate a human-readable description about the instance 
     * 
     * @return the instance's human-readable description string
     * 
     */
    @Override
    public String toString() {
        //this function is provided
        //DO you need contracts for this?
        String str = "";
        str += "Name: " + name + "\n";
        str += "Income: $" + income + "\n";
        str += "Credit Score: " + creditScore + "\n";
        str += "Monthly Debt: $" + monthlyDebtPayments + "\n";
        str += "Mortgage info: \n";
        //str += loan.toString();
      
        if( loan != null) {
          str += loan.toString();
        }

        return str;

    }
}
