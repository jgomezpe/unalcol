/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.reversi;

/**
 *
 * @author Jonatan
 */
public class Clock {
    protected boolean white_turn = true;
    protected long start_time;
    protected long white_time;
    protected long black_time;
    
    public Clock( long w_time, long b_time ){
        white_time = w_time;
        black_time = b_time;
    }
    
    public void init( boolean w_turn ){
        white_turn = w_turn;
        start_time = System.currentTimeMillis();
        System.out.println(start_time);
    }
    
    public void swap(){
        long delta = System.currentTimeMillis() - start_time;
        if( white_turn ){
            white_time -= delta;
        }else{
            black_time -= delta;
        }
        white_turn = !white_turn;
        start_time = System.currentTimeMillis();
    }
    
    public String time_format(long time){
        if( time < 0 ){
            time = 0;
        }
        StringBuilder sb = new StringBuilder();
        long ms = time%1000;
        time /= 1000;
        long s = time % 60;
        time /= 60;
        long m = time %60;
        time /= 60;
        sb.append(time);
        sb.append(':');
        sb.append(m);
        sb.append(':');
        sb.append(s);
        sb.append(':');
        sb.append(ms);
        return sb.toString();
    }
    
    public boolean white_turn(){ return white_turn; }
    public long white_time(){ return white_time; }
    public long black_time(){ return black_time; }

    public String white_time_string(){
        long time = white_time;
        if( white_turn ){
            long delta = System.currentTimeMillis() - start_time;
            time -= delta;
        }
        return time_format(time);
    }
    
    public String black_time_string(){
        long time = black_time;
        if( !white_turn ){
            long delta = System.currentTimeMillis() - start_time;
            time -= delta;
        }
        return time_format(time);
    }

    public static void main( String[] args ){
        Clock c = new Clock(80000, 80000);
        c.init(true);
        System.out.print(c.white_time_string());
        System.out.print(c.black_time_string());
    }
}
