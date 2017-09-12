package com.sys.webframe.bus.user.service;

import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 

public final class Encrypt 
{
	// log
    private static Log log = LogFactory.getLog(Encrypt.class);
    
    private String code;
    /**
     */
    private Encrypt()
    {
    }
    
    /**
     */
    public Encrypt(String code)
    {
        this.code = code;
    }

    /**
     */
    public static String encodeMD5(String password)
    {
        try
        {
            if (password == null)
            {
                return null;
            }
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            return (new sun.misc.BASE64Encoder()).encode(md.digest());
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     */
    public String getEncrypt(String passWordString)
    {
        try
        {
            if (null == passWordString)
            {
                return null;
            }
            MessageDigest md = MessageDigest.getInstance(code);
            md.update(passWordString.getBytes());
            return (new sun.misc.BASE64Encoder()).encode(md.digest());
        }
        catch (Exception e)
        {
            log.error(e);
            return null;
        }
    }
    
    public static void main(String[] args) {
	}

}
