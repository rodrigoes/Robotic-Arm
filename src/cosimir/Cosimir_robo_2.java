/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosimir;
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
public class Cosimir_robo_2
        implements GLEventListener {

    GLU glu = new GLU();
    GLUT glut = new GLUT();

    
    public static void main(String args[])
    {
        new Cosimir_robo_2();
    }
    private double g=-90;
    private double incG;
    private double g2;
    
    public Cosimir_robo_2()
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
        gl.glTranslated(-3,-4,-15);
        gl.glRotated(30,0, 1, 0);
        
        
        g2 = g2 + 0.2;
        
        gl.glPushMatrix();
            desenharBase(gl,g);
            desenharBraco(gl,g);
            desenharAnteBraco(gl,g,g);
            desenharPunho(gl,g,g);
        gl.glPopMatrix();
        
        if(g <= -90){
            incG = 0.2;
        }
        if(g >= 90){
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
    
    private void desenharBase (GL2 gl, double J1)
    {
        gl.glPushMatrix();
            gl.glTranslated(0, -2.3, 0);
            gl.glScaled(3, 0.6, 3);
            glut.glutSolidCube(1);
        gl.glPopMatrix();
        
        //J1
        gl.glRotated(g, 0, 1, 0);
        gl.glPushMatrix();
            
            
            
            gl.glRotated(90, 1, 0, 0);
            glut.glutSolidCylinder(1.2, 2, 10, 10);
            gl.glTranslated(-0.3, 0, 0);
            gl.glRotated(90, 0, 1, 0);
            glut.glutSolidCylinder(1, 0.6, 10, 10);
        gl.glPopMatrix();
    }
    
    private void desenharBraco (GL2 gl, double J2)
    {
        gl.glTranslated(0, 0.75, 0);
        gl.glRotated(J2, 1, 0, 0);
        
        //braco
        
        //J2
        gl.glPushMatrix();
            gl.glPushMatrix();
                    gl.glTranslated(0.3, 0, 0);
                    gl.glPushMatrix();
                        gl.glRotated(90, 0, 1, 0);
                        glut.glutSolidCylinder(0.75, 0.5, 10, 10);
                    gl.glPopMatrix();
                    gl.glTranslated(0.25, 1.5, 0);
                    gl.glPushMatrix();
                        gl.glScaled(0.5, 3, 1.5);
                        glut.glutSolidCube(1);
                    gl.glPopMatrix();
                    gl.glTranslated(-0.25, 1.5, 0);
                    gl.glPushMatrix();
                        gl.glRotated(90, 0, 1, 0);
                        glut.glutSolidCylinder(0.75, 0.5, 10, 10);
                    gl.glPopMatrix();
                gl.glPopMatrix();

                gl.glPushMatrix();
                    gl.glTranslated(-0.8, 0, 0);
                    gl.glPushMatrix();
                        gl.glRotated(90, 0, 1, 0);
                        glut.glutSolidCylinder(0.75, 0.5, 10, 10);
                    gl.glPopMatrix();
                    gl.glTranslated(0.25, 1.5, 0);
                    gl.glPushMatrix();
                        gl.glScaled(0.5, 3, 1.5);
                        glut.glutSolidCube(1);
                    gl.glPopMatrix();
                    gl.glTranslated(-0.25, 1.5, 0);
                    gl.glPushMatrix();
                        gl.glRotated(90, 0, 1, 0);
                        glut.glutSolidCylinder(0.75, 0.5, 10, 10);
                    gl.glPopMatrix();
                gl.glPopMatrix();

                gl.glPushMatrix();
                    gl.glTranslated(0, 1.5, 0);
                    gl.glScaled(0.6, 2, 1.5);
                    glut.glutSolidCube(1);
                 gl.glPopMatrix();
    }
    
    private void desenharAnteBraco (GL2 gl, double J3, double J4)
    {
         gl.glPushMatrix();
                gl.glTranslated(-0.3,3.25, 0);
                gl.glRotated(J3, 1, 0, 0);
                
                gl.glPushMatrix();
                    gl.glRotated(90, 0, 1, 0);
                    glut.glutSolidCylinder(0.75, 0.6, 10, 10);
                gl.glPopMatrix();
                
                gl.glTranslated(0.3,0.375, 0);
                
                gl.glPushMatrix();
                    gl.glScaled(0.6, 0.75, 1.5);
                    glut.glutSolidCube(1);
                gl.glPopMatrix();
                
                gl.glTranslated(0,1.375, 0);
                
                gl.glPushMatrix(); 
                    gl.glScaled(1.2, 2, 1.5);
                    glut.glutSolidCube(1);
                gl.glPopMatrix();
                
                //J4
                gl.glPushMatrix();
                    gl.glTranslated(0,1.5, 0);
                    gl.glRotated(J4, 0, 1, 0);
                    gl.glPushMatrix();
                        gl.glScaled(1.2, 1, 1.5);
                        glut.glutSolidCube(1);
                    gl.glPopMatrix();
                    
                    gl.glPushMatrix();
                        gl.glTranslated(-0.6,0.5, 0);
                        gl.glRotated(90, 0, 1, 0);
                        glut.glutSolidCylinder(0.75, 0.4, 10, 10);
                        
                        gl.glTranslated(0,0, 0.8);
                        glut.glutSolidCylinder(0.75, 0.4, 10, 10);
    }
    
    private void desenharPunho (GL2 gl, double J5, double J6)
    {
                        //Punho
                        //J5
                        gl.glRotated(J5, 0, 0, 1);
                        gl.glPushMatrix();
                            gl.glTranslated(0,0.5, -0.4);
                            glut.glutSolidCylinder(0.5, 0.4, 10, 10);
                            
                            gl.glTranslated(0,0.25, 0.2);
                            
                            gl.glPushMatrix();
                                gl.glScaled(1, 0.5, 0.4);
                                glut.glutSolidCube(1);
                            gl.glPopMatrix();
                            
                            gl.glRotated(90, 1, 0, 0);
                            gl.glTranslated(0,0,-0.65);
                            glut.glutSolidCylinder(0.7, 0.4, 10, 10);
                            
                            //J6
                            gl.glTranslated(0,0,-0.4);
                            gl.glRotated(J6, 0, 0, 1);
                            glut.glutSolidCylinder(0.6, 0.4, 10, 10);
                            
                            gl.glPushMatrix();
                                gl.glTranslated(0,0.5,-0.3);
                                gl.glScaled(0.2, 0.2, 0.6);
                                glut.glutSolidCube(1);
                            gl.glPopMatrix();
                            
                            gl.glPushMatrix();
                                gl.glTranslated(0,-0.5,-0.3);
                                gl.glScaled(0.2, 0.2, 0.6);
                                glut.glutSolidCube(1);
                            gl.glPopMatrix();
                            
                        gl.glPopMatrix();
                        
                    gl.glPopMatrix();
                    
                gl.glPopMatrix();
                
            gl.glPopMatrix();
            
        gl.glPopMatrix();
    }
             
    private void desenhaRobo(GL2 gl) {
     gl.glPushMatrix();
      
        //Base
        
        gl.glPushMatrix();
            gl.glTranslated(0, -2.3, 0);
            gl.glScaled(3, 0.6, 3);
            glut.glutSolidCube(1);
        gl.glPopMatrix();
        
        //J1
        gl.glRotated(g, 0, 1, 0);
        gl.glPushMatrix();
            
            
            
            gl.glRotated(90, 1, 0, 0);
            glut.glutSolidCylinder(1.2, 2, 10, 10);
            gl.glTranslated(-0.3, 0, 0);
            gl.glRotated(90, 0, 1, 0);
            glut.glutSolidCylinder(1, 0.6, 10, 10);
        gl.glPopMatrix();
        
        
        gl.glTranslated(0, 0.75, 0);
        
        
        gl.glRotated(g, 1, 0, 0);
        
        //braco
        
        //J2
        gl.glPushMatrix();
            
            gl.glPushMatrix();
                gl.glTranslated(0.3, 0, 0);
                gl.glPushMatrix();
                    gl.glRotated(90, 0, 1, 0);
                    glut.glutSolidCylinder(0.75, 0.5, 10, 10);
                gl.glPopMatrix();
                gl.glTranslated(0.25, 1.5, 0);
                gl.glPushMatrix();
                    gl.glScaled(0.5, 3, 1.5);
                    glut.glutSolidCube(1);
                gl.glPopMatrix();
                gl.glTranslated(-0.25, 1.5, 0);
                gl.glPushMatrix();
                    gl.glRotated(90, 0, 1, 0);
                    glut.glutSolidCylinder(0.75, 0.5, 10, 10);
                gl.glPopMatrix();
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslated(-0.8, 0, 0);
                gl.glPushMatrix();
                    gl.glRotated(90, 0, 1, 0);
                    glut.glutSolidCylinder(0.75, 0.5, 10, 10);
                gl.glPopMatrix();
                gl.glTranslated(0.25, 1.5, 0);
                gl.glPushMatrix();
                    gl.glScaled(0.5, 3, 1.5);
                    glut.glutSolidCube(1);
                gl.glPopMatrix();
                gl.glTranslated(-0.25, 1.5, 0);
                gl.glPushMatrix();
                    gl.glRotated(90, 0, 1, 0);
                    glut.glutSolidCylinder(0.75, 0.5, 10, 10);
                gl.glPopMatrix();
            gl.glPopMatrix();
            
            gl.glPushMatrix();
                gl.glTranslated(0, 1.5, 0);
                gl.glScaled(0.6, 2, 1.5);
                glut.glutSolidCube(1);
             gl.glPopMatrix();
             
            //Antebraco
            
            //J3
            gl.glPushMatrix();
                gl.glTranslated(-0.3,3.25, 0);
                gl.glRotated(g, 1, 0, 0);
                
                gl.glPushMatrix();
                    gl.glRotated(90, 0, 1, 0);
                    glut.glutSolidCylinder(0.75, 0.6, 10, 10);
                gl.glPopMatrix();
                
                gl.glTranslated(0.3,0.375, 0);
                
                gl.glPushMatrix();
                    gl.glScaled(0.6, 0.75, 1.5);
                    glut.glutSolidCube(1);
                gl.glPopMatrix();
                
                gl.glTranslated(0,1.375, 0);
                
                gl.glPushMatrix(); 
                    gl.glScaled(1.2, 2, 1.5);
                    glut.glutSolidCube(1);
                gl.glPopMatrix();
                
                //J4
                gl.glPushMatrix();
                    gl.glTranslated(0,1.5, 0);
                    gl.glRotated(g, 0, 1, 0);
                    gl.glPushMatrix();
                        gl.glScaled(1.2, 1, 1.5);
                        glut.glutSolidCube(1);
                    gl.glPopMatrix();
                    
                    gl.glPushMatrix();
                        gl.glTranslated(-0.6,0.5, 0);
                        gl.glRotated(90, 0, 1, 0);
                        glut.glutSolidCylinder(0.75, 0.4, 10, 10);
                        
                        gl.glTranslated(0,0, 0.8);
                        glut.glutSolidCylinder(0.75, 0.4, 10, 10);
                        
                        //Punho
                        //J5
                        gl.glRotated(g, 0, 0, 1);
                        gl.glPushMatrix();
                            gl.glTranslated(0,0.5, -0.4);
                            glut.glutSolidCylinder(0.5, 0.4, 10, 10);
                            
                            gl.glTranslated(0,0.25, 0.2);
                            
                            gl.glPushMatrix();
                                gl.glScaled(1, 0.5, 0.4);
                                glut.glutSolidCube(1);
                            gl.glPopMatrix();
                            
                            gl.glRotated(90, 1, 0, 0);
                            gl.glTranslated(0,0,-0.65);
                            glut.glutSolidCylinder(0.7, 0.4, 10, 10);
                            
                            //J6
                            gl.glTranslated(0,0,-0.4);
                            gl.glRotated(g, 0, 0, 1);
                            glut.glutSolidCylinder(0.6, 0.4, 10, 10);
                            
                            gl.glPushMatrix();
                                gl.glTranslated(0,0.5,-0.3);
                                gl.glScaled(0.2, 0.2, 0.6);
                                glut.glutSolidCube(1);
                            gl.glPopMatrix();
                            
                            gl.glPushMatrix();
                                gl.glTranslated(0,-0.5,-0.3);
                                gl.glScaled(0.2, 0.2, 0.6);
                                glut.glutSolidCube(1);
                            gl.glPopMatrix();
                            
                        gl.glPopMatrix();
                        
                    gl.glPopMatrix();
                    
                gl.glPopMatrix();
                
            gl.glPopMatrix();
            
        gl.glPopMatrix();
        
        
        
      gl.glPopMatrix();
    }
    
}











