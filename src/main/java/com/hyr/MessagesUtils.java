package com.hyr;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/*******************************************************************************
 * @date 2018-11-30 下午 4:23
 * @author: <a href=mailto:>黄跃然</a>
 * @Description: 消息工具类
 ******************************************************************************/
public class MessagesUtils {

    /**
     * 启动/关闭 消息打印
     *
     * @param clazz 启动clazz
     * @param args
     * @param LOG
     */
    public static void startupShutdownMessage(Class<?> clazz, String[] args, final org.slf4j.Logger LOG) {
        final String hostname = getHostname();
        final String classname = clazz.getSimpleName();

        LOG.info(
                getMessageInfo(args, hostname, classname)
        );

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                LOG.info(toStartupShutdownString("SHUTDOWN MESSAGE: ", new String[]{
                        "Shutting down " + classname + " at " + hostname}));
            }
        });
    }

    /**
     * 启动/关闭 消息打印
     *
     * @param clazz 启动clazz
     * @param args
     * @param LOG
     */
    public static void startupShutdownMessage(Class<?> clazz, String[] args, final org.apache.log4j.Logger LOG) {
        final String hostname = getHostname();
        final String classname = clazz.getSimpleName();

        LOG.info(
                getMessageInfo(args, hostname, classname)
        );

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                LOG.info(toStartupShutdownString("SHUTDOWN MESSAGE: ", new String[]{
                        "Shutting down " + classname + " at " + hostname}));
            }
        });
    }


    /**
     * Return a message for logging.
     *
     * @param prefix prefix keyword for the message
     * @param msg    content of the message
     * @return a message for logging
     */
    private static String toStartupShutdownString(String prefix, String[] msg) {
        StringBuilder b = new StringBuilder("\n" + prefix);
        b.append("\n/************************************************************");
        for (String s : msg)
            b.append("\n").append(prefix).append(s);
        b.append("\n************************************************************/");
        return b.toString();
    }

    /**
     * 日志详情
     *
     * @param args
     * @param hostname
     * @param classname
     * @return
     */
    private static String getMessageInfo(String[] args, String hostname, String classname) {
        return toStartupShutdownString("START_UP MESSAGE: ", new String[]{
                "  Starting " + classname,
                "  host = " + hostname,
                "  args = " + Arrays.asList(args),
                "  version = " + BrowserVersionInfo.getProjectVersion(),
                "  compiled time = " + BrowserVersionInfo.getCompiledTime(),
                "  revision = " + BrowserVersionInfo.getSVNVersion(),
                "  java = " + System.getProperty("java.version")}
        );
    }

    /**
     * Return hostname without throwing exception.
     *
     * @return hostname
     */
    private static String getHostname() {
        try {
            return "" + InetAddress.getLocalHost();
        } catch (UnknownHostException uhe) {
            return "" + uhe;
        }
    }
}
