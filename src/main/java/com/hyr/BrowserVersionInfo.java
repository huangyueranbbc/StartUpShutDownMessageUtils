package com.hyr;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/*******************************************************************************
 * @date 2018-12-04 上午 9:53
 * @author: <a href=mailto:>黄跃然</a>
 * @Description:
 ******************************************************************************/
public class BrowserVersionInfo {

    private static Logger LOG = LoggerFactory.getLogger(BrowserVersionInfo.class.getName());

    private static String VERSION = "version:";
    private static String DATATIME = "datetime:";
    private static String SVNVERSION = "svn-version:";

    private static String versionInfo;

    private static String version_str = "";
    private static String date_time_str = "";
    private static String svn_version_str = "";

    static {
        try {
            versionInfo = getVersionInfo();

            if (versionInfo != null) {
                versionInfo = versionInfo.replace(" ", "");
                versionInfo = versionInfo.replaceAll("\n", "");
                Pattern version = Pattern.compile(VERSION);
                Pattern datetime = Pattern.compile(DATATIME);
                Pattern svn_version = Pattern.compile(SVNVERSION);
                Matcher version_matcher = version.matcher(versionInfo);
                Matcher datetime_matcher = datetime.matcher(versionInfo);
                Matcher svn_version_matcher = svn_version.matcher(versionInfo);

                if (version_matcher.find() && datetime_matcher.find() && svn_version_matcher.find()) {
                    version_str = versionInfo.substring(version_matcher.end(), datetime_matcher.start());
                    date_time_str = versionInfo.substring(datetime_matcher.end(), svn_version_matcher.start());
                    svn_version_str = versionInfo.substring(svn_version_matcher.end());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get browser version
     *
     * @return
     */
    public static String getProjectVersion() {
        return !version_str.isEmpty() ? version_str : "Unknown";
    }

    /**
     * get compiled time
     *
     * @return
     */
    public static String getCompiledTime() {
        return !date_time_str.isEmpty() ? date_time_str : "Unknown";
    }

    /**
     * Get the subversion revision number for the root directory
     *
     * @return the revision number, eg. "451451"
     */
    public static String getSVNVersion() {
        return !svn_version_str.isEmpty() ? svn_version_str : "Unknown";
    }

    public static void main(String[] args) throws IOException {
        if (versionInfo != null) {
            versionInfo = versionInfo.replace(" ", "");
            System.out.println(versionInfo);
            Pattern version = Pattern.compile(VERSION);
            Pattern datetime = Pattern.compile(DATATIME);
            Pattern svn_version = Pattern.compile(SVNVERSION);
            Matcher version_matcher = version.matcher(versionInfo);
            Matcher datetime_matcher = datetime.matcher(versionInfo);
            Matcher svn_version_matcher = svn_version.matcher(versionInfo);

            if (version_matcher.find()) {
                System.out.println(version_matcher);
            }

            if (datetime_matcher.find()) {
                System.out.println(datetime_matcher);
            }

            if (svn_version_matcher.find()) {
                System.out.println(svn_version_matcher);
            }

            String version_str = versionInfo.substring(version_matcher.end(), datetime_matcher.start());

            String date_time_str = versionInfo.substring(datetime_matcher.end(), svn_version_matcher.start());

            String svn_version_str = versionInfo.substring(svn_version_matcher.end());
        }

    }

    private static String getVersionInfo() throws IOException {
        URL sourcePath = BrowserVersionInfo.class.getProtectionDomain().getCodeSource().getLocation();
        ZipFile zipFile = new ZipFile(sourcePath.getPath());
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        for (; entries.hasMoreElements(); ) {
            ZipEntry zipEntry = entries.nextElement();
            String filename = zipEntry.getName();
            if (filename.startsWith("ver_") && filename.endsWith(".txt")) {
                InputStream resourceAsStream = BrowserVersionInfo.class.getClassLoader().getResourceAsStream(filename);
                InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream,"gbk");
                return IOUtils.toString(inputStreamReader);
            }
        }
        return null;
    }

    /**
     * 读取 InputStream 到 String字符串中
     */
    public static String readStream(InputStream in) {
        try {
            //<1>创建字节数组输出流，用来输出读取到的内容
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //<2>创建缓存大小
            byte[] buffer = new byte[1024]; // 1KB
            //每次读取到内容的长度
            int len = -1;
            //<3>开始读取输入流中的内容
            while ((len = in.read(buffer)) != -1) { //当等于-1说明没有数据可以读取了
                baos.write(buffer, 0, len);   //把读取到的内容写到输出流中
            }
            //<4> 把字节数组转换为字符串
            String content = baos.toString();
            //<5>关闭输入流和输出流
            in.close();
            baos.close();
            //<6>返回字符串结果
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
