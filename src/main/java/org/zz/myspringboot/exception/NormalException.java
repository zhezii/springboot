package org.zz.myspringboot.exception;

/**
 * @author Zhou Wenzhe
 * @date 2018/6/28
 */
public class NormalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NormalException(String message) {
        super(message);
    }

}
