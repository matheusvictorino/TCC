package br.com.uniararas.beans;

import java.security.MessageDigest;


public class Aluno {
	
	private String nome;
	
	private String ra;
	
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws Exception {
//***************************** Metodo para criptografar senha SHA-256 **************************
//http://blog.caelum.com.br/guardando-senhas-criptografadas-em-java/
		
//		MessageDigest algorithm;
//		try {
//			algorithm = MessageDigest.getInstance("SHA-256");
//			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
//			 
//			StringBuilder hexString = new StringBuilder();
//			for (byte b : messageDigest) {
//			  hexString.append(String.format("%02X", 0xFF & b));
//			}
//			this.senha = hexString.toString();
//		} catch (Exception e) {
//			throw new Exception("Erro ao obter Senha.");
//		}
	
		this.senha = senha;
	}

}
