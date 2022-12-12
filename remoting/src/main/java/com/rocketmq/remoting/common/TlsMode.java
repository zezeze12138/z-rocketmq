package com.rocketmq.remoting.common;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-11  18:14
 * @Version: 1.0
 */
public enum  TlsMode {

    DISABLE("disabled"),
    PREMISSIVE("permissive"),
    ENFORCING("enforcing");

    private String name;

    TlsMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TlsMode parse(String mode){
        for(TlsMode tlsMode : TlsMode.values()){
            if(tlsMode.name.equals(mode)){
                return tlsMode;
            }
        }
        return PREMISSIVE;
    }

}
