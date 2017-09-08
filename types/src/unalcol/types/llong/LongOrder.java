package unalcol.types.llong;

import unalcol.services.MicroService;
import unalcol.sort.Order;

public class LongOrder extends MicroService<Long> implements Order<Long> {

    /**
     * Determines if the first Integer is less than (in some order) the second Integer (one<two)
     * @param one First Integer
     * @param two Second Integer
     * @return (one<two)
     */
    public int compare(Long one, Long two) {
        return one.compareTo(two);
    }
}
