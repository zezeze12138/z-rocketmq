package com.rocketmq.nameserv.util;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-05  02:53
 * @Version: 1.0
 */
public class ServerUtil {

    public static Options buildCommandlineOptions(final Options options){
        Option opt = new Option("h", "help",false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);

        opt = new Option("n", "nameservAddr", true, "Name server address list, eg:'192.168.0.1:9876;192.168.0.1:9876'");
        opt.setRequired(false);
        options.addOption(opt);
        return options;
    }
}
