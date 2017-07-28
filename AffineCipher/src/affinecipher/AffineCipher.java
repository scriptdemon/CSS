
package affinecipher;

import java.util.Scanner;


public class AffineCipher {
    
    int mkey,akey,inverseKey;
    String plaintext,encrypted,decrypted;
    public void getMkey(int mkey)
    {
        this.mkey = mkey;
    }
    public void getAkey(int akey)
    {
        this.akey = akey;
    }
    public void getPlainText(String plaintext)
    {
        this.plaintext = plaintext;
    }
    
    public int getIndex(char c)
    {
        if(Character.isLowerCase(c))
            return (int)c - 97;
        else
            return (int)c - 65;
    }
    public char encryptChar(char c)
    {
        if(Character.isLowerCase(c))
        return (char)((mkey*getIndex(c)+akey)%26 + 97);
        else
        return (char)((mkey*getIndex(c)+akey)%26 + 65);
    }
    public char decryptChar(char c)
    {
        if(Character.isLowerCase(c))
        return (char)(mod((inverseKey*(getIndex(c)-akey)),26) + 97);
        else
        return (char)(mod((inverseKey*(getIndex(c)-akey)),26) + 65);
    }
    public boolean encryptText()
    {
      if(gcd(26,mkey)==1)
      {
       char[] arr = plaintext.toCharArray();
       char[] ans = new char[arr.length];
       for(int i=0;i<arr.length;i++)
       {
           ans[i] = encryptChar(arr[i]);
       }
       encrypted = new String(ans);
       return true;
      }
      else
      {
          System.out.println("Invalid mkey");
          return false;
      }
    }
    
    public void findInverseKey()
    {
        int t1=0,t2=1,r1=26,r2=mkey;
        int q=r1/r2;
        int r=r1%r2;
        int t = t1-q*t2;
        while(r!=0)
        {
            t1=t2;
            t2=t;
            
            r1=r2;
            r2=r;
            
            q=r1/r2;
            r=r1%r2;
            
            t=t1-q*t2;
        }
        if(t2>0)
        inverseKey=t2;
        else
        inverseKey=t2+26;
    }
    public void decryptText()
    {
        char[] q =  encrypted.toCharArray();
        char[] ans =  new char[q.length];
        for(int i=0;i<q.length;i++)
        {
            ans[i] = decryptChar(q[i]);
        }
        decrypted=new String(ans);
        System.out.println(decrypted);
    }
    public void showEncrypted()
    {
        System.out.println(encrypted);
    }
    
    public int gcd(int a,int b)
    {
        if(a==0)
            return b;
        else if(b==0)
            return a;
        else
            return gcd(b,a%b);
    }
    
    public int mod(int a,int b)
    {
        int modVal = a;
        if(a >= 0)
            return a%b;
        else
        {
            while(modVal < 0)
            {
               modVal = modVal + b;
            }
            return modVal;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AffineCipher solve = new AffineCipher();
        System.out.print("Enter Plaintext: ");
        solve.getPlainText(sc.nextLine());
        System.out.print("Enter mkey: ");
        solve.getMkey(sc.nextInt());
        System.out.print("Enter akey: ");
        solve.getAkey(sc.nextInt());
        if(solve.encryptText())
        {
            solve.showEncrypted();
            solve.findInverseKey();
            solve.decryptText();
        }
    }  
}
