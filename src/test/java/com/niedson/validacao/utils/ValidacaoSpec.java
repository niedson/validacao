package com.niedson.validacao.utils;

import org.junit.Assert;
import org.junit.Test;

import com.niedson.validacao.utils.Validacao;


public class ValidacaoSpec {

	@Test
	public void palavraBemFormatada() {
		Validacao validacao = new Validacao();
		Assert.assertTrue( validacao.isTextoFormatadoCorretamenteComParentesesChavesColchetes("(ab)") );
	}
	
	@Test
	public void palavraMalFormatada() {
		Validacao validacao = new Validacao();
		Assert.assertFalse( validacao.isTextoFormatadoCorretamenteComParentesesChavesColchetes("{[abc]}{}]") );
	}


}
