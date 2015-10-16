package com.manager.memcahed;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.manager.util.Constant;

public class MemCachedFactory {

    private static MemCachedFactory instance;
    protected MemCachedClient mcc;

    private MemCachedFactory() {
	String[] servers = Constant.MEMCACHEDURL;
	Integer[] weights = { 3, 3 };

	// 创建一个实例对象SockIOPool
	SockIOPool pool = SockIOPool.getInstance();

	// set the servers and the weights
	// 设置Memcached Server
	pool.setServers(servers);
	pool.setWeights(weights);

	// set some basic pool settings
	// 5 initial, 5 min, and 250 max conns
	// and set the max idle time for a conn
	// to 6 hours
	pool.setInitConn(10);
	pool.setMinConn(10);
	pool.setMaxConn(500);
	pool.setMaxIdle(1000 * 60 * 60 * 6);

	// set the sleep for the maint thread
	// it will wake up every x seconds and
	// maintain the pool size
	pool.setMaintSleep(30);

	// Tcp的规则就是在发送一个包之前，本地机器会等待远程主机
	// 对上一次发送的包的确认信息到来；这个方法就可以关闭套接字的缓存，
	// 以至这个包准备好了就发；
	pool.setNagle(false);
	// 连接建立后对超时的控制
	pool.setSocketTO(3000);
	// 连接建立时对超时的控制
	pool.setSocketConnectTO(0);

	// initialize the connection pool
	// 初始化一些值并与MemcachedServer段建立连接
	pool.initialize();
    }

    public static MemCachedFactory getInstance() throws Exception {
	try {
	    if (instance == null) {
		instance = new MemCachedFactory();
	    }
	    return instance;
	} catch (Exception e) {
	    throw e;
	}
    }

    public MemCachedClient getMcc() throws Exception {
	try {
	    if (mcc == null) {
		mcc = new MemCachedClient();
	    }

	    // 设置启用压缩
	    // mcc.setCompressEnable(true);
	    // 超过64K压缩
	    // mcc.setCompressThreshold(64 * 1024);

	    return mcc;
	} catch (Exception e) {
	    throw e;
	}
    }

    public void setMcc(MemCachedClient mcc) {
	this.mcc = mcc;
    }
}
