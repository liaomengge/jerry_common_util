package com.sh.lmg.net;

import com.sh.lmg.log.MwLogger;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;


public final class NetworkUtil {

    private static Logger logger = new MwLogger(NetworkUtil.class);

    /**
     * 获取主机IP地址
     *
     * @return
     */
    public static String getHostAddress() {
        String result = "127.0.0.1";
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
            return result;
        }
        InetAddress ip;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address && !ip.getHostAddress().equals(result)) {
                    return ip.getHostAddress();
                }
            }
        }

        return result;

    }

    /**
     * 获取主机计算机名
     *
     * @return
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.error("NetworkUtil.getHostName error:", e);
        }
        return getMachineName();
    }

    private static String getMachineName() {
        String result = "localhost";
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
            return result;
        }
        InetAddress ip;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address && !ip.getHostName().equals(result)) {
                    return ip.getHostName();
                }
            }
        }

        return result;
    }


    /**
     * 判断端口是否被占用
     *
     * @param port
     * @return
     */
    public static boolean isLoclePortUsing(int port) {
        boolean flag = true;
        try {
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
        }
        return flag;
    }

    public static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        System.out.println(theAddress);
        try {
            ServerSocket socket = new ServerSocket(port);
            flag = true;
        } catch (IOException e) {
            logger.error("isPortUsing error=>" + host + ":" + port, e);
        }
        return flag;
    }

    public static String getMacAddress() {
        return getMacAddress('-');
    }

    public static String getMacAddress(char split) {
        NetworkInterface netInf;
        byte[] mac;
        try {
            netInf = NetworkInterface.getNetworkInterfaces().nextElement();
            mac = netInf.getHardwareAddress();
        } catch (SocketException e) {
            e.printStackTrace();
            return "00" + split + "00" + split + "00" + split + "00" + split + "00" + split + "00";
        }

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? split : ""));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getMacAddress());
    }
}
