/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _aula05.dia22102018;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */





import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

/**
 *
 * @author Glauco
 */
public class Cosimir_robo
        implements GLEventListener {

    GLU glu = new GLU();
    GLUT glut = new GLUT();

    
    public static void main(String args[])
    {
        new Cosimir_robo();
    }
    private double g;
    private double incG;
    private double g2;
    
    public Cosimir_robo()
    {
        GLJPanel canvas = new GLJPanel();
        canvas.addGLEventListener(this);
        
        JFrame frame = new JFrame("Exemplo01");
        frame.setSize(500, 500);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
      
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        System.exit(0);
                    }
                }).start();
            }
        });

    
    }
    
    
    @Override
    public void init(GLAutoDrawable glAuto) {
        Animator a = new Animator(glAuto);
        a.start();
        GL gl = glAuto.getGL();
        gl.glClearColor(0.4f, 0.4f, 0.4f, 0.4f);
        gl.glEnable(GL.GL_DEPTH_TEST);
    }

    @Override
    public void display(GLAutoDrawable glAuto) {

        GL2 gl = glAuto.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT |
                   GL.GL_DEPTH_BUFFER_BIT
        );
        
        gl.glLoadIdentity();
        gl.glTranslated(0,0,-10);
        gl.glRotated(g2, 0,1, 0);
        
        
        g2 = g2 + 0.2;
        
        
           desenhaBase(gl);
        

        
        if(g <= 0){
            incG = 0.2;
        }
        if(g >= 75){
            incG = -0.2;
        }
        g = g + incG;

    }

       
    
    
    
    
    
    
    public void reshape(GLAutoDrawable gLAutoDrawable, int x, int y, int w, int h) {
  
        GL2 gl = gLAutoDrawable.getGL().getGL2(); 
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60,1,1,300);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslated(0,0,-10);
    }


    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
      
    }

    

    @Override
    public void dispose(GLAutoDrawable glad) {
        
    }

    private void desenhaDedo(GL2 gl) {
    gl.glPushMatrix();
        //Dedo 1a
        gl.glTranslated(1,0.5,0);
        gl.glRotated(g,0,0,1);
        gl.glTranslated(0.25,-0.125,0);
        gl.glPushMatrix();
            gl.glScaled(0.5,0.25,0.25);
            glut.glutWireCube(1);
        gl.glPopMatrix();
        
        //Dedo 1b
        gl.glTranslated(0.25,-0.125,0);
        gl.glRotated(-g*2,0,0,1);
        gl.glTranslated(0.25,0.125,0);
        gl.glPushMatrix();
            gl.glScaled(0.5,0.25,0.25);
            glut.glutWireCube(1);
        gl.glPopMatrix();
        gl.glPopMatrix();
    }

    private void desenhaBase(GL2 gl) {
     gl.glPushMatrix();
      
        //Base
        //gl.glRotated(g, 0, 1, 0);
        gl.glPushMatrix();
            gl.glRotated(90, 1, 0, 0);
            glut.glutWireCylinder(1, 2, 10, 10);
            gl.glTranslated(-0.3, 0, 0);
            gl.glRotated(90, 0, 1, 0);
            glut.glutWireCylinder(1, 0.6, 10, 10);
        gl.glPopMatrix();
        
        //braco
        gl.glPushMatrix();
        gl.glTranslated(0.375, 0.75, 0);
            gl.glPushMatrix();
                gl.glRotated(90, 0, 1, 0);
                glut.glutWireCylinder(0.75, 0.5, 10, 10);
            gl.glPopMatrix();
            gl.glTranslated(0.25, 1.5, 0);
            gl.glScaled(0.375, 3, 1.5);
            glut.glutWireCube(1);
        gl.glPopMatrix();
        
        /*desenhaDedo(gl);
        gl.glPushMatrix();
            gl.glTranslated(0,0,0.5-0.125);
            desenhaDedo(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glTranslated(0,0,-0.5+0.125);
            desenhaDedo(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glRotated(180, 1, 0, 0);
            desenhaDedo(gl);
        gl.glPopMatrix();*/
      gl.glPopMatrix();
    }

    


}











