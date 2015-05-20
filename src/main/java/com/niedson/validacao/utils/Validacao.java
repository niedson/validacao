package com.niedson.validacao.utils;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validacao {
	
	private static final String REGEXP_PARENTESES_CHAVES_COLCHETES;
	
	static{
		String abreParenteses = "\\(";
		String abreChaves = "\\{";
		String abreColchetes = "\\[";
		String fechaParenteses = "\\)";
		String fechaChaves = "\\}";
		String fechaColchetes = "\\]";
		String ou = "|";
		REGEXP_PARENTESES_CHAVES_COLCHETES = "(" + abreParenteses + ou + abreChaves + ou + abreColchetes + ou +
				fechaParenteses + ou + fechaChaves + ou + fechaColchetes + ")";
	}
	
	
	public boolean isTextoFormatadoCorretamenteComParentesesChavesColchetes(String texto){
		boolean isFormatadoCorretamente = true;

		Pattern compile = Pattern.compile( REGEXP_PARENTESES_CHAVES_COLCHETES );
		Matcher matcher = compile.matcher(texto);
		
		LinkedList<String> pilha = new LinkedList<String>();
		
		boolean continuarValidacao = matcher.find();
		while(continuarValidacao){
			String caracterEncontrado = texto.substring(matcher.start(), matcher.end());
			
			if( getValorInverso(pilha.peekLast()).equals(caracterEncontrado) ){
				pilha.pollLast(); 
				continuarValidacao = matcher.find();
			} else if( isCaractererQueFechaGrupo(caracterEncontrado) ) {
				isFormatadoCorretamente = false;
				continuarValidacao = false;
			} else {
				pilha.offerLast(caracterEncontrado); 
				continuarValidacao = matcher.find();
			}
		}
		
		if ( isFormatadoCorretamente && pilha.size() > 0 ) {
			isFormatadoCorretamente = false;
		}
		
		return isFormatadoCorretamente;
	}
	
	private boolean isCaractererQueFechaGrupo(String valor) {
		switch (valor.charAt(0)) {
			case ')':
				return true;
			case ']':
				return true;
			case '}':
				return true;
		}
		return false;
	}

	private String getValorInverso(String valor){
		if(valor == null){
			return "";
		}
		switch (valor.charAt(0)) {
			case '(':
				return ")";
			case '[':
				return "]";
			case '{':
				return "}";
		}
		return "";
	}
}
