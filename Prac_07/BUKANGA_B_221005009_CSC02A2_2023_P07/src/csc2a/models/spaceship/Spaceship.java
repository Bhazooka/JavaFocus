package csc2a.models.spaceship;

/**
 * @author barbu
 *
 */
public abstract class Spaceship {
    protected boolean manned;

    /**
     * @param manned
     */
    public Spaceship(boolean manned){
        this.manned = manned;
    }

    //getters and setters
    public boolean getManned(){
        return this.manned;
    }

    public void setManned(boolean Manned){
        this.manned = Manned;
    }
}
