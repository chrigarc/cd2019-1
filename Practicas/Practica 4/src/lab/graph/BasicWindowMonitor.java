/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
