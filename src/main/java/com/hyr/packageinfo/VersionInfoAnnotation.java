package com.hyr.packageinfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*******************************************************************************
 * @date 2018-12-03 下午 4:37
 * @author: <a href=mailto:>黄跃然</a>
 * @Description:
 ******************************************************************************/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface VersionInfoAnnotation {

    /**
     * Get the Hadoop version
     * @return the version string "0.6.3-dev"
     */
    String version();

    /**
     * svn version
     * @return
     */
    String svnversion();
}
