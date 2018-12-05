package com.hyr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*******************************************************************************
 * @date 2018-12-05 下午 3:00
 * @author: <a href=mailto:>黄跃然</a>
 * @Description:
 ******************************************************************************/
public class Main {
    private static Logger LOG = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {

        MessagesUtils.startupShutdownMessage(Main.class, args, LOG);

    }

}
