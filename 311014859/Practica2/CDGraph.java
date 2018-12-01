import org.graphstream.graph.Graph;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.graphstream.graph.Node;
import java.util.concurrent.ConcurrentLinkedDeque;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.View;
/*
**
**
**
** CLASE HECHA  POR EDUARDO RUBIO LEZAMA.
**
**
**
**
*/

public class CDGraph implements Runnable{

    private Graph grafica;
    private Set<CDNode> nodos;
    private ConcurrentLinkedDeque<String[]> graficaLista;
    private boolean bandera;
    private JFrame pinta;

    public CDGraph(Graph grafica){
		this.grafica = grafica;
		this.nodos = new HashSet<CDNode>();
		this.graficaLista = new ConcurrentLinkedDeque<String[]>();
		this.bandera = true;
    }

    public void run(){
		grafica.display();
		for( Node cadaNodo : grafica.getEachNode() ){
	    	cadaNodo.addAttribute("ui.label", cadaNodo.getId());
	    	CDNode stringNodo = new CDNode(this, cadaNodo);
	    	new Thread(stringNodo).start();
	    	nodos.add(stringNodo);
	}
	this.createFrame();
	while(bandera){
	    	while(!graficaLista.isEmpty()){
			String[] temporal = graficaLista.poll();
			grafica.getNode(temporal[0]).setAttribute("ui.class",  temporal[1]);
	    }
	    sleep(100);
	}
    }

    public void addChangeColor(String nodoIdentificador, String color){
		graficaLista.add(new String[]{nodoIdentificador, color});
    }


    private void sleep(int ms){
		try{
	    	Thread.sleep(ms);
		}catch(Exception ex){
		
		}
    }

    private void createFrame(){
		pinta = new JFrame("Práctica 2");
		pinta.setSize(800, 800);
        	pinta.setLocationRelativeTo(null);                       

		javax.swing.JScrollPane arrastraPanel = new javax.swing.JScrollPane();
        	JPanel guardaPanel = new JPanel();
		guardaPanel.setLayout(null);

        	pinta.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        	javax.swing.GroupLayout guardaPanLeyout = new javax.swing.GroupLayout(guardaPanel);
        	guardaPanel.setLayout(guardaPanLeyout);
        	guardaPanLeyout.setHorizontalGroup(
            	guardaPanLeyout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            	.addGap(0, 517, Short.MAX_VALUE)
        	);
        	guardaPanLeyout.setVerticalGroup(
            	guardaPanLeyout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            	.addGap(0, 342, Short.MAX_VALUE)
        	);

        	arrastraPanel.setViewportView(guardaPanel);

        	javax.swing.GroupLayout input = new javax.swing.GroupLayout(pinta.getContentPane());
        	pinta.getContentPane().setLayout(input);
        	input.setHorizontalGroup(
            	input.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            	.addComponent(arrastraPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
        	);
        	input.setVerticalGroup(
            	input.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            	.addComponent(arrastraPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
        	);

        	pinta.pack();

		JButton clic = new JButton("Parar");
		clic.setSize(800, 30);
		clic.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    stopAction();
		}
		});
		guardaPanel.add(clic);

	

	Iterator<CDNode> it = nodos.iterator();
	int y = 30;
	while(it.hasNext()){
	    JComponent component = it.next();
	    component.setSize(760, 30);
	    component.setLocation(30, y);
	    y+=35;
	    guardaPanel.add(component);
	}
	pinta.setVisible(true);

    }
    public void stop(){
	bandera = false;
	Iterator<CDNode> it = nodos.iterator();
	while(it.hasNext()){
	    it.next().stop();
	}
    }

    private void stopAction(){
	this.stop();
    }
}