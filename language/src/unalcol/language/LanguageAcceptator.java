package unalcol.language;

import java.io.IOException;
import unalcol.io.ShortTermMemoryReader;

/**
 * <p>Title: LanguageAcceptator</p>
 *
 * <p>Description: Abstract class representing language recognition machines</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public interface LanguageAcceptator {
	/**
	 * Simulates the language recognition machine (according to its inner state) with error handling.
	 * @param input Set of symbols that will be used for simulating the language recognition machine.
	 * @return Information of the recognition process.
	 */
	public default LanguageConsumed simulateEH(ShortTermMemoryReader<?> input, LanguageErrorHandler handler) throws IOException {
		LanguageConsumed consumed = simulate(input);
		if (handler != null && consumed.accepted_as()<0) consumed = handler.apply(input, consumed, this);
		return consumed;
	}

	/**
	 * Simulates the language recognition machine (according to its inner state) without error handling.
	 * @param input Set of symbols that will be used for simulating the language recognition machine.
	 * @return Information of the recognition process.
	 */
	public LanguageConsumed simulate(ShortTermMemoryReader<?> input) throws IOException;

	/**
	 * resets the language recognition machine
	 */
	public void reset();
}