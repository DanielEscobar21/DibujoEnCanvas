
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Stack;

public class Modelo extends Canvas implements ActionListener, MouseListener, MouseMotionListener {

    private ArrayList rectangles = new ArrayList();
    private ArrayList circles = new ArrayList();
    private ArrayList lineas = new ArrayList();
    public Color color;
    private Stack<String> undo = new Stack<>();
    private Stack<ArrayList> redo = new Stack<>();
    public int strk_width;
    private Path2D shape;
    Image img;
    public boolean m_alzada = false, rectangle = false, circle = false;
    private int uX, uY, x, y;

    Modelo() {
        this.color = Color.BLACK;
        this.setBackground(Color.white);
        this.shape = new Path2D.Float();
        this.strk_width = 5;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D draw = (Graphics2D) g;
        draw.setColor(Color.white);

        draw.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(this.img, 0, 0, null);
        for (int i = 0; i < this.rectangles.size(); i++) {
            ArrayList insite = (ArrayList) this.rectangles.get(i);
            int t_x = Integer.parseInt(insite.get(0).toString());
            int t_y = Integer.parseInt(insite.get(1).toString());
            int s_x = Integer.parseInt(insite.get(2).toString());
            int s_y = Integer.parseInt(insite.get(3).toString());
            draw.setColor((Color) insite.get(4));
            draw.setStroke(new BasicStroke((Integer) insite.get(5)));
            draw.drawRect(t_x, t_y, s_x - t_x, s_y - t_y);
        }
        for (int i = 0; i < this.circles.size(); i++) {
            ArrayList insite = (ArrayList) this.circles.get(i);
            int t_x = Integer.parseInt(insite.get(0).toString());
            int t_y = Integer.parseInt(insite.get(1).toString());
            int s_x = Integer.parseInt(insite.get(2).toString());
            int s_y = Integer.parseInt(insite.get(3).toString());
            draw.setColor((Color) insite.get(4));
            draw.setStroke(new BasicStroke((Integer) insite.get(5)));
            draw.drawOval(t_x, t_y, s_x - t_x, s_y - t_y);
        }
        for (int i = 0; i < this.lineas.size(); i++) {
            ArrayList insite = (ArrayList) this.lineas.get(i);
            draw.setColor((Color) insite.get(1));
            draw.setStroke(new BasicStroke((Integer) insite.get(2)));
            draw.draw((Path2D) insite.get(0));
        }
        draw.setStroke(new BasicStroke(this.strk_width));
        draw.setColor(this.color);
        draw.draw(shape);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        this.uX = point.x;
        this.uY = point.y;
        if (this.m_alzada) {
            shape.moveTo(point.x, point.y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.rectangle) {
            this.rectangles.add(addItem(this.uX, this.uY, this.x, this.y));
            this.undo.push("rectangle");
        }
        if (this.circle) {
            this.circles.add(addItem(this.uX, this.uY, this.x, this.y));
            this.undo.push("circle");
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point point = e.getPoint();
        this.x = point.x;
        this.y = point.y;
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(this.color);
        g.setStroke(new BasicStroke(this.strk_width));
        if (this.rectangle) {
            g.drawRect(this.uX, this.uY, point.x - this.uX, point.y - this.uY);
        }
        if (this.circle) {
            g.drawOval(this.uX, this.uY, point.x - this.uX, point.y - this.uY);
        }
        if (this.m_alzada) {
            shape.lineTo(point.x, point.y);
            this.undo.push("free_hand");
        }
        repaint();
    }

    private ArrayList addItem(int v1, int v2, int v3, int v4) {
        ArrayList ayuda = new ArrayList();
        ayuda.add(v1);
        ayuda.add(v2);
        ayuda.add(v3);
        ayuda.add(v4);
        ayuda.add(this.color);
        ayuda.add(this.strk_width);
        return ayuda;
    }

    public void changeColor(Color clr) {

        if (this.shape != null) {
            ArrayList ayuda = new ArrayList();
            ayuda.add(this.shape);
            ayuda.add(this.color);
            ayuda.add(this.strk_width);
            this.color = clr;
            this.lineas.add(ayuda);
            this.shape = new Path2D.Float();
        } else {
            this.color = clr;
        }

    }

    public void undo() {
        ArrayList help = new ArrayList();
        if (undo.empty()) {
            return;
        }
        String ayuda = this.undo.pop().toString();

        if (ayuda.equals("circle")) {
            help.add((ArrayList) this.circles.get(this.circles.size() - 1));
            help.add("circle");
            this.redo.push(help);
            this.circles.remove(this.circles.size() - 1);
        }
        if (ayuda.equals("rectangle")) {
            help.add((ArrayList) this.rectangles.get(this.rectangles.size() - 1));
            help.add("rectangle");
            this.redo.push(help);
            this.rectangles.remove(this.rectangles.size() - 1);
        }
        if (ayuda.equals("free_hand")) {
            if (this.shape.getCurrentPoint() != null) {
                ArrayList fig = new ArrayList();
                fig.add(this.shape);
                fig.add(this.color);
                fig.add(this.strk_width);
                help.add(fig);
                help.add("free_hand");
                this.redo.add(help);
                this.shape = new Path2D.Float();
            } else {
                System.out.println("entre");
                help.add((ArrayList) this.lineas.get(this.lineas.size() - 1));
                help.add("free_hand");
                this.redo.push(help);
                this.lineas.remove(this.lineas.size() - 1);
            }
        }
        repaint();
    }

    public void redo() {
        if (redo.empty()) {
            return;
        }
        ArrayList last = (ArrayList) redo.pop();
        String type = last.get(1).toString();
        ArrayList element = (ArrayList) last.get(0);
        this.undo.push(type);
        if (type.equals("circle")) {
            this.circles.add(element);
        }
        if (type.equals("rectangle")) {
            this.rectangles.add(element);
        }
        if (type.equals("free_hand")) {
            this.lineas.add(element);
        }
        repaint();
    }

    public void erase() {
        this.rectangles = new ArrayList();
        this.circles = new ArrayList();
        this.lineas = new ArrayList();
        this.shape = new Path2D.Float();
        this.undo.removeAllElements();
        this.redo.removeAllElements();
        repaint();
    }

    public void setImg(Image imagen) {
        int width = imagen.getWidth(null);
        int height = imagen.getHeight(null);
        this.setSize(width, height);
        this.img = imagen;
        this.erase();
        repaint();
    }

    public void changeStrk(int v) {
        ArrayList ayuda = new ArrayList();
        ayuda.add(this.shape);
        ayuda.add(this.color);
        ayuda.add(this.strk_width);
        this.strk_width = v;
        this.lineas.add(ayuda);
        this.shape = new Path2D.Float();
    }

    public boolean isnt_empty() {
        if (this.lineas.size() > 0 || this.rectangles.size() > 0 || this.circles.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
