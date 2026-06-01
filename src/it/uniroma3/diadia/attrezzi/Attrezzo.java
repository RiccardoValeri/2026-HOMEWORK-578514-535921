package it.uniroma3.diadia.attrezzi;

public class Attrezzo implements Comparable<Attrezzo> {
	private String nome;
	private int peso;

	public Attrezzo(String nome, int peso) {
		this.nome = nome;
		this.peso = peso;
	}

	public String getNome() {
		return nome;
	}

	public int getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return this.getNome() + " (" + this.getPeso() + "kg)";
	}

	@Override
	public int compareTo(Attrezzo o) {
		return this.getNome().compareTo(o.getNome());
	}
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Attrezzo other = (Attrezzo) obj;
		return nome.equals(other.nome);
	}

	@Override
	public int hashCode() {
		return nome.hashCode();
	}
}