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


import java.util.HashMap;

public class HexaDecimalToBoolean
{
	HashMap<String,String> hexBoolMapper = new HashMap<>();
	HexaDecimalToBoolean()
	{
		hexBoolMapper.put("0","0000");
		hexBoolMapper.put("1","0001");
		hexBoolMapper.put("2","0010");
		hexBoolMapper.put("3","0011");
		hexBoolMapper.put("4","0100");
		hexBoolMapper.put("5","0101");
		hexBoolMapper.put("6","0110");
		hexBoolMapper.put("7","0111");
		hexBoolMapper.put("8","1000");
		hexBoolMapper.put("9","1001");
		hexBoolMapper.put("a","1010");
		hexBoolMapper.put("b","1011");
		hexBoolMapper.put("c","1100");
		hexBoolMapper.put("d","1101");
		hexBoolMapper.put("e","1110");
		hexBoolMapper.put("f","1111");
		hexBoolMapper.put("A","1010");
		hexBoolMapper.put("B","1011");
		hexBoolMapper.put("C","1100");
		hexBoolMapper.put("D","1101");
		hexBoolMapper.put("E","1110");
		hexBoolMapper.put("F","1111");
	}
	public String getBoolean(String hex)
	{
		StringBuffer result = new StringBuffer();
		String[] array = hex.split("");
		for(int i=0;i<array.length;i++)
		{
			result = result.append(this.hexBoolMapper.get(array[i]));
		}
		return result.toString();
	}
	public static void main(String[] args) 
	{
		HexaDecimalToBoolean htb = new HexaDecimalToBoolean();
		System.out.println(htb.getBoolean("f0f0"));
		
	}
}

