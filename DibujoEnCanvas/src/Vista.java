
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Vista extends JFrame {

    private JButton DibCirculo;
    private JButton DibLinea;
    private JButton DibRect;
    private JButton SelecColor;
    private JButton Borrador;
    private JButton jButton7;
    private JButton BorrarTodo;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JMenuBar jMenuBar2;

    Modelo canvas1;
    private JDialog dialog;
    private JSlider slider = new JSlider();

    public Vista() {
        initComponents();
        slider.setMinimum(2);
        slider.setMaximum(20);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (canvas1.m_alzada) {
                    canvas1.changeStrk(slider.getValue());
                } else {
                    canvas1.strk_width = slider.getValue();
                }
            }
        });
        dialog = new JDialog(this, "Grosor de Linea");

        canvas1 = new Modelo();
        canvas1.setBounds(20, 40, 600, 400);
        canvas1.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                canvas1.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        canvas1.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                canvas1.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                canvas1.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(canvas1);
    }

    private void initComponents() {

        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenu2 = new JMenu();
        DibCirculo = new JButton();
        DibLinea = new JButton();
        DibRect = new JButton();
        SelecColor = new JButton();
        Borrador = new JButton();
        jButton7 = new JButton();
        BorrarTodo = new JButton();
        jMenuBar2 = new JMenuBar();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        DibCirculo.setText("Circulo");
        DibCirculo.setFocusable(false);
        DibCirculo.setHorizontalTextPosition(SwingConstants.CENTER);
        DibCirculo.setVerticalTextPosition(SwingConstants.BOTTOM);
        DibCirculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DibujarCirculo(evt);
            }
        });

        DibLinea.setText("Linea");
        DibLinea.setFocusable(false);
        DibLinea.setHorizontalTextPosition(SwingConstants.CENTER);
        DibLinea.setVerticalTextPosition(SwingConstants.BOTTOM);
        DibLinea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DibujarLinea(evt);
            }
        });

        DibRect.setText("Rectangulo");
        DibRect.setFocusable(false);
        DibRect.setHorizontalTextPosition(SwingConstants.CENTER);
        DibRect.setVerticalTextPosition(SwingConstants.BOTTOM);
        DibRect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DibujarRectangulo(evt);
            }
        });

        SelecColor.setText("Color");
        SelecColor.setFocusable(false);
        SelecColor.setHorizontalTextPosition(SwingConstants.CENTER);
        SelecColor.setVerticalTextPosition(SwingConstants.BOTTOM);
        SelecColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SeleccionarColor(evt);
            }
        });

        Borrador.setText("Borrador");
        Borrador.setFocusable(false);
        Borrador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Borrador.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Borrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Borrador(evt);
            }
        });

        jButton7.setText("Change stroke");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        BorrarTodo.setText("Borrar Todo");
        BorrarTodo.setFocusable(false);
        BorrarTodo.setHorizontalTextPosition(SwingConstants.CENTER);
        BorrarTodo.setVerticalTextPosition(SwingConstants.BOTTOM);
        BorrarTodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BorrarTodo(evt);
            }
        });
        setJMenuBar(jMenuBar2);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(624, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SelecColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(DibRect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(DibLinea, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(DibCirculo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Borrador, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BorrarTodo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(DibCirculo)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DibLinea)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DibRect)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SelecColor)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Borrador)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BorrarTodo)
                                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }

    private void DibujarCirculo(ActionEvent evt) {
        canvas1.circle = true;
        canvas1.m_alzada = false;
        canvas1.rectangle = false;
    }

    private void DibujarLinea(ActionEvent evt) {
        canvas1.circle = false;
        canvas1.m_alzada = true;
        canvas1.rectangle = false;
    }

    private void DibujarRectangulo(ActionEvent evt) {
        canvas1.circle = false;
        canvas1.m_alzada = false;
        canvas1.rectangle = true;
    }

    private void SeleccionarColor(ActionEvent evt) {
        canvas1.changeColor(JColorChooser.showDialog(this, "Selecciona un color", canvas1.color));
    }

    private void Borrador(ActionEvent evt) {
        canvas1.m_alzada = true;
        canvas1.rectangle = false;
        canvas1.circle = false;
        canvas1.changeColor(Color.white);

    }

    private void jButton7ActionPerformed(ActionEvent evt) {
        slider.setValue(canvas1.strk_width);
        dialog.add(slider);
        dialog.setSize(300, 100);
        dialog.setVisible(true);
    }

    private void BorrarTodo(ActionEvent evt) {
        canvas1.erase();
    }

}
