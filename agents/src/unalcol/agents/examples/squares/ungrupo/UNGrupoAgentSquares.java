package unalcol.agents.examples.squares.ungrupo;

import java.util.ArrayList;
import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.examples.squares.Squares;

/**
 *
 * @author UNGrupo
 */
public class UNGrupoAgentSquares implements AgentProgram {

    protected String color;
    private long rowMask, colMask;
    private int sum;

    public UNGrupoAgentSquares(String color) {
        this.color = color;
    }

    private int getDepth(int remain) {
        if (remain <= 10) {
            return 3;
        }
        if (remain <= 20) {
            return 3;
        }
        if (remain <= 30) {
            return 3;
        }
        if (remain <= 40) {
            return 5;
        }
        if (remain <= 50) {
            return 3;
        }
        if (remain <= 60) {
            return 3;
        }
        if (remain <= 70) {
            return 3;
        }
        if (remain <= 80) {
            return 7;
        }
        if (remain <= 87) {
            return 11;
        }
        if (remain <= 90) {
            return 17;
        }
        return 1000;
    }

    @Override
    public Action compute(Percept p) {
        long rowMask = 0, colMask = 0;
        if (p.getAttribute(Squares.TURN).equals(color)) {
            size = Integer.parseInt((String) p.getAttribute(Squares.SIZE));
            for (int i = 0, cnt = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (((String) p.getAttribute(i + ":" + j + ":" + Squares.LEFT)).equals(Squares.TRUE) && cnt % size != 0) {
                        colMask |= 1L << (cnt % size * size + cnt / size - size);
                    }
                    if (((String) p.getAttribute(i + ":" + j + ":" + Squares.TOP)).equals(Squares.TRUE) && cnt > size-1 ) {
                        rowMask |= 1L << (cnt - size);
                    }
                    if (((String) p.getAttribute(i + ":" + j + ":" + Squares.BOTTOM)).equals(Squares.TRUE) && cnt < (size-1)*size) {
                        rowMask |= 1L << cnt;
                    }
                    if (((String) p.getAttribute(i + ":" + j + ":" + Squares.RIGHT)).equals(Squares.TRUE) && cnt % size != size-1) {
                        colMask |= 1L << (cnt % size * size + cnt / size);
                    }
                    cnt++;
                }
            }
            this.rowMask = rowMask;
            this.colMask = colMask;
            sum = 0;
            normalize();
            rowMask = this.rowMask;
            colMask = this.colMask;
            if (rowMask == (1L << size*(size-1)) - 1 && colMask == (1L << size*(size-1)) - 1) {
                return new Action(Squares.PASS);
            }
            System.out.println("Bitcount = " + (Long.bitCount(colMask) + Long.bitCount(rowMask)));
            int res = -1;
            if (Long.bitCount(colMask) + Long.bitCount(rowMask) <= size*(size-1) ) {
                ArrayList<Integer> bit = new ArrayList<Integer>();
                ArrayList<Integer> value = new ArrayList<Integer>();
                for (int i = 0; i < size*(size-1); i++) {
                    if ((rowMask & (1L << i)) == 0) {
                        this.rowMask = rowMask | (1L << i);
                        this.colMask = colMask;
                        sum = 0;
                        normalize();
                        bit.add(i);
                        value.add(sum);
                    }
                }
                for (int i = 0; i < size*(size-1); i++) {
                    if ((colMask & (1L << i)) == 0) {
                        this.rowMask = rowMask;
                        this.colMask = colMask | (1L << i);
                        sum = 0;
                        normalize();
                        bit.add(i + size*(size-1));
                        value.add(sum);
                    }
                }
                int best = Integer.MAX_VALUE;
                for (int i = 0; i < bit.size(); i++) {
                    if (best > value.get(i)) {
                        best = value.get(i);
                        res = bit.get(i);
                    }
                }
            } else {
                alpha = Integer.MIN_VALUE;
                beta = Integer.MAX_VALUE;
                res = fMax(rowMask, colMask, getDepth(Long.bitCount(colMask) + Long.bitCount(rowMask)))[1];
            }
            if (res < size*(size-1)) {
                return new Action(res / size + ":" + res % size + ":" + Squares.BOTTOM);
            } else {
                res -= size*(size-1);
                return new Action(res % size + ":" + res / size + ":" + Squares.RIGHT);
            }
        }
        return new Action(Squares.PASS);
    }

    int alpha, beta, size;

    private int[] fMax(long rowMask, long colMask, int depth) {
        this.rowMask = rowMask;
        this.colMask = colMask;
        sum = 0;
        normalize();
        rowMask = this.rowMask;
        colMask = this.colMask;
        if (rowMask == (1L << size*(size-1)) - 1 && colMask == (1L << size*(size-1)) - 1 || depth == 0) {
            return new int[]{sum, -1};
        }
        int best = Integer.MIN_VALUE, move = -1;
        int ans[];
        for (int i = 0; i < size*(size-1); i++) {
            if ((rowMask & (1L << i)) == 0) {
                ans = fMin(rowMask | (1L << i), colMask, depth - 1);
                if (best < ans[0]) {
                    best = ans[0];
                    move = i;
                }
                if (best >= beta) {
                    return new int[]{best, move};
                }
                alpha = Math.max(alpha, best);
            }
            if ((colMask & (1L << i)) == 0) {
                ans = fMin(rowMask, colMask | (1L << i), depth - 1);
                if (best < ans[0]) {
                    best = ans[0];
                    move = i + size*(size-1);
                }
                if (best >= beta) {
                    return new int[]{best, move};
                }
                alpha = Math.max(alpha, best);
            }
        }
        return new int[]{best, move};
    }

    private int[] fMin(long rowMask, long colMask, int depth) {
        this.rowMask = rowMask;
        this.colMask = colMask;
        sum = 0;
        normalize();
        rowMask = this.rowMask;
        colMask = this.colMask;
        if (rowMask == (1L << size*(size-1)) - 1 && colMask == (1L << size*(size-1)) - 1 || depth == 0) {
            return new int[]{sum, -1};
        }
        int best = Integer.MAX_VALUE, move = -1;
        int ans[];
        for (int i = 0; i < size*(size-1); i++) {
            if ((rowMask & (1L << i)) == 0) {
                ans = fMax(rowMask | (1L << i), colMask, depth - 1);
                if (best > ans[0]) {
                    best = ans[0];
                    move = i;
                }
                if (best <= alpha) {
                    return new int[]{best, move};
                }
                beta = Math.min(beta, best);
            }
            if ((colMask & (1L << i)) == 0) {
                ans = fMax(rowMask, colMask | (1L << i), depth - 1);
                if (best > ans[0]) {
                    best = ans[0];
                    move = i + size*(size-1);
                }
                if (best <= alpha) {
                    return new int[]{best, move};
                }
                beta = Math.min(beta, best);
            }
        }
        return new int[]{best, move};
    }

    private void normalize() {
        for (int i = 0; i < size*size; i++) {
            if ((size*(size-1) > i && (rowMask & (1L << i)) == 0)
                    && (i <= (size-1) || (rowMask & (1L << (i - size))) != 0)
                    && (i % size == 0 || (colMask & (1L << (i % size * size + i / size - size))) != 0)
                    && (i % size == (size-1) || (colMask & (1L << (i % size * size + i / size))) != 0)) {
                rowMask |= 1L << i;
                normalize();
                sum++;
                return;
            }
            if ((size*(size-1) <= i || (rowMask & (1L << i)) != 0)
                    && (i > (size-1) && (rowMask & (1L << (i - size))) == 0)
                    && (i % size == 0 || (colMask & (1L << (i % size * size + i / size - size))) != 0)
                    && (i % size == (size-1) || (colMask & (1L << (i % size * size + i / size))) != 0)) {
                rowMask |= 1L << (i - size);
                normalize();
                sum++;
                return;
            }
            if ((size*(size-1) <= i || (rowMask & (1L << i)) != 0)
                    && (i <= (size-1) || (rowMask & (1L << (i - size))) != 0)
                    && (i % size != 0 && (colMask & (1L << (i % size * size + i / size - size))) == 0)
                    && (i % size == (size-1) || (colMask & (1L << (i % size * size + i / size))) != 0)) {
                colMask |= 1L << (i % size * size + i / size - size);
                normalize();
                sum++;
                return;
            }
            if ((size*(size-1) <= i || (rowMask & (1L << i)) != 0)
                    && (i <= (size-1) || (rowMask & (1L << (i - size))) != 0)
                    && (i % size == 0 || (colMask & (1L << (i % size * size + i / size - size))) != 0)
                    && (i % size != (size-1) && (colMask & (1L << (i % size * size + i / size))) == 0)) {
                colMask |= 1L << (i % size * size + i / size);
                normalize();
                sum++;
                return;
            }
        }
    }

    @Override
    public void init() {
    }

}
