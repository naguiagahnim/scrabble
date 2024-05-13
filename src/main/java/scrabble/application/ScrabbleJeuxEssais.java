package scrabble.application;

import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Plateau;
import scrabble.model.Sac;

public class ScrabbleJeuxEssais {

	public static void main(String[] args) {
		Sac sac = new Sac();
		for (int i=0;i<15;i++)	{
			sac.ajout1Jeton(Jeton.E);
		}
		for (int i=0;i<9;i++)	{
			sac.ajout1Jeton(Jeton.A);
		}
		for (int i=0;i<8;i++)	{
			sac.ajout1Jeton(Jeton.I);
		}
		for (int i=0;i<6;i++)	{
			sac.ajout1Jeton(Jeton.N);
			sac.ajout1Jeton(Jeton.O);
			sac.ajout1Jeton(Jeton.R);
			sac.ajout1Jeton(Jeton.S);
			sac.ajout1Jeton(Jeton.T);
			sac.ajout1Jeton(Jeton.U);
		}
		for (int i=0;i<5;i++)	{
			sac.ajout1Jeton(Jeton.L);
		}
		for (int i=0;i<3;i++)	{
			sac.ajout1Jeton(Jeton.D);
			sac.ajout1Jeton(Jeton.M);
		}
		for (int i=0;i<2;i++)	{
			sac.ajout1Jeton(Jeton.G);
			sac.ajout1Jeton(Jeton.B);
			sac.ajout1Jeton(Jeton.C);
			sac.ajout1Jeton(Jeton.P);
			sac.ajout1Jeton(Jeton.F);
			sac.ajout1Jeton(Jeton.H);
			sac.ajout1Jeton(Jeton.V);
			sac.ajout1Jeton(Jeton.JOKER);
		}
		sac.ajout1Jeton(Jeton.J);
		sac.ajout1Jeton(Jeton.Q);
		sac.ajout1Jeton(Jeton.K);
		sac.ajout1Jeton(Jeton.W);
		sac.ajout1Jeton(Jeton.X);
		sac.ajout1Jeton(Jeton.Y);
		sac.ajout1Jeton(Jeton.Z);
		
		System.out.println("Avant de mélanger");
		System.out.println(sac.getJetons());
		sac.melanger();
		System.out.println("Après mélanger");
		System.out.println(sac.getJetons());
		
		
		Chevalet chevalet1 = new Chevalet(sac);
		chevalet1.distribuer();
		System.out.println(chevalet1.getLettres());
		System.out.println(sac.getJetons());
		Chevalet chevalet2 = new Chevalet(sac);
		chevalet2.distribuer();
		System.out.println(chevalet2.getLettres());
		chevalet2.echanger(Jeton.E);
		System.out.println(chevalet2.getLettres());
		
		Plateau plateau = new Plateau();
		System.out.println(plateau.toString());
	}
	
}
