package _aula05.dia22102018;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import com.jogamp.opengl.util.Animator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javafx.animation.Animation;
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
public class Xadrez 
        implements GLEventListener {

    GLU glu = new GLU();

    
    public static void main(String args[])
    {
        new Xadrez();
    }
    private double r;
    private double g;
    private double z;
    private double inc=-0.01;
    
    public Xadrez()
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
        gl.glTranslated(-7,-7,-20);
        gl.glRotated(g++,0 , 1, 0);
        
       
        desenhaTabuleiro(gl);
        gl.glTranslated(0, -1, 1);
        gl.glRotated(90, 1, 0, 0);
        desenhaTabuleiro(gl);
        
        
        
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

    

    private void desenhaQuadrado(int cor, GL2 gl) {
        
        gl.glColor3f(cor, cor, cor);
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3d(1,1,0);
            gl.glVertex3d(-1,1,0);
            gl.glVertex3d(-1,-1,0);
            gl.glVertex3d(1,-1,0);
        gl.glEnd();
        
    }

    private void desenhaLinha(GL2 gl, int start) {
       
       gl.glPushMatrix();
            for(int i=0;i<8;i++){
               if(start==0){
                   desenhaQuadrado(i%2,gl);
               } 
               else{
                   desenhaQuadrado((i+1)%2,gl);
               }
               
               gl.glTranslated(2,0,0);
            }
       gl.glPopMatrix();
    }

    
    
    private void desenhaTabuleiro(GL2 gl) {
        gl.glPushMatrix();
        for(int i=0;i<8;i++){
            desenhaLinha(gl,i%2);
            gl.glTranslated(0, 2, 0);
        }
       gl.glPopMatrix();
    }

}











