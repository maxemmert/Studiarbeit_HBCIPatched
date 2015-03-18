
/*  $Id: AbstractDDVPassport.java,v 1.1 2011/05/04 22:37:43 willuhn Exp $

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

import org.kapott.hbci.comm.Comm;
import org.kapott.hbci.security.Crypt;
import org.kapott.hbci.security.Sig;

public abstract class AbstractDDVPassport 
    extends AbstractHBCIPassport implements HBCIPassportChipcard
{
    protected AbstractDDVPassport(Object init)
    {
        super(init);
    }

    @Override
    public String getPassportTypeName()
    {
        return "DDV";
    }
    
    @Override
    public Comm getCommInstance()
    {
        return Comm.getInstance("Standard",this);
    }
    
    @Override
    public String getProfileMethod()
    {
        return "DDV";
    }
    
    @Override
    public String getProfileVersion()
    {
        // TODO: es gibt auch DDV-2
        return "1";
    }

    @Override
    public String getSysStatus()
    {
        return "0";
    }

    @Override
    public boolean needInstKeys()
    {
        return false;
    }

    @Override
    public boolean needUserKeys()
    {
        return false;
    }
    
    @Override
    public boolean needUserSig()
    {
        return false;
    }
    
    @Override
    public boolean hasInstSigKey()
    {
        return true;
    }
    
    @Override
    public boolean hasInstEncKey()
    {
        return true;
    }
    
    @Override
    public boolean hasMySigKey()
    {
        return true;
    }
    
    @Override
    public boolean hasMyEncKey()
    {
        return true;
    }
    
    @Override
    public String getCryptKeyType()
    {
        return Crypt.ENC_KEYTYPE_DDV;
    }

    @Override
    public String getSigFunction()
    {
        return Sig.SECFUNC_HBCI_SIG_DDV;
    }

    @Override
    public String getSigAlg()
    {
        return Sig.SIGALG_DES;
    }

    @Override
    public String getSigMode()
    {
        return Sig.SIGMODE_RETAIL_MAC;
    }

    @Override
    public String getCryptFunction()
    {
        return Crypt.SECFUNC_ENC_3DES;
    }

    @Override
    public String getCryptAlg()
    {
        return Crypt.ENCALG_2K3DES;
    }

    @Override
    public String getCryptMode()
    {
        return Crypt.ENCMODE_CBC;
    }

    @Override
    public String getHashAlg()
    {
        return Sig.HASHALG_RIPEMD160;
    }

}
