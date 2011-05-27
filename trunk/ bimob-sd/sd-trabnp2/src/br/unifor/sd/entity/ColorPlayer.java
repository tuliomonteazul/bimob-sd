package br.unifor.sd.entity;

import java.awt.Image;
import java.awt.Toolkit;

import br.unifor.sd.view.Util;

public enum ColorPlayer {
	VERMELHO, VERDE, AZUL, BRANCO;
	
	private static final String IMG_AZUL = "/jog_azul.png";
	private static final String IMG_VERMELHO = "/jog_vermelho.png";
	private static final String IMG_VERDE = "/jog_verde.png";
	private static final String IMG_BRANCO = "/jog_branco.png";
	
	public Image getImage() {
		switch (this) {
		case VERMELHO:
			return Util.loadImage(IMG_VERMELHO).getImage();
		case VERDE:
			return Util.loadImage(IMG_VERDE).getImage();
		case AZUL:
			return Util.loadImage(IMG_AZUL).getImage();
		case BRANCO:
			return Util.loadImage(IMG_BRANCO).getImage();
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
