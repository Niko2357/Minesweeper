public class Box {
    protected boolean isSeen;

    public Box() {
        this.isSeen = isSeen;
    }

    @Override
    public String toString() {
        if(isSeen){
            return "1";
        }else{
            return "0";
        }
    }

    public void see(){
        isSeen = true;
    }


    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
