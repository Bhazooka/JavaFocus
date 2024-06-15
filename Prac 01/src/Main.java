
// [15 marks correctness]
public class Main {
	
	public static void main(String[] args) {
		String imgPath = "tux.bmp";
        BitmapConvert<Integer> bitmapUtility = new BitmapConvert<>();
        GrayPixelConverter grayConverter = new GrayPixelConverter();
        Image<Integer> pixels = bitmapUtility.readBitmapToArray(imgPath, grayConverter);
        if (pixels != null) {
            bitmapUtility.writeBitmapFromArray("gray.bmp", pixels);
        }
        
        ImageOperator<Integer> scalOp = new ImageOperator<Integer>();
        Image<Integer> scalImg = scalOp.addScalar(pixels,30);
        
        if (scalImg != null) {
            bitmapUtility.writeBitmapFromArray("scalOp.bmp", scalImg);
        }
        
        ImageOperator<Integer> transOp = new ImageOperator<Integer>();
        Image<Integer> transImg = scalOp.transpose(pixels);
        
        if (scalImg != null) {
            bitmapUtility.writeBitmapFromArray("transOp.bmp", transImg);
        }
    }

}
