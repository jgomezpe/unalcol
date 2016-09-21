package services;

import unalcol.instance.Instance;

public class InstanceTest {
	public static void main( String[] args ){
		DemoC x = (DemoC)Instance.create(DemoC.class, new DemoA());
		System.out.println( x );
		x = (DemoC)Instance.create(DemoC.class, new DemoB());
		System.out.println( x );
	}

}
