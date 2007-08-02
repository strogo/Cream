package org.campware.cream.modules.util;

/* ====================================================================
 * Copyright (C) 2003-2006  Media Development Loan Fund
 *
 * Contact: contact@campware.org - http://www.campware.org
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

import java.sql.Connection;
import java.util.List;
import java.util.Vector;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;
import org.campware.cream.om.ProductPeer;
import org.campware.cream.om.Product;
import org.campware.cream.om.CustomerPeer;
import org.campware.cream.om.Customer;
import org.campware.cream.om.OnlineSubscriptionPeer;
import org.campware.cream.om.OnlineSubscription;
import org.campware.cream.om.OnlineSubscriptionIpPeer;
import org.campware.cream.om.OnlineSubscriptionIp;



/**
 * A Handler for use with the XML-RPC service that will deal
 * with clients reading information from Cream or saving customer
 * data to Cream.
 *
 *
 * @author <a href="mailto:pandzic@volny.cz">Nenad Pandzic</a>
 */
public class XmlRpcHandler
{
    /** Logging */
    private static Log log = LogFactory.getLog(XmlRpcHandler.class);

    /**
     * Default Constructor
     */
    public XmlRpcHandler()
    {
    }

    /**
     * The client has indicated that it would like
     * to receive all online subscription products.
     */
    public Vector getAllOnlineSubsProducts()
    {
        Vector result = new Vector();
        Criteria criteria = new Criteria();

        criteria.add(ProductPeer.PRODUCT_ID, 1000, Criteria.GREATER_THAN);
        criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);

        try
        {
            List records = ProductPeer.doSelect(criteria);
            int rownum= records.size();

            result.add( new Integer(rownum));
            
            for (int i = 0; i < rownum; i++) {
              result.add( ((Product) records.get(i)).getProductCode() );
              result.add( ((Product) records.get(i)).getProductDisplay() );
            }
        }
        catch (Exception e)
        {
        		result = new Vector();
            result.add( new Integer(-1));
        }
        
