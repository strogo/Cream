package org.campware.cream.modules.scheduledjobs;

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

//JDK
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
//Turbine
import org.apache.turbine.modules.ScheduledJob;
import org.apache.turbine.services.schedule.JobEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.velocity.VelocityContext;
import org.apache.turbine.util.mail.HtmlEmail;
import org.apache.turbine.Turbine;
import org.apache.torque.util.Criteria;
import org.campware.cream.om.Notification;
import org.campware.cream.om.NotificationPeer;
import org.campware.cream.om.OnlineSubscription;
import org.campware.cream.om.OnlineSubscriptionPeer;
import org.campware.cream.om.PrintSubscription;
import org.campware.cream.om.PrintSubscriptionPeer;

/**
 * Online Subscription Job.
 *
 * Close old subscriptions and send notifications
 * @author <a href="mailto:pandzic@volny.cz">Nenad Pandzic</a>
 */
public class OnlineSubscriptionJob extends ScheduledJob
{
	/** Logging */
	private static Log log = LogFactory.getLog(OnlineSubscriptionJob.class);

	private int taskcount = 0;

	/**
	 * Constructor
	 */
	 public OnlineSubscriptionJob()
	 {
		 //do Task initialization here
	 }


	/**
	 * Run the Jobentry from the scheduler queue.
	 * From ScheduledJob.
	 *
	 * @param job The job to run.
	 */
	public void run( JobEntry job ) throws Exception
	{
		// First we resolve online subscriptions
		doExpiredOnlineSubs();
		// Then we resolve print subscriptions
		doExpiredPrintSubs();
		// now we inform days in advance 7
		doOnlineSubsToExpire();
	}


	private void doOnlineSubsToExpire() throws Exception{
		Calendar rightNow = Calendar.getInstance();
		rightNow.add(Calendar.DATE, 7);
		Date notiDate= new Date(rightNow.get(Calendar.YEAR),rightNow.get(Calendar.MONTH), rightNow.get(Calendar.DATE));

		Criteria criteria = new Criteria();
		criteria.add(NotificationPeer.NOTIFICATION_ID, new Integer(1002), Criteria.EQUAL);
		Notification myNotif = (Notification) NotificationPeer.doSelect(criteria).get(0);

		VelocityContext context = new VelocityContext(); 
		ShedVelocityTool velTool= new ShedVelocityTool(context);

		Criteria osubcrit = new Criteria();
		osubcrit.add(OnlineSubscriptionPeer.END_DATE, notiDate, Criteria.EQUAL);
		osubcrit.add(OnlineSubscriptionPeer.STATUS, new Integer(30), Criteria.EQUAL);

		List substoclose = OnlineSubscriptionPeer.doSelect(osubcrit);
		Iterator i = substoclose.iterator();

		while (i.hasNext())
		{
		  OnlineSubscription subclose = (OnlineSubscription) i.next();

		  String sEmailAddress=subclose.getCustomerRelatedByCustomerId().getEmail(); 

		  if (sEmailAddress.length()>1){
			HtmlEmail ve = new HtmlEmail(); 
			ve.setCharset("UTF-8"); 
			ve.addTo( sEmailAddress, "");
			ve.setFrom(Turbine.getConfiguration().getString("mail.smtp.from"), Turbine.getConfiguration().getString("mail.smtp.from.name")); 
			ve.setSubject(myNotif.getSubject()); 

			context.put("name", subclose.getCustomerRelatedByCustomerId().getCustomerName1()); 
			context.put("display", subclose.getCustomerRelatedByCustomerId().getCustomerDisplay()); 
			context.put("dear", subclose.getCustomerRelatedByCustomerId().getDear()); 
			context.put("email", subclose.getCustomerRelatedByCustomerId().getEmail()); 
			context.put("custom1", subclose.getCustomerRelatedByCustomerId().getCustom1()); 
			context.put("custom2", subclose.getCustomerRelatedByCustomerId().getCustom2()); 
			context.put("custom3", subclose.getCustomerRelatedByCustomerId().getCustom3()); 
			context.put("custom4", subclose.getCustomerRelatedByCustomerId().getCustom4()); 
			context.put("custom5", subclose.getCustomerRelatedByCustomerId().getCustom5()); 
			context.put("custom6", subclose.getCustomerRelatedByCustomerId().getCustom6()); 
			context.put("productdisplay", subclose.getProduct().getProductDisplay()); 
			context.put("productdescription", subclose.getProduct().getProductDescription()); 
			context.put("startdate", formatDate(subclose.getStartDate())); 
			context.put("enddate", formatDate(subclose.getEndDate())); 
			
			ve.setTextMsg(velTool.evaluate(myNotif.getBody())); 
			ve.send();
		  }
		}
	}

