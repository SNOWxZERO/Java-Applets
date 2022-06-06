import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Myship extends JApplet {
    Random r = new Random();
    int Shipmove;
    int direction;
    int windmel;
    int colorshift;
    int colorshift2;
    Image offimage;
    Graphics offg;

    class star {
        int centerx;
        int centery;
        int radius;

        star() {
            centery = r.nextInt(277);
            centerx = r.nextInt(1280);
            radius = 2 + r.nextInt(4);
        }
    }

    class wave {
        int centerx;
        int centery;


        wave() {
            centery = r.nextInt(700 - 300) + 300;
            centerx = r.nextInt(1800);
        }
    }


    ArrayList<star> stars;
    ArrayList<wave> waves;

    public void init() {
        setSize(1280,720);
        windmel = 0;
        Shipmove = 0;
        direction = 0;
        colorshift = 0;
        stars = new ArrayList<star>();
        waves = new ArrayList<wave>();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    UpdateAnim();
                    repaint();
                    delay();
                }
            }
        }.start();
    }

    public void delay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void UpdateAnim() {
        windmel++;
        if (windmel == 360) {
            windmel = 0;
        }

        if (direction == 0) {
            Shipmove++;
            if (Shipmove == 350) {
                direction = 1;
            }
        }
        if (direction == 1) {
            Shipmove--;
            if (Shipmove == 0) {
                direction = 0;
            }
        }
        colorshift++;
        if (colorshift == 1500) {
            stars.clear();
            waves.clear();
            colorshift = 0;
        }
        if (stars.size() < 500) {
            if (colorshift % 2 == 0) {
                stars.add(new star());
            }
        } else {

            stars.subList(0, 20).clear();
        }
        if (waves.size() < 100) {
            if (colorshift % 5 == 0) {
                waves.add(new wave());
            }
        } else {

            waves.subList(0, 5).clear();
        }


    }

    public void paint(Graphics g) {
        update(g);
    }

    public void update(Graphics g) {
        if (offimage == null) {
            offimage = createImage(getWidth(), getHeight());
            offg = offimage.getGraphics();
        }
        Graphics2D gra = (Graphics2D) offg;
        gra.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color startColor = Color.white;
        Color endColor;
        if (colorshift < 300) {
            endColor = new Color((int) (4 + colorshift / 1.2), (int) (150 + colorshift / 5.455), (int) (255 - colorshift / 2.1));
        } else if (colorshift < 600) {
            colorshift2 = colorshift - 300;
            endColor = new Color((int) (255 - colorshift2 / 4.762), (int) (205 - colorshift2 / 2.77778), (int) (112 - colorshift2 / 5.085));
        } else if (colorshift < 900) {
            colorshift2 = colorshift - 600;
            endColor = new Color((int) (192 - colorshift2 / 2.098), 97 - colorshift2 / 12, (int) (53 + colorshift2 / 3.53));
        } else if (colorshift < 1200) {
            colorshift2 = colorshift - 900;
            endColor = new Color((int) (49 + colorshift2 / 5.357143), (int) (72 + colorshift2 / 4.6875), (int) (138 + colorshift2 / 4.225352113));
        } else {
            colorshift2 = colorshift - 1200;
            endColor = new Color((int) (105 - colorshift2 / 2.9703), (int) (136 + colorshift2 / 21.4285714), (int) (209 + colorshift2 / 6.5217391));
        }
        GradientPaint gradient = new GradientPaint(1280, 375, startColor, 1280, 0, endColor);
        gra.setPaint(gradient);
        //Sky
        gra.fillRect(0, 0, 1280, 300);
        if (colorshift > 510 && colorshift < 1275) {

            colorshift2 = colorshift - (255 * (colorshift / 255));
            if (colorshift2 < 127) {
                gra.setColor(new Color(198, 238, 243, colorshift2));
            } else if (colorshift2 < 255) {
                gra.setColor(new Color(198, 238, 243, 255 - colorshift2));
            } else {
                gra.setColor(new Color(198, 238, 243, 0));
            }
            for (star c : stars.subList(0, stars.size() / 3)) {
                gra.fillOval(c.centerx - c.radius, c.centery - c.radius, 2 * c.radius, 2 * c.radius);
            }
            if (colorshift2 < 127) {
                gra.setColor(new Color(198, 238, 243, colorshift2 + 15));
            } else if (colorshift2 < 255) {
                gra.setColor(new Color(198, 238, 243, 270 - colorshift2));
            } else {
                gra.setColor(new Color(198, 238, 243, 0));
            }
            for (star c : stars.subList(stars.size() / 3, 2 * stars.size() / 3)) {
                gra.fillOval(c.centerx - c.radius + 5, c.centery - c.radius + 3, 2 * c.radius, 2 * c.radius);
            }
            if (colorshift2 < 127) {
                gra.setColor(new Color(198, 238, 243, colorshift2 + 30));
            } else if (colorshift2 < 255) {
                gra.setColor(new Color(198, 238, 243, 285 - colorshift2));
            } else {
                gra.setColor(new Color(198, 238, 243, 0));
            }
            for (star c : stars.subList(2 * stars.size() / 3, stars.size() - 1)) {
                gra.fillOval(c.centerx - c.radius + 1, c.centery - c.radius + 1, 2 * c.radius, 2 * c.radius);
            }
        }
        gra.setColor(new Color(174, 220, 232, 174));
        gra.fillRoundRect((int) (1280 - colorshift * 1.3), 0, 400, 50, 150, 30);
        gra.fillRoundRect(1280 - colorshift, 70, 220, 60, 150, 30);
        gra.fillRoundRect(2600 - colorshift * 2, 110, 300, 60, 150, 30);
        gra.fillRoundRect(2700 - colorshift * 2, 140, 250, 60, 150, 30);
        gra.fillRoundRect(2400 - colorshift * 2, 50, 220, 60, 150, 30);
        gra.fillRoundRect((int) (1280 - colorshift * 1.1), 155, 250, 50, 150, 30);
        gra.fillRoundRect((int) (1280 - colorshift * 1.25), 222, 300, 50, 150, 30);
        //sun
        if (colorshift > 764) {
            gra.setColor(new Color(252, 164, 0, 0));
        } else {
            gra.setColor(new Color(252, 164, 0, 255 - colorshift / 3));
        }
        gra.fillOval(colorshift - 150, colorshift / 6, 70, 70);

        //Sea
        if (colorshift < 764) {
            gra.setColor(new Color(45, 157, 255, 255 - colorshift / 4));
        } else {
            gra.setColor(new Color(45, 157, 255, 64 + (colorshift - 764) / 4));
        }
        gra.fillRect(0, 300, 1280, 420);
        //waves
        colorshift2 = colorshift - (255 * (colorshift / 255));
        if (colorshift2 < 127) {
            gra.setColor(new Color(48, 109, 148, colorshift2));
        } else if (colorshift2 < 255) {
            gra.setColor(new Color(48, 109, 148, 255 - colorshift2));
        } else {
            gra.setColor(new Color(48, 109, 148, 0));
        }
        gra.fillArc(colorshift2 / 4, 600 + colorshift2 / 2, 1280 - colorshift2 / 3, 20 - colorshift2 / 20, 180, 180);
        gra.fillArc(colorshift2 / 4, 630 + colorshift2 / 2, 1280 - colorshift2 / 2, 20 - colorshift2 / 20, 180, 180);
        gra.fillArc(colorshift2 / 4, 645 + colorshift2 / 2, 1280 - colorshift2 / 4, 20 - colorshift2 / 20, 180, 180);
        gra.fillArc(colorshift2 / 4, 666 + colorshift2 / 2, 1280 - colorshift2 / 2, 20 - colorshift2 / 20, 180, 180);

        if (colorshift < 1275) {
            colorshift2 = colorshift - (255 * (colorshift / 255));
            if (colorshift2 < 127) {
                gra.setColor(new Color(138, 175, 194, colorshift2));
            } else if (colorshift2 < 255) {
                gra.setColor(new Color(138, 175, 194, 255 - colorshift2));
            } else {
                gra.setColor(new Color(138, 175, 194, 0));
            }
            for (wave c : waves.subList(0, waves.size() / 3)) {
                gra.fillRoundRect(c.centerx - colorshift / 2, c.centery + colorshift2 / 8, 100, 7, 100, 7);
            }
            if (colorshift2 < 127) {
                gra.setColor(new Color(138, 175, 194, colorshift2 + 15));
            } else if (colorshift2 < 255) {
                gra.setColor(new Color(138, 175, 194, 270 - colorshift2));
            } else {
                gra.setColor(new Color(138, 175, 194, 0));
            }
            for (wave c : waves.subList(waves.size() / 3, 2 * waves.size() / 3)) {
                gra.fillRoundRect(c.centerx - colorshift / 2, c.centery + colorshift2 / 8, 100, 7, 100, 7);
            }
            if (colorshift2 < 127) {
                gra.setColor(new Color(138, 175, 194, colorshift2 + 30));
            } else if (colorshift2 < 255) {
                gra.setColor(new Color(138, 175, 194, 285 - colorshift2));
            } else {
                gra.setColor(new Color(138, 175, 194, 0));
            }
            for (wave c : waves.subList(2 * waves.size() / 3, waves.size() - 1)) {
                gra.fillRoundRect(c.centerx - colorshift / 2, c.centery + colorshift2 / 8, 100, 7, 100, 7);
            }
        }


        // windmill
        gra.setColor(new Color(0x3a506b));
        gra.fillRect(45, 150, 20, 150);
        gra.fillRect(214, 150, 20, 150); //160
        gra.fillRect(364, 150, 20, 150); //240
        gra.setColor(new Color(0x1c2541));
        gra.fillRect(45, 150, 7, 150);
        gra.fillRect(214, 150, 7, 150); //160
        gra.fillRect(364, 150, 7, 150); //240
        // fan of windmill
        gra.setColor(new Color(0xffe6a7));
        gra.fillArc(-20, 70, 150, 150, 20 - windmel, 20);
        gra.fillArc(-20, 70, 150, 150, 140 - windmel, 20);
        gra.fillArc(-20, 70, 150, 150, 260 - windmel, 20);
        gra.fillArc(149, 70, 150, 150, 20 - windmel, 20);
        gra.fillArc(149, 70, 150, 150, 140 - windmel, 20);
        gra.fillArc(149, 70, 150, 150, 260 - windmel, 20);
        gra.fillArc(300, 70, 150, 150, 20 - windmel, 20);
        gra.fillArc(300, 70, 150, 150, 140 - windmel, 20);
        gra.fillArc(300, 70, 150, 150, 260 - windmel, 20);
        gra.setColor(new Color(0xbb9457));
        gra.fillArc(-20, 70, 150, 150, 20 - windmel, 7);
        gra.fillArc(-20, 70, 150, 150, 140 - windmel, 7);
        gra.fillArc(-20, 70, 150, 150, 260 - windmel, 7);
        gra.fillArc(149, 70, 150, 150, 20 - windmel, 7);
        gra.fillArc(149, 70, 150, 150, 140 - windmel, 7);
        gra.fillArc(149, 70, 150, 150, 260 - windmel, 7);
        gra.fillArc(300, 70, 150, 150, 20 - windmel, 7);
        gra.fillArc(300, 70, 150, 150, 140 - windmel, 7);
        gra.fillArc(300, 70, 150, 150, 260 - windmel, 7);
        gra.setColor(new Color(0x0b132b));
        gra.fillOval(38, 125, 35, 35);
        gra.fillOval(207, 125, 35, 35);
        gra.fillOval(357, 125, 35, 35);
        //mountains
        gra.setColor(new Color(0x00A896));
        int[] x = {40, 80, 120};
        int[] y = {300, 150, 300};
        gra.fillPolygon(x, y, 3);
        x = new int[]{100, 150, 200};
        y = new int[]{300, 120, 300};
        gra.fillPolygon(x, y, 3);
        x = new int[]{180, 300, 400};
        y = new int[]{300, 170, 300};
        gra.fillPolygon(x, y, 3);
        gra.setColor(new Color(0x028090));
        x = new int[]{80, 80, 120};
        y = new int[]{300, 150, 300};
        gra.fillPolygon(x, y, 3);
        x = new int[]{140, 150, 200};
        y = new int[]{300, 120, 300};
        gra.fillPolygon(x, y, 3);
        x = new int[]{250, 300, 400};
        y = new int[]{300, 170, 300};
        gra.fillPolygon(x, y, 3);
        gra.setColor(new Color(0x05668D));
        x = new int[]{100, 80, 120};
        y = new int[]{300, 150, 300};
        gra.fillPolygon(x, y, 3);
        x = new int[]{170, 150, 200};
        y = new int[]{300, 120, 300};
        gra.fillPolygon(x, y, 3);
        x = new int[]{340, 300, 400};
        y = new int[]{300, 170, 300};
        gra.fillPolygon(x, y, 3);


        // small ships
        gra.setColor(new Color(0x06113C));
        int[] ship_x = {-200 + colorshift, colorshift, -40 + colorshift, -160 + colorshift};
        int[] ship_y = {290, 290, 350, 350};
        gra.fillPolygon(ship_x, ship_y, 4);
        int[] ship2_x = {(int) (-200 + colorshift*1.5), (int) (colorshift*1.5), (int) (-40 + colorshift*1.5), (int) (-160 + colorshift*1.5)};
        int[] ship2_y = {590, 590, 650, 650};
        gra.fillPolygon(ship2_x, ship2_y, 4);
        gra.setColor(new Color(0xFF8C32));
        int[] ships_x = {-180 + colorshift, -20 + colorshift, -26 + colorshift, -174 + colorshift};
        int[] ships_y = {320, 320, 329, 329};
        gra.fillPolygon(ships_x, ships_y, 4);
        int[] ships2_x = {(int) (-180 + colorshift*1.5), (int) (-20+colorshift*1.5), (int) (-26 + colorshift*1.5), (int) (-174 + colorshift*1.5)};
        int[] ships2_y = {620, 620, 629, 629};
        gra.fillPolygon(ships2_x, ships2_y, 4);
        gra.setColor(new Color(0xDDDDDD));
        int[] triangle_xx = {-180 + colorshift , -20 + colorshift , -90 + colorshift };
        int[] triangle_yy = {285, 285, 200};
        gra.fillPolygon(triangle_xx, triangle_yy, 3);
        gra.setColor(new Color(0xEEEEEE));
        int[] triangle_x = {-180 + colorshift , -20 + colorshift , -20 + colorshift };
        int[] triangle_y = {285, 285, 200};
        gra.fillPolygon(triangle_x, triangle_y, 3);



        // big ship
        colorshift2= (int) (colorshift*1.2-420);
        gra.setColor(new Color(0xF4F1DE));
        int[] big_ship_x = {35 + colorshift2, 360 + colorshift2, 380 + colorshift2, 35 + colorshift2};
        int[] big_ship_y = {370, 370, 410, 410};
        gra.fillPolygon(big_ship_x, big_ship_y, 4);
        gra.setColor(new Color(0xF2CC8F));
        gra.fillRect(30 + colorshift2, 390, 360, 30);
        gra.setColor(new Color(0x3D405B));
        int[] big_x = { colorshift2, 420 + colorshift2, 370 + colorshift2, 50 + colorshift2};
        int[] big_y = {420, 420, 500, 500};
        gra.fillPolygon(big_x, big_y, 4);
        int[] big_xx = {35 + colorshift2, 50 + colorshift2, 345 + colorshift2, 360 + colorshift2};
        int[] big_yy = {370, 360, 360, 370};
        gra.fillPolygon(big_xx, big_yy, 4);
        gra.setColor(new Color(0xF4F1DE));
        gra.fillOval(90 + colorshift2, 425, 40, 40);
        gra.fillOval(143 + colorshift2,425, 40, 40);
        gra.fillOval(195 + colorshift2,425, 40, 40);
        gra.fillOval(247 + colorshift2,425, 40, 40);
        gra.fillOval(300 + colorshift2,425, 40, 40);
        gra.setColor(new Color(0x3D405B));
        gra.fillOval(30 + 80 + colorshift2, 395, 20, 20);
        gra.fillOval(30 + 133 + colorshift2, 395, 20, 20);
        gra.fillOval(30 + 185 + colorshift2, 395, 20, 20);
        gra.fillOval(30 + 237 + colorshift2, 395, 20, 20);
        gra.fillOval(60 + 70 + colorshift2, 372, 15, 15);
        gra.fillOval(60 + 90 + colorshift2, 372, 15, 15);
        gra.fillOval(60 + 110 + colorshift2, 372, 15, 15);
        gra.fillOval(60 + 130 + colorshift2, 372, 15, 15);
        gra.fillOval(60 + 150 + colorshift2, 372, 15, 15);
        gra.fillOval(60 + 170 + colorshift2, 372, 15, 15);
        gra.setColor(new Color(0x81B29A));
        int[] rect_x = {320 + colorshift2, 360 + colorshift2, 365 + colorshift2, 320 + colorshift2};
        int[] rect_y = {375, 375, 385, 385};
        gra.fillPolygon(rect_x, rect_y, 4);

        gra.setColor(new Color(0x81B29A));
        int[] bigships_x = {30+colorshift2, 389 + colorshift2, 383+ colorshift2, 36 + colorshift2};
        int[] bigships_y = {470, 470, 480, 480};
        gra.fillPolygon(bigships_x, bigships_y, 4);
        gra.setColor(new Color(0xF4F1DE));
        gra.fillRect(65 + colorshift2, 330, 40, 30);
        gra.fillRect(170 + colorshift2, 330, 40, 30);
        gra.fillRect(275 + colorshift2, 330, 40, 30);
        gra.setColor(new Color(0xE07A5F));
        gra.fillRect(65 + colorshift2, 325, 40, 5);
        gra.fillRect(170 + colorshift2, 325, 40, 5);
        gra.fillRect(275 + colorshift2, 325, 40, 5);


        // flag of the under small ship
        gra.setColor(new Color(0xDDDDDD));
        int[] triangle2_x = {(int) (-195 + colorshift*1.5), (int) (-90+colorshift*1.5), (int) (-90 + colorshift*1.5)};
        int[] triangle2_y = {585 , 585 , 500 };
        gra.fillPolygon(triangle2_x, triangle2_y, 3);
        gra.setColor(new Color(0xEEEEEE));
        int[] triangle2_xx = {(int) (-5 + colorshift*1.5), (int) (-97+colorshift*1.5), (int) (-97 + colorshift*1.5)};
        int[] triangle2_yy = {585 , 585 , 480 };
        gra.fillPolygon(triangle2_xx, triangle2_yy, 3);
        g.drawImage(offimage, 0, 0, this);

    }


}