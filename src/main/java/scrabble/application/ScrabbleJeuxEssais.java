package scrabble.application;

import scrabble.model.Chevalet;
import scrabble.model.Jeton;
import scrabble.model.Sac;

public class ScrabbleJeuxEssais {

	public static void main(String[] args) {
		Sac sac = new Sac();
		for (int i=0;i<15;i++)	{
			sac.jetons.add(Jeton.E);
		}
		for (int i=0;i<9;i++)	{
			sac.jetons.add(Jeton.A);
		}
		for (int i=0;i<8;i++)	{
			sac.jetons.add(Jeton.I);
		}
		for (int i=0;i<6;i++)	{
			sac.jetons.add(Jeton.N);
			sac.jetons.add(Jeton.O);
			sac.jetons.add(Jeton.R);
			sac.jetons.add(Jeton.S);
			sac.jetons.add(Jeton.T);
			sac.jetons.add(Jeton.U);
		}
		for (int i=0;i<5;i++)	{
			sac.jetons.add(Jeton.L);
		}
		for (int i=0;i<3;i++)	{
			sac.jetons.add(Jeton.D);
			sac.jetons.add(Jeton.M);
		}
		for (int i=0;i<2;i++)	{
			sac.jetons.add(Jeton.G);
			sac.jetons.add(Jeton.B);
			sac.jetons.add(Jeton.C);
			sac.jetons.add(Jeton.P);
			sac.jetons.add(Jeton.F);
			sac.jetons.add(Jeton.H);
			sac.jetons.add(Jeton.V);
			sac.jetons.add(Jeton.JOKER);
		}
		sac.jetons.add(Jeton.J);
		sac.jetons.add(Jeton.Q);
		sac.jetons.add(Jeton.K);
		sac.jetons.add(Jeton.W);
		sac.jetons.add(Jeton.X);
		sac.jetons.add(Jeton.Y);
		sac.jetons.add(Jeton.Z);
		
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
	}
	
}
