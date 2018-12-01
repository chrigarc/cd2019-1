package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class Renderer {
	
	// dimensions of the client edge window
	public Dimension clientEdge;
	
	// gladeArea is always logical square {{0,1},{0,1}}
	// gladeCenter is a coordinate of a glade center on the client edge
	public Point gladeCenter = new Point(0, 0);
	// zoom - is a multiplier for glade area square before rendering
	public double zoom = 0;
	
	private VirtualGlade virtualGlade;

	public Renderer(Dimension clientEdge, VirtualGlade virtualGlade) {
		this.clientEdge = clientEdge;
		gladeCenter.setLocation(clientEdge.width/2, clientEdge.height/2);
		zoom = 0.8*Math.min(clientEdge.width, clientEdge.height);
		this.virtualGlade = virtualGlade;
	}
	
	public void render(Graphics2D g2){
		int x0 = (int)(gladeCenter.x - zoom/2), y0 = (int)(gladeCenter.y - zoom/2);
		
		//current grass/cows distribution
		
		int size = virtualGlade.getSize();
		int maxGrass = virtualGlade.getMaxGrass();
		int grass[][] = virtualGlade.getGrass();
		ICow cows[][] = virtualGlade.getCows();
		int gray = 0;
		
		//draw grass
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++){
				gray = 255*grass[i][j]/maxGrass;
				g2.setColor(new Color(0,gray,0));
				g2.fillRect(x0 + (int)(i*zoom/size), y0 + (int)(j*zoom/size), (int)(zoom/size), (int)(zoom/size));
				g2.setColor(new Color(255,255,255));
				g2.drawRect(x0 + (int)(i*zoom/size), y0 + (int)(j*zoom/size), (int)(zoom/size), (int)(zoom/size));
			}
		
		//draw cows
                //Color color;
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++){
				if(cows[i][j] != null){
					g2.setColor(cows[i][j].getColor());
					g2.fillOval(x0 + (int)(i*zoom/size), y0 + (int)(j*zoom/size), (int)(zoom/size), (int)(zoom/size));
				}
			}
		
		//border rectangles
		g2.setColor(new Color(255,255,255));
		g2.fillRect(0, 0, clientEdge.width, y0);
		g2.fillRect(0, (int)(zoom) + y0+1, clientEdge.width, clientEdge.height);
		g2.fillRect(0, y0-5, x0, (int)(zoom)+5);
		g2.fillRect(x0+(int)(zoom)+1, 0, clientEdge.width, clientEdge.height);
		
		//glade rectangle
		g2.setColor(new Color(0,0,0));
		g2.drawRect(x0, y0, (int)(zoom),(int)(zoom));
		
		//cows list
		/*g2.setFont(new Font("TimesRoman", Font.PLAIN, 1));
		g2.setColor(new Color(0,0,0));
		g2.drawString("podskcosd", 100, 100);
		g2.drawRect(100, 91, 100, 5);*/
	}

}
