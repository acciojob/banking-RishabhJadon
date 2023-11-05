package com.driver;

public class BankAccount {

    private String name;

    public double getBalance() {
        return balance;
    }

    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        StringBuilder accountNo = new StringBuilder();
        int j = (int)Math.ceil((double) sum/digits);
        if(j<=9)
        {
            int i = j;
            while(sum>=0 && digits>0)
            {
               if(i>9)
               {
                   i = j;
               }
               if(sum<i) {
                   i = sum;
               }
               accountNo.append(i);
               sum -= i;
               ++i;
               --digits;
            }
            return accountNo.toString();
        }
        else {
            throw new Exception("Account Number can not be generated");
        }
    }

    public void deposit(double amount) {
        //add amount to balance
        balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        try
        {
           if(balance-amount>=minBalance)
           {
             balance -= amount;
           }
        }
        catch (Exception e)
        {
           System.out.println("Insufficient Balance");
        }
    }

}