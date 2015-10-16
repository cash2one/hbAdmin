package com.manager.memcahed;

import java.util.Date;

public class MemCached {

    public static Object getMccObject(String key) throws Exception {
	try {
	    return MemCachedFactory.getInstance().getMcc().get(key);
	} catch (Exception e) {
	    throw e;
	}
    }

    public static void setMccObject(String key, Object o, int objtype) throws Exception {
	try {
	    switch (objtype) {
	    case 0:
		if (MemCachedFactory.getInstance().getMcc().keyExists(key))
		    MemCachedFactory.getInstance().getMcc().replace(key, o);
		else
		    MemCachedFactory.getInstance().getMcc().add(key, o);
		break;
	    case 1:
		if (MemCachedFactory.getInstance().getMcc().keyExists(key))
		    MemCachedFactory.getInstance().getMcc().replace(key, o, new Date(600000));
		else
		    MemCachedFactory.getInstance().getMcc().add(key, o, new Date(600000));
		break;
	    case 2:
		if (MemCachedFactory.getInstance().getMcc().keyExists(key))
		    MemCachedFactory.getInstance().getMcc().replace(key, o, new Date(600000));
		else
		    MemCachedFactory.getInstance().getMcc().add(key, o, new Date(600000));
		break;
	    case 3:
		if (MemCachedFactory.getInstance().getMcc().keyExists(key))
		    MemCachedFactory.getInstance().getMcc().replace(key, o, new Date(600000));
		else
		    MemCachedFactory.getInstance().getMcc().add(key, o, new Date(600000));
		break;
	    case 4:
		if (MemCachedFactory.getInstance().getMcc().keyExists(key))
		    MemCachedFactory.getInstance().getMcc().replace(key, o, new Date(600000));
		else
		    MemCachedFactory.getInstance().getMcc().add(key, o, new Date(600000));
		break;
	    case 5:
		if (MemCachedFactory.getInstance().getMcc().keyExists(key))
		    MemCachedFactory.getInstance().getMcc().replace(key, o, new Date(86400000));
		else
		    MemCachedFactory.getInstance().getMcc().add(key, o, new Date(86400000));
		break;
	    default:
		if (MemCachedFactory.getInstance().getMcc().keyExists(key))
		    MemCachedFactory.getInstance().getMcc().replace(key, o, new Date(600000));
		else
		    MemCachedFactory.getInstance().getMcc().add(key, o, new Date(600000));
		break;
	    }
	} catch (Exception e) {
	    throw e;
	}
    }

    public static void removeMccObject(String key) throws Exception {
	try {
	    if (MemCachedFactory.getInstance().getMcc().keyExists(key))
		MemCachedFactory.getInstance().getMcc().delete(key);
	} catch (Exception e) {
	    throw e;
	}
    }

}
