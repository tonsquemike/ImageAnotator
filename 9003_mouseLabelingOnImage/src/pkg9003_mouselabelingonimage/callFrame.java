/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9003_mouselabelingonimage;

import Funciones.MyListArgs;
import Funciones.MySintaxis;
import java.util.ArrayList;

/**
 *
 * @author miguel
 */
public class callFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int width         = 0;//Height of the JFrame, extract this from BufferedImage size
        int height        = 0;//Width of the JFrame, extract this from BufferedImage size
        String background = "/Users/miguel/Desktop/pages/page3.png";//path to image source
        String IN;
        String OUT;
        String        ConfigFile = "";
        MyListArgs         Param     ;

        Param      = new MyListArgs(args)                  ;
        ConfigFile = Param.ValueArgsAsString("-CONFIG", "");

        if (!ConfigFile.equals(""))
        {
            Param.AddArgsFromFile(ConfigFile);
        }

        String Sintaxis   = "-IN:str -OUT:str";
        MySintaxis Review = new MySintaxis(Sintaxis, Param);

        IN                = Param.ValueArgsAsString ( "-IN"     , "" );
        OUT               = Param.ValueArgsAsString ( "-OUT"    , "" );
        
        ArrayList <Rectangle> rectangles;
        ArrayList <String>        labels;
        
        DrawingBoardWithMatrix d = new DrawingBoardWithMatrix();
        d.setWidth(width);
        d.setHeight(height);
        d.setImagePath(IN);
        
        d.main(args);
        
        labels     = d.getLabels();
        rectangles = d.getRectangles();
        
        System.out.println("mensaje = "+labels.size()+" ---ª "+rectangles.size());
    }
    
}
