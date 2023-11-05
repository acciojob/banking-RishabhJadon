package com.driver;

public class CurrentAccount extends BankAccount{
    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;

        if(balance<5000)
        {
            throw new Exception("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!valid(tradeLicenseId))
        {
            String rearrangedString = rearrangeString(tradeLicenseId);
            if(rearrangedString.equals(""))
            {
                throw new Exception("Valid License can not be generated");
            }
            else {
                tradeLicenseId = rearrangedString;
            }
        }
    }

    boolean valid(String str)
    {
        for(int i=0;i<str.length()-1;i++)
        {
            if(str.charAt(i)==str.charAt(i+1))
            {
                return false;
            }
        }
        return true;
    }

    static String rearrangeString(String str)
    {
        int n = str.length();

        if(n==0)
        {
            return "";
        }

        int[] arr = new int[26];

        for(int i=0;i<26;i++)
        {
            arr[i] = 0;
        }
        for(char ch : str.toCharArray())
        {
            arr[ch-'A']++;
        }

        char ch_max = getMaxCountChar(arr);
        int maxCount = arr[ch_max-'A'];

        //check if result is possible or not
        if(maxCount>(n+1)/2)
        {
            return "";
        }

        StringBuilder res = new StringBuilder();
        res.append(" ".repeat(n));

        int ind=0;
        //filling the most frequently occuring char at even indices
        while(maxCount-->0)
        {
            res = new StringBuilder(str.substring(0,ind) + ch_max + str.substring(ind+1));
            ind+=2;
        }
        arr[ch_max-'A']=0;

        //now filling others characters first fill even characters than odd characters
        for(int i=0;i<n;i++)
        {
           while(arr[i]-->0)
           {
             ind = (ind>=n)?1:ind;
             res = new StringBuilder(str.substring(0,ind) + (char)('A'+i) + str.substring(ind+1));
             ind +=2;
           }
        }

        return res.toString();
    }

   public static char getMaxCountChar(int arr[])
   {
       int max = 0;
       char ch = ' ';

       for(int i=0;i<arr.length;i++)
       {
           if(arr[i]>max)
           {
               max = arr[i];
               ch = (char)(i+'A');
           }
       }
       return ch;
   }

}
