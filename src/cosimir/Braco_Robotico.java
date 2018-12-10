package projeto_braco_robotico;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Toolkit;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;

public class Braco_Robotico implements GLEventListener, KeyListener {

    private GLUT glut;
    GLJPanel canvas = new GLJPanel();
    float pos[] = {0,0,0,1};

    private double g=-90;
    public double incG=1;
    
    
    private double incJ1;
    private double incJ2;
    private double incJ3;
    private double incJ4;
    private double incJ5;
    private double incJ6;
    private double j1=0.0;
    private double j2=0.0;
    private double j3=0.0;
    private double j4=0.0;
    private double j5=0.0;
    private double j6=0.0;
    private double v=0.0;
    
    private double position [][] = new double [100][7];
    private boolean gravar=false;
    private boolean executar=false;
    private int n =0;
    
    private int altura;
    private int largura;
    
    private int x=0;
    private int rX=0;
    private int rY=0;
    private int z=-20;
    
    private int a=0;
    private boolean maior1=false;
    private boolean menor1=false;
    private boolean maior2=false;
    private boolean menor2=false;
    private boolean maior3=false;
    private boolean menor3=false;
    private boolean maior4=false;
    private boolean menor4=false;
    private boolean maior5=false;
    private boolean menor5=false;
    private boolean maior6=false;
    private boolean menor6=false;
    
    private boolean loop=false;
    
    
    
