package org.campware.cream.modules.screens;

/* ====================================================================
 * Copyright (C) 2003-2005  Media Development Loan Fund
 *
 *  * contact: contact@campware.org - http://www.campware.org
 * Campware encourages further development. Please let us know.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */


import java.io.*;

import javax.mail.internet.*;
import javax.mail.BodyPart;

import javax.servlet.ServletOutputStream;
import org.apache.torque.util.Criteria;

import org.campware.cream.modules.scheduledjobs.Pop3Job;
import org.campware.cream.modules.util.Base64;
import org.campware.cream.om.InboxAttachment;
import org.campware.cream.om.InboxAttachmentPeer;

//Turbine stuff.
import org.apache.turbine.modules.Screen;
import org.apache.turbine.util.RunData;

// ECS Classes
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ecs.ConcreteElement;
/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class InboxAttachmentData extends Screen
{

	/** Logging */
	private static Log log = LogFactory.getLog(InboxAttachmentData.class);

	
	/**
	     * Build the Screen.  This method actually makes a call to the
	     * doOutput() method in order to generate the Screen content.
	     *
	     * @param data Turbine information.
	     * @return A ConcreteElement.
	     * @exception Exception, a generic exception.
	     */
	    protected final ConcreteElement doBuild(RunData data)
	        throws Exception
	    {

            int entry_id = data.getParameters().getInt("inboxattachmentid");
            
            if (entry_id>0)
            {
                Criteria criteria = new Criteria();
                criteria.add(InboxAttachmentPeer.INBOX_ATTACHMENT_ID, entry_id);

                try{
                	
              	InboxAttachment entry = (InboxAttachment) InboxAttachmentPeer.doSelect(criteria).get(0);

              	String tempContType= entry.getContentType().trim();
              	String fileName= entry.getFileName().trim();

              	data.getResponse().setContentType(entry.getContentType());
              	log.debug(fileName);
      	    	data.getResponse().setHeader("Content-disposition", "attachment; filename=\""+ fileName +"\"");
      	    	data.declareDirectResponse();

      	    	data.getResponse().getOutputStream().write(Base64.decode(entry.getContent()));

              }
              catch (Exception e)
              {
              }
            }
	    	
	        return null;
	    }

	    /**
	     * The layout must be set to null.
	     *
	     * @param data Turbine information.
	     * @return A null String.
	     */
	    public final String getLayout(RunData data)
	    {
	        return null;
	    }
	

}
