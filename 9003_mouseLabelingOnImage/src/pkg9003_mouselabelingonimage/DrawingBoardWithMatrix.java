/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9003_mouselabelingonimage;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DrawingBoardWithMatrix extends JFrame implements KeyListener
{  
    DrawingBoardWithMatrix d;
    boolean clear = false;
    private static ArrayList<Rectangle> rectangles = new ArrayList();
    private static ArrayList<String>        labels = new ArrayList();
    int width;
    int height;
    String imagePath;
    
     public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
        for (int i = 0; i < this.rectangles.size(); i++) {
            try{
                System.out.println(this.rectangles.get(i).toString());
                System.out.println(this.labels.get(i));
            }catch(Exception w){System.out.println("error at: "+i);}
        }
    }

     private void checkIfRectangleClicked(Point p) {
        Rectangle temp;
        for (int i = 0; i < this.rectangles.size(); i++) 
        {
            temp = this.rectangles.get(i);
            if(temp.isPointInRectangle(p))
            {
                System.out.println("delete Rectangle => "+i);
                
            }
        }
    }
     
    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
    }
    
    
    
        String label = "";
        public void main(String[] args) {
          //d = new DrawingBoardWithMatrix();
          this.addKeyListener(this);
        }

        public DrawingBoardWithMatrix() {
          this.setSize(300, 300);
          //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.add(new PaintSurface(), BorderLayout.CENTER);
          this.setVisible(true);
        }
        public DrawingBoardWithMatrix(String fileName, int width, int height) {
            this.imagePath = fileName;
            this.width     = width;
            this.height    = height;
            this.setSize(300, 300);
            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.add(new PaintSurface(), BorderLayout.CENTER);
            this.setVisible(true);
        }

    /**
     * @return the rectangles
     */
    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    /**
     * @param rectangles the rectangles to set
     */
    public void setRectangles(ArrayList<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }

    /**
     * @return the labels
     */
    public ArrayList<String> getLabels() {
        return labels;
    }

    /**
     * @param labels the labels to set
     */
    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

        private class PaintSurface extends JComponent {
          ArrayList<Shape> shapes = new ArrayList<Shape>();

          Point startDrag, endDrag;

    public PaintSurface() 
    {
        final String name;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                  startDrag = new Point(e.getX(), e.getY());
                  endDrag = startDrag;
                  repaint();
            }

            public void mouseReleased(MouseEvent e) {
                Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
                Rectangle r1 = new Rectangle(startDrag.x, startDrag.y, e.getX(), e.getY(),"");
                
                if( (startDrag.x>e.getX() | startDrag.x<e.getX()) & (startDrag.y > e.getY() | startDrag.y < e.getY()))
                {
                    rectangles.add(r1);
                    getLabel(e);      
                    shapes.add(r);
                    startDrag = null;
                    endDrag = null;                    
                }
                else
                {
                    System.out.println("one click");
                    clear = true;
                    checkIfRectangleClicked(new Point(startDrag.x, startDrag.y));
                }        
                repaint();
            }

                private String getLabel(MouseEvent e) {
                
                    //String label = "";
                    
                    final JFrame parent = new JFrame();
                    JButton button = new JButton();

                    button.setText("Click me to show dialog!");
                    parent.add(button);
                    //button.doClick();
                    parent.pack();
                    parent.setVisible(true);
                    //String name;
                    //button.doClick();
                    //button.doClick();
                    button.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            label = JOptionPane.showInputDialog(parent,
                                    "Set label for this segment", null);  
                            
                            getLabels().add(label);
                            parent.dispose();
                            //System.exit(0);
                            //setLabel(name);
                            //System.out.println("rectangle sizes = "+startDrag.x+","+ startDrag.y+","+ e.getX()+","+ e.getY()+" = "+label);
                        }
                        
                    });
                    button.doClick();
                    
                    
                    return label;
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }
        private void paintBackground(Graphics2D g2){
            Image img = null;
            try{
                img = ImageIO.read(new File(imagePath));
            }catch(Exception e){System.out.println("cant load ");}
            g2.drawImage(img, 0, 0, null);
            g2.setPaint(Color.LIGHT_GRAY);
            for (int i = 0; i < getSize().width; i += 10) {
              Shape line = new Line2D.Float(i, 0, i, getSize().height);
              g2.draw(line);
            }

            for (int i = 0; i < getSize().height; i += 10) {
              Shape line = new Line2D.Float(0, i, getSize().width, i);
              g2.draw(line);
            }


          }
        public void paint(Graphics g) 
        {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            paintBackground(g2);
            Color[] colors = { Color.YELLOW, Color.MAGENTA, Color.CYAN , Color.RED, Color.BLUE, Color.PINK};
            int colorIndex = 0;

            g2.setStroke(new BasicStroke(2));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.30f));

            for (Shape s : shapes) {
                g2.setPaint(Color.BLACK);
                g2.draw(s);
                g2.setPaint(colors[(colorIndex++) % 6]);
                g2.fill(s);
            }

            if (startDrag != null && endDrag != null ) {
                g2.setPaint(Color.RED);
                Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                g2.draw(r);
            }
            if(clear==true)
            {
                g2.clearRect(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                clear=false;
            }
        }

          private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
            return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
          }
        }
}