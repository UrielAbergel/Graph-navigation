package Graph_GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;

import javax.swing.JFrame;

public class GUI extends JFrame implements ActionListener, MouseListener
{
    LinkedList<Point3D> points = new LinkedList<Point3D>();
    DGraph p  ;

    public GUI()
    {
        initGUI();
    }

    private void initGUI()
    {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        menuBar.add(menu);
        this.setMenuBar(menuBar);

        MenuItem item1 = new MenuItem("Item 1");
        item1.addActionListener(this);

        MenuItem item2 = new MenuItem("Item 2");
        item2.addActionListener(this);

        menu.add(item1);
        menu.add(item2);

        this.addMouseListener(this);

    }
    @Override
    public void paint(Graphics g)
    {
        //super.paint(g);
        NodeData prev = null;
        for(Integer key : p.GraphMap.keySet()) {
            NodeData NData = (NodeData) p.GraphMap.get(key);
            g.drawString(""+NData.getKey() ,(int) NData.P3D.x(),(int)NData.P3D.y());
            boolean flag = true;
            for (Integer KeyNode : NData.HM.keySet()) {
                EdgeData DestEdge = NData.HM.get(KeyNode);
                g.setColor(Color.BLACK);
                NodeData src = (NodeData) p.GraphMap.get(DestEdge.getSrc());
                NodeData dest = (NodeData) p.GraphMap.get(DestEdge.getDest());
                g.fillOval((int) src.P3D.x(), (int) src.P3D.y(), 5, 5);
                g.fillOval((int) dest.P3D.x(), (int) dest.P3D.y(), 5, 5);
                if(prev!=null) {

                    g.setColor(Color.red);
                    g.drawLine((int) src.P3D.ix(), (int) src.P3D.y(), (int) dest.P3D.x(), (int) dest.P3D.y());

                    if(src.getDestEdge(dest.getKey())!= null && flag) {
                        flag= false;
                        g.setColor(Color.blue);
                        g.drawString("" + src.getDestEdge(dest.getKey()).getWeight(), (int) (((src.P3D.ix() + prev.P3D.ix()) / 2)), (int) ((src.P3D.iy() + prev.P3D.iy()) / 2));
                    }
                }

                prev = (NodeData)p.GraphMap.get(key);
            }
            flag = true;
        }


    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        DGraph q = new DGraph();
        NodeData p1 = new NodeData(1,2,4);
        NodeData p2 = new NodeData(3,4,5);
        NodeData p3 = new NodeData(5,6,7);
        NodeData p4 = new NodeData(7,8,4);
        q.addNode(p1);
        q.addNode(p2);
        q.addNode(p3);
        q.addNode(p4);
        q.connect(1,2,10);
        q.connect(1,3,4);
        q.connect(1,4,2);
        q.connect(4,3,5);
        repaint();


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point3D p = new Point3D(x,y);
        points.add(p);
        repaint();
        System.out.println("mousePressed");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }

    public static void main(String[] args) {
        DGraph q = new DGraph();
        NodeData p1 = new NodeData(100,200,4);
        NodeData p2 = new NodeData(300,400,5);
        NodeData p3 = new NodeData(150,300,7);
        NodeData p4 = new NodeData(200,450,4);
        q.addNode(p1);
        q.addNode(p2);
        q.addNode(p3);
        q.addNode(p4);
        q.connect(1,2,60);
        q.connect(1,3,50);
        q.connect(1,4,40);
        q.connect(4,3,30);

        GUI r = new GUI();
        r.p = q;
        r.repaint();
        r.setVisible(true);



    }
}
