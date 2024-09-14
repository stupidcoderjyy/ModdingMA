package ma.util.datagen.model.elements;

public interface TransformProperty<P extends TransformProperty<P>> {
    /**
     * 根据右手法则，将操作沿三个坐标轴的正方向旋转90度若干次
     * @return 旋转后的操作
     */
    P rotate(int dim, int count);
    default P rotate(ActionRecord ar) {
        return rotate(ar.rotDim(), ar.rotCount());
    }
    P shift(float px, float py, float pz);
}
