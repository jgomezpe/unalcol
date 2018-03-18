package unalcol.reflect.plugin;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtil {
	public static Element element( Node node ){	return ( node.getNodeType()==Node.ELEMENT_NODE )? ((Element)node) :  null;	}    
	public static Element[] children( Element element, String child_tag ){
		NodeList list=element.getChildNodes();
		int m=0;
		for(int i=0; i<list.getLength(); i++){
			Element e = element(list.item(i));
			if(e!=null && e.getTagName().equals(child_tag)) m++;
		}
		Element[] offspring = new Element[m];
		m=0;
		for(int i=0; i<list.getLength(); i++){
			Element e = element(list.item(i));
			if(e!=null && e.getTagName().equals(child_tag)){
				offspring[m] = e;
				m++;
			}
		}
		return offspring;
	}
}
