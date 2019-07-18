package ControllerImpl;

import Control.AuthenticationManager;
import Control.GestoreStreamingMusica;
import Control.BraniManager;
import Control.PlayListManager;

public class DefaultGestoreStreamingBuilder extends GestoreStreamingMusica.GestoreStreamingBuilder {

	/**
	 * Default constructor
	 */
	public DefaultGestoreStreamingBuilder() {
	}

	public GestoreStreamingMusica build() {
		AuthenticationManager authenticationManager = new AuthenticationManagerImpl();
		PlayListManager ordinimanager = new PlayListManagerImpl();
		BraniManager prodottimanager = new BraniManagerImpl();
		return super.build(ordinimanager, prodottimanager, authenticationManager);
	}

}

