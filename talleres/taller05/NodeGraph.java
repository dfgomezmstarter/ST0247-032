/**
 * Class Node, Object of type Node
 * 
 * @author Daniel Goméz Martinéz
 * @author Daniel Goméz Goméz
 * @author Cesar Andres Garcia Posada
 */
package taller5;

import java.util.LinkedList;

public class NodeGraph {
    
    private int Value;
    private int Color;
    
    public NodeGraph(int value){
        this.Value = value;
        this.Color = -1;
    }
    
    public int GetValue(){
        return this.Value;
    }
    
    public int GetColor(){
        return this.Color;
    }
    
    public void SetColor(int color){
        this.Color = color;
    }
    
}
