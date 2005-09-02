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

import org.apache.torque.util.Criteria;

import org.apache.velocity.context.Context;

import org.campware.cream.om.OutboxEvent;
import org.campware.cream.om.OutboxEventPeer;
import org.campware.cream.om.InboxEvent;
import org.campware.cream.om.InboxEventPeer;
import org.campware.cream.om.CustomerPeer;
import org.campware.cream.om.ProductPeer;
import org.campware.cream.om.ProjectPeer;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class OutboxEventForm extends CreamForm
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("OUTBOX");
        setIdName(OutboxEventPeer.OUTBOX_EVENT_ID);
        setFormIdName("outboxeventid");
    }

    protected boolean getEntry(Criteria criteria, Context context)
    {
        try
        {
            OutboxEvent entry = (OutboxEvent) OutboxEventPeer.doSelect(criteria).get(0);
            context.put("entry", entry);
            
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    protected boolean getNew(Context context)
    {
        try
        {
            OutboxEvent entry = new OutboxEvent();
            context.put("entry", entry);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

	protected boolean getNewRelated(int relid, Context context)
	{
		try
		{
			Criteria criteria = new Criteria();
			criteria.add(InboxEventPeer.INBOX_EVENT_ID, relid);
			InboxEvent relEntry = (InboxEvent) InboxEventPeer.doSelect(criteria).get(0);

			OutboxEvent entry = new OutboxEvent();

			entry.setCustomerId(relEntry.getCustomerId());
			entry.setProductId(relEntry.getProductId());
			entry.setProjectId(relEntry.getProjectId());

			String oldSubject= relEntry.getSubject();
			String oldSender= relEntry.getSenderEmail();
			String oldReplyTo= relEntry.getSenderReplyTo();

			if (oldReplyTo.length()>2){
				entry.setReceiverTo(oldReplyTo);
			}else{
				entry.setReceiverTo(oldSender);
			}
			
			if (oldSubject.startsWith("Re:")){
				entry.setSubject(oldSubject);
			}else{
				entry.setSubject("Re: " + oldSubject);
			}
//			entry.setBody("\n\n\n----- Original Message -----\n" + relEntry.getBody());
			entry.setBody("<br/><br/>------------------------------<br/><br/>" + relEntry.getBody());
		
			context.put("entry", entry);
            
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	protected boolean getLookups(Context context)
    {
        try
        {
            Criteria custcrit = new Criteria();
            custcrit.add(CustomerPeer.CUSTOMER_ID, 999, Criteria.GREATER_THAN);
            custcrit.addAscendingOrderByColumn(CustomerPeer.CUSTOMER_DISPLAY);
            context.put("customers", CustomerPeer.doSelect(custcrit));

            Criteria prodcrit = new Criteria();
            prodcrit.add(ProductPeer.PRODUCT_ID, 999, Criteria.GREATER_THAN);
            prodcrit.addAscendingOrderByColumn(ProductPeer.PRODUCT_DISPLAY);
            context.put("products", ProductPeer.doSelect(prodcrit));

            Criteria projcrit = new Criteria();
            projcrit.add(ProjectPeer.PROJECT_ID, 999, Criteria.GREATER_THAN);
            projcrit.addAscendingOrderByColumn(ProjectPeer.PROJECT_NAME);
            context.put("projects", ProjectPeer.doSelect(projcrit));


            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}