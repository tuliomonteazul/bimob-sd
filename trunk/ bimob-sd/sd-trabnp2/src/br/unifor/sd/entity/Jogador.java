package br.unifor.sd.entity;

import java.io.Serializable;
import java.net.InetAddress;

public class Jogador implements Serializable{
	private String nome;
	private InetAddress address;
	
	
	public Jogador() {
		super();
	}
	public Jogador(String nome, InetAddress address) {
		super();
		this.nome = nome;
		this.address = address;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public InetAddress getAddress() {
		return address;
	}
	public void setAddress(InetAddress address) {
		this.address = address;
	}
	
	
}
