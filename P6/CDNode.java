import javax.swing.JComponent;
public abstract class CDNode extends JComponent implements Runnable{
   /** Tamaño de la ventana **/
  public static final int WINDOW_SIZE = 5;
   public abstract void run();
 }