        return result;
    }

    /**
     * The client has indicated that it would like
     * to receive all modified online subscription products.
     */
    public Vector getModifiedOnlineSubsProducts(Date fromDate)
    {
        Vector result = new Vector();
        Criteria criteria = new Criteria();

        criteria.add(ProductPeer.PRODUCT_ID, 1000, Criteria.GREATER_THAN);
        criteria.add(ProductPeer.MODIFIED, fromDate, Criteria.GREATER_THAN);
        criteria.addAscendingOrderByColumn(ProductPeer.PRODUCT_CODE);

        try
        {
            List records = ProductPeer.doSelect(criteria);
            int rownum= records.size();

            result.add( new Integer(rownum));
            
            for (int i = 0; i < rownum; i++) {
              result.add( ((Product) records.get(i)).getProductCode() );
              result.add( ((Product) records.get(i)).getProductDisplay() );
            }
        }
        catch (Exception e)
        {
        		result = new Vector();
            result.add( new Integer(-1));
        }
        
        return result;
    }

    /**
     * The client has indicated that it would like
     * to receive all online subscriptions.
     */
    public Vector getAllOnlineSubscriptions()
    {
        Vector result = new Vector();
        Criteria criteria = new Criteria();

        criteria.add(OnlineSubscriptionPeer.ONLINE_SUBS_ID, 1000, Criteria.GREATER_THAN);
        criteria.addAscendingOrderByColumn(OnlineSubscriptionPeer.ONLINE_SUBS_CODE);

        try
        {
            List records = OnlineSubscriptionPeer.doSelect(criteria);
            int rownum= records.size();

            result.add( new Integer(rownum));
            
            for (int i = 0; i < rownum; i++) {
              result.add( ((OnlineSubscription) records.get(i)).getOnlineSubsCode() );
              result.add( new Integer(((OnlineSubscription) records.get(i)).getStatus() ));
              result.add( ((OnlineSubscription) records.get(i)).getStartDate() );
              result.add( ((OnlineSubscription) records.get(i)).getEndDate() );
              result.add( ((OnlineSubscription) records.get(i)).getProduct().getProductCode() );
              result.add( ((OnlineSubscription) records.get(i)).getProduct().getProductDisplay() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getCustomerCode() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getCustomerDisplay() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getLoginName() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getPasswordValue() );

              List iprecords = ((OnlineSubscription) records.get(i)).getOnlineSubscriptionIps();
              int iprownum= iprecords.size();

              result.add( new Integer(iprownum));
              for (int k = 0; k < iprownum; k++) {
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp1() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp2() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp3() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp4() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getAddressNo() ));
              }
            }
        }
        catch (Exception e)
        {
        		result = new Vector();
            result.add( new Integer(-1));
        }
        
        return result;
    }


    /**
     * The client has indicated that it would like
     * to receive all modified online subscriptions.
     */
    public Vector getModifiedOnlineSubscriptions(Date fromDate)
    {
        Vector result = new Vector();
        Criteria criteria = new Criteria();

        criteria.add(OnlineSubscriptionPeer.ONLINE_SUBS_ID, 1000, Criteria.GREATER_THAN);
        criteria.add(OnlineSubscriptionPeer.MODIFIED, fromDate, Criteria.GREATER_THAN);
        criteria.addAscendingOrderByColumn(OnlineSubscriptionPeer.ONLINE_SUBS_CODE);

        try
        {
            List records = OnlineSubscriptionPeer.doSelect(criteria);
            int rownum= records.size();

            result.add( new Integer(rownum));
            
            for (int i = 0; i < rownum; i++) {
              result.add( ((OnlineSubscription) records.get(i)).getOnlineSubsCode() );
              result.add( new Integer(((OnlineSubscription) records.get(i)).getStatus() ));
              result.add( ((OnlineSubscription) records.get(i)).getStartDate() );
              result.add( ((OnlineSubscription) records.get(i)).getEndDate() );
              result.add( ((OnlineSubscription) records.get(i)).getProduct().getProductCode() );
              result.add( ((OnlineSubscription) records.get(i)).getProduct().getProductDisplay() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getCustomerCode() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getCustomerDisplay() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getLoginName() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getPasswordValue() );

              List iprecords = ((OnlineSubscription) records.get(i)).getOnlineSubscriptionIps();
              int iprownum= iprecords.size();

              result.add( new Integer(iprownum));
              for (int k = 0; k < iprownum; k++) {
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp1() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp2() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp3() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp4() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getAddressNo() ));
              }
            }
        }
        catch (Exception e)
        {
        		result = new Vector();
            result.add( new Integer(-1));
        }
        
        return result;
    }


    /**
     * The client has indicated that it would like
     * to receive all online subscriprions for a customer.
     */
    public Vector getCustomerOnlineSubscriptions(String login, String passw)
    {
        Vector result = new Vector();
        Criteria criteria = new Criteria();

        criteria.add(OnlineSubscriptionPeer.ONLINE_SUBS_ID, 1000, Criteria.GREATER_THAN);
        criteria.add(CustomerPeer.LOGIN_NAME, (Object)login, Criteria.EQUAL);
        criteria.add(CustomerPeer.PASSWORD_VALUE, (Object)passw, Criteria.EQUAL);
        criteria.addJoin(CustomerPeer.CUSTOMER_ID, OnlineSubscriptionPeer.RECIPIENT_ID);
        criteria.addAscendingOrderByColumn(OnlineSubscriptionPeer.ONLINE_SUBS_CODE);

        try
        {
            List records = OnlineSubscriptionPeer.doSelect(criteria);
            int rownum= records.size();

            result.add( new Integer(rownum));
            
            for (int i = 0; i < rownum; i++) {
              result.add( ((OnlineSubscription) records.get(i)).getOnlineSubsCode() );
              result.add( new Integer(((OnlineSubscription) records.get(i)).getStatus() ));
              result.add( ((OnlineSubscription) records.get(i)).getStartDate() );
              result.add( ((OnlineSubscription) records.get(i)).getEndDate() );
              result.add( ((OnlineSubscription) records.get(i)).getProduct().getProductCode() );
              result.add( ((OnlineSubscription) records.get(i)).getProduct().getProductDisplay() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getCustomerCode() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getCustomerDisplay() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getLoginName() );
              result.add( ((OnlineSubscription) records.get(i)).getCustomerRelatedByRecipientId().getPasswordValue() );

              List iprecords = ((OnlineSubscription) records.get(i)).getOnlineSubscriptionIps();
              int iprownum= iprecords.size();

              result.add( new Integer(iprownum));
              for (int k = 0; k < iprownum; k++) {
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp1() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp2() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp3() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getIp4() ));
                  result.add( new Integer(((OnlineSubscriptionIp) iprecords.get(k)).getAddressNo() ));
              }
            }
        }
        catch (Exception e)
        {
        		result = new Vector();
            result.add( new Integer(-1));
        }
        
        return result;
    }


    /**
     * The client has indicated that it would like
     * to insert new customer.
     */
    public int insertCustomer(Vector params)
    {
        Customer entry = new Customer();
        entry.setCustomerDisplay((String)params.elementAt(0));
        entry.setCustomerName1((String)params.elementAt(0));
        entry.setCustomerName2((String)params.elementAt(1));
        entry.setAddress1((String)params.elementAt(2));
        entry.setAddress2((String)params.elementAt(3));
        entry.setCity((String)params.elementAt(4));
        entry.setZip((String)params.elementAt(5));
        entry.setState((String)params.elementAt(6));

        try{
        		entry.setCountryId(((Integer)params.elementAt(7)).intValue());
        } catch(Exception e){
			return -1;
        }
        entry.setPhone1((String)params.elementAt(8));
        entry.setPhone2((String)params.elementAt(9));
        entry.setFax((String)params.elementAt(10));
        entry.setEmail((String)params.elementAt(11));
        entry.setLoginName((String)params.elementAt(12));
        entry.setPasswordValue((String)params.elementAt(13));

        entry.setCreatedBy("web");
        entry.setCreated(new Date());
        entry.setModifiedBy("web");
        entry.setModified(new Date());
        

        entry.setCustomerCode(getTempCode());

        boolean success = false;

        try{
	        Connection conn = Transaction.begin(CustomerPeer.DATABASE_NAME);

	        try{
	                entry.save(conn);
	                entry.setCustomerCode(getRowCode("CU", entry.getCustomerId()));
	                entry.save(conn);
	                Transaction.commit(conn);
	                success = true;
	                return 1;
	        } finally {
	            if (!success) Transaction.safeRollback(conn);
	        }

        } catch(Exception e){
    			return -1;
        }
    
        
    }

    /**
     * The client has indicated that it would like
     * to update customer data.
     */
    public int updateCustomer(Vector params)
    {

    		try{
	    		Criteria criteria = new Criteria();
	        criteria.add(CustomerPeer.LOGIN_NAME, params.elementAt(12), Criteria.EQUAL);
	        criteria.add(CustomerPeer.PASSWORD_VALUE, params.elementAt(13),Criteria.EQUAL);
	        Customer entry = (Customer) CustomerPeer.doSelect(criteria).get(0);
	
	        entry.setCustomerDisplay((String)params.elementAt(0));
	        entry.setCustomerName1((String)params.elementAt(0));
	        entry.setCustomerName2((String)params.elementAt(1));
	        entry.setAddress1((String)params.elementAt(2));
	        entry.setAddress2((String)params.elementAt(3));
	        entry.setCity((String)params.elementAt(4));
	        entry.setZip((String)params.elementAt(5));
	        entry.setState((String)params.elementAt(6));
	
	        try{
	        		entry.setCountryId(((Integer)params.elementAt(7)).intValue());
	        } catch(Exception e){
				return -1;
	        }
	        entry.setPhone1((String)params.elementAt(8));
	        entry.setPhone2((String)params.elementAt(9));
	        entry.setFax((String)params.elementAt(10));
	        entry.setEmail((String)params.elementAt(11));
	        entry.setLoginName((String)params.elementAt(12));
	        entry.setPasswordValue((String)params.elementAt(13));
	
	        entry.setModifiedBy("web");
	        entry.setModified(new Date());

	        entry.setModified(true);
	        entry.setNew(false);
	        entry.save();

        } catch(Exception e){
    			return -1;
        }
        
        return 1;
    
    }

    private String getTempCode()
    {
        Date currDate= new Date();

        return Integer.toString(currDate.hashCode());
    }

    private String getRowCode(String s, int i)
    {
        String is= new String();
        
        is= Integer.toString(i);
        while (is.length()<7)
        {
            is="0" + is;
        }

        is= s + is;
        return is;
    }
    
}
