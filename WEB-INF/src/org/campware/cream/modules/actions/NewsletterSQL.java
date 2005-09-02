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
import java.util.List;
import java.util.Iterator;
import org.apache.velocity.context.Context;

import org.apache.turbine.util.RunData;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;
import java.sql.Connection;

import org.campware.cream.om.Newsletter;
import org.campware.cream.om.NewsletterPeer;
import org.apache.turbine.util.velocity.VelocityHtmlEmail;
import org.apache.turbine.Turbine;

import org.campware.cream.om.Customer;
import org.campware.cream.om.CustomerPeer;
import org.campware.cream.om.NewsSubscription;
import org.campware.cream.om.NewsSubscriptionPeer;

/**
 * This class provides a simple set of methods to
 * insert/update/delete records in a database.
 */
public class NewsletterSQL extends CreamAction
{
    protected void initScreen()
    {
        setModuleType(DOCUMENT);
        setModuleName("NEWSLETTER");
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
        Newsletter entry = new Newsletter();
        data.getParameters().setProperties(entry);

        String myCode=data.getParameters().getString("newslettercode");
		int myStatus= data.getParameters().getInt("status");
		
		boolean bSave=true;

		if (myStatus==30){
			bSave= sendNewsletters(data, context, entry);
			if (bSave) entry.setStatus(50);
		}

		if (bSave){

	        entry.setNewsletterCode(getTempCode());
	
	        entry.setIssuedDate(parseDate(data.getParameters().getString("issueddate")));
	        entry.setClosedDate(parseDate(data.getParameters().getString("closeddate")));
	        entry.setCreatedBy(data.getUser().getName());
	        entry.setCreated(new Date());
	        entry.setModifiedBy(data.getUser().getName());
	        entry.setModified(new Date());
	        
	        Connection conn = Transaction.begin(NewsletterPeer.DATABASE_NAME);
	        boolean success = false;
	        try {
	            entry.save(conn);
	            entry.setNewsletterCode(getRowCode("NL", entry.getNewsletterId()));
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
        Newsletter entry = new Newsletter();
        data.getParameters().setProperties(entry);

		int myStatus= data.getParameters().getInt("status");
		
		boolean bSave=true;

		if (myStatus==30){
			bSave= sendNewsletters(data, context, entry);
			if (bSave) entry.setStatus(50);
		}

		if (bSave){
	        entry.setIssuedDate(parseDate(data.getParameters().getString("issueddate")));
	        entry.setClosedDate(parseDate(data.getParameters().getString("closeddate")));
	        entry.setCreated(parseDateTime(data.getParameters().getString("created")));
	        entry.setModifiedBy(data.getUser().getName());
	        entry.setModified(new Date());
	
	        entry.setModified(true);
	        entry.setNew(false);
	        entry.save();
		}
    }

	/**
	 * Send newsletters to recipients
	 */
	private boolean sendNewsletters(RunData data, Context context, Newsletter newsletterEntry)
	throws Exception
	{

		int relDocument= newsletterEntry.getRelDocument();
		String mailSmtpFrom= Turbine.getConfiguration().getString("mail.smtp.from");
		String mailSmtpFromName= Turbine.getConfiguration().getString("mail.smtp.from.name");
		boolean bHasBadEmails= false;
		
		if (relDocument==20){
			int customerCatId= newsletterEntry.getCustomerCatId();
			int customerType= newsletterEntry.getCustomerType();
			int custLanguageId= newsletterEntry.getCustLanguageId();
			int custCountryId= newsletterEntry.getCustCountryId();
			
			Criteria criteria = new Criteria();
			
			if (customerCatId>999){
				criteria.add(CustomerPeer.CUSTOMER_CAT_ID, new Integer(customerCatId), Criteria.EQUAL);
			}
			if (customerType>1){
				criteria.add(CustomerPeer.CUSTOMER_TYPE, new Integer(customerType), Criteria.EQUAL);
			}
			if (custLanguageId>999){
				criteria.add(CustomerPeer.LANGUAGE_ID, new Integer(custLanguageId), Criteria.EQUAL);
			}
			if (custCountryId>999){
				criteria.add(CustomerPeer.COUNTRY_ID, new Integer(custCountryId), Criteria.EQUAL);
			}
			criteria.add(CustomerPeer.SEND_NEWS, new Integer(20), Criteria.EQUAL);
			criteria.add(CustomerPeer.EMAIL, (Object)"EMAIL is NOT NULL", Criteria.CUSTOM);		
			
	
	
			List receivers = CustomerPeer.doSelect(criteria);
			Iterator i = receivers.iterator();
			VelocityTool velTool= new VelocityTool(context);
	
			while (i.hasNext())
			{
			  Customer cust = (Customer) i.next();
	
			  String sEmailAddress=cust.getEmail(); 
	
			  if (sEmailAddress.length()>1){
				context.put("customerid", cust.getCustomerCode()); 
				context.put("name", cust.getCustomerName1()); 
				context.put("display", cust.getCustomerDisplay()); 
				context.put("dear", cust.getDear()); 
				context.put("email", cust.getEmail()); 
				context.put("custom1", cust.getCustom1()); 
				context.put("custom2", cust.getCustom2()); 
				context.put("custom3", cust.getCustom3()); 
				context.put("custom4", cust.getCustom4()); 
				context.put("custom5", cust.getCustom5()); 
				context.put("custom6", cust.getCustom6()); 
				context.put("emailbody", velTool.evaluate(newsletterEntry.getBody())); 
				try{
					VelocityHtmlEmail ve = new VelocityHtmlEmail(data); 
					ve.setCharset("UTF-8"); 
					ve.addTo( sEmailAddress, "");
					ve.setFrom(mailSmtpFrom, mailSmtpFromName); 
					ve.setSubject(newsletterEntry.getSubject()); 
					ve.setHtmlTemplate("screens/SendEmail.vm");
					ve.send(); 
				}catch(Exception e){
					if (!bHasBadEmails){
						bHasBadEmails=true;
						newsletterEntry.setNotes("Not sent to these addresses:");
						
					}
					newsletterEntry.setNotes(newsletterEntry.getNotes() + "\n" + sEmailAddress);
				}
			  }
			}
		} else if (relDocument==10){
			
			int relDocStatus= newsletterEntry.getRelDocStatus();
			int relProductId= newsletterEntry.getRelProductId();
			int relProjectId= newsletterEntry.getRelProjectId();
			
			Criteria criteria = new Criteria();
			
			if (relDocStatus>1){
				criteria.add(NewsSubscriptionPeer.STATUS, new Integer(relDocStatus), Criteria.EQUAL);
			}
			if (relProductId>999){
				criteria.add(NewsSubscriptionPeer.PRODUCT_ID, new Integer(relProductId), Criteria.EQUAL);
			}
			if (relProjectId>999){
				criteria.add(NewsSubscriptionPeer.PROJECT_ID, new Integer(relProjectId), Criteria.EQUAL);
			}
			criteria.add(NewsSubscriptionPeer.EMAIL, (Object)"EMAIL is NOT NULL", Criteria.CUSTOM);		
	
			List receivers = NewsSubscriptionPeer.doSelect(criteria);
			Iterator i = receivers.iterator();
			VelocityTool velTool= new VelocityTool(context);
	
			while (i.hasNext())
			{
				NewsSubscription cust = (NewsSubscription) i.next();
				String sEmailAddress=cust.getEmail(); 
	
				if (sEmailAddress.length()>1){
//					context.put("product", cust.getProduct().getProductDescription()); 
//					context.put("project", cust.getProject().getProjectName()); 
					context.put("subscriptionid", cust.getNewsSubsCode()); 
					context.put("email", cust.getEmail()); 
					context.put("emailbody", velTool.evaluate(newsletterEntry.getBody())); 
					try{
						VelocityHtmlEmail ve = new VelocityHtmlEmail(data); 
						ve.setCharset("UTF-8"); 
						ve.addTo( sEmailAddress, "");
						ve.setFrom(mailSmtpFrom, mailSmtpFromName); 
						ve.setSubject(newsletterEntry.getSubject()); 
						ve.setHtmlTemplate("screens/SendEmail.vm");
						ve.send(); 
					}catch(Exception e){
						if (!bHasBadEmails){
							bHasBadEmails=true;
							newsletterEntry.setNotes("Not sent to these addresses:");
							
						}
						newsletterEntry.setNotes(newsletterEntry.getNotes() + "\n" + sEmailAddress);
					}
				}
			}
		} else{
			return false;
		}
		
		return true; 
	}

    /**
     * Delete a record from the database using
     * the unique id gleaned from the web form.
     */
    public void doDelete(RunData data, Context context)
        throws Exception
    {
        Criteria criteria = new Criteria();
        criteria.add(NewsletterPeer.NEWSLETTER_ID, data.getParameters().getInt("newsletterid"));
        NewsletterPeer.doDelete(criteria);
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
        criteria.addIn(NewsletterPeer.NEWSLETTER_ID, delIds);
        NewsletterPeer.doDelete(criteria);
    }

}
