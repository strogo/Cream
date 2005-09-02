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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.torque.util.BasePeer;
import org.apache.torque.util.Criteria;
import org.apache.velocity.context.Context;
import org.campware.cream.om.CustomerPeer;
import org.campware.cream.om.ProductPeer;
import org.campware.cream.om.ProjectPeer;
import org.campware.cream.om.InboxEventPeer;
import org.campware.cream.om.OutboxEventPeer;
import org.campware.cream.om.NewsSubscriptionPeer;
import org.campware.cream.om.NewsletterPeer;
import org.campware.cream.om.SorderPeer;
import org.campware.cream.om.PaymentPeer;
import org.campware.cream.om.ShipmentPeer;
import org.campware.cream.om.ServicePeer;
import org.campware.cream.om.OnlineSubscriptionPeer;
import org.campware.cream.om.PrintSubscriptionPeer;

import com.workingdogs.village.Record;

/**
 * To read comments for this class, please see
 * the ancestor class
 */
public class Home extends SecureScreen
{

    public void doBuildTemplate(RunData data, Context context)
    {
        try
        {

            // customer count
        	Criteria criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + CustomerPeer.CUSTOMER_ID + ")");
            criteria.add(CustomerPeer.STATUS, new Integer(30), Criteria.EQUAL);
  
            List countrecord = BasePeer.doSelect(criteria);
            String recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("customerno", recordno);

            // product count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + ProductPeer.PRODUCT_ID + ")");
            criteria.add(ProductPeer.STATUS, new Integer(30), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("productno", recordno);

            // project count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + ProjectPeer.PROJECT_ID + ")");
            criteria.add(ProjectPeer.STATUS, new Integer(30), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("projectno", recordno);

            // inbox count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + InboxEventPeer.INBOX_EVENT_ID + ")");
            criteria.add(InboxEventPeer.STATUS, new Integer(30), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("inboxno", recordno);

            // outbox count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + OutboxEventPeer.OUTBOX_EVENT_ID + ")");
            criteria.add(OutboxEventPeer.STATUS, new Integer(10), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("outboxno", recordno);

            // news subscription count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + NewsSubscriptionPeer.NEWS_SUBS_ID + ")");
            criteria.add(NewsSubscriptionPeer.STATUS, new Integer(30), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("newssubsno", recordno);

            // newsletter count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + NewsletterPeer.NEWSLETTER_ID + ")");
            criteria.add(NewsletterPeer.STATUS, new Integer(10), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("newsletterno", recordno);

            // sales order count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + SorderPeer.SORDER_ID + ")");
            criteria.add(SorderPeer.STATUS, new Integer(30), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("sorderno", recordno);

            // payment count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + PaymentPeer.PAYMENT_ID + ")");
            criteria.add(PaymentPeer.STATUS, new Integer(30), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("paymentno", recordno);

            // shipment count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + ShipmentPeer.SHIPMENT_ID + ")");
            criteria.add(ShipmentPeer.STATUS, new Integer(30), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("shipmentno", recordno);

            // service count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + ServicePeer.SERVICE_ID + ")");
            criteria.add(ServicePeer.STATUS, new Integer(30), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("serviceno", recordno);

            // print subscription count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + PrintSubscriptionPeer.PRINT_SUBS_ID + ")");
            criteria.add(PrintSubscriptionPeer.STATUS, new Integer(30), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("printsubsno", recordno);

            // online subscription count
        	criteria = new Criteria();
            criteria.addSelectColumn("COUNT(" + OnlineSubscriptionPeer.ONLINE_SUBS_ID + ")");
            criteria.add(OnlineSubscriptionPeer.STATUS, new Integer(30), Criteria.EQUAL);
    
            countrecord = BasePeer.doSelect(criteria);
            recordno= ((Record) countrecord.get(0)).getValue(1).asString();
            context.put ("onlinesubsno", recordno);
        }
        catch (Exception e)
	    {

	    }

    }


}
