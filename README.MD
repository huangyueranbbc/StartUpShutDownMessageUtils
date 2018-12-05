# StartUpShutDownMessageUtils

  参考Hadoop源码实现 项目启动和停止时，输出项目版本信息工具

  读过Hadoop源码的人一定不陌生，其实就是借鉴Hadoop源码里的启动工具类。

  只不过Hadoop源码是用package-info.java来实现。

  https://blog.csdn.net/huangyueranbbc/article/details/84827150

  版本相关信息存放的文件只需要在build文件指明，在maven打包前在项目资源里提前生成即可，然后进行maven打包。

  e.g:

  set content=@com.bonree.T2(version = "%version%",svnversion = "%svnversion%")package com.bonree;

  for %%i in (%modules%) do echo %content% >  %%i\src\main\java\com\hyr\packageinfo\package-info.java
















