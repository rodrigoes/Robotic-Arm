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
public class Teste1 
        implements GLEventListener {

    GLU glu = new GLU();
    GLUT glut = new GLUT();
    
    public static void main(String args[])
    {
        new Teste1();
    }
    private double g;
    
    
    public Teste1()
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
        gl.glTranslated(0,0,-45);
        g+=0.2;
        gl.glPushMatrix();
            gl.glRotated(g,0,0,1);
            glut.glutWireSphere(5, 20, 20);
            gl.glTranslated(12, 0, 0);
            gl.glPushMatrix();
                gl.glRotated(g,0,0,1);
                glut.glutWireSphere(2, 20, 20);
                gl.glTranslated(5, 0, 0);
                gl.glPushMatrix();
                    gl.glRotated(g,0,0,1);
                    glut.glutWireSphere(1, 20, 20);
                gl.glPopMatrix();
            gl.glPopMatrix();
        gl.glPopMatrix();
        gl.glPushMatrix();
            gl.glRotated(g*2,0,0,1);
            gl.glTranslated(22, 0, 0);
            gl.glRotated(g,0,0,1);
            glut.glutWireSphere(3, 20, 20);
        gl.glPopMatrix();
        
        
        
        
        /*gl.glPushMatrix();
            gl.glTranslated(3, 0, 0);
            gl.glRotated(g,0,1,0);
            glut.glutWireSphere(1, 20, 20);
        gl.glPopMatrix();
        
        
        gl.glPushMatrix();
            gl.glTranslated(0, 3, 0);
            gl.glRotated(g,0,1,0);
            glut.glutWireTeapot(1);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(0, -3, 0);
            gl.glRotated(g,0,1,0);
            glut.glutWireRhombicDodecahedron();
        gl.glPopMatrix();
       
         gl.glPushMatrix();
            gl.glTranslated(-3, 0, 0);
            gl.glRotated(g++,0,1,0);
            glut.glutWireCone(1, 2, 10, 10);
        gl.glPopMatrix();*/
        
        
        
        
        
        
        
        
        
        
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

    

}











