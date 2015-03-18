
/*  $Id: AbstractRDHSWPassport.java,v 1.1 2011/05/04 22:37:43 willuhn Exp $

    This file is part of HBCI4Java
    Copyright (C) 2001-2008  Stefan Palme

    HBCI4Java is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    HBCI4Java is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package org.kapott.hbci.passport;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.kapott.cryptalgs.RSAPrivateCrtKey2;
import org.kapott.hbci.exceptions.HBCI_Exception;
import org.kapott.hbci.manager.HBCIKey;
import org.kapott.hbci.manager.HBCIUtils;
import org.kapott.hbci.manager.HBCIUtilsInternal;

public abstract class AbstractRDHSWPassport 
	extends AbstractRDHPassport 
{
    protected HBCIKey[][] keys;

    protected AbstractRDHSWPassport(Object init)
    {
    	super(init);
    	
        keys=new HBCIKey[3][];
        for (int i=0;i<3;i++) {
            keys[i]=new HBCIKey[2];
            for (int j=0;j<2;j++) {
                keys[i][j]=null;
            }
        }
    }
    
    @Override
    public boolean hasInstSigKey()
    {
        return getInstSigKey()!=null;
    }
    
    @Override
    public boolean hasInstEncKey()
    {
        return getInstEncKey()!=null;
    }
    
    @Override
    public boolean hasMySigKey()
    {
        return getMyPublicSigKey()!=null;
    }
    
    @Override
    public boolean hasMyEncKey()
    {
        return getMyPublicEncKey()!=null;
    }
    
    public HBCIKey getKey(int i,int j)
    {
        return keys[i][j];
    }
    
    @Override
    public void setInstSigKey(HBCIKey key)
    {
        setKey(0,0,key);
    }

    @Override
    public void setInstEncKey(HBCIKey key)
    {
        setKey(0,1,key);
    }

    public void setMySigKey(HBCIKey key)
    {
        setKey(1,0,key);
        setKey(1,1,key);
    }

    public void setMyEncKey(HBCIKey key)
    {
        setKey(2,0,key);
        setKey(2,1,key);
    }

    public void setMyDigKey(HBCIKey key)
    {
        // TODO
    }
    
    @Override
    public void setMyPublicSigKey(HBCIKey key)
    {
        setKey(1,0,key);
    }

    @Override
    public void setMyPrivateSigKey(HBCIKey key)
    {
        setKey(1,1,key);
    }

    @Override
    public void setMyPublicEncKey(HBCIKey key)
    {
        setKey(2,0,key);
    }

    @Override
    public void setMyPrivateEncKey(HBCIKey key)
    {
        setKey(2,1,key);
    }

    @Override
    public void setMyPublicDigKey(HBCIKey key)
    {
        // TODO
    }

    @Override
    public void setMyPrivateDigKey(HBCIKey key)
    {
        // TODO
    }

    @Override
    public HBCIKey getMyPublicSigKey()
    {
        return getKey(1,0);
    }

    @Override
    public HBCIKey getMyPrivateSigKey()
    {
        return getKey(1,1);
    }

    @Override
    public HBCIKey getMyPublicEncKey()
    {
        return getKey(2,0);
    }

    @Override
    public HBCIKey getMyPrivateEncKey()
    {
        return getKey(2,1);
    }

    @Override
    public HBCIKey getMyPublicDigKey()
    {
        // TODO
        return null;
    }
    
    @Override
    public HBCIKey getMyPrivateDigKey()
    {
        // TODO
        return null;
    }
    
    @Override
    public HBCIKey getInstSigKey()
    {
        return getKey(0,0);
    }

    @Override
    public String getInstSigKeyName()
    {
        return getInstSigKey()!=null?getInstSigKey().userid:null;
    }

    @Override
    public String getInstSigKeyNum()
    {
        return getInstSigKey()!=null?getInstSigKey().num:null;
    }

    @Override
    public String getInstSigKeyVersion()
    {
        return getInstSigKey()!=null?getInstSigKey().version:null;
    }
    
    @Override
    public HBCIKey getInstEncKey()
    {
        return getKey(0,1);
    }

    @Override
    public String getInstEncKeyName()
    {
        return getInstEncKey()!=null?getInstEncKey().userid:null;
    }

    @Override
    public String getInstEncKeyNum()
    {
        return getInstEncKey()!=null?getInstEncKey().num:null;
    }

    @Override
    public String getInstEncKeyVersion()
    {
        return getInstEncKey()!=null?getInstEncKey().version:null;
    }

    @Override
    public String getMySigKeyName()
    {
        return getMyPublicSigKey()!=null?getMyPublicSigKey().userid:null;
    }

    @Override
    public String getMySigKeyNum()
    {
        return getMyPublicSigKey()!=null?getMyPublicSigKey().num:null;
    }

    @Override
    public String getMySigKeyVersion()
    {
        return getMyPublicSigKey()!=null?getMyPublicSigKey().version:null;
    }

    @Override
    public String getMyEncKeyName()
    {
        return getMyPublicEncKey()!=null?getMyPublicEncKey().userid:null;
    }

    @Override
    public String getMyEncKeyNum()
    {
        return getMyPublicEncKey()!=null?getMyPublicEncKey().num:null;
    }

    @Override
    public String getMyEncKeyVersion()
    {
        return getMyPublicEncKey()!=null?getMyPublicEncKey().version:null;
    }

    public final void setKey(int i,int j,HBCIKey key)
    {
        // System.out.println("passportRDH: setting key "+i+","+j+" to "+(key==null?"null":key.country+":"+key.blz+":"+key.cid+":"+key.num+":"+key.version));
        keys[i][j]=key;
    }
    
    @Override
    public byte[] sign(byte[] data)
    {
        /* data is the result from the hash() method. In most cases, this is simply
         * the hbci message to be signed, because the signature algorithms used here
         * (iso-9796-1, iso-9796-2, pkcs#1-pss) INCLUDE the hash-step, so it must
         * not be done manually before.
         * the only exception for this is is RDH-10 where an extra round of hashing
         * must be done before using PKCS#1-PSS */
        
        try {
            Signature sig=getSignatureInstance();
            sig.initSign((PrivateKey)(getMyPrivateSigKey().key));
            sig.update(data);
            byte[] result=sig.sign();
            result=checkForCryptDataSize(result, getCryptDataSize(getMyPublicSigKey().key));
            return result;
        } catch (Exception ex) {
            throw new HBCI_Exception("*** signing of message failed",ex);
        }
    }

    @Override
    public boolean verify(byte[] data,byte[] sig)
    {
        /* data is the result from the hash() method. In most cases, this is simply
         * the hbci message to be signed, because the signature algorithms used here
         * (iso-9796-1, iso-9796-2, pkcs#1-pss) INCLUDE the hash-step, so it must
         * not be done manually before.
         * the only exception for this is is RDH-10 where an extra round of hashing
         * must be done before using PKCS#1-PSS */
        
        try {
            Signature sign=getSignatureInstance();
            sign.initVerify((PublicKey)(getInstSigKey().key));
            sign.update(data);
            return sign.verify(sig);
        } catch (Exception ex) {
            throw new HBCI_Exception("*** verification of message signature failed",ex); 
        }
    }

    private byte[] encryptMessage(byte[] plainMsg,SecretKey msgkey)
    {
        try {
            Cipher cipher=Cipher.getInstance("DESede/CBC/NoPadding");
            byte[] iv=new byte[8];
            Arrays.fill(iv,(byte)(0));
            IvParameterSpec spec=new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE,msgkey,spec);

            return cipher.doFinal(plainMsg);
        } catch (Exception ex) {
            throw new HBCI_Exception("*** can not encrypt message",ex);
        }
    }
    
    private byte[] encryptKey(SecretKey msgkey)
    {
        try {
            // schluessel als byte-array abspeichern

            SecretKeyFactory factory=SecretKeyFactory.getInstance("DESede");
            DESedeKeySpec spec=(DESedeKeySpec)(factory.getKeySpec(msgkey,DESedeKeySpec.class));
            byte[] plainKey=spec.getKey(); // plainKey ist der DESede-Key

            // abh�ngig von der L�nge des inst-enc-keys
            int    cryptDataSize=getCryptDataSize(getInstEncKey().key);
            byte[] plainText=new byte[cryptDataSize];
            Arrays.fill(plainText,(byte)(0));
            System.arraycopy(plainKey,0,plainText,plainText.length-16,16);
            BigInteger m=new BigInteger(+1,plainText);

            Key k=getInstEncKey().key;
            BigInteger c=m.modPow(((RSAPublicKey)(k)).getPublicExponent(),
                                  ((RSAPublicKey)(k)).getModulus());
 
            byte[] result=c.toByteArray();
            result=checkForCryptDataSize(result, cryptDataSize);
            return result;
        } catch (Exception ex) {
            throw new HBCI_Exception("*** can not encrypt message key",ex);
        }
    }

    @Override
    public byte[][] encrypt(byte[] plainMsg)
    {
        try {
            SecretKey msgkey=createMsgKey();
            byte[] cryptMsg=encryptMessage(plainMsg,msgkey);
            byte[] cryptKey=encryptKey(msgkey);

            byte[][] ret=new byte[2][];
            ret[0]=cryptKey;
            ret[1]=cryptMsg;

            return ret;
        } catch (Exception ex) {
            throw new HBCI_Exception("*** error while encrypting",ex);
        }
    }

    @Override
    public byte[] decrypt(byte[] cryptedKey,byte[] cryptedMsg)
    {
        try {
            // key entschluesseln
            Key k=getMyPrivateEncKey().key;
            
            byte[] plainKey;
            if (k instanceof RSAPrivateKey) {
                HBCIUtils.log("decrypting message key with (n,d)-algorithm",HBCIUtils.LOG_DEBUG);
                BigInteger exponent=((RSAPrivateKey)(k)).getPrivateExponent();
                BigInteger modulus=((RSAPrivateKey)(k)).getModulus();

                BigInteger c=new BigInteger(+1,cryptedKey);
                plainKey=c.modPow(exponent,modulus).toByteArray();
            } else {
                HBCIUtils.log("decrypting message key with (p,q,dP,dQ,qInv)-algorithm",HBCIUtils.LOG_DEBUG);
                BigInteger p=((RSAPrivateCrtKey2)k).getP();
                BigInteger q=((RSAPrivateCrtKey2)k).getQ();
                BigInteger dP=((RSAPrivateCrtKey2)k).getdP();
                BigInteger dQ=((RSAPrivateCrtKey2)k).getdQ();
                BigInteger qInv=((RSAPrivateCrtKey2)k).getQInv();
        
                BigInteger c=new BigInteger(+1,cryptedKey);
                BigInteger m1=c.modPow(dP,p);
                BigInteger m2=c.modPow(dQ,q);
                BigInteger h=m1.subtract(m2).multiply(qInv).mod(p); 
                plainKey=m2.add(q.multiply(h)).toByteArray();
            }

            byte[] realPlainKey=new byte[24];
            System.arraycopy(plainKey,plainKey.length-16,realPlainKey,0,16);
            System.arraycopy(plainKey,plainKey.length-16,realPlainKey,16,8);

            DESedeKeySpec spec=new DESedeKeySpec(realPlainKey);
            SecretKeyFactory fac=SecretKeyFactory.getInstance("DESede");
            SecretKey key=fac.generateSecret(spec);

            // nachricht entschluesseln
            Cipher cipher=Cipher.getInstance("DESede/CBC/NoPadding");
            byte[] ivarray=new byte[8];
            Arrays.fill(ivarray,(byte)(0));
            IvParameterSpec iv=new IvParameterSpec(ivarray);
            cipher.init(Cipher.DECRYPT_MODE,key,iv);
            return cipher.doFinal(cryptedMsg);
        } catch (Exception ex) {
            throw new HBCI_Exception("*** error while decrypting message",ex);
        }
    }
    
    private int getKeySizeByProfile()
    {
        int ret=-1;
        int profile=Integer.parseInt(getProfileVersion());
        
        switch (profile) {
        case 1:
            ret=768;
            break;
        case 2:
            ret=2048;
            break;
        case 10:
            HBCIKey k=getInstSigKey();
            if (k==null) {
                k=getInstEncKey();
            }
            if (k!=null) {
                RSAPublicKey pkey=(RSAPublicKey)k.key;
                ret = pkey.getModulus().bitLength();
            } else {
                ret=4096;
            }
            break;
        default:
            throw new HBCI_Exception("*** dont know which keysize to use for profile rdh-"+profile);
        }
        
        HBCIUtils.log("using keysize "+ret+" bits for newly generated keys",HBCIUtils.LOG_DEBUG);
        return ret;
    }

    @Override
    public HBCIKey[][] generateNewUserKeys() 
    {
        HBCIKey[] newSigKey=null;
        HBCIKey[] newEncKey=null;
        try {
            HBCIUtils.log("generating new user keys",HBCIUtils.LOG_INFO);

            String blz=getBLZ();
            String country=getCountry();
            String userid=getUserId();
            String profileVersion=getProfileVersion();

            newSigKey=new HBCIKey[2];
            newEncKey=new HBCIKey[2];
            
            String num=hasMySigKey()?getMyPublicSigKey().num:profileVersion;
            String version=hasMySigKey()?getMyPublicSigKey().version:"0";
            version=Integer.toString(Integer.parseInt(version)+1);
            
            int keySize=getKeySizeByProfile();

            // TODO: auch dig key neu generieren?
            for (int i=0;i<2;i++) {
                KeyPairGenerator keygen=KeyPairGenerator.getInstance("RSA");
                // die schl�ssell�nge ist vom sicherheitsprofil abh�ngig 
                keygen.initialize(keySize);
                KeyPair pair=keygen.generateKeyPair();

                if (i==0) {
                    newSigKey[0]=new HBCIKey(country,blz,userid,num,version,pair.getPublic());
                    newSigKey[1]=new HBCIKey(country,blz,userid,num,version,pair.getPrivate());
                } else {
                    newEncKey[0]=new HBCIKey(country,blz,userid,num,version,pair.getPublic());
                    newEncKey[1]=new HBCIKey(country,blz,userid,num,version,pair.getPrivate());
                }
            }
        } catch (Exception ex) {
            throw new HBCI_Exception(HBCIUtilsInternal.getLocMsg("EXCMSG_GENKEYS_ERR"),ex);
        }
        
        HBCIKey[][] ret=new HBCIKey[3][];
        ret[0]=newSigKey;
        ret[1]=newEncKey;
        // TODO: dig keys
        ret[2]=null;
        
        return ret;
    }
}
