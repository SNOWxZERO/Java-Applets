import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;

public class projecthotdogdancing extends JApplet {

    Random r = new Random();

    class Circle {

        int centerx;
        int centery;
        int radius;

        Circle() {

            centery = r.nextInt(700 - 625) + 625;
            centerx = r.nextInt(850 - 350) + 350;
            radius = 2 + r.nextInt(5);

        }

        void expand() {
            radius = radius + 1;
        }

    }

    ArrayList<Circle> Circles;

    ArrayList<Integer> Lahalet;
    ArrayList<Integer> Lahalet_Increase_Rate;

    int Frame;
    int Sogo2;
    int Shift;
    int Shift2;
    int Direction;

    Image OffImage;
    Graphics OffG;

    public void init() {

        setSize(1200, 900);
        Circles = new ArrayList<Circle>();
        Lahalet = new ArrayList<Integer>();
        Lahalet_Increase_Rate = new ArrayList<Integer>();

        for (int i = 0; i <= 30; i++) {
            Lahalet.add(r.nextInt(120 - 80) + 80);
        }

        for (int i = 0; i <= 30; i++) {
            Lahalet_Increase_Rate.add(r.nextInt(3 - 1) + 1);
        }
        Frame = 0;
        Direction = 1;

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

    private void delay() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void UpdateAnim() {
        if (Circles.size() < 15) {
            Circles.add(new Circle());
        } else {
            Circles.subList(0, 5).clear();
        }


        if (Frame < 531 ) {
            Frame++;

        } else {
            Frame=0;

        }
        if (Frame % 12 == 0) {

            Direction = Direction * -1;

        }

        for (Circle c : Circles) {
            c.expand();
        }


    }

    public void paint(Graphics g) {
        update(g);
    }

    public void update(Graphics g) {
        if (OffImage == null) {
            OffImage = createImage(getWidth(), getHeight());
            OffG = OffImage.getGraphics();
        }
        Graphics2D graphics2D = (Graphics2D) OffG;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform reset = graphics2D.getTransform();



        //---------------------------Sky-------------------------------//
        graphics2D.setColor(new Color(0x08a1ff));
        graphics2D.fillRect(0, 0, 1200, 900);
        //---------------------------Clouds-------------------------------//
        if (Frame <= 133) {
            Shift = Frame;
        } else if (Frame <= 399) {
            Shift = 266 - Frame;
        } else {
            Shift = -532 + Frame;
        }
        graphics2D.setColor(new Color(0xF7ECDE));
        graphics2D.fillRoundRect(200 + Shift, 125, 225, 50, 50, 50);
        graphics2D.fillRoundRect(775 + Shift, 350, 225, 40, 40, 40);
        graphics2D.setColor(new Color(0xFBF8F1));
        graphics2D.fillRoundRect(725 + Shift, 325, 200, 40, 40, 40);
        graphics2D.fillRoundRect(250 + Shift, 150, 225, 50, 50, 50);
        //-------------------------------------------------------------//


        if (Frame >= 100) {
            Shift2 = Frame - 100;

            /**For Shifting The Island and The Sogo2 **/
            if (Shift2 <= 108) {
                Shift = Shift2;
            } else if (Shift2 <= 324) {
                Shift = 216 - Shift2;
            } else {
                Shift = -432 + Shift2;
            }
            graphics2D.translate(Shift * 2, 0);
            /**------------------------------------**/
        }


        //-----------------------ground shadow----------------------------------//
        graphics2D.setColor(new Color(0x0791e6));
        graphics2D.fillOval(250, 800, 700, 20);
        //--------------------------Island-----------------------------//
        graphics2D.setColor(new Color(0x324670));
        graphics2D.fillRoundRect(300, 540, 600, 200, 100, 100);
        //--------------------------Circles-----------------------------//
        graphics2D.setColor(new Color(0x28385a));
        /*Shift = Frame - (127 * (Frame / 127));
        if (Shift < 63) {
            graphics2D.setColor(new Color(40, 56, 90, Shift * 2));
        } else if (Shift < 127) {
            graphics2D.setColor(new Color(40, 56, 90, 255 - Shift * 2));
        } else {
            graphics2D.setColor(new Color(40, 56, 90, 0));
        }*/

        for (Circle c : Circles) {
            graphics2D.fillOval(c.centerx - c.radius, c.centery - c.radius, 2 * c.radius, 2 * c.radius);

        }
        //-----------------------BigCircles Left And Right----------------------------//
        graphics2D.setColor(new Color(0xb4eb7f));
        for (int i = 0; i <= 4; i++) {
            int x = r.nextInt(30 - 5) + 5;
            graphics2D.fillOval(360 + i * 7, 500 + r.nextInt(10), x, x);

        }
        for (int i = 4; i <= 8; i++) {
            int x = r.nextInt(20 - 5) + 5;
            graphics2D.fillOval(360 + i * 7, 500 + r.nextInt(10), x, x);

        }
        graphics2D.fillArc(345, 500, 100, 100, 0, 180);
        //------------//-----------------//---------------//
        for (int i = 0; i <= 4; i++) {
            int x = r.nextInt(30 - 5) + 5;
            graphics2D.fillOval(360 + i * 7 + 410, 500 + r.nextInt(10), x, x);

        }
        graphics2D.fillArc(345 + 410, 500, 100, 100, 0, 180);
        for (int i = 4; i <= 8; i++) {
            int x = r.nextInt(20 - 5) + 5;
            graphics2D.fillOval(360 + i * 7 + 410, 500 + r.nextInt(10), x, x);

        }

        //--------------------------GrassTop-----------------------------//
        graphics2D.fillRoundRect(290, 520, 620, 50, 40, 50);

        //--------------------------Lahalet and Shadows-----------------------------//
        graphics2D.setColor(new Color(0x88de46));
        graphics2D.fillRect(290, 545, 620, 50);

        Shift2 = Frame - (48 * (Frame / 48));
        if (Shift2 <= 24) {
            for (int i = 0; i <= 17; i += 2) {
                graphics2D.setColor(new Color(0x263553));
                graphics2D.fillRoundRect((int) (290 + 36.47 * i), 550, 37, Lahalet.get(i) + Shift2 * Lahalet_Increase_Rate.get(i), 37, 40);
                graphics2D.setColor(new Color(0x88de46));
                graphics2D.fillRoundRect((int) (290 + 36.47 * i), 545, 37, Lahalet.get(i) + Shift2 * Lahalet_Increase_Rate.get(i), 37, 37);
                if (i == 16) {
                    break;
                }
                graphics2D.setColor(new Color(0x263553));
                graphics2D.fillArc((int) (327 + 36.47 * i), 575, 37, 40, 0, 180);
                graphics2D.setColor(new Color(0x324670));
                graphics2D.fillArc((int) (327 + 36.47 * i), 580, 37, 37, 0, 180);

            }
        } else {
            for (int i = 0; i <= 17; i += 2) {
                graphics2D.setColor(new Color(0x263553));
                graphics2D.fillRoundRect((int) (290 + 36.47 * i), 550, 37, Lahalet.get(i) - Shift2 * Lahalet_Increase_Rate.get(i) + 48 * Lahalet_Increase_Rate.get(i), 37, 40);
                graphics2D.setColor(new Color(0x88de46));
                graphics2D.fillRoundRect((int) (290 + 36.47 * i), 545, 37, Lahalet.get(i) - Shift2 * Lahalet_Increase_Rate.get(i) + 48 * Lahalet_Increase_Rate.get(i), 37, 37);
                if (i == 16) {
                    break;
                }
                graphics2D.setColor(new Color(0x263553));
                graphics2D.fillArc((int) (327 + 36.47 * i), 575, 37, 40, 0, 180);
                graphics2D.setColor(new Color(0x324670));
                graphics2D.fillArc((int) (327 + 36.47 * i), 580, 37, 37, 0, 180);

            }
        }
        //-------------------------------------------------------------//

        //Reset Before Sogo2 Drawing
        AffineTransform reset1 = graphics2D.getTransform();

        //------------------------Sogo2---------------------//
        if (Frame < 25) {
            Sogo2TayehL(graphics2D, reset1);
        } else if (Frame < 50) {
            Sogo2TayehR(graphics2D, reset1);
        } else if (Frame < 75) {
            Sogo2TayehL(graphics2D, reset1);
        } else if (Frame < 100) {
            Sogo2TayehR(graphics2D, reset1);
        } else {
            if (Direction == 1) {
                Sogo2R(graphics2D, reset1);
            } else {
                Sogo2L(graphics2D, reset1);
            }
            graphics2D.setTransform(reset);
        }
        //-------------------------------------------------//

        g.drawImage(OffImage, 0, 0, this);


    }

    //---------------Sogo2 Dancing Functions Left and Right-----------------------//
    public void Sogo2R(Graphics2D graphics2D, AffineTransform reset) {
        /** For Rotations in Sogo2 Parts **/
        Sogo2 = Frame - (12 * (Frame / 12));

        graphics2D.setColor(new Color(0xaf3e52));

        //-----------------Right Leg----------------------//
        graphics2D.rotate(Math.toRadians(-7.5 * Sogo2 / 2), 630, 410);
        graphics2D.fillArc(624, 507, 50, 50, 0, 180);
        GeneralPath path = new GeneralPath();
        path.moveTo(630, 410);
        path.quadTo(645, 470, 624, 532);
        path.lineTo(644, 532);
        path.quadTo(665, 465, 650, 410);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.setTransform(reset);

        //-----------------Right Hand----------------------//
        graphics2D.rotate(Math.toRadians(-7.5 * Sogo2 / 2), 561 + 85 - Sogo2 * 5, 273);
        path = new GeneralPath();
        path.moveTo(561 + 115 - Sogo2 * 5, 273);
        path.quadTo(510 + 120, 335, 535 + 145, 387);
        path.lineTo(555 + 140, 387);
        path.quadTo(530 + 120, 335, 574 + 115 - Sogo2 * 5, 273);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.fillOval(525 + 145, 370, 35, 35);
        graphics2D.setTransform(reset);

        graphics2D.setColor(new Color(0, 0, 0, 50));

        //-----------------Shadow----------------------//
        graphics2D.fillOval(490 + Sogo2 / 2, 527, 247, 8);

        graphics2D.setColor(new Color(0xe9536e));

        //-----------------Main Body----------------------//
        graphics2D.rotate(Math.toRadians(-2.12 * Sogo2 / 2), 535, 440);
        graphics2D.fillRoundRect(535, 140, 155, 300, 155, 155);
        graphics2D.setTransform(reset);

        //-----------------Left Leg----------------------//
        graphics2D.fillArc(624 - 64, 507, 50, 50, 0, 180);
        path = new GeneralPath();
        path.moveTo(630 - 64, 410);
        path.quadTo(645 - 64, 470, 624 - 64, 532);
        path.lineTo(644 - 64, 532);
        path.quadTo(665 - 64, 465, 650 - 64, 410);
        path.closePath();
        graphics2D.fill(path);

        graphics2D.setColor(new Color(0xf297a7));

        //-----------------Sal3a----------------------//
        graphics2D.rotate(Math.toRadians(-2.12 * Sogo2 / 2), 535, 440);
        AffineTransform sal3areset = graphics2D.getTransform();
        graphics2D.rotate(Math.toRadians(-45), 577, 180);
        graphics2D.fillOval(545, 161, 65, 40);
        graphics2D.setTransform(sal3areset);

        graphics2D.setColor(new Color(0x000000));

        //-----------------Eyes----------------------//
        graphics2D.fillRoundRect(615, 220, 18, 34, 18, 18);
        graphics2D.fillRoundRect(650, 220, 18, 34, 18, 18);
        graphics2D.setTransform(reset);

        graphics2D.setColor(new Color(0xec728a));

        //-----------------Left Hand----------------------//
        graphics2D.rotate(Math.toRadians(7.5 * Sogo2 / 2), 561, 273);
        path = new GeneralPath();
        path.moveTo(561, 273);
        path.quadTo(510, 335, 535, 387);
        path.lineTo(555, 387);
        path.quadTo(530, 335, 574, 285);
        path.quadTo(578, 269, 561, 273);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.fillOval(525, 370, 35, 35);

        //-----------------Resetting to the main transform----------------------//
        graphics2D.setTransform(reset);
    }

    public void Sogo2L(Graphics2D graphics2D, AffineTransform reset) {
        /** For Rotations in Sogo2 Parts **/
        Sogo2 = Frame - (12 * (Frame / 12));

        graphics2D.setColor(new Color(0xaf3e52));

        //-----------------Left Leg----------------------//
        graphics2D.rotate(Math.toRadians(7.5 * Sogo2 / 2), 575, 410);
        graphics2D.fillArc(549, 507, 50, 50, 0, 180);
        GeneralPath path = new GeneralPath();
        path.moveTo(575, 410);
        path.quadTo(563, 470, 583, 532);
        path.lineTo(603, 532);
        path.quadTo(583, 465, 595, 410);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.setTransform(reset);

        //-----------------Left Hand----------------------//
        graphics2D.rotate(Math.toRadians(7.5 * Sogo2 / 2), 560 + Sogo2 * 5, 270 + 5 * Sogo2 / 2);
        path = new GeneralPath();
        path.moveTo(561, 270);
        path.quadTo(510 + 70, 335, 535, 387);
        path.lineTo(555, 387);
        path.quadTo(530 + 70, 335, 574 + 10, 280);
        path.quadTo(578, 269, 561, 270);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.fillOval(525, 370, 35, 35);
        graphics2D.setTransform(reset);

        graphics2D.setColor(new Color(0, 0, 0, 50));

        //-----------------Shadow----------------------//
        graphics2D.fillOval(490 + Sogo2 / 2, 527, 247, 8);


        graphics2D.setColor(new Color(0xe9536e));

        //-----------------Main Body----------------------//
        graphics2D.rotate(Math.toRadians(2.12 * Sogo2 / 2), 535, 440);
        graphics2D.fillRoundRect(535, 140, 155, 300, 155, 155);
        graphics2D.setTransform(reset);

        //-----------------Right Leg----------------------//
        graphics2D.fillArc(549 + 64 - Sogo2, 507, 50, 50, 0, 180);
        path = new GeneralPath();
        path.moveTo(575 + 64, 410);
        path.quadTo(563 + 64 - Sogo2, 470, 583 - Sogo2 + 64, 532);
        path.lineTo(603 + 64 - Sogo2, 532);
        path.quadTo(583 + 64 - Sogo2, 465, 595 + 64, 410);
        path.closePath();
        graphics2D.fill(path);

        graphics2D.setColor(new Color(0xf297a7));

        //-----------------Sal3a----------------------//
        graphics2D.rotate(Math.toRadians(2.12 * Sogo2 / 2), 535, 440);
        AffineTransform sal3areset = graphics2D.getTransform();
        graphics2D.rotate(Math.toRadians(45), 645, 180);
        graphics2D.fillOval(614, 161, 65, 40);
        graphics2D.setTransform(sal3areset);

        graphics2D.setColor(new Color(0x000000));

        //--------------------Eyes----------------------//
        graphics2D.fillRoundRect(555, 220, 18, 34, 18, 18);
        graphics2D.fillRoundRect(590, 220, 18, 34, 18, 18);
        graphics2D.setTransform(reset);


        graphics2D.setColor(new Color(0xec728a));

        //-----------------Right Hand----------------------//
        graphics2D.rotate(Math.toRadians(-7.5 * Sogo2 / 2), 660 + Sogo2 * 5 / 2, 285);
        path = new GeneralPath();
        path.moveTo(662 + Sogo2 * 5 / 2, 272);
        path.quadTo(701 + 20 + Sogo2 * 5 / 2, 335 - 20, 698 + Sogo2 * 5 / 2, 387);
        path.lineTo(698 - 20 + Sogo2 * 5 / 2, 387);
        path.quadTo(701 - 20 + 20 + Sogo2 * 5 / 2, 335 - 20, 649 + Sogo2 * 5 / 2, 284);
        path.quadTo(650 + Sogo2 * 5 / 2, 273, 662 + Sogo2 * 5 / 2, 272);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.fillOval(525 + 145 + Sogo2 * 5 / 2, 370, 35, 35);

        //-----------------Resetting to the main transform----------------------//
        graphics2D.setTransform(reset);

    }

    //---------------Sogo2 Standing Functions Left and Right-----------------------//
    public void Sogo2TayehL(Graphics2D graphics2D, AffineTransform reset) {
        Sogo2 = 0;
        graphics2D.setColor(new Color(0xaf3e52));
        graphics2D.rotate(Math.toRadians(7.5 * Sogo2), 630, 410);
        graphics2D.fillArc(549, 507, 50, 50, 0, 180);
        GeneralPath path = new GeneralPath();
        path.moveTo(575, 410);
        path.quadTo(563, 470, 583, 532);
        path.lineTo(603, 532);
        path.quadTo(583, 465, 595, 410);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.setTransform(reset);

        graphics2D.rotate(Math.toRadians(7.5 * Sogo2), 561 + 85 - Sogo2 * 10, 273);
        path = new GeneralPath();
        path.moveTo(561 + 115 - Sogo2 * 10, 273);
        path.quadTo(510 + 120, 335, 535 + 145, 387);
        path.lineTo(555 + 140, 387);
        path.quadTo(530 + 120, 335, 574 + 115 - Sogo2 * 10, 273);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.fillOval(525 + 145, 370, 35, 35);
        graphics2D.setTransform(reset);

        graphics2D.setColor(new Color(0, 0, 0, 50));
        graphics2D.fillOval(490 + Sogo2, 527, 247, 8);


        graphics2D.setColor(new Color(0xe9536e));

        graphics2D.rotate(Math.toRadians(2.12 * Sogo2), 535, 440);
        graphics2D.fillRoundRect(535, 140, 155, 300, 155, 155);
        graphics2D.setTransform(reset);

        graphics2D.fillArc(549 + 64, 507, 50, 50, 0, 180);
        path = new GeneralPath();
        path.moveTo(575 + 64, 410);
        path.quadTo(563 + 64, 470, 583 + 64, 532);
        path.lineTo(603 + 64, 532);
        path.quadTo(583 + 64, 465, 595 + 64, 410);
        path.closePath();
        graphics2D.fill(path);

        graphics2D.setColor(new Color(0xf297a7));
        graphics2D.rotate(Math.toRadians(2.12 * Sogo2), 535, 440);
        AffineTransform sal3areset = graphics2D.getTransform();
        graphics2D.rotate(Math.toRadians(-45), 577, 180);
        graphics2D.fillOval(545, 161, 65, 40);
        graphics2D.setTransform(sal3areset);

        graphics2D.setColor(new Color(0x000000));
        graphics2D.fillRoundRect(615, 220, 18, 34, 18, 18);
        graphics2D.fillRoundRect(650, 220, 18, 34, 18, 18);
        graphics2D.setTransform(reset);

        graphics2D.setColor(new Color(0xec728a));
        graphics2D.rotate(Math.toRadians(-7.5 * Sogo2), 561, 273);
        path = new GeneralPath();
        path.moveTo(561, 273);
        path.quadTo(510, 335, 535, 387);
        path.lineTo(555, 387);
        path.quadTo(530, 335, 574, 285);
        path.quadTo(578, 269, 561, 273);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.fillOval(525, 370, 35, 35);


        graphics2D.setTransform(reset);
    }

    public void Sogo2TayehR(Graphics2D graphics2D, AffineTransform reset) {
        Sogo2 = 0;

        graphics2D.setColor(new Color(0xaf3e52));

        //-----------------Right Leg----------------------//
        graphics2D.rotate(Math.toRadians(-7.5 * Sogo2 / 2), 630, 410);
        graphics2D.fillArc(624, 507, 50, 50, 0, 180);
        GeneralPath path = new GeneralPath();
        path.moveTo(630, 410);
        path.quadTo(645, 470, 624, 532);
        path.lineTo(644, 532);
        path.quadTo(665, 465, 650, 410);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.setTransform(reset);

        //-----------------Left Hand----------------------//
        graphics2D.rotate(Math.toRadians(7.5 * Sogo2 / 2), 560 + Sogo2 * 5, 270 + 5 * Sogo2 / 2);
        path = new GeneralPath();
        path.moveTo(561, 270);
        path.quadTo(510 + 70, 335, 535, 387);
        path.lineTo(555, 387);
        path.quadTo(530 + 70, 335, 574 + 10, 280);
        path.quadTo(578, 269, 561, 270);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.fillOval(525, 370, 35, 35);
        graphics2D.setTransform(reset);


        graphics2D.setColor(new Color(0, 0, 0, 50));

        //-----------------Shadow----------------------//
        graphics2D.fillOval(490 + Sogo2 / 2, 527, 247, 8);


        graphics2D.setColor(new Color(0xe9536e));

        //-----------------Main Body----------------------//
        graphics2D.rotate(Math.toRadians(2.12 * Sogo2 / 2), 535, 440);
        graphics2D.fillRoundRect(535, 140, 155, 300, 155, 155);
        graphics2D.setTransform(reset);

        //-----------------Left Leg----------------------//
        graphics2D.fillArc(624 - 64, 507, 50, 50, 0, 180);
        path = new GeneralPath();
        path.moveTo(630 - 64, 410);
        path.quadTo(645 - 64, 470, 624 - 64, 532);
        path.lineTo(644 - 64, 532);
        path.quadTo(665 - 64, 465, 650 - 64, 410);
        path.closePath();
        graphics2D.fill(path);

        graphics2D.setColor(new Color(0xf297a7));

        //-----------------Sal3a----------------------//
        graphics2D.rotate(Math.toRadians(2.12 * Sogo2 / 2), 535, 440);
        AffineTransform sal3areset = graphics2D.getTransform();
        graphics2D.rotate(Math.toRadians(45), 645, 180);
        graphics2D.fillOval(614, 161, 65, 40);
        graphics2D.setTransform(sal3areset);

        graphics2D.setColor(new Color(0x000000));

        //--------------------Eyes----------------------//
        graphics2D.fillRoundRect(555, 220, 18, 34, 18, 18);
        graphics2D.fillRoundRect(590, 220, 18, 34, 18, 18);
        graphics2D.setTransform(reset);

        graphics2D.setColor(new Color(0xec728a));

        //-----------------Right Hand----------------------//
        graphics2D.rotate(Math.toRadians(-7.5 * Sogo2 / 2), 660 + Sogo2 * 5 / 2, 285);
        path = new GeneralPath();
        path.moveTo(662 + Sogo2 * 5 / 2, 272);
        path.quadTo(701 + 20 + Sogo2 * 5 / 2, 335 - 20, 698 + Sogo2 * 5 / 2, 387);
        path.lineTo(698 - 20 + Sogo2 * 5 / 2, 387);
        path.quadTo(701 - 20 + 20 + Sogo2 * 5 / 2, 335 - 20, 649 + Sogo2 * 5 / 2, 284);
        path.quadTo(650 + Sogo2 * 5 / 2, 273, 662 + Sogo2 * 5 / 2, 272);
        path.closePath();
        graphics2D.fill(path);
        graphics2D.fillOval(525 + 145 + Sogo2 * 5 / 2, 370, 35, 35);

        //-----------------Resetting to the main transform----------------------//
        graphics2D.setTransform(reset);
    }
}
