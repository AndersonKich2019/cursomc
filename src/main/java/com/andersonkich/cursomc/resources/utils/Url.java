package com.andersonkich.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Url {

	public static String decodeParam(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return ""; //retorna uma string vazia
		}
	}
	
	
	public static List<Integer> decodeIntList(String str){
		String[] vet = str.split(",");
		List<Integer> list = new ArrayList<>();
		for(int i = 0;i<vet.length;i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		//return Arrays.asList(str.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	/*Expressão Lambda return Arrays.asList(str.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());  
	 * Arrays.asList(str.split(",")) = Convert um vetor em uma lista
	 */
}
