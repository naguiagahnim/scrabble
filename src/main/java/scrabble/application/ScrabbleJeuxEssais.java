package scrabble.application;

import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Sac;

public class ScrabbleJeuxEssais {

	public static void main(String[] args) {
		for (int i=0;i<15;i++)	{
			Sac.jetonspioche.add(Jeton.E);
		}
		for (int i=0;i<9;i++)	{
			Sac.jetonspioche.add(Jeton.A);
		}
		for (int i=0;i<8;i++)	{
			Sac.jetonspioche.add(Jeton.I);
		}
		for (int i=0;i<6;i++)	{
			Sac.jetonspioche.add(Jeton.N);
			Sac.jetonspioche.add(Jeton.O);
			Sac.jetonspioche.add(Jeton.R);
			Sac.jetonspioche.add(Jeton.S);
			Sac.jetonspioche.add(Jeton.T);
			Sac.jetonspioche.add(Jeton.U);
		}
		for (int i=0;i<5;i++)	{
			Sac.jetonspioche.add(Jeton.L);
		}
		for (int i=0;i<3;i++)	{
			Sac.jetonspioche.add(Jeton.D);
			Sac.jetonspioche.add(Jeton.M);
		}
		for (int i=0;i<2;i++)	{
			Sac.jetonspioche.add(Jeton.G);
			Sac.jetonspioche.add(Jeton.B);
			Sac.jetonspioche.add(Jeton.C);
			Sac.jetonspioche.add(Jeton.P);
			Sac.jetonspioche.add(Jeton.F);
			Sac.jetonspioche.add(Jeton.H);
			Sac.jetonspioche.add(Jeton.V);
			Sac.jetonspioche.add(Jeton.JOKER);
		}
		Sac.jetonspioche.add(Jeton.J);
		Sac.jetonspioche.add(Jeton.Q);
		Sac.jetonspioche.add(Jeton.K);
		Sac.jetonspioche.add(Jeton.W);
		Sac.jetonspioche.add(Jeton.X);
		Sac.jetonspioche.add(Jeton.Y);
		Sac.jetonspioche.add(Jeton.Z);
		
		System.out.println("Avant de mélanger");
		System.out.println(Sac.getJetonspioche());
		Sac.melanger();
		System.out.println("Après mélanger");
		System.out.println(Sac.getJetonspioche());
		
		
		Chevalet chevalet1 = new Chevalet();
		chevalet1.distribuer();
		System.out.println(chevalet1.getLettres());
		System.out.println(Sac.getJetonspioche());
		Chevalet chevalet2 = new Chevalet();
		chevalet2.distribuer();
		System.out.println(chevalet2.getLettres());
	}
	
}
