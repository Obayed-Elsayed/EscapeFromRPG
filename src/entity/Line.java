package entity;

public class Line {
    public double m;
    public int x;
    public int y;
    public double b;

    public boolean vertical = false;


    public Line(int x1, int y1, int x2, int y2){
        if(y2-y1 == 0){
            m =0;
        }else if(x2-x1 == 0){
            vertical = true;
            m = x1;
        }else{
            y = y2 - y1;
            x = x2 - x1;
            m = (double) (y2 - y1) / (double) (x2 - x1);
            b = y1 - m * x1;
        }
    }

    public double f(int x){
        if(vertical) return m;
        return x * m + b;
    }

}
