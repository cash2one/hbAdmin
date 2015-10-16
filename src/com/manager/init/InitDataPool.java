package com.manager.init;

public interface InitDataPool {

    /**
     * 获取提示信息
     * @param parName
     */
    public String getSP(String parName);

    /**
     * Int类型信息
     * @param parName
     */
    public int getIntSP(String parName);
    
    /**
     * 本地信息初始化
     */
    public void initLocalParameters();
    
    /**
     * 国际化初始
     * @param parName
     * @return
     */
    public String getLP(String parName);

}
