package Boutique;

import Boutique.exception.QtaVendutaMoreThanQtaDisponibileException;

public interface IProdotto {
	public void decrementaQtaProdotto(Prodotto prod,Integer qtaVend) throws QtaVendutaMoreThanQtaDisponibileException;
}
