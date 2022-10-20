package com.programm.onepiece.engine.loggin;

public class ConsoleLogger implements ILogger {

    private static final String open = "{";
    private static final String close = "}";

    private final int level;

    public ConsoleLogger(int level) {
        this.level = level;
    }

    public String format(String message, Object... args) {
        if(message == null || args == null || args.length == 0) return message;

        StringBuilder sb = new StringBuilder();

        int msgLen = message.length();
        int curArgIndex = 0;
        int last = 0;
        outerLoop:
        for(int i=0;i<msgLen;i++){
            if(message.startsWith(open, i)){
                int p = i + open.length();
                for(;p<msgLen;p++){
                    if(!Character.isDigit(message.charAt(p))) break;
                    else if(p + 1 == msgLen) break outerLoop;
                }

                if(!message.startsWith(close, p)) {
                    i = p - 1;
                    continue;
                }

                int num;
                boolean curArgs = false;
                if(p == i + open.length()){
                    num = curArgIndex;
                    curArgIndex++;
                    curArgs = true;
                }
                else {
                    String _num = message.substring(i + open.length(), p);
                    num = Integer.parseInt(_num);
                }

                if(num < 0 || num >= args.length){
                    if(curArgs) curArgIndex--;
                    i = p + close.length() - 1;
                    continue;
                }

                sb.append(message, last, i);
                sb.append(args[num]);

                i = p + close.length() - 1;
                last = i + 1;
            }
        }

        sb.append(message, last, msgLen);

        return sb.toString();
    }

    private void log(String level, String s, Object... objs){
        String _level = "[" + level + " ".repeat(Math.max(0, 5 - level.length())) + "]: ";
        System.out.println(_level + format(s, objs));
    }

    @Override
    public void trace(String s, Object... objs) {
        if(level > LEVEL_TRACE) return;
        log("TRACE", s, objs);
    }

    @Override
    public void debug(String s, Object... objs) {
        if(level > LEVEL_DEBUG) return;
        log("DEBUG", s, objs);
    }

    @Override
    public void info(String s, Object... objs) {
        if(level > LEVEL_INFO) return;
        log("INFO", s, objs);
    }

    @Override
    public void warn(String s, Object... objs) {
        if(level > LEVEL_WARN) return;
        log("WARN", s, objs);
    }

    @Override
    public void error(String s, Object... objs) {
        if(level > LEVEL_ERROR) return;
        log("ERROR", s, objs);
    }
}
