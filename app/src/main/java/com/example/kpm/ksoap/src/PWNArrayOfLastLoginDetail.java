package com.example.kpm.ksoap.src;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 5.12.1.0
//
// Created by Quasar Development 
//
//----------------------------------------------------


import org.ksoap2.serialization.AttributeContainer;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.Hashtable;
import java.util.Vector;

public class PWNArrayOfLastLoginDetail extends Vector<PWNLastLoginDetail> implements KvmSerializable
{
    private transient Object __source;

    public PWNArrayOfLastLoginDetail()
    {
    }

    public PWNArrayOfLastLoginDetail(int initialCapactiy)
    {
        super(initialCapactiy);
    }

    public PWNArrayOfLastLoginDetail(java.util.Collection< PWNLastLoginDetail> initialCapactiy)
    {
        super(initialCapactiy);
    }

    public void loadFromSoap(Object inObj, PWNExtendedSoapSerializationEnvelope __envelope)
    {
        if (inObj == null)
            return;
        __source=inObj;
            SoapObject soapObject=(SoapObject)inObj;
            int size = soapObject.getPropertyCount();
            for (int i0=0;i0< size;i0++)
            {
                Object obj = soapObject.getProperty(i0);
                if (obj!=null && obj instanceof AttributeContainer)
                {
                    AttributeContainer j =(AttributeContainer) soapObject.getProperty(i0);
                    PWNLastLoginDetail j1= (PWNLastLoginDetail)__envelope.get(j,PWNLastLoginDetail.class,false);
                    add(j1);
                    
                }
            }
    }

    public Object getSourceObject()
    {
        return __source;
    }
    
    @Override
    public Object getProperty(int arg0) {
        return this.get(arg0)!=null?this.get(arg0): SoapPrimitive.NullNilElement;
    }
    
    @Override
    public int getPropertyCount() {
        return this.size();
    }
    
    @Override
    public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
        info.name = "LastLoginDetail";
        info.type = PWNLastLoginDetail.class;
    	info.namespace= "http://schemas.datacontract.org/2004/07/ClientCabinet_MicroService";
    }
    
    @Override
    public void setProperty(int arg0, Object arg1) {
    }


}