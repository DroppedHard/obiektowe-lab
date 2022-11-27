package agh.ics.oop;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver{
    final SortedSet<BoundaryElement> sortedx = new TreeSet<>((a, b) -> {
        int odpx = Integer.compare(a.position.x, b.position.x);
        if (odpx == 0) {
            int odpy = Integer.compare(a.position.y, b.position.y);
            if (odpy == 0){
                return compareClass(a, b);
            }else {
                return odpy;
            }
        }else {
            return odpx;
        }
    });
    final SortedSet<BoundaryElement> sortedy = new TreeSet<>((a, b) -> {
        int odpy = Integer.compare(a.position.y, b.position.y);
        if (odpy == 0) {
            int odpx = Integer.compare(a.position.x, b.position.x);
            if (odpx == 0){
                return compareClass(a, b);
            }else {
                return odpx;
            }
        }else {
            return odpy;
        }
    });

    public void addBoundaryElement(BoundaryElement el){
        sortedx.add(el);
        sortedy.add(el);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        Class[] klasy = new Class[]{Animal.class, Grass.class};     // od razu jest gotowe na potencjane przemieszczenie trawy
        Class[] klasy = new Class[]{Animal.class};     // wersja bez mozliwości przeniesienia trawy
        for (Class klasa: klasy) {
            BoundaryElement el = new BoundaryElement(oldPosition, klasa);
            if (sortedx.contains(el)){
                removeBoundaryElement(el);
                el.position = newPosition;
                addBoundaryElement(el);
                return;
            }
        }
    }
    public void removeBoundaryElement(BoundaryElement el){
        sortedx.remove(el);
        sortedy.remove(el);
    }
    private int compareClass(BoundaryElement a, BoundaryElement b){
        if (a.className == Animal.class && b.className == Grass.class){
            return -1;
        }else if (a.className == Grass.class && b.className == Animal.class){
            return 1;
        }else {
            return 0;
        }
    }
}
