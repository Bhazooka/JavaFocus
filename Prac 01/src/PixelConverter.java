interface PixelConverter<T> {
    T apply(int red, int green, int blue);
}