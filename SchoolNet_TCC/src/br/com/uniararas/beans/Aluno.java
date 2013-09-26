package br.com.uniararas.beans;

import java.security.MessageDigest;


public class Aluno {
	
	public String nomealuno;
	
	public String ra;
	
	public String email;
	
	private String senha;

	public String getSenha(){
		return this.senha;
	}
	
	public void setSenha(String senha) throws Exception {
		senha = senha.toUpperCase();
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
			 
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			this.senha = hexString.toString();
		} catch (Exception e) {
			throw new Exception("Erro ao obter Senha.");
		}
	}

}
