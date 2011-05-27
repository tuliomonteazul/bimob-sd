package br.unifor.sd.view;

import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

public class Util {
	
	private static final Util INSTANCE = new Util();
	
	/**
	 * Carrega uma imagem a partir do seu nome utilizando getResourceAsStream e
	 * retorna a imagem como um ImageIcon.
	 * 
	 * @param caminho
	 *            - caminho da imagem
	 * @return ImageIcon
	 */
    public static ImageIcon loadImage(final String caminho) {
    	final int maxImageSize = 1024;  // tamanho máxima de uma imagem
        int count = 0;
        final BufferedInputStream imgStream = new BufferedInputStream(INSTANCE.getClass().getResourceAsStream(caminho));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (imgStream == null) {
			Logger.getLogger(Util.class.getName()).log(Level.SEVERE, "Arquivo não encontrado" + ":" + caminho);
			return null;
		} else {
			final byte buf[] = new byte[maxImageSize];
			try {
				while (imgStream.available() > 0) {
					count += imgStream.read(buf);
					out.write(buf);
					out.flush();
				}
				out.close();
				imgStream.close();
			} catch (java.io.IOException ioe) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, ioe.getMessage(),	"Arquivo com problema de leitura" + ":" + caminho);
				return null;
			}
			if (count <= 0) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, "Arquivo vazio" + ":" + caminho);
				return null;
			}
			return new ImageIcon(Toolkit.getDefaultToolkit().createImage(out.toByteArray()));
        }
    }
}
