package unalcol.gui.paint;

import unalcol.json.JSON;
import unalcol.json.JSON2Instance;

public class CommandInstance implements JSON2Instance<Command>{
	public CommandInstance() {}

	@Override
	public Command load(JSON json) { return new Command( json ); }

	@Override
	public JSON store(Command command){ return command; }
}