	private void doExpiredOnlineSubs() throws Exception{
		Criteria criteria = new Criteria();
		criteria.add(NotificationPeer.NOTIFICATION_ID, new Integer(1003), Criteria.EQUAL);
		Notification myNotif = (Notification) NotificationPeer.doSelect(criteria).get(0);

		VelocityContext context = new VelocityContext(); 
		ShedVelocityTool velTool= new ShedVelocityTool(context);

		Criteria osubcrit = new Criteria();
		osubcrit.add(OnlineSubscriptionPeer.END_DATE, new Date(), Criteria.LESS_EQUAL);
		osubcrit.add(OnlineSubscriptionPeer.STATUS, new Integer(30), Criteria.EQUAL);

		List substoclose = OnlineSubscriptionPeer.doSelect(osubcrit);
		Iterator i = substoclose.iterator();

		while (i.hasNext())
		{
		  OnlineSubscription subclose = (OnlineSubscription) i.next();

		  subclose.setStatus(50);
		  subclose.setModifiedBy("system");
		  subclose.setModified(new Date());
		  subclose.setModified(true);
		  subclose.setNew(false);
		  subclose.save();

		  String sEmailAddress=subclose.getCustomerRelatedByCustomerId().getEmail(); 

		  if (sEmailAddress.length()>1){
			HtmlEmail ve = new HtmlEmail(); 
			ve.setCharset("UTF-8"); 
			ve.addTo( sEmailAddress, "");
			ve.setFrom(Turbine.getConfiguration().getString("mail.smtp.from"), Turbine.getConfiguration().getString("mail.smtp.from.name")); 
			ve.setSubject(myNotif.getSubject()); 

			context.put("name", subclose.getCustomerRelatedByCustomerId().getCustomerName1()); 
			context.put("display", subclose.getCustomerRelatedByCustomerId().getCustomerDisplay()); 
			context.put("dear", subclose.getCustomerRelatedByCustomerId().getDear()); 
			context.put("email", subclose.getCustomerRelatedByCustomerId().getEmail()); 
			context.put("custom1", subclose.getCustomerRelatedByCustomerId().getCustom1()); 
			context.put("custom2", subclose.getCustomerRelatedByCustomerId().getCustom2()); 
			context.put("custom3", subclose.getCustomerRelatedByCustomerId().getCustom3()); 
			context.put("custom4", subclose.getCustomerRelatedByCustomerId().getCustom4()); 
			context.put("custom5", subclose.getCustomerRelatedByCustomerId().getCustom5()); 
			context.put("custom6", subclose.getCustomerRelatedByCustomerId().getCustom6()); 
			context.put("productdisplay", subclose.getProduct().getProductDisplay()); 
			context.put("productdescription", subclose.getProduct().getProductDescription()); 
			context.put("startdate", formatDate(subclose.getStartDate())); 
			context.put("enddate", formatDate(subclose.getEndDate())); 
			
			ve.setTextMsg(velTool.evaluate(myNotif.getBody())); 
			ve.send();
		  }


		}
	}
	
	private void doExpiredPrintSubs() throws Exception{
		Criteria osubcrit = new Criteria();
		osubcrit.add(PrintSubscriptionPeer.END_DATE, new Date(), Criteria.LESS_EQUAL);
		osubcrit.add(PrintSubscriptionPeer.STATUS, new Integer(30), Criteria.EQUAL);

		List substoclose = PrintSubscriptionPeer.doSelect(osubcrit);
		Iterator i = substoclose.iterator();

		while (i.hasNext())
		{
		  PrintSubscription subclose = (PrintSubscription) i.next();

		  subclose.setStatus(50);
		  subclose.setModifiedBy("system");
		  subclose.setModified(new Date());
		  subclose.setModified(true);
		  subclose.setNew(false);
		  subclose.save();
		}
	}


	private String formatDate(Date d)
	{
		SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy");
		return formatter.format(d);
	}
    
	
}