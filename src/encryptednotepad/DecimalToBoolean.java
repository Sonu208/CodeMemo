/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptednotepad;

/**
 *
 * @author LAVESH
 */
public class DecimalToBoolean 
{
    public String getBoolean(int decimal)
	{
		StringBuffer result = new StringBuffer();
		while(decimal != 0)
		{
			result.append((char)((decimal%2)+48));
			decimal = decimal/2;
		}
		result = result.reverse();
		return result.toString();
	}
	public String getFormattedBoolean(int decimal,int zeros)
	{
		StringBuffer result = new StringBuffer();
		while(decimal != 0)
		{
			result.append((char)((decimal%2)+48));
			decimal = decimal/2;
		}
		result = result.reverse();
		for(int i=result.length();i<zeros;i++)
		{
			result.insert(0,'0');
		}
		return result.toString();


	}
    public static void main(String[] args) 
    {
    	DecimalToBoolean dtb = new DecimalToBoolean();
    	for(int i=0;i<10;i++)
    	{
    		System.out.println(dtb.getFormattedBoolean(i,4));
    	}	
    }

    
}
