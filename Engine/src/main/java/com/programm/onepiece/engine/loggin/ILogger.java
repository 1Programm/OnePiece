package com.programm.onepiece.engine.loggin;

public interface ILogger {

    int LEVEL_TRACE = 0;
    int LEVEL_DEBUG = 1;
    int LEVEL_INFO = 2;
    int LEVEL_WARN = 3;
    int LEVEL_ERROR = 4;

    void trace(String s, Object... objs);
    void debug(String s, Object... objs);
    void info(String s, Object... objs);
    void warn(String s, Object... objs);
    void error(String s, Object... objs);

}
