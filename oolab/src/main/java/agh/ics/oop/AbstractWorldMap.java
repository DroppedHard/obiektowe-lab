package agh.ics.oop;

abstract class AbstractWorldMap {
    // Niestety te klasy są różne w implementacji, ponieważ klasa RectangularMap korzysta z tablicy wymiarów width na height
    // a klasa GrassField korzysta z listy elementów i iteruje po kolei (powinienem dodac słownik / hashmapę, ale ucze się nadal javy)
    // Więc ta klasa nie większości metod ani elementów
    protected Vector2d upperRight;
    protected Vector2d lowerLeft;
    public String toString() {
        MapVisualizer odp = new MapVisualizer((IWorldMap) this);
        return odp.draw(this.lowerLeft, this.upperRight);
    }
}