    public Braco_Robotico() {
        canvas.addGLEventListener(this);
        
        JFrame frame = new JFrame("Braço Robótico");
        altura = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        largura = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        frame.setSize(largura+16, altura);
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
        new Braco_Robotico();
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
        
        
        
        
        gl.glPushMatrix();
            gl.glTranslated(0, 0, -3);
            gl.glEnable(GL2.GL_COLOR_MATERIAL);
            gl.glColor3d(0, 0, 0);
            gl.glRasterPos2d(0.8, 1.60);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_18,"CONTROL:");
            gl.glRasterPos2d(0.8, 1.45);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"NUMPAD1 = J1+");
            gl.glRasterPos2d(0.8, 1.40);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"ALT + NUMPAD1 = J1-");
            gl.glRasterPos2d(0.8, 1.35);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"NUMPAD2 = J2+");
            gl.glRasterPos2d(0.8, 1.30);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"ALT + NUMPAD2 = J2-");
            gl.glRasterPos2d(0.8, 1.25);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"NUMPAD3 = J3+");
            gl.glRasterPos2d(0.8, 1.20);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"ALT + NUMPAD3 = J3-");
            gl.glRasterPos2d(0.8, 1.15);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"NUMPAD4 = J4+");
            gl.glRasterPos2d(0.8, 1.10);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"ALT + NUMPAD4 = J4-");
            gl.glRasterPos2d(0.8, 1.05);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"NUMPAD5 = J5+");
            gl.glRasterPos2d(0.8, 1.00);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"ALT + NUMPAD5 = J5-");
            gl.glRasterPos2d(0.8, 0.95);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"NUMPAD6 = J6+");
            gl.glRasterPos2d(0.8, 0.90);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"ALT + NUMPAD6 = J6-");
            
            gl.glRasterPos2d(1.10, 1.45);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"+ = velocidade ++");
            gl.glRasterPos2d(1.10, 1.40);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"- = velocidade --");
            gl.glRasterPos2d(1.10, 1.35);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"UP = Zoom ++");
            gl.glRasterPos2d(1.10, 1.30);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"DOWN = Zoom --");
            gl.glRasterPos2d(1.10, 1.25);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"ALT + UP = Rotated in + X");
            gl.glRasterPos2d(1.10, 1.20);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"ALT + DOWN = Rotated in - X");
            gl.glRasterPos2d(1.10, 1.15);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"LEFT = Translated in - X");
            gl.glRasterPos2d(1.10, 1.10);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"RIGTH = Translated in + X");
            gl.glRasterPos2d(1.10, 1.05);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"ALT + LEFT = Rotated in - Y");
            gl.glRasterPos2d(1.10, 1.00);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"ALT + RIGTH = Rotated in + Y");
            gl.glRasterPos2d(1.10, 0.95);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"SPACE = Record Point");
            gl.glRasterPos2d(1.10, 0.90);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"ENTER = Execute");
            
            gl.glRasterPos2d(0.80, 0.85);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"R = Reset Position");
            
            gl.glRasterPos2d(1.10, 0.85);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"L = loop");
            
            gl.glRasterPos2d(0.8, 0.75);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_18,"DADOS:");
            
            gl.glRasterPos2d(0.8, 0.65);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_18,"VELOCIDADE: "+v);
            
            gl.glRasterPos2d(0.8, 0.55);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_18,"POSIÇÕES GRAVADAS: "+n);
            
            gl.glRasterPos2d(0.8, 0.42);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_18,"POSIÇÃO ATUAL: ");
            
            gl.glRasterPos2d(0.8, 0.35);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"J1: "+j1);
            
            gl.glRasterPos2d(0.8, 0.30);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"J2: "+j2);
            
            gl.glRasterPos2d(0.8, 0.25);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"J3: "+j3);
            
            gl.glRasterPos2d(0.8, 0.20);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"J4: "+j4);
            
            gl.glRasterPos2d(0.8, 0.15);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"J5: "+j5);
            
            gl.glRasterPos2d(0.8, 0.10);
            glut.glutBitmapString(glut.BITMAP_HELVETICA_12,"J6: "+j6);
            
            
            gl.glDisable(GL2.GL_COLOR_MATERIAL);
        gl.glPopMatrix();
        
        float matDifusa1[]  ={0.5f,0.5f,0.5f,1.0f};
        float materialAmbiente1[] ={0.8f,0.8f,0.8f,1.0f};
        float materialEspecular1[] ={0.9f,0.9f,0.9f,1.0f};
        float brilho1 = 80;
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,
                        GL2.GL_DIFFUSE,
                        matDifusa1,
                        0);
       
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,
                        GL2.GL_AMBIENT,
                        materialAmbiente1,
                        0);
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,
                        GL2.GL_SPECULAR,
                        materialEspecular1,
                        0);
        
        gl.glMaterialf(GL.GL_FRONT_AND_BACK,
                        GL2.GL_SHININESS,
                        brilho1
                        );
        
        gl.glPushMatrix();
            gl.glTranslated(0, -6.6, 0);
            gl.glScaled((double)altura/(largura+16), 1, 1);
            gl.glBegin(GL2.GL_QUADS);
                
                int l=60;
                gl.glVertex3d(-(l/3), 0,0);
                gl.glVertex3d((l/3), 0,0);
                gl.glVertex3d((l/3), 0,-l);
                gl.glVertex3d(-(l/3), 0,-l);
                
                gl.glVertex3d(-(l/3), 0,-l);
                gl.glVertex3d(-(l/3), l/3,-l);
                gl.glVertex3d((l/3), l/3,-l);
                gl.glVertex3d((l/3), -(l/3),-l);
                
                gl.glVertex3d((l/3), -(l/3),-l);
                gl.glVertex3d((l/3), -(l/3),0);
                gl.glVertex3d((l/3), (l/3),0);
                gl.glVertex3d((l/3), (l/3),-l);
                
                gl.glVertex3d((l/3), (l/3),-l);
                gl.glVertex3d((l/3), (l/3),0);
                gl.glVertex3d(-(l/3), (l/3),0);
                gl.glVertex3d(-(l/3), (l/3),-l);
                
                gl.glVertex3d(-(l/3), (l/3),-l);
                gl.glVertex3d(-(l/3), -(l/3),-l);
                gl.glVertex3d(-(l/3), -(l/3),0);
                gl.glVertex3d(-(l/3),(l/3),0);
                
            gl.glEnd();
        gl.glPopMatrix();
        
        
        //gl.glRotated(r, 0, 1, 0);
        //r = r + 0.05;

        float matDifusa[]  ={0.7f,0.3f,0,1.0f};
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
        
        if(!executar)
            movimentacao();
        
  
        if(gravar)
        {
            
            if(v>0)
            {
                gravarPosicao(j1,j2,j3,j4,j5,j6,v,n);
                System.out.println("J1= "+j1+" J2= "+j2+" J3= "+j3+" J4= "+j4+" J5= "+j5+" J6= "+j6+" v= "+v);
                n++;
            }
            gravar=false;
        }
        
        if(executar||loop)
        {
            executarMovimento();  
        }      
            
        
        
        
        gl.glPushMatrix();
            
            gl.glTranslated(x,-4,z);
            gl.glRotated(rX,1, 0, 0);            
            gl.glScaled((double)altura/(largura+16), 1, 1);
            gl.glRotated(rY,0, 1, 0);
            
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


        if(key.getKeyCode()== KeyEvent.VK_ADD)
        {
            if(v<4)
                v+=0.1;
        }      
        if(key.getKeyCode()== KeyEvent.VK_SUBTRACT)
        {
            if(v>0)
                v-=0.1;
        }            
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD1)
        {
            if(key.isAltDown())
                incJ1=v;
            else
                incJ1=-v;
        }        
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD2)
        {
            if(key.isAltDown())
                incJ2=v;
            else
                incJ2=-v;
        }        
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD3)
        {
            if(key.isAltDown())
                incJ3=v;
            else
                incJ3=-v;
        }        
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD4)
        {
            if(key.isAltDown())
                incJ4=v;
            else
                incJ4=-v;
        }        
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD5)
        {
            if(key.isAltDown())
                incJ5=v;
            else
                incJ5=-v;
        }        
        if(key.getKeyCode()== KeyEvent.VK_NUMPAD6)
        {
            if(key.isAltDown())
                incJ6=v;
            else
                incJ6=-v;
        }        
        if(key.getKeyCode()== KeyEvent.VK_SPACE)
            gravar=true;        
        if(key.getKeyCode()== KeyEvent.VK_ENTER)
            executar=true;  
        
        if(key.getKeyCode()== KeyEvent.VK_LEFT)
        {
            if(key.isAltDown())
            {
                if(rY>-180)
                    rY--;
            }
            else
                if(x>-(15*altura/(largura+16)))
                    x--;
        } 
        
        if(key.getKeyCode()== KeyEvent.VK_RIGHT)
        {
            if(key.isAltDown())
            {
                if(rY<180)
                    rY++;
            }
            else
                if(x<(15*altura/(largura+16)))
                x++;
        } 
        
        if(key.getKeyCode()== KeyEvent.VK_UP)
        {
            if(key.isAltDown())
            {
                if(rX<90)
                    rX++;
            }
            else
            {
                if (z<-10)
                    z++;
            }
        }
        
        if(key.getKeyCode()== KeyEvent.VK_DOWN)
        {
            if(key.isAltDown())
            {
                if(rX>-90)
                    rX--;
            }
            else
            {
                if(z>-40)
                z--;
            }
        } 
        
        if(key.getKeyCode()== KeyEvent.VK_R)
        {
            x=0;
            rX=0;
            rY=0;
            z=-20;
        }
        
        if(key.getKeyCode()== KeyEvent.VK_L)
            loop=!loop;
    }
    
    @Override
    public void keyReleased(KeyEvent key) {
     
        if(key.getKeyCode()== KeyEvent.VK_ADD)
            v+=0;
        if(key.getKeyCode()== KeyEvent.VK_SUBTRACT)
            v+=0;        
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
        if(key.getKeyCode()== KeyEvent.VK_SPACE)
            gravar=false;
        //if(key.getKeyCode()== KeyEvent.VK_ENTER)
          //  executar=false;
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
    
    public void movimentacao ()
    {
        if(j1 >= -90 && j1<=90)
            j1+=incJ1;
        if(j1>90)
            j1=90;
        if(j1<-90)
            j1=-90;
        
        if(j2 >= -90 && j2<=90)
            j2+=incJ2;
        if(j2>90)
            j2=90;
        if(j2<-90)
            j2=-90;
        
        if(j3 >= -90 && j3<=90)
            j3+=incJ3;
        if(j3>90)
            j3=90;
        if(j3<-90)
            j3=-90;
        
        if(j4 >= -90 && j4<=90)
            j4+=incJ4;
        if(j4>90)
            j4=90;
        if(j4<-90)
            j4=-90;
        
        if(j5 >= -90 && j5<=90)
            j5+=incJ5;
        if(j5>90)
            j5=90;
        if(j5<-90)
            j5=-90;
        
        if(j6 >= -90 && j6<=90)
            j6+=incJ6;
        if(j6>90)
            j6=90;
        if(j6<-90)
            j6=-90;
    }
    
    void gravarPosicao(double j1, double j2, double j3, double j4, double j5, double j6, double v, int n)
    {
        double p[]={j1,j2,j3,j4,j5,j6,v};
        for (int i=0;i<7;i++)
        {
            position[n][i]=p[i];
        }
        System.out.print("Ponto "+(n+1)+" gravado com sucesso: ");
    }
    
    void executarMovimento ()
    {
        System.out.println("Executando movimento: ");
            if(n>0)
            {
                double p[]= new double [7];
                if (a<n)
                {
                    System.out.print("Posicao "+(a+1));
                    for(int j=0;j<7;j++)
                    {
                        p[j]=position[a][j];
                        System.out.print(" J"+(j+1));
                    }
                    System.out.println(" copiadas com sucesso ! ");
                    
                    if(j1 == p[0])
                    {
                        menor1=false;
                        maior1=false;
                    }
                    if(j2 == p[1])
                    {
                        menor2=false;
                        maior2=false;
                    }
                    if(j3 == p[2])
                    {
                        menor3=false;
                        maior3=false;
                    }
                    if(j4 == p[3])
                    {
                        menor4=false;
                        maior4=false;
                    }
                    if(j5 == p[4])
                    {
                        menor5=false;
                        maior5=false;
                    }
                    if(j6 == p[5])
                    {
                        menor6=false;
                        maior6=false;
                    }
                    
                    if(j1!=p[0] || j2!=p[1] || j3!=p[2] || j4!=p[3] || j5!=p[4] || j6!=p[5])
                    {
                        System.out.println(" movimentando");

                        if(j1<p[0])
                        {
                            j1+=p[6];
                            System.out.println("J1="+j1);
                            menor1=true;
                        }
                        else if(j1>p[0])
                        {
                            j1-=p[6];
                            System.out.println("J1="+j1);
                            maior1=true;
                        }                        
                        if (menor1&&maior1)
                        {
                            j1=p[0];
                            menor1=false;
                            maior1=false;
                        }
                        
                        if(j2<p[1])
                        {
                            j2+=p[6];
                            menor2=true;
                        }
                        else if(j2>p[1])
                        {
                            j2-=p[6];
                            maior2=true;
                        }
                        if (menor2&&maior2)
                        {
                            j2=p[1];
                            menor2=false;
                            maior2=false;
                        }
                        
                        if(j3<p[2])
                        {
                            j3+=p[6];
                            menor3=true;
                        }
                        else if(j3>p[2])
                        {
                            j3-=p[6];
                            maior3=true;
                        }
                        if (menor3&&maior3)
                        {
                            j3=p[2];
                            menor3=false;
                            maior3=false;
                        }

                        if(j4<p[3])
                        {
                            j4+=p[6];
                            menor4=true;
                        }
                        else if(j4>p[3])
                        {
                            j4-=p[6];
                            maior4=true;
                        }
                        if (menor4&&maior4)
                        {
                            j4=p[3];
                            menor4=false;
                            maior4=false;
                        }

                        if(j5<p[4])
                        {
                            j5+=p[6];
                            menor5=true;
                        }
                        else if(j5>p[4])
                        {
                            j5-=p[6];
                            maior5=true;
                        }
                        if (menor5&&maior5)
                        {
                            j5=p[4];
                            menor5=false;
                            maior5=false;
                        }

                        if(j6<p[5])
                        {
                            j6+=p[6];
                            menor6=true;
                        }
                        else if(j6>p[5])
                        {
                            j6-=p[6];
                            maior6=true;
                        }
                        if (menor6&&maior6)
                        {
                            j6=p[5];
                            menor6=false;
                            maior6=false;
                        }
                    }
                    else
                    {
                        a++;
                    }
                }
                else
                {
                    a=0;
                    executar=false;
                }
            }
    }
}
