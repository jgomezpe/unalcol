package unalcol.reflect.plugin;

import org.w3c.dom.Element;

public interface PlugIn{
	public static final String plugin = "plugin";
	public static final String className = "class";
	public static final String nickName = "nick";

	public void init(Element element, PlugInLoader builder);
}