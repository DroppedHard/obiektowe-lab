package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class IntegrationTest {
    @Test
    void movement1(){
        Animal zwierz = new Animal();
        String[] args = {"r", "forward", "f","forward"};
        World.run(OptionsParser.parse(args), zwierz);
        assertEquals(zwierz.toString(), "(4,2) Wschod");
    }
    @Test
    void movement2(){
        Animal zwierz = new Animal();
        String[] args = {"f","lewo","left","f","forward","prawo","right","b","backward","tyl","r","vssvs"};
        World.run(OptionsParser.parse(args), zwierz);
        assertEquals(zwierz.toString(), "(0,1) Wschod");
    }
    @Test
    void movement3(){
        Animal zwierz = new Animal();
        String[] args = {"fa","lewo","lefts","sf","forwdard","pradwo","rigdht","ab","backsward","tdyl","ra","vssvs"};
        World.run(OptionsParser.parse(args), zwierz);
        assertEquals(zwierz.toString(), "(2,2) Polnoc");
    }
    @Test
    void movement4(){
        Animal zwierz = new Animal();
        String[] args = {"r","f","b","b","l","f","r","r"};
        World.run(OptionsParser.parse(args), zwierz);
        assertEquals(zwierz.toString(), "(1,3) Poludnie");
    }
    @Test
    void movement5(){
        Animal zwierz = new Animal();
        String[] args = {"f","l","f","r","b","l","b"};
        World.run(OptionsParser.parse(args), zwierz);
        assertEquals(zwierz.toString(), "(2,2) Zachod");
    }
}
