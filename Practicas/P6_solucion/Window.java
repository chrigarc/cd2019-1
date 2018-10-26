public class Window{
    private Segment[] segments;
    private int index;

    public Window(){
        segments = new Segment[CDNode.WINDOW_SIZE];
        index = 0;
    }

    public int getSize(){
        return index;
    }

    public boolean addContent(String content){
        boolean status = false;
        if(index<CDNode.WINDOW_SIZE){
            segments[index++] = new Segment(content);
            status = true;
        }
        return status;
    }

    public Segment[] getSegments(){
        return segments;
    }

    public void retry(){
        for(Segment s:segments){
            if(s!=null){
                s.setValid(true);
            }
        }
    }
}
