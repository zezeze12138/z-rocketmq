package com.rocketmq.remoting.netty;

import com.rocketmq.remoting.common.TlsMode;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-11  18:29
 * @Version: 1.0
 */
public class TlsSystemConfig {

    public static final String TLS_SERVER_MODE = "tls.server.mode";

    public static TlsMode tlsMode = TlsMode.parse(System.getProperty(TLS_SERVER_MODE, "permissive"));

}
