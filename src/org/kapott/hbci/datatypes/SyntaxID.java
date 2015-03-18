
/*  $Id: SyntaxID.java,v 1.1 2011/05/04 22:37:55 willuhn Exp $

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

package org.kapott.hbci.datatypes;

/* a special case of the datatype AN, where maxlen is
    predefined to be 30 */
public final class SyntaxID
     extends SyntaxAN
{
    public SyntaxID(String x, int minlen, int maxlen)
    {
        super(x, minlen, 30);
    }

    @Override
    public void init(String x, int minlen, int maxlen)
    {
        super.init(x, minlen, 30);
    }

    // --------------------------------------------------------------------------------

    public SyntaxID(StringBuffer res, int minsize, int maxsize)
    {
        super(res, minsize, 30);
    }
    
    @Override
    public void init(StringBuffer res, int minsize, int maxsize)
    {
        super.init(res, minsize, 30);
    }
}
