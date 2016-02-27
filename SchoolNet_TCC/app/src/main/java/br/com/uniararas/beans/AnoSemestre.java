package br.com.uniararas.beans;
/**
 *   Copyright 2013 Gerson Donscoi Junior, Leandro Motta M. Oliveira
 * 
 *   Este arquivo é parte do programa SchoolNet Mobile
 *
 *
 *   SchoolNet Mobile é um software livre; você pode redistribuí-lo e/ou 
 *
 *   modificá-lo dentro dos termos da Licença Pública Geral GNU como 
 *
 *   publicada pela Fundação do Software Livre (FSF); na versão 2 da 
 *
 *   Licença, ou (na sua opinião) qualquer versão.
 *
 *
 *
 *   Este programa é distribuído na esperança de que possa ser  útil, 
 *
 *   mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 *
 *   MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 *
 *   Licença Pública Geral GNU para maiores detalhes.
 *
 *
 *
 *   Você deve ter recebido uma cópia da Licença Pública Geral GNU
 *
 *   junto com este programa, se não, escreva para a Fundação do Software
 *
 *   Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
public class AnoSemestre implements Comparable<AnoSemestre>  {

	public String anolevito;

	public String semestre;

	@Override
	public int compareTo(AnoSemestre anoSemestre) {
		if (Integer.parseInt(this.anolevito) < Integer
				.parseInt(anoSemestre.anolevito)) {
			return -1;
		}
		if (Integer.parseInt(this.anolevito) == Integer
				.parseInt(anoSemestre.anolevito)) {
			if (Integer.parseInt(this.semestre) < Integer
					.parseInt(anoSemestre.semestre)) {
				return -1;
			} else {
				return 1;
			}
		}
		if (Integer.parseInt(this.anolevito) > Integer
				.parseInt(anoSemestre.anolevito)) {
			return 1;
		}
		return 0;
	}
}
