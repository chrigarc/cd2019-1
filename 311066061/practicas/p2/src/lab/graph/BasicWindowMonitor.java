package lab.graph;

import java.awt.Window;
import java.awt.event.*;

public class BasicWindowMonitor extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
	    Window w = e.getWindow();
	    w.setVisible(false);
	    w.dispose();
	    System.exit(0);
	  }
}
