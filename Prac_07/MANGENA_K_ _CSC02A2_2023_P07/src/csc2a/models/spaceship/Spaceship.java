package csc2a.models.spaceship;

public abstract class Spaceship {
    protected boolean manned;

    public Spaceship(boolean manned){
        this.manned = manned;
    }

/*
    public boolean getManned(){
        return this.manned;
    }

    public void setManned(){
        this.manned = newManned;
    }
 */

    public boolean getManned(){
        return this.manned;
    }

    public void setManned(boolean manned){
        this.manned = manned;
    }
}
