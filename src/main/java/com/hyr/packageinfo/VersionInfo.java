package com.hyr.packageinfo;

/*******************************************************************************
 * @date 2018-12-03 下午 4:37
 * @author: <a href=mailto:>黄跃然</a>
 * @Description:
 ******************************************************************************/
public class VersionInfo {
    private static Package aPackage;
    private static VersionInfoAnnotation version;

    static {
        aPackage = VersionInfoAnnotation.class.getPackage();
        version = aPackage.getAnnotation(VersionInfoAnnotation.class);
    }

    public static void main(String[] args) {
        System.out.println(version.toString());
    }

    public static String getVersion() {
        return version != null ? version.version() : "Unknown";
    }

    public static String getSVNVersion() {
        return version != null ? version.svnversion() : "Unknown";
    }
}
