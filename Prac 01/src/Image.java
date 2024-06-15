
class Image<T> {
    private T[][] data;
    private int width;
    private int height;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        instantiateArray(height , width);
    }
    
    private void instantiateArray(int height,int width) {
    	//TODO: Complete code [5 marks]
    	
    	data = (T[][]) new Object[height][width];	// casting the created 2D array to a 2D array of type T
    
    }

    public T[][] getData() {
        return data;
    }

    public void setData(T[][] data) {
        this.data = data;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
