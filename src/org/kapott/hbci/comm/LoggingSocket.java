
/*  $Id: LoggingSocket.java,v 1.1 2011/05/04 22:37:51 willuhn Exp $

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

package org.kapott.hbci.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* original idea for how to integrate "logging sockets"
 * by Thomas Kruse <tkruse@sforce.org> */
public class LoggingSocket
    extends SSLSocket
{
    private SSLSocket    targetSocket;
    private OutputStream logger;
    
    public LoggingSocket(Socket targetSocket, OutputStream logger)
    {
        this.targetSocket = (SSLSocket)targetSocket;
        this.logger = logger;
    }
    
    @Override
    public InputStream getInputStream()
        throws IOException
    {
        LoggingInputStream logInputStream = new LoggingInputStream(targetSocket.getInputStream(), logger);
        return logInputStream;
    }

    @Override
    public OutputStream getOutputStream()
        throws IOException
    {
        LoggingOutputStream outputStream = new LoggingOutputStream(targetSocket.getOutputStream(), logger);
        return outputStream;
    }

    @Override
    public void addHandshakeCompletedListener(HandshakeCompletedListener arg0)
    {
        targetSocket.addHandshakeCompletedListener(arg0);
    }

    @Override
    public void bind(SocketAddress bindpoint)
        throws IOException
    {
        targetSocket.bind(bindpoint);
    }

    @Override
    public void close()
        throws IOException
    {
        targetSocket.close();
    }

    @Override
    public void connect(SocketAddress endpoint, int timeout)
        throws IOException
    {
        targetSocket.connect(endpoint, timeout);
    }

    @Override
    public void connect(SocketAddress endpoint)
        throws IOException
    {
        targetSocket.connect(endpoint);
    }

    @Override
    public boolean equals(Object obj)
    {
        return targetSocket.equals(obj);
    }

    @Override
    public SocketChannel getChannel()
    {
        return targetSocket.getChannel();
    }

    @Override
    public String[] getEnabledCipherSuites()
    {
        return targetSocket.getEnabledCipherSuites();
    }

    @Override
    public String[] getEnabledProtocols()
    {
        return targetSocket.getEnabledProtocols();
    }

    @Override
    public boolean getEnableSessionCreation()
    {
        return targetSocket.getEnableSessionCreation();
    }

    @Override
    public InetAddress getInetAddress()
    {
        return targetSocket.getInetAddress();
    }

    @Override
    public boolean getKeepAlive()
        throws SocketException
    {
        return targetSocket.getKeepAlive();
    }

    @Override
    public InetAddress getLocalAddress()
    {
        return targetSocket.getLocalAddress();
    }

    @Override
    public int getLocalPort()
    {
        return targetSocket.getLocalPort();
    }

    @Override
    public SocketAddress getLocalSocketAddress()
    {
        return targetSocket.getLocalSocketAddress();
    }

    @Override
    public boolean getNeedClientAuth()
    {
        return targetSocket.getNeedClientAuth();
    }

    @Override
    public boolean getOOBInline()
        throws SocketException
    {
        return targetSocket.getOOBInline();
    }

    @Override
    public int getPort()
    {
        return targetSocket.getPort();
    }

    @Override
    public int getReceiveBufferSize()
        throws SocketException
    {
        return targetSocket.getReceiveBufferSize();
    }

    @Override
    public SocketAddress getRemoteSocketAddress()
    {
        return targetSocket.getRemoteSocketAddress();
    }

    @Override
    public boolean getReuseAddress()
        throws SocketException
    {
        return targetSocket.getReuseAddress();
    }

    @Override
    public int getSendBufferSize()
        throws SocketException
    {
        return targetSocket.getSendBufferSize();
    }

    @Override
    public SSLSession getSession()
    {
        return targetSocket.getSession();
    }

    @Override
    public int getSoLinger()
        throws SocketException
    {
        return targetSocket.getSoLinger();
    }

    @Override
    public int getSoTimeout()
        throws SocketException
    {
        return targetSocket.getSoTimeout();
    }

    @Override
    public String[] getSupportedCipherSuites()
    {
        return targetSocket.getSupportedCipherSuites();
    }

    @Override
    public String[] getSupportedProtocols()
    {
        return targetSocket.getSupportedProtocols();
    }

    @Override
    public boolean getTcpNoDelay()
        throws SocketException
    {
        return targetSocket.getTcpNoDelay();
    }

    @Override
    public int getTrafficClass()
        throws SocketException
    {
        return targetSocket.getTrafficClass();
    }

    @Override
    public boolean getUseClientMode()
    {
        return targetSocket.getUseClientMode();
    }

    @Override
    public boolean getWantClientAuth()
    {
        return targetSocket.getWantClientAuth();
    }

    @Override
    public int hashCode()
    {
        return targetSocket.hashCode();
    }

    @Override
    public boolean isBound()
    {
        return targetSocket.isBound();
    }

    @Override
    public boolean isClosed()
    {
        return targetSocket.isClosed();
    }

    @Override
    public boolean isConnected()
    {
        return targetSocket.isConnected();
    }

    @Override
    public boolean isInputShutdown()
    {
        return targetSocket.isInputShutdown();
    }

    @Override
    public boolean isOutputShutdown()
    {
        return targetSocket.isOutputShutdown();
    }

    @Override
    public void removeHandshakeCompletedListener(HandshakeCompletedListener arg0)
    {
        targetSocket.removeHandshakeCompletedListener(arg0);
    }

    @Override
    public void sendUrgentData(int data)
        throws IOException
    {
        targetSocket.sendUrgentData(data);
    }

    @Override
    public void setEnabledCipherSuites(String[] arg0)
    {
        targetSocket.setEnabledCipherSuites(arg0);
    }

    @Override
    public void setEnabledProtocols(String[] arg0)
    {
        targetSocket.setEnabledProtocols(arg0);
    }

    @Override
    public void setEnableSessionCreation(boolean arg0)
    {
        targetSocket.setEnableSessionCreation(arg0);
    }

    @Override
    public void setKeepAlive(boolean on)
        throws SocketException
    {
        targetSocket.setKeepAlive(on);
    }

    @Override
    public void setNeedClientAuth(boolean arg0)
    {
        targetSocket.setNeedClientAuth(arg0);
    }

    @Override
    public void setOOBInline(boolean on)
        throws SocketException
    {
        targetSocket.setOOBInline(on);
    }

    @Override
    public void setReceiveBufferSize(int size)
        throws SocketException
    {
        targetSocket.setReceiveBufferSize(size);
    }

    @Override
    public void setReuseAddress(boolean on)
        throws SocketException
    {
        targetSocket.setReuseAddress(on);
    }

    @Override
    public void setSendBufferSize(int size)
        throws SocketException
    {
        targetSocket.setSendBufferSize(size);
    }

    @Override
    public void setSoLinger(boolean on, int linger)
        throws SocketException
    {
        targetSocket.setSoLinger(on, linger);
    }

    @Override
    public void setSoTimeout(int timeout)
        throws SocketException
    {
        targetSocket.setSoTimeout(timeout);
    }

    @Override
    public void setTcpNoDelay(boolean on)
        throws SocketException
    {
        targetSocket.setTcpNoDelay(on);
    }

    @Override
    public void setTrafficClass(int tc)
        throws SocketException
    {
        targetSocket.setTrafficClass(tc);
    }

    @Override
    public void setUseClientMode(boolean arg0)
    {
        targetSocket.setUseClientMode(arg0);
    }

    @Override
    public void setWantClientAuth(boolean arg0)
    {
        targetSocket.setWantClientAuth(arg0);
    }

    @Override
    public void shutdownInput()
        throws IOException
    {
        targetSocket.shutdownInput();
    }

    @Override
    public void shutdownOutput()
        throws IOException
    {
        targetSocket.shutdownOutput();
    }

    @Override
    public void startHandshake()
        throws IOException
    {
        targetSocket.startHandshake();
    }

    @Override
    public String toString()
    {
        return targetSocket.toString();
    }
}
