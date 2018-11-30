package main;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class MainApp extends Frame{
	private static final long serialVersionUID = 1L;
	
	private Point dragPt = new Point(0,0);
	private Renderer renderer;
	private VirtualGlade virtualGlade;
	
	public class WindowEventListener extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
		    Window w = e.getWindow();
		    w.setVisible(false);
		    w.dispose();
		    System.exit(0);
		  }
	}
	
	private void updateView(){
		BufferStrategy bf = this.getBufferStrategy();
		Graphics2D g;
		try{
			g = (Graphics2D) bf.getDrawGraphics();
			renderer.clientEdge = getSize();
			renderer.render(g);
			g.dispose();
			bf.show();
		}catch(Exception e){}
		
	}
	
	public class MouseEventListener extends MouseAdapter {
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			renderer.zoom += 0.05*renderer.zoom*e.getPreciseWheelRotation();
			updateView();
			super.mouseWheelMoved(e);
		}
		@Override
		public void mousePressed(MouseEvent e) {
			dragPt.setLocation(e.getX(), e.getY());
			super.mousePressed(e);
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			renderer.gladeCenter.translate( - dragPt.x + e.getX(), - dragPt.y + e.getY());
			dragPt.setLocation(e.getX(), e.getY());
			updateView();
			super.mouseDragged(e);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		updateView();
		super.paint(g);
	}

	public static void main(String[] args) throws Exception {
		new MainApp();
	}
	
	public MainApp() throws Exception{
		super("Cows space");
		
		setSize(800, 600);
		
		virtualGlade = new VirtualGlade();
		renderer = new Renderer(getSize(), virtualGlade);
		
		setBackground(Color.WHITE);
		addWindowListener(new WindowEventListener());
		addMouseWheelListener(new MouseEventListener());
		addMouseMotionListener(new MouseEventListener());
		addMouseListener(new MouseEventListener());
		
		this.setVisible(true);
		this.createBufferStrategy(2);
		
		//********************* THE GAME ************************
		
		//fill initial cows
		Map<ICow,Integer> cowsGrass = new HashMap<ICow,Integer>();
		ICow cows[][] = virtualGlade.getCows();
		
		for(int i = 0; i < Config.gladeSize; i++)
    		for(int j = 0; j < Config.gladeSize; j++){
    			if(cows[i][j] != null) cowsGrass.put(virtualGlade.getCows()[i][j], Config.grassInit);
    	}
		
    	System.out.println("Total num of cows: " + cowsGrass.size());
    	
    	//eating loop
		Action action;
		LocalInfo li = new LocalInfo();
		ICow cow;
		NearestCell ni;
                Map<Integer,Integer> idxs;
                Map<Integer,List<Integer>> stats = new HashMap<Integer,List<Integer>>();
		do {
			for (int i = 0; i < Config.gladeSize; i++)
				for (int j = 0; j < Config.gladeSize; j++) {
					cow = cows[i][j];
					if (cow != null) {
						// consume internal grass
						cowsGrass.put(cow, cowsGrass.get(cow) - 1);
						li.grassEaten = cowsGrass.get(cow);
						// kill cow
						if (li.grassEaten <= 0) {
							cowsGrass.remove(cow);
							cows[i][j] = null;
							//System.out.println("killed");
							continue;
						}

						// define LocalInfo for cow [i][j]
						for (int k = i - 1; k <= i + 1; k++)
							for (int l = j - 1; l <= j + 1; l++) {
								// out of glade
								if (k < 0 || k >= Config.gladeSize || l < 0 || l >= Config.gladeSize) {
									li.nc[k - i + 1][l - j + 1] = null;
								} else {
									ni = new NearestCell();
									ni.cowID = cows[k][l] == null ? null : cows[k][l].getSTUDENTid();
									ni.grass = virtualGlade.grass[k][l];
									li.nc[k + 1 - i][l + 1 - j] = ni;
								}
							}
						
						// produce cow action
						action = cow.make_turn(li.clone());
						if(action.getActionType().equals(ActionType.MOVE)){
							if(li.nc[action.getDx()+1][action.getDy()+1] != null && li.nc[action.getDx()+1][action.getDy()+1].cowID == null){
								cows[i+action.getDx()][j+action.getDy()] = cows[i][j];
								cows[i][j] = null;
							}
						}else if(action.getActionType().equals(ActionType.EAT)){
							if(virtualGlade.grass[i][j] > 0){
								virtualGlade.grass[i][j]--;
								if(cowsGrass.get(cow) < Config.grassMax) cowsGrass.put(cow, cowsGrass.get(cow) + 2);
							}
						}else if(action.getActionType().equals(ActionType.BREED)){
							if(li.nc[action.getDx()+1][action.getDy()+1] != null && li.nc[action.getDx()+1][action.getDy()+1].cowID == null && cowsGrass.get(cow) > Config.grassToBreed){
								cows[i+action.getDx()][j+action.getDy()] = cows[i][j].clone();
								cowsGrass.put(cow, cowsGrass.get(cow) - Config.grassToBreed);
								cowsGrass.put(cows[i+action.getDx()][j+action.getDy()], Config.grassToBreed);
								//System.out.println("produced");
							}
						}
					}
				}
			updateView();
                        //statistics
                        idxs = pupolations(cowsGrass);
                        for(Integer id : idxs.keySet()){
                            if(stats.containsKey(id)){
                                stats.get(id).add(idxs.get(id));
                            }else{
                                stats.put(id, new ArrayList<Integer>(idxs.get(id)));
                            }
                        }
			Thread.sleep(50);
		} while (cowsGrass.size() > 0);
		
		print_populatios(stats);
            
	}
        
        Map<Integer,Integer> pupolations(Map<ICow,Integer> cowsGrass){
            Map<Integer,Integer> idxs = new HashMap<Integer,Integer>();
            
            Integer cur;
            for(ICow cow : cowsGrass.keySet()){
                cur = idxs.containsKey(cow.getSTUDENTid()) ? idxs.get(cow.getSTUDENTid()) : 0;
                idxs.put(cow.getSTUDENTid(), cur + 1);
            }
            
            return idxs;
        }
        
        void print_populatios(Map<Integer,List<Integer>> stats) throws Exception {
            File file = new File("test.dat");
            
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            
            Integer ts;
            for(Integer id : stats.keySet()){
                bw.write("#Cow " + id + "\n");
                ts = 0;
                for(Integer pop : stats.get(id)){
                    bw.write(ts + " " + pop + "\n");
                    ts++;
                }
                bw.write("\n\n");
            }
            
            System.out.println("\nMain score:");
            for(Integer id : stats.keySet()){
            	System.out.println("STUDENTid=" + id + " lived for " + stats.get(id).size() + " steps");
            }
            System.out.println("\n");
            
            bw.close();
            System.out.println("Statistics written to: " + file.getAbsolutePath());
        }
}

