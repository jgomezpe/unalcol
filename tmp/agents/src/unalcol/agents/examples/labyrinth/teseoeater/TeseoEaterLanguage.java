package  unalcol.agents.examples.labyrinth.teseoeater;
import unalcol.agents.simulate.util.*;

public class TeseoEaterLanguage extends SimpleLanguage {
  protected String[] colors = null;
  protected String[] shapes = null;
  protected String[] sizes = null;
  protected String[] weights = null;

  public TeseoEaterLanguage( String[] _percepts, String[] _actions ){
      this( _percepts, _actions,
            new String[]{"black","white"}, new String[]{"rectangle","circle"},
            new String[]{"big","small"}, new String[]{"heavy","light"} );
  }

  public TeseoEaterLanguage( String[] _percepts, String[] _actions,
                             String[] _colors, String[] _shapes,
                             String[] _sizes, String[] _weights ){
    super( _percepts, _actions );
    colors = _colors;
    shapes = _shapes;
    sizes = _sizes;
    weights = _weights;
  }

  public int getColorIndex( String color ){
    return Language.getIndex(colors, color);
  }

  public int getShapeIndex( String shape ){
    return Language.getIndex(shapes, shape);
  }

  public int getSizeIndex( String size ){
    return Language.getIndex(sizes, size);
  }

  public int getWeightIndex( String weight ){
    return Language.getIndex(weights, weight);
  }


  public String getShape( int index ){ return shapes[index]; }
  public String getColor( int index ){ return colors[index]; }
  public String getSize( int index ){ return sizes[index]; }
  public String getWeight( int index ){ return weights[index]; }


  public int getColorsNumber(){ return colors.length; }
  public int getShapesNumber(){ return shapes.length; }
  public int getSizesNumber(){ return sizes.length; }
  public int getWeightsNumber(){ return weights.length; }
}
