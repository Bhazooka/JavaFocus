
public class ImageOperator<T> {
	private Image<T> img = new Image<T>(0, 0);
	
	
	   
	/*
	 * A function that adds a scalar to every pixel in the Image
	 * returns transformed image
	 */
	public Image<T> addScalar(Image<T> img, int scalar){
		//TODO: Complete code [5 marks]
		
		Image<T> result = new Image<>(img.getWidth(), img.getHeight());
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
            	int add = scalar +(Integer)img.getData()[i][j];
                result.getData()[i][j] = (T)Integer.valueOf(add); //check what if you left it as Integer and not cast to T
              
            }
        }
        return result;	
        
	}
	
	/*
	 * A function that multiplies a scalar to every pixel in the Image
	 * returns transformed image
	 */
	public Image<T> multiplyScalar(Image<T> img, int scalar){
		//TODO: Complete code [5 marks]
		
		 Image<T>answer = new Image<>(img.getWidth(), img.getHeight());
	        for (int i = 0; i < img.getHeight(); i++) {
	            for (int j = 0; j< img.getWidth(); j++) {
	            	int multiply = scalar *(Integer)img.getData()[i][j];
	               
	               answer.getData()[i][j] = (T)Integer.valueOf(multiply);
	               
	            }
	        }
	        return answer;

	}

	
	/*
	 * A function that flips / transposes the image 
	 * returns transformed image
	 */
	
	public Image<T> transpose(Image<T> img){
		//TODO: Complete code [5 marks]
		
		
		Image<T> result = new Image<>(img.getHeight(), img.getWidth());
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j ++) {
                result.getData()[j][i] = img.getData()[i][j];
            }
        }
        return result;
	}
	

}
