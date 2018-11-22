import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.CHAIN_APPROX_SIMPLE;
import static org.opencv.imgproc.Imgproc.CV_HOUGH_GRADIENT;
import static org.opencv.imgproc.Imgproc.MORPH_GRADIENT;
import static org.opencv.imgproc.Imgproc.RETR_CCOMP;
import static org.opencv.imgproc.Imgproc.RETR_EXTERNAL;
import static org.opencv.imgproc.Imgproc.RETR_TREE;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY_INV;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carolina
 */
public class Dados 
{
        public  static  void main ( String [] args) 
    {
        
        Mat salida =  new Mat();
        Mat salidaf =  new Mat();
        Mat contor = new Mat();
        Mat circles = new Mat() ;
        List<MatOfPoint> contours = new ArrayList<>();
        int cantCircu = 0;
        int cantDados = 0;
        
        //Mat imagen = Imgcodecs.imread("C:\\Users\\carol\\Documents\\NetBeansProjects\\Taller\\dado1.jpg");//carga imagen original
        Mat imagen = Imgcodecs.imread("C:\\Users\\carol\\Documents\\NetBeansProjects\\Dados\\Dados.png");//carga imagen original
        Imgproc.cvtColor(imagen, salidaf, Imgproc.COLOR_RGB2GRAY);//pasar la imagen a escala de grises
        Imgproc.blur(salidaf, salidaf, new Size(9, 9), new Point(-1, -1));
        Imgproc.threshold(salidaf, salida,220,255,THRESH_BINARY_INV); // 128 es el valor de umbral al azar.
        //Imgproc.Canny (salida, borde, 50, 200, 3, false );
        
        
        //Imgproc.HoughCircles(salidaf, Arrays.asList(circles), CV_HOUGH_GRADIENT, 2, salidaf.rows()/2, 200, 100 );
        Imgproc.HoughCircles(salida, circles, CV_HOUGH_GRADIENT, 1.0,
                ( double ) salida.rows () /100, // cambiar este valor para detectar círculos con diferentes distancias entre sí
                10.0, 18.0, 1, 40);
        //Por ultimo dibujamos los círculos que encontramos en la imagen
        for ( int i = 0; i < circles.cols(); i++ ) 
        {
            double [] c = circles.get (0, i);
            Point centro = new  Point (Math.round (c [0]), Math.round (c [1]));
            // centro del círculo
            Imgproc.circle (imagen, centro, 1, new Scalar (0,100,100), 3, 8, 0);
            //(Mat img, Point center, int radius, Scalar color)
            // contorno del círculo
            int radio = (int) Math.round (c [2]);
            Imgproc.circle (imagen, centro, radio, new  Scalar (255,90,255), 3, 8, 0);
            
            cantCircu = cantCircu+1;
            //System.out.println(cantCircu);
                    
        }
             
        
        //contorno dados
        Imgproc.findContours(salida, contours, contor, RETR_CCOMP, CHAIN_APPROX_SIMPLE);
        //(Mat image, List<MatOfPoint> contours, Mat hierarchy, int mode, int method)
        Imgproc.drawContours(salida,(List<MatOfPoint>)contours,-1, new Scalar(0,0,255), 2);
        cantDados = contours.size()-1-cantCircu;
        System.out.println("Hay  "+cantDados +"   dados");//numero de dados*/
        System.out.println("Los dados suman  " + cantCircu);
        
        HighGui.imshow("Original", imagen);// impresion de la imagen original
        //HighGui.imshow("Circulos", salida);
        HighGui.waitKey();
       
    }
    
    static
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

       
}
