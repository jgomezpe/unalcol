package unalcol.language.parser;

import unalcol.language.Typed;

public interface Rule<T> {
    public Typed get( LookAHeadParser<T> parser );
}
