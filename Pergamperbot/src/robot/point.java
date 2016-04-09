package robot;
 
 
public class point {
 
     
    private int x; 
    private int y;
     
    public point(int a, int b){
        x = a; 
        y = b; 
    }
     
     
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    } 
    public void set(int x, int y){
        this.x = x; 
        this.y = y; 
    }
     
    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        point mypoint = (point) o;
        return (this.x == mypoint.getX()) && (this.y == mypoint.getY());
    }
     
    @Override
    public int hashCode() {
        return 37*this.x+this.y;
     
    }
     
 
     
     
     
}