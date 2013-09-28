package br.com.uniararas.beans;

public class AnoSemestre implements Comparable<AnoSemestre> {

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
