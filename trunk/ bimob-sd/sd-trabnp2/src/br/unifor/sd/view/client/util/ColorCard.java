package br.unifor.sd.view.client.util;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class ColorCard {
	
//	private static final Color ROXO = new Color(133, 50, 134);
//	private static final Color AZUL_CLARO = new Color(78, 202, 245);
//	private static final Color LARANJA = new Color(246, 137, 59);
//	private static final Color VERMELHO = new Color(238, 59, 53);
//	private static final Color AMARELO = new Color(255, 224, 26);
//	private static final Color VERDE = new Color(45, 181, 85);
//	private static final Color AZUL = new Color(0, 117, 191);
	private static final List<Color> CORES = Arrays.asList(new Color[] {
		new Color(133, 50, 134), // roxo
		new Color(78, 202, 245), // azul claro
		new Color(246, 137, 59), // laranja
		new Color(255,0,255), // rosa
		new Color(238, 59, 53), // vermelho
		new Color(255, 224, 26), // amarelo
		new Color(45, 181, 85), // verde
		new Color(0, 117, 191), // azul
	});
	
	public static Color getColor(int id) {
		return CORES.get(id);
	}
}
