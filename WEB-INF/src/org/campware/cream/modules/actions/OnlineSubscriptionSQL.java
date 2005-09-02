package org.campware.cream.modules.actions;

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

import java.util.Date;
import org.apache.velocity.context.Context;

import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;
import java.sql.Connection;

import org.campware.cream.om.OnlineSubscription;
import org.campware.cream.om.OnlineSubscriptionPeer;
import org.apache.turbine.util.velocity.VelocityHtmlEmail;
import org.apache.turbine.Turbine;

import org.campware.cream.om.Notification;
import org.campware.cream.om.NotificationPeer;

/**
 * This class provides a simple set of methods to
 * insert/update/delete records in a database.
 */
public class OnlineSubscriptionSQL extends CreamAction
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("ONLINE_SUBSCRIPTION");
    }

    /**
     * This simply takes an entry from the web form and
     * inserts it directly into the database.
     *
     * This would not be good in practice as the
     * data should be verified before being allowed
     * into the database. This is merely an
     * example of how to use peers, this certainly
     * wouldn't be secure.
     */
    public void doInsert(RunData data, Context context)
        throws Exception
    {
        OnlineSubscription entry = new OnlineSubscription();
        data.getParameters().setProperties(entry);
		int myStatus= data.getParameters().getInt("status");
		int notify= data.getParameters().getInt("notify", 10);

		entry.setIssuedDate(parseDate(data.getParameters().getString("issueddate")));
		entry.setClosedDate(parseDate(data.getParameters().getString("closeddate")));
		entry.setStartDate(parseDate(data.getParameters().getString("startdate")));
		entry.setEndDate(parseDate(data.getParameters().getString("enddate")));
		entry.setCreatedBy(data.getUser().getName());
		entry.setCreated(new Date());
		entry.setModifiedBy(data.getUser().getName());
		entry.setModified(new Date());

		
		boolean bSave=true;

		if (myStatus!=10 && notify==20){
			bSave= sendEmail(data, context, entry);
		}

		if (bSave){
	        entry.setOnlineSubsCode(getTempCode());
	
	        
	        Connection conn = Transaction.begin(OnlineSubscriptionPeer.DATABASE_NAME);
	        boolean success = false;
	        try {
	            entry.save(conn);
	            entry.setOnlineSubsCode(getRowCode("OS", entry.getOnlineSubsId()));
	            entry.save(conn);
	            Transaction.commit(conn);
	            success = true;
	
	        } finally {
	            if (!success) Transaction.safeRollback(conn);
	        }
		}
    }

    /**
     * Update a record in the database with the
     * information present in the web form.
     *
     * Again, this is merely an example. The data
     * should be checked before being allowed
     * into the database.
     */
    public void doUpdate(RunData data, Context context)
        throws Exception
    {
        OnlineSubscription entry = new OnlineSubscription();
        data.getParameters().setProperties(entry);

		int myStatus= data.getParameters().getInt("status");
		int notify= data.getParameters().getInt("notify", 10);
		
		entry.setIssuedDate(parseDate(data.getParameters().getString("issueddate")));
		entry.setClosedDate(parseDate(data.getParameters().getString("closeddate")));
		entry.setStartDate(parseDate(data.getParameters().getString("startdate")));
		entry.setEndDate(parseDate(data.getParameters().getString("enddate")));
		entry.setCreated(parseDateTime(data.getParameters().getString("created")));
		entry.setModifiedBy(data.getUser().getName());
		entry.setModified(new Date());

		boolean bSave=true;

		if (myStatus!=10 && notify==20){
			bSave= sendEmail(data, context, entry);
		}

		if (bSave){
	        entry.setModified(true);
	        entry.setNew(false);
	        entry.save();
		}
    }

	/**
	 * Send email to customer
	 */
	private boolean sendEmail(RunData data, Context context, OnlineSubscription emailEntry)
	throws Exception
	{
		int notifid;
		int status= emailEntry.getStatus();
		
		if (status==30){
			notifid=1001;
		}else{
			notifid=1003;
		}

		Criteria criteria = new Criteria();
		criteria.add(NotificationPeer.NOTIFICATION_ID, new Integer(notifid), Criteria.EQUAL);
		Notification myNotif = (Notification) NotificationPeer.doSelect(criteria).get(0);
		VelocityTool velTool= new VelocityTool(context);

		  String sEmailAddress=emailEntry.getCustomerRelatedByCustomerId().getEmail(); 

		  if (sEmailAddress.length()>1){
			VelocityHtmlEmail ve = new VelocityHtmlEmail(data); 
			ve.setCharset("UTF-8"); 
			ve.addTo( sEmailAddress, "");
			ve.setFrom(Turbine.getConfiguration().getString("mail.smtp.from"), Turbine.getConfiguration().getString("mail.smtp.from.name")); 
			ve.setSubject(myNotif.getSubject()); 
			context.put("name", emailEntry.getCustomerRelatedByCustomerId().getCustomerName1()); 
			context.put("display", emailEntry.getCustomerRelatedByCustomerId().getCustomerDisplay()); 
			context.put("dear", emailEntry.getCustomerRelatedByCustomerId().getDear()); 
			context.put("email", emailEntry.getCustomerRelatedByCustomerId().getEmail()); 
			context.put("custom1", emailEntry.getCustomerRelatedByCustomerId().getCustom1()); 
			context.put("custom2", emailEntry.getCustomerRelatedByCustomerId().getCustom2()); 
			context.put("custom3", emailEntry.getCustomerRelatedByCustomerId().getCustom3()); 
			context.put("custom4", emailEntry.getCustomerRelatedByCustomerId().getCustom4()); 
			context.put("custom5", emailEntry.getCustomerRelatedByCustomerId().getCustom5()); 
			context.put("custom6", emailEntry.getCustomerRelatedByCustomerId().getCustom6()); 
			context.put("productdisplay", emailEntry.getProduct().getProductDisplay()); 
			context.put("productdescription", emailEntry.getProduct().getProductDescription()); 
			context.put("startdate", formatDate(emailEntry.getStartDate())); 
			context.put("enddate", formatDate(emailEntry.getEndDate())); 
			
			context.put("emailbody", velTool.evaluate(myNotif.getBody())); 
			ve.setTextTemplate("screens/SendEmail.vm");
			ve.send();
			
			return true; 
			
		  }else{
			data.setMessage("Sorry, this customer has empty email address!");
			data.setScreenTemplate("CreamError.vm");
		  	return false;
		  }
	}



    /**
     * Delete a record from the database using
     * the unique id gleaned from the web form.
     */
    public void doDelete(RunData data, Context context)
        throws Exception
    {
        Criteria criteria = new Criteria();
        criteria.add(OnlineSubscriptionPeer.ONLINE_SUBS_ID, data.getParameters().getInt("onlinesubsid"));
        OnlineSubscriptionPeer.doDelete(criteria);
    }

    /**
     * Delete selected records from the database using
     * the unique ids gleaned from the web form.
     */
    public void doDeleteselected(RunData data, Context context)
        throws Exception
    {
        int[] delIds= data.getParameters().getInts("rowid");
        Criteria criteria = new Criteria();
        criteria.addIn(OnlineSubscriptionPeer.ONLINE_SUBS_ID, delIds);
        OnlineSubscriptionPeer.doDelete(criteria);
    }

}
