/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aula01;

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
public class JOGLEXemplo05 
             implements GLEventListener {

    GLU glu = new GLU();

    
    public static void main(String args[])
    {
        new JOGLEXemplo05();
    }
    
    public JOGLEXemplo05()
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
    
    
    
    public void init(GLAutoDrawable glAuto) {
      
    }

    public void display(GLAutoDrawable glAuto) {

        GL2 gl = glAuto.getGL().getGL2();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        gl.glLoadIdentity();
        gl.glTranslated(0,0,-20);
        
        desenhaEstrela(gl);
        
        gl.glTranslated(4,4,0);
        gl.glScaled(0.5, 0.5, 0.5);
        desenhaEstrela(gl);
        gl.glTranslated(4,-8,0);
        gl.glScaled(0.5, 2, 0.5);
        desenhaEstrela(gl);
        
        
        
        
        
    }

       
    
    
    
    
    
    
    public void reshape(GLAutoDrawable gLAutoDrawable, int x, int y, int w, int h) {
  
        GL2 gl = gLAutoDrawable.getGL().getGL2(); 
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60,1,1,30);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslated(0,0,-5);
    }


    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
      
    }

    public void dispose(GLAutoDrawable glad) {

    }

    private void desenhaEstrela(GL2 gl) {
    gl.glBegin(GL2.GL_TRIANGLES);
           gl.glVertex3d(-1, 1, 0);
           gl.glVertex3d( 0, 3, 0);
           gl.glVertex3d( 1, 1, 0);
           
           gl.glVertex3d(-1, -1, 0);
           gl.glVertex3d( 0, -3, 0);
           gl.glVertex3d( 1, -1, 0);
           
           gl.glVertex3d(-1, 1, 0);
           gl.glVertex3d( -3, 0, 0);
           gl.glVertex3d( -1, -1, 0);
           
           gl.glVertex3d( 1,  1, 0);
           gl.glVertex3d( 3,  0, 0);
           gl.glVertex3d( 1, -1, 0);
           
           
        gl.glEnd();

    }

}











