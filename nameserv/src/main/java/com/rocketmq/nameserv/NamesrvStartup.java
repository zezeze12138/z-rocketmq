package com.rocketmq.nameserv;

import com.rocketmq.nameserv.config.NamesrvConfig;
import com.rocketmq.nameserv.config.NettyServerConfig;
import com.rocketmq.nameserv.util.ServerUtil;
import org.apache.commons.cli.*;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-04  20:47
 * @Version: 1.0
 */
public class NamesrvStartup {

    public static void main(String[] args) {
        try{
            NameservController controller = createNamesrvController(args);
        }catch (Throwable e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static NameservController createNamesrvController(String[] args) {
        Options options = ServerUtil.buildCommandlineOptions(new Options());
        final NamesrvConfig nameserConfig = new NamesrvConfig();
        final NettyServerConfig nettyServerConfig = new NettyServerConfig();
        return null;
    }

}
