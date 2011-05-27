package br.unifor.sd.entity;

import java.awt.Image;
import java.awt.Toolkit;

public enum ColorPlayer {
	VERMELHO, VERDE, AZUL, BRANCO;
	
	private static final String IMG_AZUL = "images/jog_azul.png";
	private static final String IMG_VERMELHO = "images/jog_vermelho.png";
	private static final String IMG_VERDE = "images/jog_verde.png";
	private static final String IMG_BRANCO = "images/jog_branco.png";
	
	public Image getImage() {
		switch (this) {
		case VERMELHO:
			return Toolkit.getDefaultToolkit().getImage(IMG_VERMELHO);
		case VERDE:
			return Toolkit.getDefaultToolkit().getImage(IMG_VERDE);
		case AZUL:
			return Toolkit.getDefaultToolkit().getImage(IMG_AZUL);
		case BRANCO:
			return Toolkit.getDefaultToolkit().getImage(IMG_BRANCO);
		}
		return null;
	}
	
	public String getText() {
		switch (this) {
		case VERMELHO:
			return "Vermelho";
		case VERDE:
			return "Verde";
		case AZUL:
			return "Azul";
		case BRANCO:
			return "Branco";
		}
		return null;
	}
}
