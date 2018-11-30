import org.graphstream.graph.Graph;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import org.graphstream.graph.Node;
import java.util.concurrent.ConcurrentLinkedDeque;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.View;


public class CDGraph implements Runnable{

    private Graph graph;
    private Set<CDNode> nodes;
    private ConcurrentLinkedDeque<String[]> list;
    private boolean active;
    private JFrame frame;

    public CDGraph(Graph g){
	this.graph = g;
	this.nodes = new HashSet<CDNode>();
	list = new ConcurrentLinkedDeque<String[]>();
	this.active = true;
    }

    public void run(){
	graph.display();
	for( Node i : graph.getEachNode() ){
	    i.addAttribute("ui.label", i.getId());
	    CDNode cdn = new CDNode(this, i);
	    new Thread(cdn).start();
	    nodes.add(cdn);
	}
	this.createFrame();
	while(active){
	    while(!list.isEmpty()){
		String[] tmp = list.poll();
		graph.getNode(tmp[0]).setAttribute("ui.class",  tmp[1]);
	    }
	    sleep(100);
	}
    }

    public void addChangeColor(String nodeId, String color){
	list.add(new String[]{nodeId, color});
    }

    private void createFrame(){
	frame = new JFrame("Práctica 2");
	frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla

	javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JPanel jPanel1 = new JPanel();
	jPanel1.setLayout(null);

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
        );

        frame.pack();

	JButton button = new JButton("Detener");
	button.setSize(800, 30);
	button.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    stopAction();
		}
	});
	jPanel1.add(button);

	Iterator<CDNode> iterator = nodes.iterator();
	int y = 30;
	while(iterator.hasNext()){
	    JComponent component = iterator.next();
	    component.setSize(760, 30);
	    component.setLocation(30, y);
	    y+=35;
	    jPanel1.add(component);
	}
	frame.setVisible(true);

    }

    private void sleep(int ms){
	try{
	    Thread.sleep(ms);
	}catch(Exception ex){
	}
    }

    public void stop(){
	active = false;
	Iterator<CDNode> iterator = nodes.iterator();
	while(iterator.hasNext()){
	    iterator.next().stop();
	}
    }

    private void stopAction(){
	System.out.println("¿Qué necesitas que haga para detener todo?");
    }
}
