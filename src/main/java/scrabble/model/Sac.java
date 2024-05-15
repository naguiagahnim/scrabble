package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;

public class Sac {
	
	private ArrayList<Jeton> jetons;

	public Sac() {
		this.jetons = new ArrayList<Jeton>() ;
	}
	
	public ArrayList<Jeton> getJetons() {
		return this.jetons;
	}
	
	public Jeton getJeton(int indice) {
		return this.jetons.get(indice);
	}
	
	public void ajout1Jeton(Jeton jeton) {
		this.jetons.add(jeton);
	}
	
	public void setJetonspioche(ArrayList<Jeton> jetons) {
		this.jetons = jetons;
	}
	
	public void supprimerJeton(int indice) {
		this.jetons.remove(indice);
	};
	
	
	public void melanger() {
		Collections.shuffle(jetons);
	}
	public void remplirJeuFrançais() {
		for (int i=0;i<15;i++)	{
			this.ajout1Jeton(Jeton.E);
		}
		for (int i=0;i<9;i++)	{
			this.ajout1Jeton(Jeton.A);
		}
		for (int i=0;i<8;i++)	{
			this.ajout1Jeton(Jeton.I);
		}
		for (int i=0;i<6;i++)	{
			this.ajout1Jeton(Jeton.N);
			this.ajout1Jeton(Jeton.O);
			this.ajout1Jeton(Jeton.R);
			this.ajout1Jeton(Jeton.S);
			this.ajout1Jeton(Jeton.T);
			this.ajout1Jeton(Jeton.U);
		}
		for (int i=0;i<5;i++)	{
			this.ajout1Jeton(Jeton.L);
		}
		for (int i=0;i<3;i++)	{
			this.ajout1Jeton(Jeton.D);
			this.ajout1Jeton(Jeton.M);
		}
		for (int i=0;i<2;i++)	{
			this.ajout1Jeton(Jeton.G);
			this.ajout1Jeton(Jeton.B);
			this.ajout1Jeton(Jeton.C);
			this.ajout1Jeton(Jeton.P);
			this.ajout1Jeton(Jeton.F);
			this.ajout1Jeton(Jeton.H);
			this.ajout1Jeton(Jeton.V);
			this.ajout1Jeton(Jeton.JOKER);
		}
		this.ajout1Jeton(Jeton.J);
		this.ajout1Jeton(Jeton.Q);
		this.ajout1Jeton(Jeton.K);
		this.ajout1Jeton(Jeton.W);
		this.ajout1Jeton(Jeton.X);
		this.ajout1Jeton(Jeton.Y);
		this.ajout1Jeton(Jeton.Z);
		
	}
}
