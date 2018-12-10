package cosimir;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;

public class projeto implements GLEventListener, KeyListener {

    private GLUT glut;
    GLJPanel canvas = new GLJPanel();
    float pos[] = {0,0,0,1};

    private double g=-90;
    public double incG=1;
    private double g2;
    private double incJ1;
    private double incJ2;
    private double incJ3;
    private double incJ4;
    private double incJ5;
    private double incJ6;
    private double j1=0;
    private double j2;
    private double j3;
    private double j4;
    private double j5;
    private double j6;
    private double v=0;
    
    
    
    
    public projeto() {
        canvas.addGLEventListener(this);
        
        JFrame frame = new JFrame("Exemplo01");
        int altura = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setSize(altura, altura);
        frame.setLocation(-8,0);
         frame.addKeyListener(this);
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

    public static void main(String[] args) {
        new projeto();
    }

    /*
 * Initialize material property, light source, lighting model, and depth
 * buffer.
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        Animator ani = new Animator(drawable);
        ani.start();

        glut = new GLUT();
        

        //Habilita o teste de profundidade
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT1);
  

        float luzEspecular[] = {1,1,1,1};
        float luzDifusa[]  ={1f,1f,1f,1.0f};
        float luzAmbiente[]  ={0.1f,0.1f,0.1f,1.0f};
        
        gl.glLightfv(GL2.GL_LIGHT1, 
                     GL2.GL_DIFFUSE, 
                     luzDifusa,0); 

        gl.glLightfv(GL2.GL_LIGHT1, 
                     GL2.GL_AMBIENT,
                     luzAmbiente,0); 
        
        
        gl.glLightfv(GL2.GL_LIGHT1, 
                     GL2.GL_SPECULAR,
                     luzEspecular,0); 
        
        
        

    }

    double r = 0;
    float posZ = -10;
    float incZ = 0.1f;

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT
                | GL.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        
        
        
      //  pos[1]=30;
        
        
        gl.glLightfv(GL2.GL_LIGHT1,
                     GL2.GL_POSITION,
                     pos,
                     0);
        
        gl.glTranslated(-3,-4,-15);
        gl.glRotated(30,0, 1, 0);
        
        //gl.glRotated(r, 0, 1, 0);
        //r = r + 0.05;

        float matDifusa[]  ={0.9f,0,0,1.0f};
        float materialAmbiente[] ={0.25f,0,0,1};
        float materialEspecular[] ={1,1,1,1};
        float brilho = 40;
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,
                        GL2.GL_DIFFUSE,
                        matDifusa,
                        0);
       
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,
                        GL2.GL_AMBIENT,
                        materialAmbiente,
                        0);
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,
                        GL2.GL_SPECULAR,
                        materialEspecular,
                        0);
        
        gl.glMaterialf(GL.GL_FRONT_AND_BACK,
                        GL2.GL_SHININESS,
                        brilho
                        );

        if(j1 >= -90 && j1<=90)
            j1+=incJ1;
      
        
         if(j2 >= -90)
            j2+=incJ2;
        if(j2 <= 90)
            j2+=incJ2;
        
        if(j3 >= -90)
            j3+=incJ3;
        if(j3 <= 90)
            j3+=incJ3;
        
        if(j4 >= -90)
            j4+=incJ4;
        if(j4 <= 90)
            j4+=incJ4;
        
        if(j5 >= -90)
            j5+=incJ5;
        if(j1 <= 90)
            j5+=incJ5;
        
        if(j6 >= -90)
            j6+=incJ6;
        if(j6 <= 90)
            j6+=incJ6;

        
         gl.glPushMatrix();
            desenharBase(gl,j1);
            desenharBraco(gl,j2);
            desenharAnteBraco(gl,j3,j4);
            desenharPunho(gl,j5,j6);
        gl.glPopMatrix();

    }

    GLU glu = new GLU();
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
           GL2 gl = drawable.getGL().getGL2(); 
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60,1,1,300);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslated(0,0,-10);
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
            boolean deviceChanged) {
    }
    
    @Override
      public void keyTyped(KeyEvent key) {
    }

    @Override
    public void keyPressed(KeyEvent key) {


        if(key.getKeyCode()== KeyEvent.VK_PLUS)
        {
            if(v<2)
                v+=0.1;
        }
            
        if(key.getKeyCode()== KeyEvent.VK_MINUS)
        {
            if(v>=0)
                v-=0.1;
        }
            
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD1)
        {
            if(key.isAltDown())
                incJ1=0.2;
            else
                incJ1=-0.2;
        }
        
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD2)
        {
            if(key.isAltDown())
                incJ2=0.2;
            else
                incJ2=-0.2;
        }
        
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD3)
        {
            if(key.isAltDown())
                incJ3=0.2;
            else
                incJ3=-0.2;
        }
        
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD4)
        {
            if(key.isAltDown())
                incJ4=0.2;
            else
                incJ4=-0.2;
        }
        
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD5)
        {
            if(key.isAltDown())
                incJ5=0.2;
            else
                incJ5=-0.2;
        }
        
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD6)
        {
            if(key.isAltDown())
                incJ6=0.2;
            else
                incJ6=-0.2;
        }
        
      
        
    }
    
    public void keyReleased(KeyEvent key) {
     
        if(key.getKeyCode()== KeyEvent.VK_PLUS)
            v+=0;
        if(key.getKeyCode()== KeyEvent.VK_MINUS)
            v+=0.1;
        
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD1)
            incJ1=0;
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD2)
            incJ2=0;
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD3)
            incJ3=0;
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD4)
            incJ4=0;
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD5)
            incJ5=0;
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD6)
            incJ6=0;
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
        gl.glRotated(J1, 0, 1, 0);
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
}
