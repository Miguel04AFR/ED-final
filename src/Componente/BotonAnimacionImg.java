package Componente;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import componentesVisuales.BotonAnimacion;

public class BotonAnimacionImg extends BotonAni {
    public BotonAnimacionImg() {
        super();
        setContentAreaFilled(false);
        setOpaque(false);
    }

    public void setColorEfecto(Color colorEfecto) {
        this.colorEfecto = colorEfecto;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int ancho = getWidth();
        int alto = getHeight();
        BufferedImage img = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, ancho, alto, alto, alto);

        // Dibuja el ícono
        if (getIcon() != null) {
            getIcon().paintIcon(this, g2, (ancho - getIcon().getIconWidth()) / 2, (alto - getIcon().getIconHeight()) / 2);
        }

        // Dibuja el efecto de color
        if (puntoPresionado != null && colorEfecto != null) {
            g2.setColor(colorEfecto);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g2.fillOval((int) (puntoPresionado.x - tamAnim / 2), (int) (puntoPresionado.y - tamAnim / 2), (int) tamAnim, (int) tamAnim);
        }

        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }
}
