package Vue;

import java.awt.*;

public class VueForge implements Affichable{

    @Override
    public void draw(Graphics g, Affichage a) {
        g.setColor(Color.YELLOW);
        g.drawOval(a.fitX(10), a.fitY(10),a.fitX(80), a.fitY(80));
    }
}
