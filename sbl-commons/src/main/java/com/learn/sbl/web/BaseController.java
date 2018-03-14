package com.learn.sbl.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kevin
 */
public abstract class BaseController {
    protected static final String _data = "data";
    protected static final String _redirect = "redirect:";
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseController() {
    }

}