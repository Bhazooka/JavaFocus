
public class GrayPixelConverter implements PixelConverter{

	@Override
	public Object apply(int red, int green, int blue) {
		return (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
	}

}
